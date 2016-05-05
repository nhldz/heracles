/**
 * JavaScript con funcionalidades correspondiestes a la creacion de rutinas
 */

$(document).ready(function() {
	
	dataTable = $('#activityTable').DataTable(//{
//		'processing' : false,
//		'serverSide' : false,
//		'sAjaxSource' : '/routines/list',
//		'bJQueryUI' : true,
//		'autoWidth' : true,
//		'order' : [[ 1, "asc" ]],
//		'aoColumns' : [
//		    {'mData' : 'id'}, 
//		    {'mData' : 'name'},
//		    {'mData' : 'description'},
//		    {'mData' : 'excerciseCount'}
//		],
//		'columnDefs' : [{
//			'targets' :	0,
//			'searchable' :	false,
//			'ordenable' :	false,
//			'className' :	'dt-body-center',
//			'render' :	function(data, type, row) {
//							return '<input name="col" type="radio" id='	+ row.id + ' value=' + row.id + ' >';
//						}
//			},{
//			"targets":	2,
//			"render" :	function(data, type, row) {
//							return data.surname + ', ' + data.name;
//						}
//			}],
//		'language' : {
//			"lengthMenu" : "_MENU_ elementos por p&aacute;gina",
//			"zeroRecords" : "No se obtuvieron resultados",
//			"info" : "P&aacute;gina _PAGE_ de _PAGES_",
//			"infoEmpty" : "No se obtuvieron resultados",
//			"infoFiltered" : "(de _MAX_ elementos)"
//		}
//	});
									); //});
	
	$('#clients').combobox();
		
	$('#save').click(function(){
		$("#groupActivity").removeClass('hidden');
	});

	$('#routineForm').submit(function(e) {
		var frm = $('#routineForm');
		e.preventDefault();
		$.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type : frm.attr('method'),
			url : frm.attr('action'),
			data : JSON.stringify(frm.serialize()),
			dataType: "json",
			success : function() {
//				dataTable.ajax.reload();
			},
			error : function() {
				alert("Error!");
			}
		});
	});	
});
