<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>User Search</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TaskManager/css/autocomplete.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
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
    var project_id = window.location.href.slice(window.location.href.indexOf('=') + 1);
    $("#submit").click(function(event) {
        event.preventDefault();
        $.ajax({
            url: '/TaskManager/searchusers.do',
            type:'POST',
            data:{"project":project_id, "user":$("#user").val()},
            success: function(ajaxData) {
                if (ajaxData == "User Added."){
                    alert(ajaxData);
                    window.top.location.href = 'userHome.jsp';
                }
                else {
                	alert(ajaxData);
                }
            },
            error: function(request, status, error) {
                alert('Error');
            }
        }); 
    });
});
</script>
</head>
<body>
    <h3>Search for a User</h3>
    <div id="formWrap">
        <form id="searchForm" method="POST" action="/TaskManager/searchusers.do">
            <div id="users">
                <label id="userLabel" for="user">Enter nickname or email:</label>
                <input id="user" type="text" name="user" />
            </div>
            <div>
                <input type="submit" style="width: 100px" id="submit" value="Add User">
            </div>
        </form>
    </div>
</body>
</html>
