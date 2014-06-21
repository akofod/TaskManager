<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update User Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

function getHTML(strAction) 
{
	var currEmail=$('#email_orig').val();
	var newEmail=$('#email').val(); 
	var nickname=$('#nickname').val();
	var first_name=$('#firstname').val();
	var last_name=$('#lastname').val();
	var newPass=$('#password').val();
	var newPassConfirm=$('#confirm').val();
	var oldPass=$('#current').val(); 
	var html = '';
	$.ajax({
		async: false,
		cache: false,
		url: '/TaskManager/UpdateUserAjax',
		type:'POST',
		data:{"new_user_id":newEmail,"reqAction":strAction,"nickname":nickname,"firstname":first_name,
			  "lastname":last_name,"newPass":newPass,"newPassConfirm":newPassConfirm,"oldPass":oldPass},
		success: function(ajaxData) {
			html = ajaxData;
			},
		error: function(request, status, error) {
			alert('Error');
		}
	});
	$('#outputAuth').html(html);
}
</script>

</head>
<body>
    <h2 id="heading">View/Update Account Information</h2>
    <form id="registrationForm" class="form">
        <div class="ui-widget">
            <label for="email">Email:</label>
            <input id="email" type="email" style="width: 300px" name="email" value=${user.user_id}>
            <input type="button" style="width: 200px" id="submitEmail" value="Update_Email">
        </div>
        <div class="ui-widget">
            <label for="nickname">Nickname:</label>
            <input id="nickname" type="text" style="width: 300px" name="nickname" value=${user.nickname}>
            <input type="button" style="width: 200px" id="submitNickname" value="Update_Nickname">
        </div>
        <div class="ui-widget">
            <label for="firstname">First Name:</label>
            <input id="firstname" type="text" style="width: 300px" name="firstname" value=${user.firstname}>
            <input type="button" style="width: 200px" id="submitFirstName" value="Update_First_Name">
        </div>
        <div class="ui-widget">
            <label for="lastname">Last Name:</label>
            <input id="lastname" type="text" style="width: 300px" name="lastname" value=${user.lastname}>
            <input type="button" style="width: 200px" id="submitLastName" value="Update_Last_Name">
        </div>
        <div class="ui-widget">
            <label for="password">New Password:</label>
            <input autocomplete="off" id="password" type="password" style="width: 300px" name="password">
        </div>
        <div class="ui-widget">
            <label for="confirm">Confirm New Password:</label>
            <input autocomplete="off" id="confirm" type="password" style="width: 300px" name="confirm">
            <input type="button" style="width: 200px" id="submitPassword" value="Update_Password">
        </div>
        <div class="ui-widget">
            <label for="old_password">Current Password (Required):</label>
            <input autocomplete="off" id="current" type="password" style="width: 300px" name="current_password">
        </div>
    </form>
<br>    
<div id="outputAuth" style="width:100%;">
</div>

<script type="text/javascript">
 $('#submitEmail').click(function() {   
	getHTML('submitEmail');
	});
 
 $('#submitNickname').click(function() {   
	 getHTML('submitNickname');
	});    
 
 $('#submitFirstName').on('click', function() {
	 getHTML('submitFirstName');
	}); 
 
 $('#submitLastName').on('click', function() {
	 getHTML('submitLastName');
	}); 
 
 $('#submitPassword').on('click', function() {
	 getHTML('submitPassword');
	}); 
 </script>
 
</body>
</html>