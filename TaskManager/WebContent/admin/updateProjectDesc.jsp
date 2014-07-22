<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">
$(document).ready(function() {
	var project_id = window.location.href.slice(window.location.href.indexOf('=') + 1);
	$.ajax({
        url: '/TaskManager/UpdateProjectSettings',
        type:'POST',
        data:{"project":project_id, "action":"getDescription"},
        success: function(ajaxData) {
            if (ajaxData !== ""){
				$('#description').val(ajaxData);
            }
            else {
            	alert("Error getting description.");
            }
        },
        error: function(request, status, error) {
            alert('Error Getting Description.');
        }
    });
	
	    $("#submit").click(function(event) {
	        event.preventDefault();
	        $.ajax({
	            url: '/TaskManager/UpdateProjectSettings',
	            type:'POST',
	            data:{"project":project_id, "action":"updateDescription", "description":$('#description').val()},
	            success: function(ajaxData) {
	                if (ajaxData == "Description Successfully Updated."){
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
<h3>Project Description</h3>
<input type="text" id="description" />
<input type="submit" id="submit" value="Update" />
</body>
</html>