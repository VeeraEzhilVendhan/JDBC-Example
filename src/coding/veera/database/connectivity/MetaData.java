package coding.veera.database.connectivity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class MetaData {
	
	public static void main(String[] args) throws SQLException {
		
		String URL = "jdbc:mysql://localhost:3306/testing_database";
		String username = "root";
		String password = "sqladmin";
		
		Connection connection = DriverManager.getConnection(URL, username, password);
		
		databaseMetaData(connection);
		
		resultsetMetaData(connection);
		
	}

	private static void databaseMetaData(Connection connection) throws SQLException {
		
		DatabaseMetaData dbMetaData = connection.getMetaData();
		
		System.out.println("Database MetaData----");
		System.out.println("Driver name - "+dbMetaData.getDriverName());
		System.out.println("Driver version - "+dbMetaData.getDriverVersion());
		System.out.println("Driver version - "+dbMetaData.getUserName());
		String table[]= {"TABLE"};
		ResultSet result = dbMetaData.getTables(null,null,null,table);
		while(result.next()) {
			System.out.println("Table name - "+result.getString(3));

		}
		
	}
	
	private static void resultsetMetaData(Connection connection) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE SALARY=?");
		
		pstmt.setDouble(1, 50000);
		
		ResultSet result = pstmt.executeQuery();
		
		ResultSetMetaData resultMetaData = result.getMetaData();
		
		System.out.println("ResultSet Data----");
		
		System.out.println("No of columns - "+resultMetaData.getColumnCount());
		System.out.println("Table name - "+resultMetaData.getTableName(3));
		
	}
}
