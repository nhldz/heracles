<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="wrapper">
	<jsp:include page="../trainer/menu.jsp" />
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Ejercicios</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table id="exercisesTable"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="no-sort id"></th>
									<th>Nombre</th>
									<th>Tipo</th>
									<th>Descripci&oacute;n</th>
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
			<button type="button" class="btn btn-success" id="btnAdd">Agregar</button>
			<button type="button" class="btn btn-primary" id="btnEdit">Editar</button>
			<button type="button" class="btn btn-danger" id="btnDelete">Borrar</button>
		</div>
	</div>
	<br>
	<form id="exerciseForm" action="/exercises" method="POST" data-toggle="validator">
		<div id="myContent" class="row hidden">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<input type="hidden" name="id" />
							<input type="hidden" name="enabled" value="true"/>
						</div>
						<div class="form-group">
							<label for="name">Nombre:</label> 
							<input name="name" type="text" class="form-control" required >
						</div>
						<div class="form-group">
							<label for="type">Tipo:</label> 
							<select name="type" required>
								<option value="">Seleccione..</option>
								<c:forEach items="${excercisesTypes}" var="type">
									<option value="${type}">${type.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="equipment">Herramienta:</label> 
							<select	name="equipment" required>
								<option value="">Seleccione..</option>
								<c:forEach items="${equipments}" var="equipment">
									<option value="${equipment}">${equipment.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="bodyParts">Partes Trabajadas:</label> 
							<select	name="bodyParts" multiple="multiple" required>
								<c:forEach items="${bodyParts}" var="bodyPart">
									<option value="${bodyPart}">${bodyPart.name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="description">Descripci&oacute;n:</label>
							<input type="text" name="description" class="form-control" rows="3" required/>
						</div>
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default" onclick="toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script src="resources/js/exercises.js"></script>

</body>
</html>