package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static final String DRIVER = "com.mysql.jdbc.Driver";

//	5020
	private static final String URL = "jdbc:mysql://localhost:3306/golddatabase";
	private static final String USER = "retetalenti";
	private static final String PASSWORD = "rtacli5020";
	
//	D80
//	private static final String URL = "jdbc:mysql://localhost:3306/golddatabase";
//	private static final String USER = "root";
//	private static final String PASSWORD = "mysql";

	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER, PASSWORD);
                
	}
}
