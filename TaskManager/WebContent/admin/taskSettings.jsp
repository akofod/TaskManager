<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/projectTask.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<link rel="stylesheet" href="/TaskManager/css/mainStyle.css">
<link rel="stylesheet" href="/TaskManager/css/mainTemplate.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>Task Details</title>
</head>
<body>
<h3>Task Details</h3>
<body>
		<form id="addTask" class="form">
			<label for="desc">Description:</label>
		    <input type="text" style="width: 300px" id="task-desc" value="${task.description}">
		    <br/>
		    <label for="prior">Priority:</label>
		    <select id="task-prior">
				<option value="Low">Low</option>
				<option value="Medium">Medium</option>
				<option value="High">High</option>
			</select>
			<br/>
			<label for="stat">Status:</label>
		    <select id="task-stat">
				<option value="Open">Open</option>
				<option value="In Process">In Process</option>
				<option value="Completed">Completed</option>
			</select>
			<br/>
			<label for="time-est">Time Estimate (Hours):</label>
            <input type="text" style="width: 100px" id="task-timeest" value="${task.time_estimate}">
            <br/>
            <label for="time-comp">Time Completed (Hours):</label>
            <input type="text" style="width: 100px" id="task-timecomp" value="${task.time_completed}">
            <br/>
			<label for="duedate">Task Due Date:</label>
            <input id="duedate" type="text" name="duedate" value="${task.due_date}"/>
            <br/>
            <input type="hidden" id="currProj" value="${task.project_id}">
            <input type="hidden" id="taskID" value="${task.task_id}">
		    <input type="button" style="width: 150px" id="addTaskB" value="Update Task">
	    </form>		

<script>

$(function() {
	$("#duedate").datepicker();
});

$('#addTaskB').click(function() {  
	
	var taskId=$('#taskID').val();
	var desc=$('#task-desc').val();
	var priority=$('#task-prior').val();
	var status=$('#task-stat').val();
	var estimate=$('#task-timeest').val();
	var completed=$('#task-timecomp').val();
	var due =$('#duedate').val();
	var project = $('#currProj').val();
	$.ajax({
		url: '/TaskManager/UpdateTaskAjax',
		type:'POST',
		data:{"task_id":taskId, "desc":desc,"priority":priority,"status":status,"estimate":estimate,"completed":completed,"due":due, "project":project},
		success: function(ajaxData) {
			alert (ajaxData);
			if (ajaxData == "Task has been updated."){
				location.reload();
			}
		},
		error: function(request, status, error) {
			alert('Error');
		}
	}); 
});

</script>
</body>
</body>
</html>