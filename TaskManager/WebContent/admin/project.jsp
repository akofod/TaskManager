<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/TaskManager/css/mainStyle.css">
<link rel="stylesheet" href="../css/colorbox.css" type="text/css" media="screen" />
<link rel="stylesheet" type="text/css" href="/TaskManager/css/projectTask.css">
<link rel="stylesheet" href="/TaskManager/css/jquery-ui-1.10.4.css">
<script src="/TaskManager/js/jquery1.11.js" type="text/javascript"></script>
<script src="/TaskManager/js/jquery-ui-1.10.4.min.js" type="text/javascript"></script>
<script src="/TaskManager/js/projectScripts.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery.colorbox-min.js"></script>
<title>Home</title>
</head>
<body>
<div class="contentWrapper">
	<div class="header">
		<div class="appName"><a href="/TaskManager/admin/userHome.jsp">TaskManager</a></div>
		<div class="userControl">Welcome ${user.user_id } // 
		<a href="accountDetails.jsp" class="accountAdmin">User Settings</a> // 
		<a href="/TaskManager/LoginLogoutAjax?reqAction=LogOut" >Logout</a></div>
	</div>
	<div class="links">
	<c:set var="project_id">
			<c:url value="addTask.jsp">
				<c:param name="project_id" value="${project.project_id}"/>
			</c:url>
		</c:set>
	<a href="${project_id}" class="newTask">New Task</a>	
	</div>
	<div class="content">
		<div id="Open" class="column">
			Open Tasks
			<c:forEach var="tsk" items="${project.projectTasks}">
				<c:choose>
					<c:when test="${tsk.status == 'Open'}">
						<div class="task" id="${tsk.task_id}">
							<c:set var="task_url">
								<c:url value="taskSettings.jsp">
									<c:param name="task_id" value="${tsk.task_id}"/>
								</c:url>
							</c:set>
							<div class="task-description"><a href="${task_url}">${tsk.description}</a></div>
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
							<c:set var="task_url">
								<c:url value="taskSettings.jsp">
									<c:param name="task_id" value="${tsk.task_id}"/>
								</c:url>
							</c:set>
							<div class="task-description"><a href="${task_url}">${tsk.description}</a></div>
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
							<c:set var="task_url">
								<c:url value="taskSettings.jsp">
									<c:param name="task_id" value="${tsk.task_id}"/>
								</c:url>
							</c:set>
							<div class="task-description"><a href="${task_url}">${tsk.description}</a></div>
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

</body>
</html>