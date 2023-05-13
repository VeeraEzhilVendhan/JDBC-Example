package coding.veera.database.connectivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Blob;

public class Image_File {
	
	public static void main(String[] args) throws SQLException, IOException {
		
		String URL = "jdbc:mysql://localhost:3306/testing_database";
		
		String username = "root";
		
		String password = "sqladmin";
		
		Connection connection = DriverManager.getConnection(URL, username, password);
		
		writeImgFileToDB(connection);
		
		readImageFileFromDB(connection);
		
		queryAllRecord(connection);
	}
	
	private static void writeImgFileToDB(Connection connection) throws SQLException, IOException {
		
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO IMAGE_FILE(image, file)"
				+ "VALUES(?,?)");
		
		for(int i=1;i<=2;i++) {
			
			FileInputStream image = new FileInputStream("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Image\\sample_"+i+".png");
			FileInputStream file = new FileInputStream("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Files\\sample_"+i+".txt");	
			System.out.println("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Image\\sample_"+i+".png");
			System.out.println("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Files\\sample_"+i+".txt");
			pstmt.setBinaryStream(1, image, image.available());
			pstmt.setBinaryStream(2, file, file.available());
			pstmt.execute();
			image.close();
			file.close();
		}		
	}
	
	
	private static void readImageFileFromDB(Connection connection) throws SQLException, IOException{
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM IMAGE_FILE");
		
		ResultSet result = pstmt.executeQuery();
		
		int i=1;
		
		while(result.next()) {

			FileOutputStream image = new FileOutputStream("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Image\\db_retrieve_"+i+".png");
			FileOutputStream file = new FileOutputStream("D:\\Project-Workspaces\\Java\\JDBC-Practice\\Sample_Files\\db_retrieve_"+i+".txt");
			System.out.println(i);
			Blob image_blob = (Blob) result.getBlob(2);
			Blob file_blob = (Blob) result.getBlob(3);
			
			byte[] image_bytes = image_blob.getBytes(1, (int)image_blob.length());
			byte[] file_bytes = file_blob.getBytes(1, (int)file_blob.length());
			
			image.write(image_bytes);
			file.write(file_bytes);
			
			image.close();
			file.close();
			
			++i;
		}
		
				
	}
	
	private static void queryAllRecord(Connection connection) throws SQLException {
		
		PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM IMAGE_FILE");
		
		//pstmt.setInt(1, 1);
		
		ResultSet result = pstmt.executeQuery();
		
		while(result.next()) {
			
			System.out.println(result.getInt(1)+"-"+result.getBlob(2)+"-"+result.getBlob(3));
			
		}
		
	}
	
	
	

}
