/*
 * Copyright (C) 2004-2018 L2J Unity
 *
 * This file is part of L2J Unity.
 *
 * L2J Unity is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2J Unity is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package agent.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import agent.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class manages the database connections.
 */
public final class DatabaseFactory
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseFactory.class);
	
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
