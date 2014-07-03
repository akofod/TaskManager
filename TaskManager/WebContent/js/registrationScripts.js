$(document).ready(function() {
	$('#email').on('input', function() {
	    var input=$(this);
	    var re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
	    var is_email=re.test(input.val());
	    if(is_email){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
	});
	$('#nickname').on('input', function() {
	    var input=$(this);
	    var re=/^[a-zA-Z0-9]{6,15}$/;
	    var is_name=re.test(input.val());
	    if(is_name){input.removeClass("invalid").addClass("valid");}
	    else{input.removeClass("valid").addClass("invalid");}
	});
	$('#password').on('input', function() {
		var input=$(this);
		var re=/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{8,}$/;
		var is_password=re.test(input.val());
		if(is_password){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});
	$('#confirm').on('input', function() {
		var input=$(this);
		var text = input.val();
		var pass = $('#password').val();
		if(text == pass){input.removeClass("invalid").addClass("valid");}
		else{input.removeClass("valid").addClass("invalid");}
	});
	$('#email').on('blur', function() {
		var input=$(this);
		var valid=input.hasClass("valid");
		var error_element=$('span', $('#email').parent());
		if (!valid || input==="") {
			error_element.removeClass("error").addClass("error_show");
		}
		else {
			error_element.removeClass("error_show").addClass("error");
		}
	});
	$('#nickname').on('blur', function() {
        var input=$(this);
        var valid=input.hasClass("valid");
        var error_element=$('span', $('#nickname').parent());
        if (!valid || input==="") {
            error_element.removeClass("error").addClass("error_show");
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
    });
	$('#password').on('blur', function() {
        var input=$(this);
        var valid=input.hasClass("valid");
        var error_element=$('span', $('#password').parent());
        if (!valid || input==="") {
            error_element.removeClass("error").addClass("error_show");
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
    });
	$('#confirm').on('blur', function() {
        var input=$(this);
        var valid=input.hasClass("valid");
        var error_element=$('span', $('#confirm').parent());
        if (!valid || input==="") {
            error_element.removeClass("error").addClass("error_show");
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
    });
	$('#submit').click(function(event){
	    var error_free=true;
	    var email=$('#email').val();
	    var nickname=$('#nickname').val();
	    var password=$('#password').val();
	    if (email==="" || nickname==="" || password==="") {
	    	error_free=false;
	    }
	    if ($('#email').hasClass("invalid")) {
	    	error_free=false;
	    }
	    if ($('#nickname').hasClass("invalid")) {
            error_free=false;
        }
	    if ($('#password').hasClass("invalid")) {
            error_free=false;
        }
	    if ($('#confirm').hasClass("invalid")) {
            error_free=false;
        }
	    if (!error_free){
	        event.preventDefault();
	    }
	});
	$('#cancel').on('click', function() {
		
	});


});
