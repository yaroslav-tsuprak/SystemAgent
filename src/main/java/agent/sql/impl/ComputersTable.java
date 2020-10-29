package agent.sql.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import agent.model.ComputerParameters;
import agent.utils.ParamsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import agent.model.Computer;
import agent.sql.DatabaseFactory;

public class ComputersTable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComputersTable.class);
	
	// SQL queries
	private static final String INSERT_COMPUTER = "INSERT INTO computers (computerHashId, createDate) values (?,?)";
	private static final String UPDATE_COMPUTER = "UPDATE computers SET level=? WHERE computerHashId=?";
	private static final String SELECT_COMPUTER = "SELECT * FROM computers WHERE computerHashId=?";

	/**
	 * Create a new computer in the computer table of the database.
	 * @param computer the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean create(Computer computer)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(INSERT_COMPUTER))
		{
			statement.setInt(1, computer.getComputerHashId());
			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.error("Could not insert computer data: " + e.getMessage(), e);
			return false;
		}
	}
	
	/**
	 * Stores the computer base data in the database.
	 * @param diffs the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean store(ParamsSet diffs)
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
	 * Restores the computer data for the given {@code computerHashId} from the database.
	 * @param computerHashId the computer hashid whose data to restore.
	 * @return a new entry of the computer data or {@code null} if there are no entries in the database.
	 */
	public ComputerParameters restore(int computerHashId)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER))
		{
			ps.setInt(1, computerHashId);
			
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
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

	public static ComputersTable getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder
	{
		protected static final ComputersTable INSTANCE = new ComputersTable();
	}
}
