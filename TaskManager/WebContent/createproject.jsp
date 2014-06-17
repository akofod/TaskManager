<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create a New Project</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TaskManager/css/autocomplete.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery.ui.autocomplete.html.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $(function() {
    	$("#category").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "createProject.do",
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
    $(function() {
    	$( "#deadline" ).datepicker();
    });
    $('#cancel').on('click', function() {
        window.location.replace("index.jsp");
    });
});
</script>
</head>
<body>
    <h2>Create a New Project</h2>
    <div id="formWrap">
        <form id="newProjectForm" method="POST" action="createProject.do">
            <div class="ui-widget">
                <label id="projectNameLabel" for="projectName">Project Name:</label>
                <input id="projectName" type="text" name="projectName" />
            </div><br/>
            <div class="ui-widget">
                <label id="categoryLabel" for="category">Category:</label>
                <input id="category" type="text" name="category" />
            </div><br/>
            <div class="ui-widget">
                <label id="deadlineLabel" for="deadline">Project Due Date:</label>
                <input id="deadline" type="text" name="deadline" />
            </div><br/>
            <div id="buttons">
                <input type="submit" style="width: 100px" id="submit" value="Submit">
                <input type="reset" style="width: 100px" id="reset" value="Reset"/>
                <input type="button" style="width: 100px" id="cancel" value="Cancel"/>
            </div>
            <div id="error_text">${error}</div>
        </form>
    </div>
</body>
</html>
