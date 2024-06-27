<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Student" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet"
			href="webjars/bootstrap/5.3.3/css/bootstrap.min.css">
		
		<title>โปรแกรมฐานข้อมูลนักศึกษา</title>
	</head>

	<body>
		<div class="container">
			<header>
				<%@include file="header.jsp" %>
			</header>
			<nav>
				<form class="row mt-3" action="StudentController" method="get">		
					<div class="col-1">
						<input type="submit" class="btn btn-primary" name="submit" value="search"/>
					</div>
					<div class="col-2">
						<input type="text" class="form-control" name="id"  value="" />
					</div>
					
					<div class="col-2">
						<input type="submit" class="btn btn-success" name="submit" value="add"/>
					</div>
					
				</form>	
			</nav>
			<article class="mt-3" style="height : 60vh;">
				<%@include file="container.jsp" %>
			</article>
			
			<footer>
				<%@include file="footer.jsp" %>
			</footer>
		</div>
	</body>
</html>
