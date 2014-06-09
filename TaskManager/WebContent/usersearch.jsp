<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

function getHTML(strAction) 
{
    
}

</script>
</head>
<body>
    <h2>Search for Users</h2>
    <form id="searchForm" method="POST" action="searchusers.do">
        <div>
            <label for="user">Enter an email or nickname to search:</label>
            <input id="user" type="text" style="width: 150px" name="user" />
            <span class="error"></span>
        </div>
        <div>
            <input type="submit" style="width: 100px" id="submit" value="Search">
        </div>
    </form>

</body>
</html>
