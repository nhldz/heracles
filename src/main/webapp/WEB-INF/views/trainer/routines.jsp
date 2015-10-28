<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Rutinas</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<c:if test="${not empty objects}">
							<table id="routinesTable"
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
	<script src="resources/js/dataTables/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables/dataTables.bootstrap.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#routinesTable').DataTable();
		});
	</script>
</body>
</html>