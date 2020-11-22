package agent.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import agent.config.Configuration;

public final class DatabaseFactory
{
	private static DatabaseFactory INSTANCE;
	
	private DatabaseFactory()
	{
		// visibility
	}

	public static DatabaseFactory getInstance()
	{
		if (INSTANCE == null)
		{
			synchronized (DatabaseFactory.class)
			{
				if (INSTANCE == null)
				{
					INSTANCE = new DatabaseFactory();
				}
			}
		}
		
		return INSTANCE;
	}

	/**
	 * Gets the connection.
	 * @return the connection
	 */
	public Connection getConnection()
	{
		try
		{
			Connection con = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
			return con;
		}
		catch (SQLException e)
		{
			throw new LostConnectionException("Couldn't connect to the database!", e);
		}
	}

	private static final class LostConnectionException extends RuntimeException
	{
		private static final long serialVersionUID = -8014108841473905011L;

		LostConnectionException(String message, Throwable cause)
		{
			super(message, cause);
		}
	}
}
