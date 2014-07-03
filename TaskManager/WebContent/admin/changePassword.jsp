<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update User Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/accountAdminScripts.js" type="text/javascript"></script>

</head>
<body>
    <h3>Update Password</h3>
    <form id="changePasswordForm" action="/TaskManager/UpdateUserAjax?type=updatePassword" method="POST" class="form">
	    <div>
            <label for="password">New Password:</label>
            <input autocomplete="off" id="password" type="password" name="password">
        </div>
        <div>
            <label for="confirm">Confirm New Password:</label>
            <input autocomplete="off" id="confirm" type="password" name="confirm">
        </div>
        <div >
            <label for="old_password">Current Password:</label>
            <input autocomplete="off" id="current" type="password" name="current_password">
        </div>
        <div>
        	<input type="submit" id="submitPassword" value="Update">
        </div>
        <div>
        	<a href="accountDetails.jsp">Go Back</a>
        </div>
     </form>
<br>    
<div id="outputAuth" style="width:100%;">
</div>
</body>
</html>