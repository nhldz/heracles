{$exercisesJson}
<jsp:include page="../templates/includes/common_head.jsp" />
<div id="wrapper">
	<jsp:include page="../trainer/menu.jsp" />
</div>
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
									<th>Name</th>
									<th>Description</th>
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
	<form id="exerciseForm" action="guardar" method="POST" data-toggle="validator">
		<div id="myContent" class="row hidden">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<input type="hidden" name="id"/>
						</div>
						<div class="form-group">
							<label for="name">Nombre:</label>
							<input name="name" class="form-control" required/>
						</div>
						<div class="form-group">
							<label for="description">Descripción:</label>
							<textarea name="description" class="form-control" rows="3" required></textarea>
						</div>
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default"
							onclick="toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>	
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script>
		$(document).ready(function() {
			dataTable = $('#exercisesTable').DataTable({
				'processing' : false,
				'serverSide' : false,
				'sAjaxSource' : '/getExercises',
				'bJQueryUI' : true,
				'autoWidth': true,
				'order': [[ 1, "asc" ]],
				'aoColumns' : [{
					'mData' : 'id'
				},{
					'mData' : 'name'
				},{
					'mData' : 'description'
				}],
				'columnDefs': [{
					'targets': 0,
					'searchable':false,
			        'ordenable':false,
			        'className': 'dt-body-center',
                    'render': function (data, type, row) {
                        return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
                    }
                }],
				'language' : {
					"lengthMenu" : "_MENU_ elementos por página",
					"zeroRecords" : "No se obtuvieron resultados",
					"info" : "Página _PAGE_ de _PAGES_",
					"infoEmpty" : "No se obtuvieron resultados",
					"infoFiltered" : "(de _MAX_ elementos)"
				}
			});
						
			$('#page-wrapper').on('click', '#exercisesTable tr', function() {
				$(this).find('input:radio').attr('checked', true);
			});

			$('#exerciseForm').submit(function(e) {
				var frm = $('#exerciseForm');
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
				$('#exerciseForm')[0].reset();
			});
			
			$('#btnEdit').click(function(e) {
				var frm = $('#exerciseForm');
				frm.method = "PUT";
				toogleButtons();
				frm[0].reset();
				$.ajax({
					type : "GET",
					url : "/exercises/"+ $('input[type="radio"]:checked').val(),
// 					data : "id=" + $('input[type="radio"]:checked').val(),
					success : function(callback) {
						frm.loadJSON(callback);
						toogle('myContent');
					},
					error : function() {
						alert("Error!");
					}
				});
			});
			
			$('#btnDelete').click(function(){
				$.ajax({
					type : "DELETE",
					url : "/exercises/"+ $('input[type="radio"]:checked').val(),
					success : function() {
					    var tr = $('input[type="radio"]:checked').parent().parent();
						dataTable.row(tr).remove().draw(false);
					},
					error : function() {
						alert("Error!");
					}
				});
			});
		});
	</script>

</body>
</html>