<!DOCTYPE HTML><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Home Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/mainTemplate.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script> 
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
$(function() {
	$( ".column" ).sortable({connectWith: ".column", placeholder:"ui-state-highlight"});
	$( ".item" ).find( ".item-assign" ).end().find( ".item-class" ).end().find( ".item-footer").end().find(".item-points").end().find(".item-dueDate");
	$( ".item-header .ui-icon" ).click(function() {$( this ).parents( ".item" ).find( ".item-content" ).toggle();});
	$( ".column" ).disableSelection();
});
</script>
</head>
<body>
<div id="wrapper">
	<header>
		<div>
			<select id="dueDate">
				<option value="">&nbsp;</option>
				<option value="0">Due This Week</option>
				<option value="1">Due Next Week</option>
				<option value="2">All</option>
			</select>
			<select id="class">
				<option value="">&nbsp;</option>
				<option value="0">COMP 394</option>
				<option value="1">COMP 325</option>
				<option value="2">ISEC 300</option>
			</select>
			<select id="type">
				<option value="">&nbsp;</option>
				<option value="0">Paper</option>
				<option value="1">Programming</option>
				<option value="2">Q & A</option>
				<option value="3">Exam</option>
			</select>
		</div>
		<button class="add">ADD</button>
	</header>
	<div id="content-wrapper">
		<div id="ToDo" class="column">
			<div class="item" id="one">
				<div class="item-assign">11-1 Program <button class="delete">X</button></div>
				<div class="item-class">COMP 311</div>
				<div class="item-dueDate">8/07/2013</div>
				<div class="item-points">--/50</div>
				<div class="item-footer">Details . . .</div>
			</div>
			<div class="item" id="two">
				<div class="item-assign">13-1 Lorem Ipsum <button class="delete">X</button></div>
				<div class="item-class">COMP 394</div>
				<div class="item-dueDate">8/08/2013</div>
				<div class="item-points">--/10</div>
				<div class="item-footer">Details . . .</div>
			</div>
		</div>
		<div id="Done" class="column">
		</div>
		<div id="Graded" class="column">
			<div class="item" id="three">
				<div class="item-assign">12-1 New Assignment <button class="delete">X</button></div>
				<div class="item-class">ISEC 300</div>
				<div class="item-dueDate">8/16/2013</div>
				<div class="item-points">--/100</div>
				<div class="item-footer">Details . . .</div>
			</div>
		</div>		
	</div>
	<footer>
	</footer>
</div>
<script>
$(".column").on('click', '.delete', function () {
    $(this).closest('.item').remove();
});

$(".add").click(function () {
	$newdiv1 = $("<div class='item' id='four'>");
	$newdiv2 = $newdiv1.append($("<div class='item-assign'>19-1 New Assignment <button class='delete'>X</button></div>"));
	$newdiv3 = $newdiv2.append($("<div class='item-class'>ISEC 300</div>"));
	$newdiv4 = $newdiv3.append($("<div class='item-dueDate'>8/24/2014</div>"));
	$newdiv5 = $newdiv4.append($("<div class='item-points'>--/200</div>"));
	$newdiv6 = $newdiv5.append($("<div class='item-footer'>Details . . . </div>"));
	$("#ToDo").prepend($newdiv6);
});

$(document).ready(function() {
		$.ajaxSetup({
			url: 'PortletAjax'
		});
	});
</script>
</body>
</html>