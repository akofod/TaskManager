<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/registrationStyle.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
<title>New User Registration</title>
<script type="text/javascript">
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
		if (!valid) {
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
        if (!valid) {
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
        if (!valid) {
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
        if (!valid) {
            error_element.removeClass("error").addClass("error_show");
        }
        else {
            error_element.removeClass("error_show").addClass("error");
        }
    });
	$("#submit").click(function(event){
	    var error_free=true;
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


});

</script>
</head>
<body>
    <h2>New User Registration</h2>
    <form id="registrationForm" method="POST" action="register.do">
        <div>
            <label for="email">Email:</label>
            <input id="email" type="email" style="width: 150px" name="email" />
            <span class="error">A valid email address is required</span>
        </div>
        <div>
            <label for="nickname">Nickname:</label>
            <input id="nickname" type="text" style="width: 150px" name="nickname" />
            <span class="error">Nickname must be 6-15 characters, numbers and letters only</span>
        </div>
        <div>
            <label for="firstname">First Name:</label>
            <input id="firstname" type="text" style="width: 150px" name="firstname" />
            <span class="error"></span>
        </div>
        <div>
            <label for="lastname">Last Name:</label>
            <input id="lastname" type="text" style="width: 150px" name="lastname" />
            <span class="error"></span>
        </div>
        <div>
            <label for="password">Password:</label>
            <input autocomplete="off" id="password" type="password" style="width: 150px" name="password" />
            <span class="error">Password must be at least 8 characters, with at least one capital letter, one lowercase letter, and one number</span>
        </div>
        <div>
            <label for="confirm">Confirm Password:</label>
            <input autocomplete="off" id="confirm" type="password" style="width: 150px" name="confirm" />
            <span class="error">Does not match password</span>
        </div>
        <div id="buttons">
            <input type="submit" style="width: 100px" id="submit" value="Submit">
            <input type="reset" style="width: 100px" id="reset" value="Reset"/>
            <input type="button" style="width: 100px" id="cancel" value="Cancel"/>
        </div>
        <div id="error_text">${error}</div>
    </form>
    
</body>
</html>