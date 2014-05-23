<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Test Log In</title>
</head>
<body>
<b>${message}</b> <br>
To authenticate, User ID = "user", Password = "pass"<br>
<form method="POST" action="AuthServletTest">
User ID:
<input type="text" style="width: 200px" name="username" />
Password:
<input type="password" style="width: 200px" name="password">
<input type="submit" style="width: 200px" name="action" value="Login">
<input type="submit" style="width: 200px" name="action" value="Logout">
</form>
<a href = "admin/protected.jsp">Protected Page</a>
<a href = "unprotected.jsp">Unprotected Page</a>
</body>
</html>