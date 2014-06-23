<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/mainTemplate.css">
<link rel="stylesheet" href="../css/thickbox.css" type="text/css" media="screen" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/thickbox.js"></script>
<title>Home</title>
</head>
<body>
<script type="text/javascript" src="/TaskManager/js/include.js"> </script>
<b>Projects currently assigned to ${user.user_id}</b> <br> <br>
<div>
	<a href="createproject.jsp?KeepThis=true&TB_iframe=true&height=300&width=350&modal=true" class="thickbox">New Project</a> | 
	<a href="accountAdmin.jsp?KeepThis=true&TB_iframe=true&height=300&width=350&modal=true" class="thickbox">Update User Details</a>
</div>
<table border="1">
	<tr>
		<td><center>Project ID</center></td>
		<td><center>Project Description</center></td>
		<td><center>Project Deadline</center></td>
	</tr>

	<c:forEach var="proj" items="${userProjects}">
		<c:set var="projectURL">
			<c:url value="project.jsp">
				<c:param name="projectID" value="${proj.project_id}"/>
			</c:url>
		</c:set>
		<tr>
			<td>
				<center><a href="${projectURL}">${proj.project_id}</a></center>
			</td>
			<td>
				<center><a href="${projectURL}">${proj.description}</a></center>
			</td>
			<td>
				<center><a href="${projectURL}">${proj.final_deadline}</a></center>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>