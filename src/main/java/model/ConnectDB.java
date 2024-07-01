package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class ConnectDB {
	private  Connection con;
	private  String driver = "org.mariadb.jdbc.Driver";
	private  String dbURL = "jdbc:mariadb://localhost:3307/user2?characterEncoding=utf-8";
	private  String username = "user2";
	private  String password = "asdf";
	
	public  ConnectDB() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connect DB OK");

		} catch (ClassNotFoundException e) {
			System.out.print("ค้นหาคลาสไม่เจอ" + e.getMessage());
		} catch (SQLException e) {
			System.out.print("คำสั่ง sql ไม่ถูกต้อง" + e.getMessage());
		}
	}
	public  Connection getConnection() {
		return con;
	}
	public  void closeConnection() throws SQLException {
		con.close();
	}
}
