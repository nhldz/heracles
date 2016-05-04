<%@ include file="../templates/includes/common_head.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="wrapper">
	<%@ include file="../trainer/menu.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="panel panel-danger">
				<div class="panel-heading">Alta de Actividad</div>
				<div class="panel-body">
					<form id="activityForm" method="POST" data-toggle="validator" action="/routines/addActivity">
						<div id="myContent" class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-body">
										<div class="form-group">
											<input type="hidden" name="id" /><input type="hidden" name="enabled" value="true" />
										</div>
										<div class="form-group">
											<label for="name">Nombre:</label><input name="name" class="form-control" required />
										</div>
										<div class="form-group">
											<label for="alumno">Descripcion:</label><input name="description" class="form-control" required />
										</div>
										<div class="form-group">
											<div class="panel panel-default">
												<div class="panel-heading">Ejercicio</div>
												<div id="panelExercise" class="panel-body">
													<div class="dataTable_wrapper">
														<table id="excerciseTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
															<thead>
																<tr>
																	<th class="no-sort id"></th>
																	<th>Tipo</th>
																	<th>Descripcion</th>
																	<th>Partes Trabajadas</th>
																	<th>Equipamiento</th>
																</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</div>
													<div id="btnsTable">
														<a class="btn btn-primary" href="#exerciseDialog" id="btnAdd" data-toggle="modal">Agregar</a>
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
							<button type="button" class="btn btn-danger">Cancelar</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="modal fade" id="exerciseDialog" tabindex="-1" role="dialog">
	<div class="modal-dialog  modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Configuraci&oacute;n de ejercicio</h4>
			</div>
			<form id="exerciseForm" method="POST" class="form-horizontal" action="/routines/addExcercise" >
				<div class="modal-body">
<!-- 					<div class="form-group"> -->
<!-- 						<label class="col-sm-4" for="exercise">Ejercicio:</label> -->
<!-- 						<div class="col-sm-8"> -->
<!-- 							<select name="exercise" required > -->
<!-- 								<option value="">Seleccione..</option> -->
<%-- 								<c:forEach items="${exercises}" var="exercise"> --%>
<%-- 									<option value="${exercise}">${exercise.name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="form-group">
						<label class="col-sm-4" for="sets">Cantidad:</label>
						<div class="col-sm-8">
							<input name="sets" type="number" class="form-control" id="sets" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4" for="reps">Repeticiones:</label>
						<div class="col-sm-8">
							<input name="reps" type="number" class="form-control" id="reps" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4" for="rest">Descanso:</label>
						<div class="col-sm-8">
							<input name="rest" type="number" class="form-control" id="rest" required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4" for="weigth">Peso:</label>
						<div class="col-sm-8">
							<input name="weigth" type="number" class="form-control" id="weigth" required/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success" value="Aceptar">Aceptar</button>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
					<div id='response'></div>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="../templates/includes/common_foot.jsp"%>
<script src="../resources/js/activity/activity.js"></script>
