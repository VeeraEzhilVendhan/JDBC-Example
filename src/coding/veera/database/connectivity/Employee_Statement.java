package coding.veera.database.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee_Statement {
	
	public static void main(String[] args) throws SQLException {
		
		//Class.forName();
		String URL = "jdbc:mysql://localhost:3306/testing_database";
		String username="root";
		String password="sqladmin";

		Connection connection = DriverManager.getConnection(URL, username, password);
	    
		UpdateTable(connection, "UPDATE EMPLOYEE SET SALARY=50000");
		
		deleteRecord(connection, "DELETE FROM EMPLOYEE WHERE ID=6");
		
		queryAllRecords(connection);
		
		connection.close();
	}

	private static void queryAllRecords(Connection connection) throws SQLException {
		
		Statement stmt = connection.createStatement();
		
		ResultSet result = stmt.executeQuery("SELECT * FROM EMPLOYEE");
		
		System.out.println("Id-Name-Age-Salary");
		
		while(result.next()) {
			
			System.out.println(result.getInt(1)+"-"+result.getString(2)
					+"-"+result.getInt(3)+"-"+result.getDouble(4));
			
		}
	}
	
	private static void UpdateTable(Connection connection, String sql) throws SQLException {
		
		Statement stmt = connection.createStatement();
		
		int affCount = stmt.executeUpdate(sql);
		
		System.out.println("Update sucessful, rows affected "+affCount);
		
	}
	
	private static void deleteRecord(Connection connection, String sql) throws SQLException{
		
		Statement stmt = connection.createStatement();
		
		int affCount = stmt.executeUpdate(sql);
		
		System.out.println("deleted sucessfully, no of record deleted "+affCount);
	}

}



