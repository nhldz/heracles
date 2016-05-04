/**
 * JavaScript con funcionalidades correspondiestes a la presentacion de ejercicios
 */
$(document).ready(function() {
	
	dataTable = $('#exercisesTable').DataTable(
	{
		'processing' : false,
		'serverSide' : false,
		'sAjaxSource' : '/exercises/list',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [ [ 1, "asc" ] ],
		'aoColumns' : [ {
			'mData' : 'id'
		}, {
			'mData' : 'name'
		}, {
			'mData' : 'type'
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
		var frmContainer = $('#exerciseForm');
		var frm = frmContainer[0];
		frm.reset();
		frm.method = "POST";
	});

	//Abre formulario de edición para el ejercicio seleccionado
	$('#btnEdit').click(function(e) {
		var id =  selectId();
		var frmContainer = $('#exerciseForm');
		var frm = frmContainer[0];
		frm.method = "PUT";
		toogleButtons();
		frm.reset();
		if (typeof id === "undefined") {
			toogleButtons();
			alert("Por favor, seleccione un elemento de la lista");
		} else {
			$.ajax({
				type : "GET",
				url : "/exercises/"
						+ id,
				success : function(callback) {
					frmContainer.loadJSON(callback);
					toogle('myContent');
				},
				error : function() {
					alert("Error!");
				}
			});
		}
	});

	//Elimina el ejercicio selecionado
	$('#btnDelete').click(function() {
		var id =  selectId();
		if (typeof id === "undefined") {
			toogleButtons();
			alert("Por favor, seleccione un elemento de la lista");
		}else {
			$.ajax({
				type : "DELETE",
				url : "/exercises/"
						+ id,
				success : function() {
					var tr = $('input[type="radio"]:checked')
							.parent().parent();
					dataTable.row(tr).remove().draw(false);
				},
				error : function() {
					alert("Error!");
				}
			});
		}
	});
	
	var selectId = function (){
		return $('input[type="radio"]:checked').val();
	};
});
