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
				alert(html);
			}
		},
		error: function(request, status, error) {
			alert(html);
		}
	});
}