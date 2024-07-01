package model;

import java.sql.Connection;

public class DepDAO {
	private Student student;
	private Connection con;
	
	public DepDAO() {
		con = new ConnectDB().getConnection();
	}
	
}
