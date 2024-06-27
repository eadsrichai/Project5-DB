package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class StudentDAO {
	private Student student;
	private Connection con;
		public StudentDAO() {
			try {
	//			Class.forName("com.mysql.cj.jdbc.Driver");  for mysql
	//			String dbURL = "jdbc:mysql://localhost/user2?characterEncoding=utf-8";
				Class.forName("org.mariadb.jdbc.Driver");
				String dbURL = "jdbc:mariadb://localhost:3307/user2?characterEncoding=utf-8";
				con = DriverManager.getConnection(dbURL, "user2", "asdf");
				System.out.println("Connect DB OK");
	
			} catch (ClassNotFoundException e) {
				System.out.print("ค้นหาคลาสไม่เจอ" + e.getMessage());
			} catch (SQLException e) {
				System.out.print("คำสั่ง sql ไม่ถูกต้อง" + e.getMessage());
			}
		}
		public void closeConnection() throws SQLException {
			con.close();
		}
		
		public List<Student> listAllStudent() throws SQLException {
	        List<Student> listStudent = new ArrayList<>();
	         
	        String sql = "SELECT  id,fname,lname,tel FROM re ORDER BY id ASC ";
	                   
	        PreparedStatement pStatement = con.prepareStatement(sql);

	        ResultSet resultSet = pStatement.executeQuery();

	        while (resultSet.next()) {
	            String id = resultSet.getString("id");
	            String fname = resultSet.getString("fname");
	            String lname = resultSet.getString("lname");
	            String tel = resultSet.getString("tel");
	             
	            Student stu = new Student(id, fname, lname, tel);
	            listStudent.add(stu);
	        }
	        resultSet.close();
	        return listStudent;
	    }
		
		
		public Student searchStudent(String myid) throws SQLException {
			Student stu = null;
			String sql = "SELECT * FROM re WHERE id = ? OR fname like ? OR lname like ? OR tel like ?";
		     PreparedStatement pStatement = con.prepareStatement(sql);
		     pStatement.setString(1,myid);
		     pStatement.setString(2,"%"+myid+"%");
		     pStatement.setString(3,"%"+myid+"%");
		     pStatement.setString(4,"%"+myid+"%");
		     
		     ResultSet resultSet = pStatement.executeQuery();    
		     if (resultSet.next()) {
		    	 String id = resultSet.getString("id");
		         String fname = resultSet.getString("fname");
		         String lname = resultSet.getString("lname");
		         String tel = resultSet.getString("tel");
		         stu = new Student(id, fname, lname, tel);    
		     }
		     resultSet.close();
		     return stu;
		}
		
		
		public boolean addStudent(Student stu) throws SQLException {
			Boolean ans = false;
			if(searchStudent(stu.getId()) == null) {
			
			PreparedStatement pStatement;
			try {
				String sql = "INSERT INTO re(id,fname,lname,tel) VALUES(?,?,?,?)";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1, stu.getId());
				pStatement.setString(2, stu.getFname());
				pStatement.setString(3, stu.getLname());
				pStatement.setString(4, stu.getTel());
				
				int row = pStatement.executeUpdate();
				System.out.println(row);
				ans = true;	
			}catch (SQLException e) {	e.printStackTrace();	}
			}else { System.out.print("มีข้อมูลอยู่แล้ว ไม่สามารถเพิ่มได้");}
			return ans;
		}

		
		public boolean updateStudent(Student stu) throws SQLException {
			Boolean ans = false;
			if(searchStudent(stu.getId()) != null) {
			PreparedStatement pStatement;
			try {
				String sql = "UPDATE  re SET  fname = ?, lname = ?, tel = ? WHERE id = ?";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1,stu.getFname());
				pStatement.setString(2,stu.getLname());
				pStatement.setString(3,stu.getTel());
				pStatement.setString(4,stu.getId());
				int row = pStatement.executeUpdate();
				System.out.println(row);
				ans = true;	
			}catch (SQLException e) {	e.printStackTrace();	}
			}
			return ans;
		}
		
		public boolean delStudent(String id) {
			Boolean ans = false;
			PreparedStatement pStatement;
			try {
				String sql = "DELETE FROM re WHERE id = ?";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1,id);
				int row = pStatement.executeUpdate();
				System.out.println(row);
				ans = true;	
			}catch (SQLException e) {	e.printStackTrace();	}
			return ans;
		}
		
		
		
		
}
