/**
 * JavaScript con funcionalidades correspondiestes a la creacion de rutinas
 */

$(document).ready(function() {

	dataTable = $('#activityTable').DataTable({
		'processing' : false,
		'serverSide' : false,
		'sAjaxSource' : '/routines/list',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [[ 1, "asc" ]],
		'aoColumns' : [
		    {'mData' : 'id'},
		    {'mData' : 'name'},
		    {'mData' : 'description'},
		    {'mData' : 'excerciseCount'}
		],
		'columnDefs' : [{
			'targets' :	0,
			'searchable' :	false,
			'ordenable' :	false,
			'className' :	'dt-body-center',
			'render' :	function(data, type, row) {
							return '<input name="col" type="radio" id='	+ row.id + ' value=' + row.id + ' >';
						}
			},{
			"targets":	2,
			"render" :	function(data, type, row) {
							return data.surname + ', ' + data.name;
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
	
	$('#btnAdd').click(function(e) {
        $("#excerciseList").removeClass('disabled');
        $('#excerciseList a[href="#excercise"]').attr("data-toggle","tab");
        $('#activityList').removeClass('active');
        $('#activity').removeClass('active');
        $('#excerciseList').addClass('active');
		$('#excercise').addClass('active');
      });
	
	$('#btnEdit').click(function(e) {
        $("#activityDetails").removeClass('disabled');
        $('#activityDetails a[href="#activityDetails"]').attr("data-toggle","tab");
        $('#activityDetails a[href="#activityDetails"]').tab('show');
      });
	
//	$('#activity').click(function(e) {
//        $("#activityDetails").addClass('disabled');
//        $("#activityDetails a").removeAttr("data-toggle");
//        $("#activity a").click();
//     });
//	
//	$('#routine').click(function(e) {
//        $("#activityDetails").addClass('disabled');
//        $("#activityDetails a").removeAttr("data-toggle");
//        $("#routine a").click();
//     });

	dataTable = $('#clientsTable')
		.DataTable({
			'processing': false,
			'serverSide': false,
			'sAjaxSource': '/client/list',
			'bJQueryUI': true,
			'autoWidth': true,
			'order': [
				[1, "asc"]
			],
			'aoColumns': [{
				'mData': 'id'
			}, {
				'mData': 'name'
			}, {
				'mData': 'userName'
			}, {
				'mData': 'email'
			}, {
				'mData': 'phone',
				"defaultContent": ""
			}],
			'columnDefs': [{
				'targets': 0,
				'searchable': false,
				'ordenable': false,
				'className': 'dt-body-center',
				'render': function(data,
					type, row) {
					return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
				}
			},{
				"render": function(data,
					type, row) {
					return row.surname + ', ' + data;
				},
				"targets": 2
			}],
			'language': {
				"lengthMenu": "_MENU_ elementos por p&aacute;gina",
				"zeroRecords": "No se obtuvieron resultados",
				"info": "P&aacute;gina _PAGE_ de _PAGES_",
				"infoEmpty": "No se obtuvieron resultados",
				"infoFiltered": "(de _MAX_ elementos)"
			}
		});

		dataTable = $('#excerciseTable')
			.DataTable({
				'processing': false,
				'serverSide': false,
				'sAjaxSource': '/client/list',
				'bJQueryUI': true,
				'autoWidth': true,
				'order': [
					[1, "asc"]
				],
				'aoColumns': [{
					'mData': 'id'
				}, {
					'mData': 'name'
				}, {
					'mData': 'userName'
				}, {
					'mData': 'email'
				}, {
					'mData': 'phone',
					"defaultContent": ""
				}],
				'columnDefs': [{
					'targets': 0,
					'searchable': false,
					'ordenable': false,
					'className': 'dt-body-center',
					'render': function(data,
						type, row) {
						return '<input name="col" type="radio" id=' + row.id + ' value=' + row.id + ' >';
					}
				},{
					"render": function(data,
						type, row) {
						return row.surname + ', ' + data;
					},
					"targets": 2
				}],
				'language': {
					"lengthMenu": "_MENU_ elementos por p&aacute;gina",
					"zeroRecords": "No se obtuvieron resultados",
					"info": "P&aacute;gina _PAGE_ de _PAGES_",
					"infoEmpty": "No se obtuvieron resultados",
					"infoFiltered": "(de _MAX_ elementos)"
				}
			});

	$('#save').click(function(e) {
	//	var frm = $('#routineForm');
		var data = 'name=' + $("input[name=name]").val() + '&clientid=' + $("input[name=col]:checked").val();
		e.preventDefault();
		$.ajax({
			type : 'POST',
			url : '/routines',
			data : data,
			success : function(response) {
				console.log(response);
				//dataTable.ajax.reload();
			},
			error : function(response) {
				alert(response.statusText);
			}
		});
	});
});
