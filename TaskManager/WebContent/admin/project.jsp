<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/projectTask.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>Project Tasks</title>
</head>
<body>
<div id="wrapper">
	<div id="content-wrapperP">
	
		<form id="addTask" class="form">
			<label for="desc">Description:</label>
		    <input type="text" style="width: 300px" id="task-desc">
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
            <input type="text" style="width: 100px" id="task-timeest" value=0>
            <br/>
            <label for="time-comp">Time Completed (Hours):</label>
            <input type="text" style="width: 100px" id="task-timecomp" value=0>
            <br/>
			<label for="duedate">Task Due Date:</label>
            <input id="duedate" type="text" name="duedate" />
            <br/>
		    <input type="button" style="width: 150px" id="addTaskB" value="Add Task To Project">
		    <input type="hidden" id="currProj" value="${project.project_id}">
	    </form>
	    
	    <br/>
		<div id="Open" class="column">
			Open Tasks
			<c:forEach var="tsk" items="${project.projectTasks}">
				<c:choose>
					<c:when test="${tsk.status == 'Open'}">
						<div class="task" id="${tsk.task_id}">
							<div class="task-id"><b>ID:</b> ${tsk.task_id} &nbsp; <button id="${tsk.task_id}" onClick="delete_click(this.id)">Delete Task From Project</button></div>
							<div class="task-description"><b>Description:</b> ${tsk.description}</div>
							<div class="task-priority"><b>Priority:</b> ${tsk.priority}</div>
							<div class="task-status"><b>Status:</b> ${tsk.status}</div>
							<div class="task-time-estimate"><b>Time Estimate:</b> ${tsk.time_estimate}</div>
							<div class="task-time-completed"><b>Time Completed:</b> ${tsk.time_completed}</div>
							<div class="task-due-date"><b>Due Date:</b> ${tsk.due_date}</div>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>	
		</div>
		<div id="InProcess" class="column">
			In Process Tasks
			<c:forEach var="tsk" items="${project.projectTasks}">
				<c:choose>
					<c:when test="${tsk.status == 'In Process'}">
						<div class="task" id="${tsk.task_id}">
							<div class="task-id"><b>ID:</b> ${tsk.task_id} &nbsp; <button id="${tsk.task_id}" onClick="delete_click(this.id)">Delete Task From Project</button></div>
							<div class="task-description"><b>Description:</b> ${tsk.description}</div>
							<div class="task-priority"><b>Priority:</b> ${tsk.priority}</div>
							<div class="task-status"><b>Status:</b> ${tsk.status}</div>
							<div class="task-time-estimate"><b>Time Estimate:</b> ${tsk.time_estimate}</div>
							<div class="task-time-completed"><b>Time Completed:</b> ${tsk.time_completed}</div>
							<div class="task-due-date"><b>Due Date:</b> ${tsk.due_date}</div>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>
		<div id="Completed" class="column">
			Completed Tasks
			<c:forEach var="tsk" items="${project.projectTasks}">
				<c:choose>
					<c:when test="${tsk.status == 'Completed'}">
						<div class="task" id="${tsk.task_id}">
							<div class="task-id"><b>ID:</b> ${tsk.task_id} &nbsp; <button id="${tsk.task_id}" onClick="delete_click(this.id)">Delete Task From Project</button></div>
							<div class="task-description"><b>Description:</b> ${tsk.description}</div>
							<div class="task-priority"><b>Priority:</b> ${tsk.priority}</div>
							<div class="task-status"><b>Status:</b> ${tsk.status}</div>
							<div class="task-time-estimate"><b>Time Estimate:</b> ${tsk.time_estimate}</div>
							<div class="task-time-completed"><b>Time Completed:</b> ${tsk.time_completed}</div>
							<div class="task-due-date"><b>Due Date:</b> ${tsk.due_date}</div>
						</div>
					</c:when>
				</c:choose>
			</c:forEach>
		</div>		
	</div>
</div>

<script>
$(function() {
	$(".column").sortable({connectWith:".column"});
});

function delete_click(clicked_id) {
	$.ajax({
		url: '/TaskManager/DeleteTaskAjax',
		type:'POST',
		data:{"task":clicked_id},
		success: function(ajaxData) {
			alert (ajaxData);
			if (ajaxData == "Task deleted."){
				location.reload();
			}
		},
		error: function(request, status, error) {
			alert('Error');
		}
	}); 
}
$(function() {
	$("#duedate").datepicker();
});

$('#addTaskB').click(function() {  
	
	var desc=$('#task-desc').val();
	var priority=$('#task-prior').val();
	var status=$('#task-stat').val();
	var estimate=$('#task-timeest').val();
	var completed=$('#task-timecomp').val();
	var due =$('#duedate').val();
	var project = $('#currProj').val();
	$.ajax({
		url: '/TaskManager/CreateNewTaskAjax',
		type:'POST',
		data:{"desc":desc,"priority":priority,"status":status,"estimate":estimate,"completed":completed,"due":due, "project":project},
		success: function(ajaxData) {
			alert (ajaxData);
			if (ajaxData == "Task has been added."){
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
</html>