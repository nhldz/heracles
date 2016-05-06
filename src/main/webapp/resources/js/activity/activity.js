/**
 * JavaScript con funcionalidades correspondiestes a la creacion de actividades
 */

$(document).ready(function() {
					
	dataTable = $('#excerciseTable').DataTable({
//		'processing' : false,
//		'serverSide' : false,
//		'sAjaxSource' : '/routines/list',
//		'bJQueryUI' : true,
//		'autoWidth' : true,
//		'order' : [ [ 1, "asc" ] ],
//		'aoColumns' : [ {
//			'mData' : 'id'
//		}, {
//			'mData' : 'name'
//		}, {
//			'mData' : 'client'
//		}, {
//			'mData' : 'createDate'
//		}, {
//			'mData' : 'endDate',
//			"defaultContent" : ""
//		} ],
//		'columnDefs' : [
//				{
//					'targets' : 0,
//					'searchable' : false,
//					'ordenable' : false,
//					'className' : 'dt-body-center',
//					'render' : function(data,
//							type, row) {
//						return '<input name="col" type="radio" id='
//								+ row.id
//								+ ' value='
//								+ row.id + ' >';
//					}
//				},
//				{
//					"targets" : 2,
//					"render" : function(data,
//							type, row) {
//						return data.surname
//								+ ', '
//								+ data.name;
//					}
//				},
//				{
//					'targets' : 3,
//					"sType" : 'date',
//					'render' : function(data,
//							type, row) {
//						return formatDate(data);
//					}
//				} ],
//		'language' : {
//			"lengthMenu" : "_MENU_ elementos por p&aacute;gina",
//			"zeroRecords" : "No se obtuvieron resultados",
//			"info" : "P&aacute;gina _PAGE_ de _PAGES_",
//			"infoEmpty" : "No se obtuvieron resultados",
//			"infoFiltered" : "(de _MAX_ elementos)"
//		}
	});

	$('#exerciseForm').submit(function(e) {
		var frm = $('#exerciseForm');
		console.log(frm.serialize());
		e.preventDefault();
		$.ajax({
			type : frm.attr('method'),
			url : frm.attr('action'),
			data : frm.serialize(),
			success : function() {
//	    		$('exerciseDialog').modal('toggle');
	    		console.log("Se guardo")
			},
			error : function() {
				alert("Error!");
			}
		});
	});
});
