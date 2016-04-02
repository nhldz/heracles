<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="wrapper">
	<jsp:include page="../client/menu.jsp" />
<div id="page-wrapper">
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
						<table id="routineTable"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="no-sort id"></th>
									<th>Nombre</th>
									<th>Entrenador</th>
									<th>Inicio</th>
									<th>Fin</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<button type="button" class="btn btn-success" id="btnView">Ver</button>
		</div>
	</div>
	<br>
</div>
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script src="${contextPath}/resources/js/client-routines.js"></script>
