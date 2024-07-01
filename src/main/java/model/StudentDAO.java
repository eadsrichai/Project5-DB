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
			con = new ConnectDB().getConnection();
		}
		
		public List<Student> listAllStudent() throws SQLException {
	        List<Student> listStudent = new ArrayList<>();
	         
	        String sql = "SELECT  r.id,r.fname,r.lname,r.tel,d.id_dep,d.name_dep "
	        			+ "FROM re r,dep d "
	        			+ "WHERE r.id_dep = d.id_dep "
	        			+ "ORDER BY id ASC ";
	                   
	        PreparedStatement pStatement = con.prepareStatement(sql);

	        ResultSet resultSet = pStatement.executeQuery();
	        Dep dep;
	        
	        while (resultSet.next()) {
	            String id = resultSet.getString("id");
	            String fname = resultSet.getString("fname");
	            String lname = resultSet.getString("lname");
	            String tel = resultSet.getString("tel");
	            dep =  new Dep();
	            dep.setId_dep(resultSet.getString("id_dep"));
	            dep.setName_dep(resultSet.getString("name_dep"));
	             
	            Student stu = new Student(id, fname, lname, tel,dep);
	      
	            listStudent.add(stu);
	        }
	        resultSet.close();
	        return listStudent;
	    }
		
		
		public Student searchStudent(String myid) throws SQLException {
			Student stu = null;
			String sql = "SELECT r.id,r.fname,r.lname,r.tel,d.id_dep,d.name_dep FROM re r,dep d WHERE r.id_dep = d.id_dep AND id = ? OR fname like ? OR lname like ? OR tel like ?";
		     PreparedStatement pStatement = con.prepareStatement(sql);
		     pStatement.setString(1,myid);
		     pStatement.setString(2,"%"+myid+"%");
		     pStatement.setString(3,"%"+myid+"%");
		     pStatement.setString(4,"%"+myid+"%");
		     
		     ResultSet resultSet = pStatement.executeQuery(); 
		     Dep dep;
		     if (resultSet.next()) {
		    	 String id = resultSet.getString("id");
		         String fname = resultSet.getString("fname");
		         String lname = resultSet.getString("lname");
		         String tel = resultSet.getString("tel");
		         dep = new Dep();
		         dep.setId_dep(resultSet.getString("id_dep"));
		         dep.setName_dep(resultSet.getString("name_dep"));
		         stu = new Student(id, fname, lname, tel,dep);    
		     }
		     resultSet.close();
		     return stu;
		}
		
		
		public boolean addStudent(Student stu) throws SQLException {
			Boolean ans = false;
			if(searchStudent(stu.getId()) == null) {
			
			PreparedStatement pStatement;
			try {
				String sql = "INSERT INTO re(id,fname,lname,tel,id_dep) VALUES(?,?,?,?,?)";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1, stu.getId());
				pStatement.setString(2, stu.getFname());
				pStatement.setString(3, stu.getLname());
				pStatement.setString(4, stu.getTel());
				pStatement.setString(5, stu.getDep().getId_dep());
				int row = pStatement.executeUpdate();
				System.out.println(row);
				ans = true;	
				pStatement.close();
			}catch (SQLException e) {	e.printStackTrace();	}
			}else { System.out.print("มีข้อมูลอยู่แล้ว ไม่สามารถเพิ่มได้");}
		
			return ans;
		}

		
		public boolean updateStudent(Student stu) throws SQLException {
			Boolean ans = false;
			if(searchStudent(stu.getId()) != null) {
			PreparedStatement pStatement;
			try {
				String sql = "UPDATE  re SET  fname = ?, lname = ?, tel = ?, id_dep = ? WHERE id = ?";
				pStatement = con.prepareStatement(sql);
				pStatement.setString(1,stu.getFname());
				pStatement.setString(2,stu.getLname());
				pStatement.setString(3,stu.getTel());
				pStatement.setString(4,stu.getDep().getId_dep());
				pStatement.setString(5,stu.getId());
				
				int row = pStatement.executeUpdate();
				System.out.println(row);
				ans = true;	
				pStatement.close();
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
