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
    $('#submit').click(function(event){
	    var error_free=true;
	    var projectName=$('#projectName').val();
	    var category=$('#category').val();
	    var deadline=$('#deadline').val();
	    if (projectName==="" || category==="" || deadline==="") {
	    	error_free=false;
	    }
	    if ($('#projectName').hasClass("invalid")) {
	    	error_free=false;
	    }
	    if ($('#category').hasClass("invalid")) {
            error_free=false;
        }
	    if ($('#deadline').hasClass("invalid")) {
            error_free=false;
        }
	    if (!error_free){
	        event.preventDefault();
	    }
	    getHTML("createProject");
	});
});

function getHTML(strAction) 
{
	var projectName=$('#projectName').val();
	var category=$('#category').val();
	var deadline=$('#deadline').val();
	var html = '';
	
	$.ajax({
		async: false,
		cache: false,
		url: 'createProject.do',
		type:'POST',
		data:{"projectName":projectName,"category":category,"deadline":deadline},
		success: function(ajaxData) {
			if (ajaxData == "success"){
				window.top.location.href = 'userHome.jsp';
			}	
			else {
				html = ajaxData;
			}
		},
		error: function(request, status, error) {
			alert(html);
		}
	});
}
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
            </div>
            <div id="error_text">${error}</div>
        </form>
    </div>
</body>
</html>
