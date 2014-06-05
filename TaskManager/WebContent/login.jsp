<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<table>
	<tr><td>User ID:</td><td><input type="text" style="width: 200px" id="username" value=${userIdCookie}></td>
	<tr><td>Password:</td><td><input type="password" style="width: 200px" id="password" value=${userPassCookie}></td>
	<td><input type="checkbox" style="width: 20px" id="remember">Remember Me</td></tr>
	<tr><td><input type="button" style="width: 200px" id="login" value="Login"></td>
	<td><input type="button" style="width: 200px" id="logout" value="Logout"></td></tr>
</table>

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
</script>

</body>
</html>