<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
				<div class="panel-heading">Profesores</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table id="trainersTable"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th class="no-sort id"></th>
									<th>Habilitado</th>
									<th>Apellido y Nombre</th>
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
	<form id="trainerForm" action="saveTrainer" method="POST"
		data-toggle="validator">
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
								type="date" name="birthday" class="form-control" required />
						</div>
						<div class="form-group">
							<label for="gender">Sexo:</label> <select name="gender" required>
								<option value="">Seleccione..</option>
								<c:forEach items="${genders}" var="gender">
									<option value="${gender}">${gender.type}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="cellPhone">Tel&eacute;fono:</label> <input
								type="text" name="cellPhone" class="form-control" />
						</div>
						<div class="form-group">
							<label for="email">E-mail:</label> <input type="email"
								name="email" class="form-control" />
						</div>
						<div class="form-group">
						<label for="password">Contrase&ntilde;a:</label> <input type="password"
							name="password" class="form-control" required/>
						</div>	
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default"
							onclick="toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>

	<script>
		$(document).ready(function(){
			$( "#birthday" ).datepicker({
				changeMonth: true,
			    changeYear: true,
			    yearRange: "-100:+0",
			    dateFormat: 'dd/mm/yy'
			});
			 
			dataTable = $('#trainersTable').DataTable({
					'type' : 'GET',
					'processing' : false,
					'serverSide' : false,
					'sAjaxSource' : '/getTrainers',
					'bJQueryUI' : true,
					'autoWidth' : true,
					'order' : [ [ 1, "asc" ] ],
					'aoColumns' : [ {
						'mData' : 'id'
					}, {
						'mData' : 'enabledUser'
					}, {
						'mData' : 'name'
					},{
						'mData' : 'email'
					}, {
						'mData' : 'cellPhone'
					} ],
					'columnDefs' : [ {
						'targets' : 0,
						'searchable' : false,
						'ordenable' : false,
						'className' : 'dt-body-center',
						'render' : function(data,type, row) {
							return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
						}
					},{
						'targets' : 1,
						'width': '5%',
						'searchable' : false,
						'className' : 'dt-body-center',
						'render' : function(data,type, row) {
							return (data === true) ? '<span class="glyphicon glyphicon-ok centerSpan"></span>' : '<span class="glyphicon glyphicon-remove centerSpan"></span>';;
						}
					},{
		                "render": function ( data, type, row ) {
		                    return row.surname + ', '+ data;
		                },
		                "targets": 2
		            }],
					'language' : {
						"lengthMenu" : "_MENU_ elementos por p&aacute;gina",
						"zeroRecords" : "No se obtuvieron resultados",
						"info" : "P&aacute;gina _PAGE_ de _PAGES_",
						"infoEmpty" : "No se obtuvieron resultados",
						"infoFiltered" : "(de _MAX_ elementos)"
					}
			});

			$('#page-wrapper').on('click','#clientsTable tr',function() {
				$(this).find('input:radio').attr(
						'checked', true);
			});

			$('#trainerForm').submit(function(e) {
				var frm = $('#trainerForm');
				e.preventDefault();
				$.ajax({
					type : frm.attr('method'),
					url : frm.attr('action'),
					data : frm.serialize(),
					success : function() {
						dataTable.ajax.reload();
						toogle('myContent');
						toogleButtons();
					},
					error : function() {
						alert("Error!");
					}
				});
			});

			$('#btnAdd').click(function(e) {
				toogle('myContent');
				toogleButtons();
				$('#trainerForm')[0].reset();
			});

// 			$('#btnEdit').click(function(e) {
// 				var id = $('input[type="radio"]:checked').val();
// 				var frm = $('#clientForm');
// 				toogleButtons();
// 				frm[0].reset();
// 				if(typeof id === "undefined"){
// 					toogleButtons();
// 					alert("Por favor, seleccione un elemento de la lista");	
// 				}else{
// 					$.ajax({
// 						type : "POST",
// 						url : "/editar",
// 						data : "id=" + id,
// 						success : function(callback) {
// 							frm.loadJSON(callback);
// 							toogle('myContent');
// 						},
// 						error : function() {
// 							alert("Error!");
// 						}
// 					});
// 				};	
// 			});

// 			$('#btnDelete').click(function(){
// 				var id = $('input[type="radio"]:checked').val();
// 				if(typeof id === "undefined"){
// 					alert("Por favor, seleccione un elemento de la lista");	
// 				}else{
// 					$.ajax({
// 						type : "POST",
// 						url : "/borrar",
// 						data : "id=" + id,
// 						success : function() {
// 						    var tr = $('input[type="radio"]:checked').parent().parent();
// 							dataTable.row(tr).remove().draw(false);
// 						},
// 						error : function() {
// 							alert("Error!");
// 						}
// 					});
// 				};
// 			});
		});
	</script>
</body>
</html>