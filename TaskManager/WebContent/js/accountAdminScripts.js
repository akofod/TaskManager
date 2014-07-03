function getHTML(strAction) 
{
	var currEmail=$('#email_orig').val();
	var newEmail=$('#email').val(); 
	var nickname=$('#nickname').val();
	var first_name=$('#firstname').val();
	var last_name=$('#lastname').val();
	var newPass=$('#password').val();
	var newPassConfirm=$('#confirm').val();
	var oldPass=$('#current').val(); 
	var html = '';
	$.ajax({
		async: false,
		cache: false,
		url: '/TaskManager/UpdateUserAjax',
		type:'POST',
		data:{"new_user_id":newEmail,"reqAction":strAction,"nickname":nickname,"firstname":first_name,
			  "lastname":last_name,"newPass":newPass,"newPassConfirm":newPassConfirm,"oldPass":oldPass},
		success: function(ajaxData) {
			html = ajaxData;
			},
		error: function(request, status, error) {
			alert('Error');
		}
	});
	$('#outputAuth').html(html);
}

 $('#submit').on('click', function() {
	 getHTML('updateUserDetails');
	}); 