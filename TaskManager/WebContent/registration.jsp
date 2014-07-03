<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css" >
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/registrationScripts.js" type="text/javascript"></script>
<title>New User Registration</title>

</head>
<body>
    <h3>New User Registration</h3>
    <div align="center">
    <form id="registrationForm" class="form" method="POST" action="register.do">
        <div>
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" />
            <span class="error">A valid email address is required</span>
        </div>
        <div>
            <label for="nickname">Nickname:</label>
            <input id="nickname" type="text" name="nickname" />
            <span class="error">Nickname must be 6-15 characters, numbers and letters only</span>
        </div>
        <div>
            <label for="firstname">First Name:</label>
            <input id="firstname" type="text" name="firstname" />
            <span class="error"></span>
        </div>
        <div>
            <label for="lastname">Last Name:</label>
            <input id="lastname" type="text" name="lastname" />
            <span class="error"></span>
        </div>
        <div>
            <label for="password">Password:</label>
            <input autocomplete="off" id="password" type="password" name="password" />
            <span class="error">Password must be at least 8 characters, with at least one capital letter, one lowercase letter, and one number</span>
        </div>
        <div>
            <label for="confirm">Confirm Password:</label>
            <input autocomplete="off" id="confirm" type="password" name="confirm" />
            <span class="error">Does not match password</span>
        </div>
        <div id="buttons">
            <input type="submit" id="submit" value="Submit">
            <input type="reset" id="reset" value="Reset"/>
            
        </div>
        <div id="error_text">${error}</div>
    </form>
    </div>
</body>
</html>