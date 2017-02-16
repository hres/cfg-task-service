package ca.gc.ip346.classification.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHandler {
	String driver           = "oracle.jdbc.driver.OracleDriver";
	String connString       = "jdbc:oracle:thin:@orasade02:1521:sdv9007";
	private Connection conn = null;

	public DataHandler() {
	}

	public void getDBConnection() {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e) {
			System.out.println("[01;34mWhere is your Oracle JDBC driver?  [00;00m");
			e.printStackTrace();
			return;
		}

		try {
			conn = DriverManager.getConnection(connString, "cnf_nss_dm_read", "read_dev");
		} catch(SQLException e) {
			System.out.println("[01;31mConnection failed!  [00;00m");
			e.printStackTrace();
			return;
		}
	}

	public Connection getConn() {
		return conn;
	}
}
