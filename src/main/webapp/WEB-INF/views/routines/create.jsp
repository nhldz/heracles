<%@ include file="../templates/includes/common_head.jsp"%>
<div id="wrapper">
	<%@ include file="../trainer/menu.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">Alta de Rutina</div>
				<div class="panel-body">
					<ul class="nav nav-tabs" role="tablist">
						<li id="routuineList" role="presentation" class="active"><a
							href="#routine" aria-controls="home" role="tab" data-toggle="tab">Rutina</a>
						</li>
						<li id="activityList" role="presentation"><a href="#activity"
							aria-controls="profile" role="tab" data-toggle="tab">Actividades</a>
						</li>
						<li id="exerciseList" role="presentation" class="disabled">
							<a href="#exercise" aria-controls="profile" role="tab">Detalle
								Actividad</a>
						</li>
					</ul>
					<div class="tab-content" style="min-height: 700px">
						<div role="tabpanel" class="tab-pane active" id="routine">
							<div class="row">
								<div class="col-xs-offset-2 col-xs-8">
									<div class="panel-body">
										<form id="routineForm" method="POST" data-toggle="validator" action="/routines">
											<div class="form-group">
												<label for="name">Nombre Rutina:</label>
												<span id="errorRoutineName" class="label label-danger hidden">Ingrese un nombre</span> 
												<input id="routineName" type="text"	name="name" class="form-control col-xs-4" required/>
											</div>	
											<input id="clientid" type="hidden" name="clientid"/>
										</form>
										<div class="form-group">
											<label for="clientid">Alumno:</label>
											<span id="errorRoutine" class="label label-danger hidden">Debe seleccionar un alumno</span>
											<div class="panel panel-default">
												<div class="panel-body">
													<div class="dataTable_wrapper">
														<table id="clientsTable"
															class="table table-striped table-bordered table-hover"
															cellspacing="0" width="100%">
															<thead>
																<tr>
																	<th class="no-sort id"></th>
																	<th>Apellido y Nombre</th>
																	<th>Usuario</th>
																	<th>Email</th>
																	<th>Tel&eacute;fono</th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="activity">
							<div id="groupActivity" class="form-group">
								<div class="col-md-offset-2 col-md-8">
									<span id="errorActivity" style="margin-left:15px;" class="label label-danger hidden">Debe crear al menos una actividad para la rutina</span>
									<div class="panel-body">
										<div class="dataTable_wrapper">
											<table id="activityTable"
												class="table table-striped table-bordered table-hover"
												cellspacing="0" width="100%">
												<thead>
													<tr>
														<th class="no-sort id"></th>
														<th>Nombre</th>
														<th>Descripcion</th>
														<th>Cant. de Ejercicios</th>
													</tr>
												</thead>
												<tbody></tbody>
											</table>
										</div>
										<div id="btnsTable">
											<button type="button" class="btn btn-success" id="btnActivityAdd">Agregar</button>
											<button type="button" class="btn btn-default" id="btnActivityEdit">Editar</button>
											<button type="button" class="btn btn-warning" id="btnActivityDelete">Borrar</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="exercise">
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="panel-body">
										<form id="activityForm" method="POST" data-toggle="validator" action="/routines/activity">
											<div class="form-group">
												<input type="hidden" name="id" />
											</div>
											<div class="form-group">
												<label for="activityName">Nombre Actividad:</label>
												<span id="errorActivityName" class="label label-danger hidden">Ingrese un nombre</span> 
												<input value="" type="text" id="activityName" name="name" class="form-control col-xs-4"/>
											</div>
											<div class="form-group">
												<label for="description">Descripci&oacute;n:</label>
												<span id="errorActivityDescription" class="label label-danger hidden">Ingrese una descripcion</span>
												<textarea  class="form-control" rows="5" id="activityDescription" name="description"></textarea>
											</div>
										</form>
										<div class="form-group">
											<label>Ejercicios:</label>
											<div class="panel panel-default">						
												<div class="panel-body">
													<span id="errorExercise" class="label label-danger hidden">Debe crear al menos un ejercicio para la actividad</span>
													<div class="dataTable_wrapper">
														<table id="exerciseTable" class="table table-striped table-bordered table-hover"
															cellspacing="0" width="100%">
															<thead>
																<tr>
																	<th class="no-sort id"></th>
																	<th>Ejercicio</th>
																	<th>Series</th>
																	<th>Repeticiones</th>
																	<th>Peso</th>
																	<th>Descanso</th>
																</tr>
															</thead>
															<tbody></tbody>
														</table>
													</div>
													<div id="btnsTable">
														<button type="button" class="btn btn-success" id="btnExerciseAdd">Agregar</button>
														<button type="button" class="btn btn-default" id="btnExerciseEdit">Editar</button>
														<button type="button" class="btn btn-warning" id="btnExerciseDelete">Borrar</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-footer">
					<div id="btnsForm" style="text-align: center;">
						<button type="button" id="saveActivity" class="btn btn-success hidden">Aceptar</button>
						<button type="button" id="saveRoutine" class="btn btn-success">Aceptar</button>
						<button type="button" id="cancelActivity" class="btn btn-danger hidden" >Cancelar</button>
						<a href="/routines" id="cancelRoutine" class="btn btn-danger" >Cancelar</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div id="exerciseModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Agregar Ejercicio</h4>
			</div>
			<form id="exerciseForm" action="/routines/exercise" data-toggle="validator">
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12">
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="form-group">
										<input type="hidden" name="id" />
									</div>
									<div class="form-group">
										<label for="exerciseId">Ejercicio:</label> 
										<select id="exerciseId" name="exerciseId" class="form-control" required>
											<option value="">Seleccione..</option>
											<c:forEach items="${exercises}" var="exercise">
												<option value="${exercise.id}">${exercise.name}</option>
											</c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="sets">Series:</label>
										<input type="number" id="sets" name="sets" class="form-control" required />
									</div>
									<div class="form-group">
										<label for="reps">Repeticiones:</label> 
										<input type="number" id="reps" name="reps" class="form-control" required />
									</div>
									<div class="form-group">
										<label for="weigth">Peso:</label> 
										<input type="number" id="weigth" name="weigth" class="form-control" required />
									</div>
									<div class="form-group">
										<label for="rest">Descanzo:</label> 
										<input type="number" id="rest" name="rest" class="form-control" required />
									</div>																
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">Aceptar</button>
					<button type="button" onclick="clearForm(this.form);" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="../templates/includes/common_foot.jsp"%>
<script src="${contextPath}/resources/js/routines/create.js"></script>
<script src="${contextPath}/resources/js/routines/routines.js"></script>
