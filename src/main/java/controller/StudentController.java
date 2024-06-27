package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Student;
import model.StudentDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentController() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDAO studao = new StudentDAO();	// กำหนดโครงสร้างข้อมูลแบบ List โดยมีการเก็บแบบ Class Student
		Student stu = new Student();
		List<Student> stuList = null;
		HttpSession session = request.getSession();
		if(request.getParameter("submit")!= null && request.getParameter("submit").equals("search") && request.getParameter("id") != "") {
			String id = request.getParameter("id");
			try {
				 stu = studao.searchStudent(id);
				 if(stu != null) {
					 stuList = new ArrayList<Student>();
					 stuList.add(stu);
				 }	 
					 session.setAttribute("studentList", stuList );	  
					 RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);	
						
			} catch (SQLException e) {	e.printStackTrace(); }
		}else if(request.getParameter("submit")!= null && request.getParameter("submit").equals("add")) {
			RequestDispatcher rd=request.getRequestDispatcher("frminputstudent.jsp");
			rd.forward(request, response);	
		} else if(request.getParameter("submit")!= null && request.getParameter("submit").equals("save")) {
			String id = request.getParameter("id");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String tel = request.getParameter("tel");
			
			stu = new Student(id,fname,lname,tel);
			try {
				studao.addStudent(stu);
				stuList = (List<Student>) studao.listAllStudent(); // วัตถุ studao ทำการเรียก method listAllStudent()
				session.setAttribute("studentList", stuList );      // นำข้อมูลใน stuList เก็บไว้ที่ session studentList
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			try {
				stuList = (List<Student>) studao.listAllStudent(); // วัตถุ studao ทำการเรียก method listAllStudent()
				session.setAttribute("studentList", stuList );      // นำข้อมูลใน stuList เก็บไว้ที่ session studentList
				RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);	
				
			} catch (SQLException e) {	e.printStackTrace();	}
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
