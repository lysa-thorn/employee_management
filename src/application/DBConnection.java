package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection cnn;
	
	public static Connection initDB() {
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee_management",
					"root",
					""
			);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return cnn;
		
	}
	

}
