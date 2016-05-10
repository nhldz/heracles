<%@ include file="../templates/includes/common_head.jsp"%>
<div id="wrapper">
	<%@ include file="../trainer/menu.jsp" %>
	<div id="page-wrapper">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">Alta de Rutina</div>
					<div class="panel-body">
						<ul class="nav nav-tabs" role="tablist">
    						<li role="presentation" class="active"><a href="#routine" aria-controls="home" role="tab" data-toggle="tab">Rutina</a></li>
							<li role="presentation"><a href="#activity" aria-controls="profile" role="tab" data-toggle="tab">Actividades</a></li>
						</ul>
						
						<form id="routineForm" method="POST" data-toggle="validator" action="/routines">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="routine">
								<div id="myContent" class="row">
									<div class="col-md-offset-2 col-md-8">
										<div class="panel-body">
											<div class="form-group">
												<input type="hidden" name="id"/>
											</div>
											<div class="form-group">
												<label for="name">Nombre:</label> <input type="text" name="name" class="form-control" required/>
											</div>
											<div class="form-group">
											  <label for="alumno">Alumno:</label>
										      <select id="clients" name="clientid" class="form-control" required>
												<option value="">Seleccione..</option>
												<c:forEach items="${clients}" var="client">
													<option value="${client.id}">${client.surname}, ${client.name}</option>
												</c:forEach>
											  </select>
											</div>
										</div>
									</div>
								</div>
							</div>
						  	<div role="tabpanel" class="tab-pane" id="activity">
						  		<div id="groupActivity" class="form-group">
									<div class="col-md-offset-2 col-md-8">
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
						<div id="btnsForm" style="text-align: center;">
							<button type="submit" id="save" class="btn btn-success">Aceptar</button>
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

