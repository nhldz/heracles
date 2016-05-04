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
			<button type="button" class="btn btn-success" id="btnAdd">Agregar</button>
			<button type="button" class="btn btn-primary" id="btnEdit">Editar</button>
			<button type="button" class="btn btn-danger" id="btnDelete">Borrar</button>
		</div>
	</div>
	<br>
	<form id="routineForm" method="POST" data-toggle="validator">
		<div id="myContent" class="row hidden">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<input type="hidden" name="id" />
							<input type="hidden" name="enabled" value="true"/>
						</div>
						<div class="form-group">
							<label for="name">Nombre:</label> <input name="name"
								class="form-control" required />
						</div>
						<div class="form-group">
							<label for="description">Descripci&oacute;n:</label>
							<textarea name="description" class="form-control" rows="3" required></textarea>
						</div>
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default" onclick="clearForm(this.form); toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script>
	$(document).ready(function() {
		dataTable = $('#routineTable').DataTable({
			'processing' : false,
			'serverSide' : false,
			'sAjaxSource' : '/routines/list',
			'bJQueryUI' : true,
			'autoWidth' : true,
			'order' : [ [ 1, "asc" ] ],
			'aoColumns' : [ {
				'mData' : 'id'
			}, {
				'mData' : 'name'
			}, {
				'mData' : 'client'
			}, {
				'mData' : 'createDate'
			}, {
				'mData' : 'endDate',
				"defaultContent" : ""
			}],
			'columnDefs' : [ {
				'targets' : 0,
				'searchable' : false,
				'ordenable' : false,
				'className' : 'dt-body-center',
				'render' : function(data, type,
						row) {
					return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
				}
			},{
				"targets": 2,
    			"render": function ( data, type, row ) {
                    return data.surname + ', '+ data.name;
                }
			},{
				'targets' : 3,
				"sType": 'date',
				'render' : function(data,type,row) {
					return formatDate(data);
				}
			}],
			'language' : {
				"lengthMenu" : "_MENU_ elementos por p&aacute;gina",
				"zeroRecords" : "No se obtuvieron resultados",
				"info" : "P&aacute;gina _PAGE_ de _PAGES_",
				"infoEmpty" : "No se obtuvieron resultados",
				"infoFiltered" : "(de _MAX_ elementos)"
			}
		});

		$('#page-wrapper').on('click','#routinesTable tr',function() {
			$(this).find('input:radio').attr('checked',true);
		});
	
		$('#routineForm').submit(function(e) {
			var frm = $('#routineForm');
			e.preventDefault();
			$.ajax({
				type : frm.attr('method'),
				url : frm.attr('action'),
				data : frm.serialize(),
				success : function() {
					dataTable.ajax.reload();
					toogle('myContent');
					toogleButtons();
					clearForm(frm[0]);
				},
				error : function() {
					alert("Error!");
				}
			});
		});
	
		$('#btnAdd').click(function(e) {
			toogle('myContent');
			toogleButtons();
			$('#routineForm').attr('action', '/routines/save');
		});
	
		$('#btnEdit').click(function(e) {
			var id = $('input[type="radio"]:checked').val();
			var frm = $('#routineForm');
			frm.attr('action','/routines/update');
			toogleButtons();
			if (typeof id === "undefined") {
				toogleButtons();
				alert("Por favor, seleccione un elemento de la lista");
			} else {
				$.ajax({
					type : "GET",
					url : "/routines/load/"+ id,
					success : function(callback) {
						frm.loadJSON(callback);
						toogle('myContent');
					},
					error : function() {
						alert("Error!");
					}
				});
			};
		});
	
		$('#btnDelete').click(function() {
			var id = $('input[type="radio"]:checked').val();
			if (typeof id === "undefined") {
				toogleButtons();
				alert("Por favor, seleccione un elemento de la lista");
			}else {
				$.ajax({
					type : "POST",
					url : "/routines/remove/"+ id,
					success : function() {
						var tr = $('input[type="radio"]:checked').parent().parent();
						dataTable.row(tr).remove().draw(false);
					},
					error : function() {
						alert("Error!");
					}
				});
			};
		});
	});
</script>