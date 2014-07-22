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
		<c:set var="project_id">
			<c:url value="addTask.jsp">
				<c:param name="project_id" value="${project.project_id}"/>
			</c:url>
		</c:set>
	<a href="${project_id}">Add Task To Project</a>	
	</div>
</div>
<script>
	
$(function() {
	$(".column").sortable({
		connectWith:".column",
		stop:function(event, ui){
			var idChange = ui.item.context.id;	
			$('.task').each(function(index, value){
				var taskId = $(this).attr('id');
				var taskStatus = $(this).parent().attr('id');
				if (idChange == taskId){
					updateTasks(taskId, taskStatus);
				}	
	    	});
		}
	});
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

function updateTasks(task, status) {
	$.ajax({
		url: '/TaskManager/UserTasksAjax',
		type:'POST',
		data:{"task":task,"status":status},
		success: function(ajaxData) {
			if (ajaxData == "Status Update Successful."){
				location.reload();
			}
		},
		error: function(request, status, error) {
			alert('Error');
		}
	});
}

</script>
</body>
</html>