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
				<div class="row">
					<div class="col-4">
						<form action="StudentController" method="get">
							<input type="submit" name="submit" value="search"/>
							<input type="text" name="id"  value="" />
						</form>
					</div>
				</div>
			</nav>
			<article style="height : 60vh;">
				<%@include file="container.jsp" %>
			</article>
			
			<footer>
				<%@include file="footer.jsp" %>
			</footer>
		</div>
	</body>
</html>
