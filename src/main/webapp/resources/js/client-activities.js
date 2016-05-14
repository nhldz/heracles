/**
 * 
 */
$(document).ready(function() {
	dataTable = $('#activityTable').DataTable({
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
		    	var url = '/client/'+userName+'/routine/'+routineId+'/activity/'+data.id;
		    	return '<a class="btn btn-default" href='+url+' role="button">Ver Actividad</a>';}
		
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