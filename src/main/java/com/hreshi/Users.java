package com.hreshi;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Users {
	private static String signInQuery = "select pass from cred where name=\"%s\";";
	private static String signUpQuery = "insert into cred (name, pass) values (\"%s\", \"%s\");";
	Connection con = null;
	Users () {
		try {
			con = new Database().getConnection();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	boolean signInService(String name, String pass) {
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(String.format(signInQuery, name));
			boolean correctCred = result.next() == true && result.getString("pass").equals(pass);
			con.close();
			return correctCred; 	
		} catch (Exception e) {
			System.out.println(e);
			return false;
		} 
	}

	boolean signUpService(String name, String pass) {
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(String.format(signInQuery, name));
			if (!result.next()) {
				stmt.executeUpdate(String.format(signUpQuery, name, pass));
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
}