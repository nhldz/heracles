<%@ include file="../templates/includes/common_head.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="../trainer/menu.jsp" %>
	<div id="page-wrapper">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">Alta de Rutina</div>
				<div class="panel-body">
					<form id="routineForm" method="POST" data-toggle="validator">
						<div id="myContent" class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
											<input type="hidden" name="id" /> <input type="hidden" name="enabled" value="true" />
										</div>
										<div class="form-group">
											<label for="name">Nombre:</label> <input name="name" class="form-control" required />
										</div>
										<div class="form-group">
											<label for="alumno">Alumno:</label> <input name="alumno" class="form-control" required />
										</div>
										<div class="form-group">
											<div class="panel panel-default">
												<div class="panel-heading">Actividades</div>
												<div class="panel-body">
													<div class="dataTable_wrapper">
														<table id="activityTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
															<thead>
																<tr>
																	<th class="no-sort id"></th>
																	<th>Nombre</th>
																	<th>Descripcion</th>
																	<th>Cant. de Ejercicios</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
													<div id="btnsTable">
														<a class="btn btn-success" href="${contextPath}/routines/createActivity" id="btnAdd">Agregar</a>
														<button type="button" class="btn btn-default" id="btnEdit">Editar</button>
														<button type="button" class="btn btn-warning" id="btnDelete">Borrar</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="btnsForm" style="text-align: center;">
							<button type="submit" class="btn btn-success">Aceptar</button>
							<button type="button" class="btn btn-danger" >Cancelar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="../templates/includes/common_foot.jsp"%>
<script src="${contextPath}/resources/js/routines/create.js"></script>

