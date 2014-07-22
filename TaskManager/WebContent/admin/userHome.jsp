<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/mainStyle.css">
<link rel="stylesheet" href="../css/colorbox.css" type="text/css" media="screen" />
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.colorbox-min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".newProject").colorbox({iframe: true, innerWidth:400, innerHeight:250});
    $(".accountAdmin").colorbox({iframe: true, innerWidth:400, innerHeight:300});
    $(".settings").colorbox({iframe: true, innerWidth:400, innerHeight:300});
});
</script>
<title>Home</title>
</head>
<body>
<div class="contentWrapper">
	<div class="header">
		<div class="appName"><a href="/TaskManager/admin/userHome.jsp">TaskManager</a></div>
		<div class="userControl">Welcome ${user.user_id } // 
		<a href="accountDetails.jsp" class="accountAdmin">User Settings</a> // 
		<a href="/TaskManager/LoginLogoutAjax?reqAction=LogOut" >Logout</a></div>
	</div>
	<div class="links">
	<a href="createproject.jsp" class="newProject">New Project</a>
	</div>
	<div class="content">
		
			<c:forEach var="proj" items="${userProjects}">
				<c:set var="settingsURL">
					<c:url value="projectSettings.jsp">
						<c:param name="projectID" value="${proj.project_id}"/>
					</c:url>
				</c:set>
				<c:set var="taskURL">
					<c:url value="project.jsp">
						<c:param name="projectID" value="${proj.project_id }" />
					</c:url>
				</c:set>
				<div class="project">
					<div class="project-description"><a href="${taskURL}">${proj.description}</a></div>
					<div class="project-category">Category: ${proj.category}</div>
					<div class="project-dueDate">Due: ${proj.final_deadline}</div>
					<div class="project-footer"><a class="settings" href="${settingsURL}">Settings . . .</a></div>
				</div>
			</c:forEach>
	</div>
</div>
	

</body>
</html>