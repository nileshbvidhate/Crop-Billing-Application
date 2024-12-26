package org.nilesh.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static DBConnection instance = null;
	protected Connection connection = null;
	

	protected DBConnection() {
		try {
			// Load properties file
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream("src/main/resources/DBConfig.properties");
			props.load(fis);

			// Read properties
			String driver = props.getProperty("driver");
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			// Load driver class
			Class.forName(driver);

			// Establish connection
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			throw new RuntimeException("Failed to connect to the database", e);
		}
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}

		return instance;
	}

	public Connection getConnection() {
		return connection;
	}

	
	
}
