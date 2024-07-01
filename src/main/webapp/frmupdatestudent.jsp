<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Student" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
            href="webjars/bootstrap/5.3.3/css/bootstrap.min.css">
        <title>Update ข้อมูลนักศึกษา</title>
    </head>
    <body>
    
        <div class="container">
            <%@ include file="header.jsp" %>
            <% if (session.getAttribute("studentList") != null) {
        		Student stu = (Student) session.getAttribute("studentList"); 
        		
        	%>
            
            
            
            
            <form action="StudentController" method="POST">
            <fieldset class="border border-1 border-primary mt-3 rounded">
                <legend></legend>
            <div class="row p-3">
                <div class="col-4">
                    <input type="text" class="form-control" name="id" value="<%out.print(stu.getId()); %>"
                        placeholder="รหัสนักศึกษา" aria-label="รหัสนักศึกษา">
                </div>
            </div>
            <div class="row p-3">
                <div class="col">
                    <input type="text" class="form-control" name="fname"
                    	value="<%out.print(stu.getFname()); %>"
                        placeholder="ชื่อ" aria-label="First name">
                </div>
                <div class="col">
                    <input type="text" class="form-control" name="lname"
                    	value="<%out.print(stu.getLname()); %>"
                        placeholder="นามสกุล" aria-label="Last name">
                </div>
            </div>

            <div class="row p-3">
                <div class="col-2">
                    <input type="text" class="form-control" name="tel"
                    	value="<%out.print(stu.getTel()); %>"
                        placeholder="เบอร์โทร" aria-label="เบอร์โทร">
                </div>
                
                <div class="col-2">
					<select name="id_dep" class="form-control" >
					  <option value="00">--- เลือกสาขาวิชา ----</option>
					  <option value="09">เทคโนโลยีสารสนเทศ</option>
					  <option value="08">ธุรกิจดิจิทัล</option>
					</select>
                    
                </div>
                
            </div>
            
        </fieldset>
        <div class="row p-3">
            <div class="col-2">
                <input type="submit" class="btn btn-primary" name="submit" value="save"/>
            </div>
            
        </div>
    </form>
        </div>
        <% } %>
    </body>
</html>