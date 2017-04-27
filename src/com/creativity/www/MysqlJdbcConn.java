package com.creativity.www;

import java.sql.DriverManager;
import java.sql.*;

import com.mysql.jdbc.Connection;

public class MysqlJdbcConn {
	private static MysqlJdbcConn instance = new MysqlJdbcConn();
	public static final String URL = "jdbc:mysql://localhost:3306/test";
	public static final String USER = "madhan";
	public static final String PASSWORD = "madhan";
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
	
	// constructor
	public MysqlJdbcConn() {
		try {
			//Step 2: Load MySQL Java driver
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection createConnection() {

		Connection connection = null;
		try {
			//Step 3: Establish Java MySQL connection
			connection = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("ERROR: Unable to Connect to Database.");
		}
		return connection;
	}	
	
	public static Connection getConnection() {
		return instance.createConnection();
	}
}
