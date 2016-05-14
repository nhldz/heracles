/**
 * 
 */
$(document).ready(function() {
	dataTable = $('#routineTable').DataTable({
		'processing' : false,
		'serverSide' : false,
		'sAjaxSource' : '/client/'+userName+'/routine/'+routineId+'/activities',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [ [ 1, "asc" ] ],
		'aoColumns' : [ {
			'mData' : 'id'
		}, {
			'mData' : 'name'
		}, {
			'mData' : 'description'
		},{
			"mData": null,
		    "bSortable": false,
		    "mRender": function(data, type, full) {
	          return '<button type="button" class="btn btn-success btnView" value="'+data.id+'" >' + 'Ver' + '</button>';}
	     }],
		'columnDefs' : [ {
			'targets' : 0,
			'searchable' : false,
			'ordenable' : false,
			'render' : function(data, type, row) {
				return '<input name="col" type="hidden" id=' + data + ' value=' + data + ' >';}
		},
		{
			"targets": 2,
			"render": function ( data, type, row ) {
                return data;
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

	
	dataTable.on('click', '.btnView', function(){
		console.log($(this).value());
	});

});