<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User Registration</title>
<script>function cancel() {window.location.replace("index.jsp");}
        function clearForm() {document.getElementById("registrationForm").reset();}
        function validateForm() {
        	var user_id = document.forms["registrationForm"]["email"].value;
        	var nickname = document.forms["registrationForm"]["nickname"].value;
        	var password = document.forms["registrationForm"]["password"].value;
        	var confirm = document.forms["registrationForm"]["confirm"].value;
        	var atpos = user_id.indexOf("@");
        	var dotpos = user_id.lastIndexOf(".");
        	if (user_id == null || user_id == "") {
        		alert("A valid email address is required.");
        		return false;
        	}
        	if (nickname == null || nickname == "") {
        		alert("Nickname is required.");
        		return false;
        	}
        	if (nickname.length < 6 || nickname.length > 15) {
        		alert("Nickname must be between 6 and 15 characters.");
        		return false;
        	}
        	if (password == null || password == "") {
        		alert("You must enter a password.");
        		return false;
        	}
        	if (password != confirm) {
        		alert("Password does not match.");
        		return false;
        	}
        	if (password.length < 8) {
        		alert("Password must be at least 8 characters long.");
                return false;
        	}
        	if (atpos < 1 || (atpos + 2) > user_id.lastIndexOf(".") || (dotpos + 2) >= user_id.length) {
                alert("Not a valid e-mail address.");
                return false;
            }
        	var alphaNumeric = /^[a-z0-9]+$/i;
        	if (!(alphaNumeric.test(nickname))) {
        		alert("Nickname may only contain letters and numbers.");
        		return false;
        	}
        	if ((password.search(/\d/) == -1) || (password.search(/[A-Z]/) == -1) || (password.search(/[a-z]/) == -1)) {
        		alert("Password must contain at least one uppercase letter, one lowercase letter and one number.");
        		return false;
        	}
        }
</script>
</head>
<body>
    <b>${error}</b> <br/><br/>
    <form id="registrationForm" method="POST" action="register.do" onsubmit="return validateForm()">
        Email: <br/>
        <input id="email" type="text" style="width: 200px" name="email" /><br/>
        Nickname (Must be 6-15 characters, numbers and letters only): <br/>
        <input id="nickname" type="text" style="width: 200px" name="nickname" /><br/>
        First Name: <br/>
        <input id="firstname" type="text" style="width: 200px" name="firstname" /><br/>
        Last Name: <br/>
        <input id="lastname" type="text" style="width: 200px" name="lastname" /><br/>
        Password: <br/>
        <input autocomplete="off" id="password" type="password" style="width: 200px" name="password" /><br/>
        Confirm Password: <br/>
        <input autocomplete="off" id="confirm" type="password" style="width: 200px" name="confirm" /><br/>
        <input type="submit" style="width: 200px" name="action" value="Register" />
        <input type="button" style="width: 200px" value="Reset" onclick=clearForm() />
        <input type="button" style="width: 200px" value="Cancel" onclick=cancel() />
        
    </form>
</body>
</html>