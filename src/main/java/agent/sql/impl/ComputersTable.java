package agent.sql.impl;

import java.sql.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import agent.enums.Parameters;
import agent.model.ComputerParameters;
import agent.utils.ParamsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import agent.sql.DatabaseFactory;

public class ComputersTable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputersTable.class);

	// SQL queries
	private static final String INSERT_COMPUTER = "INSERT INTO computers (computer_hash_id) values (?)";
	private static final String INSERT_COMPUTER_PARAMS = "INSERT INTO computer_params (" +
			"computer_hash_id,os_full_name,bios_description,bios_manufacturer,bios_name,bios_release_date,bios_version,cpu_id,cpu_identifier,cpu_name,cpu_vendor," +
			"logical_cpu_count,physical_cpu_count,motherboard_manufacturer,motherboard_model,motherboard_serial,motherboard_version,disk_model," +
			"disk_name,disk_serial,disk_size,usb_name,usb_unique_device_id,usb_vendor,graphics_card_name,graphics_card_vendor,graphics_card_vram," +
			"memory_bank_label,memory_capacity,memory_manufacturer,memory_total,memory_type,adapters_names,ip_address,mac_address,netbios_full_name) " +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_COMPUTER_PARAMETERS = "UPDATE computer_params SET ### WHERE computer_hash_id=?";
	private static final String SELECT_COMPUTER = "SELECT * FROM computers WHERE computer_hash_id=?";
	private static final String SELECT_COMPUTER_PARAMS = "SELECT * FROM computer_params WHERE computer_hash_id=?";
	private static final String UPDATE_COMPUTER_LAST_ACTIVE = "UPDATE computers SET last_active=NOW() WHERE computer_hash_id=?";
	private String sqlQueryes = "";

	/**
	 * Update last active of agent in the computer_params table of the database.
	 * @param computerHashId the computer which last active to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean saveLastActive(int computerHashId) {
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement statement = con.prepareStatement(UPDATE_COMPUTER_LAST_ACTIVE))
		{
			statement.setInt(1, computerHashId);
			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not insert computer last active data: " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Create a new computer in the computer table of the database.
	 * @param computer_hash_id the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean createComputer(int computer_hash_id)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(INSERT_COMPUTER))
		{
			statement.setInt(1, computer_hash_id);
			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not insert computer data: " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * Create a new computer in the computer table of the database.
	 * @param computerParameters the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean createComputerParameters(ParamsSet computerParameters)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement st = con.prepareStatement(INSERT_COMPUTER_PARAMS))
		{
			Parameters.VALUES.forEach(params -> setStatement(st, params.getFieldId(), params.getFieldName(), computerParameters));
			return st.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not insert computer prameters data: " + e.getMessage(), e);
			return false;
		}
	}

	public void setStatement(PreparedStatement st, int fieldId, String fieldName, ParamsSet computerParameters) {
		try {
			if (computerParameters.getMap().get(fieldName) instanceof String)
			{
				st.setString(fieldId, computerParameters.getString(fieldName));
			}
			else if (computerParameters.getMap().get(fieldName) instanceof Integer)
			{
				st.setInt(fieldId, computerParameters.getInt(fieldName));
			}
			else if (computerParameters.getMap().get(fieldName) instanceof Long)
			{
				st.setLong(fieldId, computerParameters.getLong(fieldName));
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	public void setUpdatableStatement(PreparedStatement statement, int stNumber, Object computerDiffParam) {
		try {
			if (computerDiffParam instanceof String)
			{
				statement.setString(stNumber, (String) computerDiffParam);
			}
			else if (computerDiffParam instanceof Integer)
			{
				statement.setInt(stNumber, (int) computerDiffParam);
			}
			else if (computerDiffParam instanceof Long)
			{
				statement.setLong(stNumber, (Long) computerDiffParam);
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Updates identified changes the computer parameters data in the database.
	 * @param computerDiff the changes which to save.
	 */
	public boolean saveComputerDiff(int computerHashId, ParamsSet computerDiff)
	{
		computerDiff.getMap().forEach((k, v) -> sqlQueryes = sqlQueryes + k.toString() + "=?" + ",");
		sqlQueryes = UPDATE_COMPUTER_PARAMETERS.replace("###", removeLastCharacter(sqlQueryes));
		try (Connection conn = DatabaseFactory.getInstance().getConnection();
			  PreparedStatement update = conn.prepareStatement(sqlQueryes))
		{
			AtomicInteger i = new AtomicInteger(1);
			computerDiff.getMap().forEach((k, v) -> setUpdatableStatement(update, i.getAndIncrement(), v));
			update.setInt(i.get(), computerHashId);
			return update.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not update computer prameter data: " + computerDiff.toString() + " ::: " + e.getMessage(), e);
			return false;
		}
	}

	public static String removeLastCharacter(String str) {
		String result = Optional.ofNullable(str)
				.filter(sStr -> sStr.length() != 0)
				.map(sStr -> sStr.substring(0, sStr.length() - 1))
				.orElse(str);
		return result;
	}
	
	/**
	 * Search the computer for the given {@code computerHashId} from the database.
	 * @param computerHashId the computer hashid whose data to restore.
	 * @return true or false.
	 */
	public boolean selectComputer(int computerHashId)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER))
		{
			ps.setInt(1, computerHashId);
			
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					return true;
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.warn("Error occurred while loading computer data for computerHashId: {}", computerHashId, e);
		}
		return false;
	}

	/**
	 * Get the parameters of computer for the given {@code computerHashId} from the database.
	 * @param computerHashId the computer hashid whose data to restore.
	 * @return true or false.
	 */
	public ComputerParameters selectComputerParameters(int computerHashId)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER_PARAMS))
		{
			ps.setInt(1, computerHashId);
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next()) {
					return new ComputerParameters(rs);
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.warn("Error occurred while loading computer data for computerHashId: {}", computerHashId, e);
		}
		return null;
	}

	public void createComputerWithParameters (int computerHashId, ParamsSet computerParameters)
	{
		if (createComputer(computerHashId))
		{
			createComputerParameters(computerParameters);
		}
	}

	public static ComputersTable getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final ComputersTable INSTANCE = new ComputersTable();
	}
}
