/**
 * 
 */
$(document).ready(function() {
	dataTable = $('#exerciseTable').DataTable({
		'processing' : false,
		'serverSide' : false,
		'sAjaxSource' : '/client/'+userName+'/routine/'+routineId+'/activity/'+activityId+'/exercises',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [ [ 1, "asc" ] ],
		'aoColumns' : [ {
			'mData' : 'exercise.id'
		}, {
			'mData' : 'exercise.name'
		}, {
			'mData' : 'exercise.type'
		},{
			'mData' : 'exercise.description'
		},{
			'mData' : 'sets'
		},{
			'mData' : 'reps'
		},{
			'mData' : 'rest'
		},{
			'mData' : 'weigth'
		},{
			"mData": null,
		    "bSortable": false,
		    "mRender": function(data, type, full) {
		    	var btnRun = '<button type="button" class="btn btn-success btnRun" value="'+data.id+'" >' + 'Ejecutar' + '</button>';
		    	var btnCancel = '<button type="button" class="btn btn-danger btnCancel" value="'+data.id+'" >' + 'Cancelar' + '</button>'
	          return btnRun + btnCancel
//	          return '<a class="btn btn-primary" href="/client/"'+userName+'"/routine/"'+routineId+'"/activity/"'+activityId+'"/exercise/"'+data.id+'" role="button">Detener</a>'
	          ;}
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
		},{
			"targets": 3,
			"render": function ( data, type, row ) {
                return data;
            }
		},{
			"targets": 4,
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

	
	dataTable.on('click', '.btnRun', function(){
		var data = $(this).val();
		runExercie(data);
	});
	
	dataTable.on('click', '.btnCancel', function(){
		var data = $(this).val();
		cancelExercie(data);
	});
	
	
	function stopExercie(data){
		$.ajax({
			type: "POST",
			url: '/client/'+userName+'/routine/'+routineId+'/activity/'+activityId+'/exercise/'+data+'/stop',
			data: "STOP",
			succes: function(){
				console.log("SUCCES");
			}
		});
		location.reload();
	}
	
	function runExercie(data){
		$.ajax({
			type: "POST",
			url: '/client/'+userName+'/routine/'+routineId+'/activity/'+activityId+'/exercise/'+data+'/run',
			data: "RUN",
			succes: function(){
				console.log("SUCCES");
			}
		});
		location.reload();
	}
	
	function cancelExercie(data){
		$.ajax({
			type: "POST",
			url: '/client/'+userName+'/routine/'+routineId+'/activity/'+activityId+'/exercise/'+data+'/cancel',
			data: "RUN",
			succes: function(){
				console.log("SUCCES");
			}
		});
		location.reload();
	}
	
	$('.btnStop').click(function(){
		var data = $(this).val();
		stopExercie(data);
	})

});