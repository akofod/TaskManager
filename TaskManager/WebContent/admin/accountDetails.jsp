<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <h3>View Account Information</h3>
    <center>
	       <table>
	      		<tr>
		       		<td class="label"><label for="password">Password:</label></td>
		       		<td class="text">****&nbsp;&nbsp;<a href="changePassword.jsp">Change</a></td>
		       	</tr>
		       	<tr>
		       		<td class="label"><label for="email">Email:</label></td>
		       		<td class="text">${user.user_id}</td>
		       	</tr>
		       	<tr>
		       		<td class="label"><label for="nickname">Nickname:</label></td>
		           	<td class="text">${user.nickname}</td>
		       	</tr>
		       	<tr>
		        	<td class="label"><label for="firstname">First Name:</label></td>
		            <td class="text">${user.firstname}</td>
		       	</tr>
		       	<tr>
		       		<td class="label"><label for="lastname">Last Name:</label></td>
		           	<td class="text">${user.lastname}</td>
		       	</tr>
		       	<tr>
		       		<td colspan="2" class="buttons"><a href="accountAdmin.jsp">Update User Details</a></td>
		       	</tr>
	       </table>
       </center>
<br>    
<div id="outputAuth" style="width:100%;">
</div>
</body>
</html>