package coding.veera.database.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee_PreparedStatement {
	
	public static void main(String[] args) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/testing_database";
		String username = "root";
		String password = "sqladmin";
		
		Connection connection = DriverManager.getConnection(URL, username, password);
		
		createRecord(connection);
		
		updateSalary(connection, 10);
		
		deleteRecord(connection, 8);
		
		queryAllRecords(connection);		
	}
	
	private static void createRecord(Connection connection) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO EMPLOYEE (NAME, AGE, SALARY)"
				+ "VALUES(?,?,?)");
		
		pstmt.setString(1, "John K");
		pstmt.setInt(2, 27);
		pstmt.setDouble(3, 15000.45);
		
		pstmt.execute();
		
	}
	
	private static void queryAllRecords(Connection connection) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM EMPLOYEE");
		
		ResultSet result = pstmt.executeQuery();
		
		System.out.println("Id-Name-Age-Salary");
		
		while(result.next()) {
			System.out.println(result.getInt(1)+"-"+result.getString(2)+"-"
					+result.getInt(3)+"-"+result.getDouble(4));
		}
	}
	
	private static void deleteRecord(Connection connection, int id) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("DELETE FROM "
				+" EMPLOYEE WHERE ID=?");
		
		pstmt.setInt(1, id);
		
		pstmt.execute();
		
		System.out.println("Deleted record with id "+id);
	}
	
	private static void updateSalary(Connection connection, int id) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("UPDATE EMPLOYEE "
				+ "SET SALARY=? WHERE ID=?");
		
		pstmt.setDouble(1, 40000.86);
		pstmt.setInt(2, id);
		
		pstmt.execute();
		
		System.out.println("Salary updated to 40000.86 for record with id "+id);
	}

}
