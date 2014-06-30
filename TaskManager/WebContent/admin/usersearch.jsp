<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TaskManager/css/autocomplete.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery.ui.autocomplete.html.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $(function() {
        $("#user").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "/TaskManager/searchusers.do",
                    type: "GET",
                    data: { "term" : request.term },
                    contentType: "application/json",
                    dataType: "json",
                    success: function(data) {
                        response(data);
                    }
               });              
            }   
        });
    });
});
</script>
</head>
<body>
    <h2>Search for a User</h2>
    <div id="formWrap">
        <form id="searchForm" method="POST" action="/TaskManager/searchusers.do">
            <div id="users" class="ui-widget">
                <label id="userLabel" for="user">Enter nickname or email:</label>
                <input id="user" type="text" name="user" />
            </div>
            <div>
                <input type="submit" style="width: 100px" id="submit" value="Find User">
            </div>
        </form>
    </div>
</body>
</html>
