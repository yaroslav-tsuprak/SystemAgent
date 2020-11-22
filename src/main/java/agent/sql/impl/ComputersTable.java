package agent.sql.impl;

import java.sql.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import agent.enums.Parameters;
import agent.model.ComputerParameters;
import agent.utils.ParamsSet;

import agent.sql.DatabaseFactory;

public class ComputersTable {

	// SQL queries
	private static final String INSERT_COMPUTER = "INSERT INTO computers (computer_hash_id) values (?)";
	private static final String INSERT_EVENT = "INSERT INTO events (computer_hash_id,message_id,field_name,old_value,new_value) values (?,?,?,?,?)";
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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

	public void setStatement(PreparedStatement statement, int stNumber, Object computerDiffParam) {
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

	public void saveComputerDiffsAndEvents(int computerHashId, ParamsSet computerFromDatabase, ParamsSet computerDiff)
	{
		saveComputerDiff(computerHashId, computerDiff);
		computerDiff.getMap().forEach((k, v) -> saveEvent(computerHashId, k.toString(), computerFromDatabase.getString(k.toString()), v.toString()));
	}

	/**
	 * Updates identified changes the computer parameters data in the database.
	 * @param computerHashId the unique computer's id.
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
			computerDiff.getMap().forEach((k, v) -> setStatement(update, i.getAndIncrement(), v));
			update.setInt(i.get(), computerHashId);
			return update.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
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
	 * Save history about changes the computer parameters in the database.
	 * @param computerHashId the unique computer's id to save.
	 * @param fieldName the name of changed field to save.
	 * @param oldValue old value to save.
	 * @param newValue new value to save.
	 */
	public boolean saveEvent(int computerHashId, String fieldName, String oldValue, String newValue)
	{
		try (Connection conn = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement event = conn.prepareStatement(INSERT_EVENT)) {
			event.setInt(1, computerHashId);
			event.setInt(2, Parameters.findFieldIdByName(fieldName));
			event.setString(3, fieldName);
			event.setString(4, oldValue);
			event.setString(5, newValue);
			return event.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
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
			e.printStackTrace();
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
			e.printStackTrace();
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
