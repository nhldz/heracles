<%@ include file="../templates/includes/common_head.jsp"%>
<div id="wrapper">
	<%@ include file="../trainer/menu.jsp" %>
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
							<table id="routineTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="no-sort id"></th>
										<th>Nombre</th>
										<th>Alumno</th>
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
				<a class="btn btn-success" href="routines/create" id="btnAdd">Agregar</a>
			</div>
		</div>
		<br>
	</div>
</div>
<%@ include file="../templates/includes/common_foot.jsp"%>
<script src="resources/js/routines/routines.js"></script>