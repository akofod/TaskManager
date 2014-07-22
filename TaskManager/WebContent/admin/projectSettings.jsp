<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update User Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/accountAdminScripts.js" type="text/javascript"></script>
</head>
<body>
    <h3>Project Settings</h3>
    <center>
    	<c:url var="updateDescription" value="updateProjectDesc.jsp">
            <c:param name="project" value="<%= request.getParameter(\"projectID\") %>"/>
        </c:url>
		<a href="${updateDescription}" id="update_description">Update Description</a><br>
		<c:url var="updateDueDate" value="updateProjectDueDate.jsp">
            <c:param name="project" value="<%= request.getParameter(\"projectID\") %>"/>
        </c:url>
		<a href="${updateDueDate }">Change Due Date</a><br>
		<c:url var="inviteUsers" value="usersearch.jsp">
            <c:param name="project" value="<%= request.getParameter(\"projectID\") %>"/>
        </c:url>
		<a id="invite_user" href="${inviteUsers}">Invite Users</a><br>
		<a id="delete_project" href="userHome.jsp">Delete Project</a>
    </center>
<br>    
<div id="outputAuth" style="width:100%;">
</div>
<script>
$(document).ready(function() {
	var project_id = window.location.href.slice(window.location.href.indexOf('=') + 1);
	$("#delete_project").click(function(event) {
		event.preventDefault();
		$.ajax({
	        url: '/TaskManager/DeleteProjectAjax',
	        type:'POST',
	        data:{"project":project_id},
	        success: function(ajaxData) {
	            if (ajaxData == "Project deleted."){
	                alert(ajaxData);
	                window.top.location.href = 'userHome.jsp';
	            }
	        },
	        error: function(request, status, error) {
	            alert('Error');
	        }
	    }); 
	});
});
</script>
</body>
</html>