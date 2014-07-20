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
    
    $('#submit').click(function(){
        getHTML();
    });
});

function getHTML() 
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
				alert(html);
			}
		},
		error: function(request, status, error) {
			alert(html);
		}
	});
}