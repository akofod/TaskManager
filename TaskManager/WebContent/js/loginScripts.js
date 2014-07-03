function getHTML(strAction) 
{
	var user=$('#username').val();
	var pwd=$('#password').val();
	var html = '';
	var remem = 'false';
	if (document.getElementById('remember').checked) 
	{
		remem='true';
	}
	$.ajax({
		async: false,
		cache: false,
		url: 'LoginLogoutAjax',
		type:'POST',
		data:{"user":user,"password":pwd,"reqAction":strAction,"rememberMe":remem},
		success: function(ajaxData) {
			if (ajaxData == "success"){
				window.top.location.href = 'admin/userHome.jsp';
			}	
			else {
				html = ajaxData;
			}
		},
		error: function(request, status, error) {
			alert('Error');
		}
	});
	$('#outputAuth').html(html);
}
