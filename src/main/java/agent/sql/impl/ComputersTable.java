package agent.sql.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	 * @param computer the computer which to save.
	 * @return {@code true} if changes to database were made, {@code false} otherwise.
	 */
	public boolean store(Computer computer)
	{
		
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(UPDATE_COMPUTER))
		{
			statement.setInt(1, level);
			statement.setInt(2, computer.getMax());

			return statement.executeUpdate() >= 1;
		}
		catch (Exception e)
		{
			LOGGER.warn("Could not store comp base data for computer: {}", computer, e);
			return false;
		}
	}
	
	/**
	 * Restores the character data for the given {@code charId} from the database.
	 * @param charId the character id whose data to restore.
	 * @return a new entry of the character's data or {@code null} if there are no entries in the database.
	 */
	public ComputerEntry restore(int computerHashId)
	{
		try (Connection con = DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_COMPUTER))
		{
			ps.setInt(1, computerHashId);
			
			try (ResultSet rs = ps.executeQuery())
			{
				if (rs.next())
				{
					return new ComputerEntry(rs);
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.warn("Error occurred while loading computer data for computerHashId: {}", computerHashId, e);
		}
		
		return null;
	}
	
	/**
	 * A class that holds all variables within the {@code characters} database table.
	 */
	public static class ComputerEntry
	{
		private final String _a;
		private final int _computerHashId;
		
		private ComputerEntry(ResultSet rset) throws SQLException
		{
			_a = rset.getString("a");
			_computerHashId = rset.getInt("_computerHashId");
		}
		
		public String getA()
		{
			return _a;
		}
		
		public int getcomputerHashId()
		{
			return _computerHashId;
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
