$(document).ready(function() {
	$(".newTask").colorbox({iframe: true, innerWidth:400, innerHeight:250});
});

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