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
				<div class="panel-heading">Clientes</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table id="clientsTable"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="no-sort id"></th>
									<th>Habilitado</th>
									<th>Apellido y Nombre</th>
									<th>Usuario</th>
									<th>Email</th>
									<th>Tel&eacute;fono</th>
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
	<form id="clientForm" action="/client"
		data-toggle="validator" autocomplete="off">
		<div id="myContent" class="row hidden">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<input type="hidden" name="id" />
						</div>
						<div class="form-group">
							<label for="name">Nombre:</label> <input type="text" name="name"
								class="form-control" required />
						</div>
						<div class="form-group">
						<label for="surname">Apellido:</label> <input type="text"
							name="surname" class="form-control" required />
						</div>
						<div class="form-group">
							<label for="birthday">Fecha Nac.:</label> <input id="birthday"
								type="text" name="birthday" class="form-control" required />
						</div>
						<div class="form-group">
							<label for="gender">Sexo:</label> <select name="gender"  class="form-control" required/>
								<option value="0">Seleccione..</option>
								<c:forEach items="${genders}" var="gender">
									<option value="${gender}">${gender.type}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="phone">Tel&eacute;fono:</label> <input
								type="text" name="phone" class="form-control" />
						</div>
						<div class="form-group">
							<label for="email">E-mail:</label> <input id="email" type="email"
								name="email" class="form-control" />
						</div>
						<div class="form-group">
							<label for="userName">Usuario:</label> <input id="userName" type="text" name="userName"
								class="form-control" required />
						</div>
						<div id="pass" class="form-group">
						<label for="password">Contrase&ntilde;a:</label> <input type="password"
							id="passInput" name="password" class="form-control" required/>
						</div>
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default"
							onclick="clearForm(this.form); toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
</div>
	<%@ include file="../templates/includes/common_foot.jsp"%>
	<script src="resources/js/client/client.js"></script>
