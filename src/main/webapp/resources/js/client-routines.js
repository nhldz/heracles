/**
 * 
 */
$(document).ready(function() {
	dataTable = $('#routineTable').DataTable({
		'processing' : false,
		'serverSide' : false,
//		'sAjaxSource' : '/client/{name}/routines/',
		'sAjaxSource' : '/client/ClientName1/routines/list',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [ [ 1, "asc" ] ],
		'aoColumns' : [ {
			'mData' : 'id'
		}, {
			'mData' : 'name'
		}, {
			'mData' : 'trainer'
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
			},
			error : function() {
				alert("Error!");
			}
		});
	});

	$('#btnAdd').click(function(e) {
		toogle('myContent');
		toogleButtons();
		$('#routineForm')[0].reset();
		$('#routineForm').attr('action', '/routines/save');
	});

	$('#btnEdit').click(function(e) {
		var id = $('input[type="radio"]:checked').val();
		var frm = $('#routineForm');
		frm.attr('action','/routines/update');
		toogleButtons();
		frm[0].reset();
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