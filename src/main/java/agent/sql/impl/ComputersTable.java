package agent.sql.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agent.enums.Parameters;
import agent.model.ComputerParameters;
import agent.utils.ParamsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import agent.model.Computer;
import agent.sql.DatabaseFactory;

public class ComputersTable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputersTable.class);
	
	// SQL queries
	private static final String INSERT_COMPUTER = "INSERT INTO computers (computer_hash_id) values (?)";
	private static final String INSERT_COMPUTER_PARAMS = "INSERT INTO computer_params (" +
			"computer_hash_id,os_full_name,bios_description,bios_manufacturer,bios_name,bios_release_date,bios_version,cpu_id,cpu_identifier,cpu_name,cpu_vendor" +
			"logical_cpu_count,physical_cpu_count,motherboard_manufacturer,motherboard_model,motherboard_serial,motherboard_version,disk_model," +
			"disk_name,disk_serial,disk_size,usb_name,usb_unique_device_id,usb_vendor,graphics_card_name,graphics_card_vendor,graphics_card_vram," +
			"memory_bank_label,memory_capacity,memory_manufacturer,memory_total,memory_type,adapters_names,ip_address,mac_address,netbios_full_name) " +
			"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_COMPUTER = "UPDATE computers SET level=? WHERE computer_hash_id=?";
	private static final String SELECT_COMPUTER = "SELECT * FROM computers WHERE computer_hash_id=?";
	private static final String SELECT_COMPUTER_PARAMS = "SELECT * FROM computer_params WHERE computer_hash_id=?";

	/**
	 * Create a new computer in the computer table of the database.
	 * @param hash_id the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean createComputer(String hash_id)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(INSERT_COMPUTER))
		{
			statement.setString(1, hash_id);
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
			 PreparedStatement statement = con.prepareStatement(INSERT_COMPUTER_PARAMS))
		{
			Parameters.VALUES.forEach(e -> saveToDatabase(statement, e, computerParameters));
			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not insert computer prameters data: " + e.getMessage(), e);
			return false;
		}
	}

	public void saveToDatabase(PreparedStatement statement, Parameters param, ParamsSet computerParameters) {
		try {
			statement.setString(param.getFieldId(), computerParameters.getMap().);
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Stores the computer base data in the database.
	 * @param diffs the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean saveComputerDiff(ParamsSet computerOnline, ParamsSet diffs)
	{
		
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(UPDATE_COMPUTER))
		{
//			statement.setInt(1, level);
//			statement.setInt(2, computer.getMax());

			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.warn("Could not store comp base data for computer: {}", diffs, e);
			return false;
		}
	}
	
	/**
	 * Search the computer for the given {@code computerHashId} from the database.
	 * @param computerHashId the computer hashid whose data to restore.
	 * @return true or false.
	 */
	public boolean selectComputer(String computerHashId)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER))
		{
			ps.setString(1, computerHashId);
			
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
	public ComputerParameters selectComputerParameters(String computerHashId)
	{
		ComputerParameters cp = null;
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			 PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER_PARAMS))
		{
			ps.setString(1, computerHashId);

			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next()) {
					cp.setParamSet(new ComputerParameters(rs).getParamSet());
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.warn("Error occurred while loading computer data for computerHashId: {}", computerHashId, e);
		}
		return cp;
	}

	public void createComputerWithParameters (String computerHashId, ParamsSet computerParameters)
	{
		if (createComputer(computerHashId))
		{
			createComputerParameters(computerParameters);
		}
	}

	public ComputerParameters selectComputerWithParams(String computerHashId)
	{
		ComputerParameters cp = null;
		if (selectComputer(computerHashId))
		{
			cp = selectComputerParameters(computerHashId);
		}
		return cp;
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
