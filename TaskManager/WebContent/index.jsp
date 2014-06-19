<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="./css/indexPage.css">
<link rel="stylesheet" href="./css/thickbox.css" type="text/css" media="screen" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="./js/thickbox.js"></script>
<title>Welcome to Task Manager</title>
<script type="text/javascript">
$(document).ready(function() {
    

});

</script>
</head>
<body>
    <h2>Welcome to Task Manager</h2>
    <div id="buttons">
        <a href="login.jsp?KeepThis=true&TB_iframe=true&height=200&width=550&modal=true" class="thickbox">
            <span class="button" id="login"> &nbsp;Login&nbsp; </span>
        </a>
        <a href="registration.jsp?KeepThis=true&TB_iframe=true&height=400&width=500&modal=true" class="thickbox">
            <span class="button" id="register">Register</span>
        </a>
    </div>
</body>
</html>