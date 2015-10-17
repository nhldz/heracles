<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link href="resources/css/metisMenu.css" rel="stylesheet">
<link href="resources/css/timeline.css" rel="stylesheet">
<link href="resources/css/sb-admin-2.css" rel="stylesheet">
<link rel="stylesheet"
	href="resources/css/dataTables/dataTables.bootstrap.min.css">
<link href="resources/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<title>Heracles</title>
</head>
<body>
	<div id="wrapper">
		<c:choose>
			<c:when test="${menu == 'client'}">
				<%@include file="client/menu.jsp"%>
			</c:when>
			<c:otherwise>
				<%@include file="trainer/menu.jsp"%>
			</c:otherwise>
		</c:choose>
		<div id="page-wrapper"></div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/js/jquery-1.11.3.min.js"></script>
	<script src="resources/js/jquery.loadJSON.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/sb-admin-2.js"></script>
	<script src="resources/js/metisMenu.min.js"></script>
	<script src="resources/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables/dataTables.bootstrap.min.js"></script>
	<script>
	
		function toogle(idElement) {
			var elem = $('#' + idElement);
			if (elem.is(":visible")) {
				elem.attr('style', 'display:none !important');
			} else {
				elem.attr('style', 'display:block !important');
			}
		}
		
		function toogleButtons(){
			var bool = !$('#btnAdd').is(":disabled");
			$('#btnAdd').attr('disabled', bool);
			$('#btnDelete').attr('disabled', bool);
			$('#btnEdit').attr('disabled', bool);
		};
		
		$(document).ready(function() {
			$("#trainers").click(function() {
				$("#page-wrapper").load("/trainers");
				return false;
			});

			$("#excercises").click(function() {
				$("#page-wrapper").load("/excercises");
				return false;
			});

			$("#routines").click(function() {
				$("#page-wrapper").load("/routines");
				return false;
			});

			$("#clients").click(function() {
				$("#page-wrapper").load("/clients");
				return false;
			});
		});
	</script>
</body>
</html>