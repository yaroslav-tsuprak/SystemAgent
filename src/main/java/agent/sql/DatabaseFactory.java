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
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * This class manages the database connections.
 */
public final class DatabaseFactory
{
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseFactory.class);
	
	private static DatabaseFactory INSTANCE;
	
	private ComboPooledDataSource _dataSource;
	
	private DatabaseFactory()
	{
		// visibility
	}
	
	public void init(DataSourceInitializer config) throws Exception
	{
		_dataSource = config.initDataSource();
		
		/* Test the connection. */
		_dataSource.getConnection().close();
		
		LOGGER.info("Initialized DB '{}' as user '{}' using '{}' driver.", config.getJdbcURL(), config.getUsername(), config.getDriver());
	}
	
	private void closeSource()
	{
		if (_dataSource != null)
		{
			_dataSource.close();
		}
		
		// Is this really necessary?
		_dataSource = null;
	}
	
	/**
	 * Shutdown.
	 */
	public static void shutdown()
	{
		if (INSTANCE == null)
		{
			return;
		}
		
		LOGGER.info("Shutting down.");
		
		try
		{
			DatabaseFactory.getInstance().closeSource();
		}
		catch (Exception e)
		{
			LOGGER.warn("", e);
		}
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
	 * Gets implementation of {@link DataSource}.
	 * @return data source
	 */
	public DataSource getDataSource()
	{
		return _dataSource;
	}
	
	/**
	 * Gets the connection.
	 * @return the connection
	 */
	public Connection getConnection()
	{
		try
		{
			return _dataSource.getConnection();
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
