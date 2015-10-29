<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link href="resources/css/dataTables/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
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
		<%@include file="menu.jsp"%>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Tables</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">DataTables Advanced Tables</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<c:if test="${not empty objects}">
									<table id="example"
										class="table table-striped table-bordered table-hover"
										cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>Name</th>
												<th>Position</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="o" items="${objects}">
												<tr>
													<td>${o.name}</td>
													<td>${o.description}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/js/jquery-1.11.3.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/sb-admin-2.js"></script>
	<script src="resources/js/metisMenu.min.js"></script>
	<script src="resources/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables/dataTables.bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			var t = $('#example').DataTable();
			// 					var counter = 1;

			// 					$('#addRow').on(
			// 							'click',
			// 							function() {
			// 								t.row
			// 										.add(
			// 												[ counter + '.1',
			// 														counter + '.2',
			// 														counter + '.3',
			// 														counter + '.4',
			// 														counter + '.5',
			// 														counter + '.6' ])
			// 										.draw();
			// 								counter++;
			// 							});

			// 					// Automatically add a first row of data
			// 					$('#addRow').click();
		});
	</script>
</body>
</html>