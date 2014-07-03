<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="./css/mainStyle.css">
<link rel="stylesheet" type="text/css" href="./css/indexPage.css">
<link rel="stylesheet" href="./css/colorbox.css" type="text/css" media="screen" />
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/jquery.colorbox.js"></script>
<title>Welcome to Task Manager</title>

</head>
<body>
<center>
    <h2>Task Manager</h2>
    <table>
    	<tr>
    		<td><a href="login.jsp" class="logCb" >Login</a></td>
    		<td><a href="registration.jsp" class="regCb" >Register</a></td>
    	</tr>
    </table>
 </center>
</body>
<script type="text/javascript">
$(document).ready(function() {
	$(".logCb").colorbox({iframe: true, innerWidth:350, innerHeight:180});
    $(".regCb").colorbox({iframe: true, innerWidth:350, innerHeight:370});
});

</script>
</html>