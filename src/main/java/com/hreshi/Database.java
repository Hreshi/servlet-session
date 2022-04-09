package com.hreshi;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Database {
	private static String database = "jdbc:mysql://localhost:3306/users";
	private static String username = "hreshi";
	private static String password = "hreshi";

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(database, username, password);
	}
}