package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	public static Connection conn=null;
	public static Connection getConnection() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/filehiding?useSSL=false","root","mk123");
		
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connection is set");
		return conn;
	}
	
	public static void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]) {
		MyConnection.getConnection();
	}
	
}
