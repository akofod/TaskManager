<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/registrationStyle.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

function getHTML(strAction) 
{
	var user=$('#username').val();
	var pwd=$('#password').val();
	var html = '';
	var remem = 'false';
	if (document.getElementById('remember').checked) 
	{
		remem='true';
	}
	$.ajax({
		async: false,
		cache: false,
		url: 'LoginLogoutAjax',
		type:'POST',
		data:{"user":user,"password":pwd,"reqAction":strAction,"rememberMe":remem},
		success: function(ajaxData) {
			if (ajaxData == "success"){
				window.top.location.href = 'admin/userHome.jsp';
			}	
			else {
				html = ajaxData;
			}
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
    <div id="regSuccess">${regSuccess}</div>
    <form id="loginForm" class="form">
        <div class="ui-widget">
	       <label for="username">User ID:</label>
	       <input type="text" style="width: 200px" id="username">
	    </div>
	    <div class="ui-widget">
	       <label for="password">Password:</label>
	       <input type="password" style="width: 200px" id="password">
	    </div>
	    <div class="ui-widget">
	       <input type="checkbox" style="width: 20px" id="remember">
	       <label for="remember">Remember Me</label>
	    </div>
        <div id="buttons">
	       <input type="button" style="width: 100px" id="login" value="Login">
	       <input type="button" style="width: 100px" id="logout" value="Logout">
	       <input type="button" style="width: 100px" id="cancel" value="Cancel"/>
	   </div>
    </form>

<div id="outputAuth" style="width:100%;">
</div>

<script type="text/javascript">
   $('#login').click(function()
       {   
           getHTML('login');           
       });
   $('#logout').click(function()
	   {   
	       getHTML('logout');           
	   });
   $('#cancel').on('click', function() {
       self.parent.tb_remove();
   });
</script>

</body>
</html>