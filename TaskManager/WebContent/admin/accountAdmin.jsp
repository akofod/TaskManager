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
    <h3>Update Account Information</h3>
    <form id="updateAccountDetails" action="/TaskManager/UpdateUserAjax?type=updateUserDetails" method="POST" class="form">
        <div style="display:none">
            <label for="email">Email:</label>
            <input id="email" type="email" name="email" value=${user.user_id}>
        </div>
        <div>
            <label for="nickname">Nickname:</label>
            <input id="nickname" type="text" name="nickname" value=${user.nickname}>
        </div>
        <div>
            <label for="firstname">First Name:</label>
            <input id="firstname" type="text" name="firstname" value=${user.firstname}>
        </div>
        <div>
            <label for="lastname">Last Name:</label>
            <input id="lastname" type="text" name="lastname" value=${user.lastname}>
        </div>
        <div>
        	<input type="submit" value="Update" />
        </div>
        <div>
        	<a href="accountDetails.jsp">Go Back</a>
        </div>
        
    </form>
<br>    
<div id="outputAuth" style="width:100%;">
</div>
</body>
<script type="text/javascript">
$(document).read(function() {
	parent.$.colorbox.resize();
});
</script>
</html>