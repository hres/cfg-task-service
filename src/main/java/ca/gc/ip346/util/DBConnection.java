package ca.gc.ip346.util;

import java.io.*;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBConnection {
	private static final Logger logger = LogManager.getLogger(DBConnection.class);

	public static Connection getConnection() throws SQLException, Exception, IOException {

		Connection conn  = null;
		Properties props = new Properties();
		InputStream in   = DBConnection.class.getClassLoader().getResourceAsStream("ca/gc/ip346/util/db.properties");
		props.load(in);
		in.close();

		String driver = props.getProperty("jdbc.driver");
		logger.debug("[01;03;34m" + "postgresql driver name test: " + driver + "[00;00m");
		if (driver == null) {
			throw new Exception("jdbc.driver is not defined");
		}

		String url      = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		Class.forName("org.postgresql.Driver");

		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}
}
