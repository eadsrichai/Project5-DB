<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="true"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Student" %>

<%
	if (session.getAttribute("studentList") != null) {
		List<Student> stuList = (List<Student>) session.getAttribute("studentList");
		
%>
<div>
	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th scope="col">รหัส</th><th scope="col">ชื่อ</th>
				<th scope="col">สกุล</th><th scope="col">เบอร์โทร</th>
				<th scope="col">สาขาวิชา</th>
			</tr>
		</thead>
		<tbody>
			<% for (int i = 0; i < stuList.size(); i++) { %>
			<tr>
				<td><%out.print(stuList.get(i).getId());%></td>
				<td><%out.print(stuList.get(i).getFname());%></td>
				<td><%out.print(stuList.get(i).getLname());%></td>
				<td><%out.print(stuList.get(i).getTel());%></td>
				<td><%out.print(stuList.get(i).getDep().getName_dep());%></td>
			</tr>
			<%	}	%>
		</tbody>
	</table>
</div> 
<% }else {  %>
<div class="alert alert-warning mt-3 p-3 h2">
	<span>
		<% out.print("ไม่พบข้อมูลที่ค้นหา");   %>
	</span>
</div>
<% } %>