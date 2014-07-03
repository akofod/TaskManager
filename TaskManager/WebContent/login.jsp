<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/loginScripts.js"></script>
</head>
<body>
	<h3>User Login</h2>
    <div id="regSuccess">${regSuccess}</div>
    <center>
    <form id="loginForm" class="form">
    	<table>
    		<tr>
    			<td class="label"><label for="username">User ID:</label></td>
	       		<td><input type="text" style="width: 200px" id="username"></td>
    		</tr>
    		<tr>
    			<td class="label"><label for="password">Password:</label></td>
	       		<td><input type="password" style="width: 200px" id="password"></td>
    		</tr>
    		<tr>
    			<td class="buttons" colspan="2"><label for="remember">Remember Me</label>
	       		<input type="checkbox" style="width: 20px" id="remember"></td>
    		</tr>
    		<tr>
    			<td colspan="2" class="buttons">
	    			<input type="button" style="width: 100px" id="login" value="Login" onclick="getHTML('login')">
    			</td>
    		</tr>
    	</table>
    </form>
    </center>

<div id="outputAuth" style="width:100%;text-align:center;">
</div>
</body>

</html>