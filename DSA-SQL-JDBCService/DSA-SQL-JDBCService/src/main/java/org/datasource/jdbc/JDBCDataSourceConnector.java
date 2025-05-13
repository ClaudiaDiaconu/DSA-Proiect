package org.datasource.jdbc;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class JDBCDataSourceConnector {
	private static Logger logger = Logger.getLogger(JDBCDataSourceConnector.class.getName());

	private static final String URL = "jdbc:postgresql://localhost:5433/real_estate_pg";
	private static final String USER = "postgres";
	private static final String PASSWORD = "Claudia*100";

	public Connection getConnection() {
		try {
			logger.info(">>> Connecting to PostgreSQL...");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			logger.severe(">>> PostgreSQL connection failed: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
