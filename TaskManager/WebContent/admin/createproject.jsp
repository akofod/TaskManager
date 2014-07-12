<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create a New Project</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TaskManager/css/autocomplete.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery.ui.autocomplete.html.js" type="text/javascript"></script>
<script src="/TaskManager/js/createProjectScripts.js" type="text/javascript"></script>
</head>
<body>
    <h3>Create a New Project</h3>
    <div id="formWrap">
        <center>
        <form id="newProjectForm">
        	<table>
        		<tr>
        			<td class="label"><label id="projectNameLabel" for="projectName">Project Name:</label></td>
        			<td><input id="projectName" type="text" name="projectName" /></td>
        		</tr>
        		<tr>
        			<td class="label"><label id="categoryLabel" for="category">Category:</label></td>
                	<td><input id="category" type="text" name="category" /></td>
        		</tr>
        		<tr>
        			<td class="label"><label id="deadlineLabel" for="deadline">Project Due Date:</label></td>
                	<td><input id="deadline" type="text" name="deadline" /></td>
        		</tr>
        		<tr>
        			<td colspan="2" class="buttons">
	        			<input type="button" style="width: 100px" id="submit" value="Submit">
	                	<input type="reset" style="width: 100px" id="reset" value="Reset"/>
        			</td>
        		</tr>
        	</table>
        </form>
        </center>
    </div>
</body>
</html>
