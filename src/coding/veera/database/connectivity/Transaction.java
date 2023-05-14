package coding.veera.database.connectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Transaction {
	
	public static void main(String[] args) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/testing_database";
		String username = "root";
		String password = "sqladmin";
		
		Connection connection = DriverManager.getConnection(URL, username, password);
		
		System.out.println("Set auto commit - y/n ?");
		
		Scanner scan = new Scanner(System.in);
		String setAutoCommit = scan.next();
		
		
		if("y".equalsIgnoreCase(setAutoCommit))
			connection.setAutoCommit(true);
		else if("n".equalsIgnoreCase(setAutoCommit))
			connection.setAutoCommit(false);
		
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO EMPLOYEE(NAME, AGE, SALARY)"
				+" VALUES('JOHN',29,100000)");
		
		pstmt.execute();
		
		
		
		System.out.println("Roll back the transaction - y/n?");
		String rollBack = scan.next();
		
		if("y".equalsIgnoreCase(rollBack))
			connection.rollback();
		
		if("n".equalsIgnoreCase(setAutoCommit))
			connection.commit();
		
		queryAllRecords(connection);
		
		scan.close();
		connection.close();
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
}
/*

		
		
		
		
*/