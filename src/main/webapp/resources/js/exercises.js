/**
 * JavaScript con funcionalidades correspondiestes a la presentacion de ejercicios
 */
$(document).ready(
		function() {
			dataTable = $('#exercisesTable').DataTable(
					{
						'processing' : false,
						'serverSide' : false,
						'sAjaxSource' : '/getExercises',
						'bJQueryUI' : true,
						'autoWidth' : true,
						'order' : [ [ 1, "asc" ] ],
						'aoColumns' : [ {
							'mData' : 'id'
						}, {
							'mData' : 'name'
						}, {
							'mData' : 'description'
						} ],
						'columnDefs' : [ {
							'targets' : 0,
							'searchable' : false,
							'ordenable' : false,
							'className' : 'dt-body-center',
							'render' : function(data, type, row) {
								return '<input name="col" type="radio" id='
										+ row.id + ' value=' + row.id + ' >';
							}
						} ],
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

			//Abre formulario creación para un nuevo ejercicio
			$('#btnAdd').click(function(e) {
				toogle('myContent');
				toogleButtons();
				$('#exerciseForm')[0].reset();
			});

			//Abre formulario de edición para el ejercicio seleccionado
			$('#btnEdit').click(
					function(e) {
						var frm = $('#exerciseForm');
						frm.method = "PUT";
						toogleButtons();
						frm[0].reset();
						$.ajax({
							type : "GET",
							url : "/exercises/"
									+ $('input[type="radio"]:checked').val(),
							// data : "id=" +
							// $('input[type="radio"]:checked').val(),
							success : function(callback) {
								frm.loadJSON(callback);
								toogle('myContent');
							},
							error : function() {
								alert("Error!");
							}
						});
					});

			//Elimina el ejercicio selecionado
			$('#btnDelete').click(
					function() {
						$.ajax({
							type : "DELETE",
							url : "/exercises/"
									+ $('input[type="radio"]:checked').val(),
							success : function() {
								var tr = $('input[type="radio"]:checked')
										.parent().parent();
								dataTable.row(tr).remove().draw(false);
							},
							error : function() {
								alert("Error!");
							}
						});
					});
		});