import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;



public class Test {
	
	private static String database = "jdbc:mysql://localhost:3306/users";
	private static String username = "hreshi";
	private static String password = "hreshi";
	private static String signInQuery = "select pass from cred where name=\"%s\";";
	private static String signUpQuery = "insert into cred (name, pass) values (\"%s\", \"%s\")";

	private static Connection getConnection() {
		try {
			return DriverManager.getConnection(database, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	static boolean signInService(String name, String pass) {
		boolean correct = false;
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(String.format(signInQuery, name));
			correct = result.first() == true && result.getString("pass") == pass;
		} catch (Exception e) {
			correct = false;
		} 
		return correct;
	}

	static boolean signUpService(String name, String pass) {
		boolean correct = true;
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(String.format(signUpQuery, name));
		} catch (Exception e) {
			correct = false;
		}
		return correct;
	}

	public static void main(String[] args) {
		String name = "hreshi";
		String signInQuery = "select pass from cred where name=\"%s\";";
		String signUpQuery = "insert into cred (name, pass) values (\"%s\", \"%s\");";
		System.out.println(String.format(signUpQuery, name, "hreshi"));
		System.out.println(String.format(signInQuery, name));
	}

}