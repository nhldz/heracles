/**
 * JavaScript con funcionalidades correspondiestes a la creacion de rutinas
 */

$(document).ready(function() {
	
	/**
	 * CAMBIA A LA VISTA DE RUTINA
	 */	
	var showViewRoutine = function(){
		//Se bloquea a la vista de ejercicios
		$("#exercise").removeClass('active');
		$("#exerciseList").addClass('disabled');
		$('#exerciseList a[href="#exercise"]').removeAttr("data-toggle");
        $('#exerciseList').removeClass('active');
        //Se muestra la vista de actividades
        $('#routine').addClass('active');
        $("#routuineList").removeClass('disabled');
        $('#routuineList a[href="#activity"]').attr("data-toggle","tab");
        $('#routuineList').addClass('active');
        //Se muestra la vista de actividades;
        $("#activity").removeClass('active');
        $("#activityList").removeClass('disabled');
        $('#activityList a[href="#routine"]').attr("data-toggle","tab");
        $('#activityList').removeClass('active');
        //Se cambian los botones
		$('#saveActivity').addClass('hidden');
		$('#cancelActivity').addClass('hidden');
		$('#saveRoutine').removeClass('hidden');
		$('#cancelRoutine').removeClass('hidden');
	}
	
	/**
	 * CAMBIA EL TAB A LA VISTA DE ACTIVIDAD
	 */
	var showViewActivity = function(){
		//Se bloquea a la vista de ejercicios
		$("#exercise").removeClass('active');
		$("#exerciseList").addClass('disabled');
		$('#exerciseList a[href="#exercise"]').removeAttr("data-toggle");
        $('#exerciseList').removeClass('active');
        //Se muestra la vista de actividades
        $('#activity').addClass('active');
        $("#activityList").removeClass('disabled');
        $('#activityList a[href="#activity"]').attr("data-toggle","tab");
        $('#activityList').addClass('active');
        //Se muestra la vista de actividades;
        $("#routine").removeClass('active');
        $('#routuineList').removeClass('active');
        $("#routuineList").removeClass('disabled');
        $('#routuineList a[href="#routine"]').attr("data-toggle","tab");
        //Se cambian los botones
		$('#saveActivity').addClass('hidden');
		$('#cancelActivity').addClass('hidden');
		$('#saveRoutine').removeClass('hidden');
		$('#cancelRoutine').removeClass('hidden');
	}
	
	/**
	 * CAMBIA EL TAB A LA VISTA DE DETALLE ACTIVIDAD
	 */
	var showViewActivityDetails = function(){
		//Se cambia a la vista de ejercicios
		$('#exercise').addClass('active');
        $("#exerciseList").removeClass('disabled');
        $('#exerciseList a[href="#exercise"]').attr("data-toggle","tab");
        $('#exerciseList').addClass('active');
        //Se bloquea la vista de actividades
        $("#activity").removeClass('active');
		$("#activityList").addClass('disabled');
		$('#activityList a[href="#activity"]').removeAttr("data-toggle");
        $('#activityList').removeClass('active');
        //Se bloquea la vista de rutinas
		$("#routuineList").addClass('disabled');
		$('#routuineList a[href="#routine"]').removeAttr("data-toggle");
        //Se muestra el boton para guardar la actividad
		$('#saveActivity').removeClass('hidden');
		$('#cancelActivity').removeClass('hidden');
		//Se oculta el boton para guardar la rutina
		$('#saveRoutine').addClass('hidden');	
		$('#cancelRoutine').addClass('hidden');
	}
	
	/**
	 * VALIDADOR DE FORM ACTIVIDAD
	 */
	var validActivityDetailForm = function(){
		var valid = true;
		if($("#activityName").val() == ''){
			$('#errorActivityName').removeClass('hidden');
			valid = false;
		}else{
			$('#errorActivityName').addClass('hidden');
		}
		if($("#activityDescription").val() == ''){
			$('#errorActivityDescription').removeClass('hidden');
			valid = false;
		}else{
			$('#errorActivityDescription').addClass('hidden');
		}
		if(exerciseTable.rows().count() == 0){
			$('#errorExercise').removeClass('hidden');
			valid = false
		}else{
			$('#errorExercise').addClass('hidden');
		}
		return valid;
	}
	
	var validRoutineForm = function(){
		var valid = true;
		if($("#routineName").val() == ''){
			$('#errorRoutineName').removeClass('hidden');
			valid = false;
		}else{
			$('#errorRoutineName').addClass('hidden');
		}
		if($("input[name=col]:checked").val()){
			$('#errorRoutine').addClass('hidden');
		}else{
			$('#errorRoutine').removeClass('hidden');
			valid = false
		}
		return valid;
	}
	
	var validActivityForm = function(){
		var valid = true;
		if(activityTable.rows().count() == 0){
			$('#errorActivity').removeClass('hidden');
			valid = false
		}else{
			$('#errorActivity').addClass('hidden');
		}
		return valid;
	}
	
	/**
	 * FUNCIONALIDAD DE BOTON GUARDAR RUTINA
	 */
	$('#saveRoutine').click(function(e) {
		if(validRoutineForm()){
			if(validActivityForm()){
				$('#clientid').val($("input[name=col]:checked").val());
				var frm = $('#routineForm');
				frm.submit();
			}else{
				showViewActivity();
			}
		}else{
			showViewRoutine();
		}
	});
	
	/**
	 * FUNCIONALIDAD DE BOTONES ACTIVIDADES
	 */
	$('#btnActivityAdd').click(function(e) {
		exerciseTable.clear().draw();
		var frm = $('#activityForm');
	    frm.attr("method", "POST");
		showViewActivityDetails();
      });
	
	$('#btnActivityEdit').click(function(e) {
		  var id = $('input[type="radio"]:checked').val();
	      var frm = $('#activityForm');
	      frm.attr("method", "PUT");
	      if (typeof id === "undefined") {
	        alert("Por favor, seleccione un elemento de la lista");
	      } else {
	        $.ajax({
	            type: "GET",
	            url: "/routines/activity/" + id,
	            success: function(callback) {
	              exerciseTable.ajax.reload(); 	
	              frm.loadJSON(callback);
	              $('#activityName').val(callback.name);
	              $('#activityDescription').val(callback.description);
	              showViewActivityDetails();	
	            },
	            error: function() {
	              alert("Error!");
	            }
	          });
	    };
    });
	
	$('#btnActivityDelete').click(function() {
        var id = $('input[type="radio"]:checked').val();
        if (typeof id === "undefined") {
          alert("Por favor, seleccione un elemento de la lista");
        } else {
          $.ajax({
            type: "DELETE",
            url: "/routines/activity/" + id,
            success: function() {
            	activityTable.ajax.reload();
            },
            error: function() {
              alert("Error!");
            }
          });
        };
	});
	
	$('#saveActivity').click(function(e) {
		if(validActivityDetailForm()){
	    	var frm = $('#activityForm');
	    	$.ajax({
	          type: frm.attr('method'),
	          url: frm.attr('action'),
	          data: frm.serialize(),
	          success: function() {
	        	activityTable.ajax.reload();
	        	exerciseTable.ajax.reload();
	        	showViewActivity();
	            clearForm(frm[0]);
	          },
	          error: function(response) {
	            alert(response.statusText);
	          }
	        });
	    };
	});
	
	$('#cancelActivity').click(function(e) {
		var frm = $('#activityForm');
		clearForm(frm[0]);
		showViewActivity();
		$.ajax({
			type: "POST",
	        url: "/routines/cleanActivity"
	    });
    });
	
	/**
	 * FUNCIONALIDAD DE BOTONES EJERCICIOS
	 */
	$('#btnExerciseAdd').click(function(e) {
		var frm = $('#exerciseForm');
		frm.attr("method", "POST");
		$('#exerciseModal').modal('show');
    });
	
	$('#btnExerciseEdit').click(function(e) {
      var id = $('input[type="radio"]:checked').val();
      var frm = $('#exerciseForm');
      frm.attr("method", "PUT");
      if (typeof id === "undefined") {
        alert("Por favor, seleccione un elemento de la lista");
      } else {
        $.ajax({
            type: "GET",
            url: "/routines/exercise/" + id,
            success: function(callback) {
              frm.loadJSON(callback);	
              $('#rest').val(callback.rest);
              $('#reps').val(callback.reps);
              $('#sets').val(callback.sets);
              $('#weigth').val(callback.weigth);
              $('#exerciseId').val(callback.exerciseId);
              $('#exerciseModal').modal('show');
            },
            error: function() {
              alert("Error!");
            }
          });
      };
    });
	
	$('#btnExerciseDelete').click(
      function() {
        var id = $('input[type="radio"]:checked').val();
        if (typeof id === "undefined") {
          alert("Por favor, seleccione un elemento de la lista");
        } else {
          $.ajax({
            type: "DELETE",
            url: "/routines/exercise/" + id,
            success: function() {
            	exerciseTable.ajax.reload();
            },
            error: function() {
              alert("Error!");
            }
          });
        };
	});
	
	
	/**
	 * FUNCIONALIDAD FORMULARIOS
	 */
    $('#exerciseForm').submit(function(e) {
        var frm = $('#exerciseForm');
        e.preventDefault();
        $.ajax({
          type: frm.attr('method'),
          url: frm.attr('action'),
          data: frm.serialize(),
          success: function() {
            exerciseTable.ajax.reload(); 
            $('#exerciseModal').modal('hide');
            $('#errorExercise').addClass('hidden');
            clearForm(frm[0]);
          },
          error: function(response) {
            alert(response.statusText);
          }
        });
    });
    	
    /**
     * FUNCIONALIDAD DATATABLES
     */
    activityTable = $('#activityTable').DataTable({
		'processing' : false,
		'serverSide' : false,
		'sAjaxSource' : '/routines/activityList',
		'bJQueryUI' : true,
		'autoWidth' : true,
		'order' : [[ 1, "asc" ]],
		'aoColumns' : [
		    {'mData' : 'id'},
		    {'mData' : 'name'},
		    {'mData' : 'description'},
		    {'mData' : 'exerciseCount'}
		],
		'columnDefs' : [{
			'targets' :	0,
			'searchable' :	false,
			'ordenable' :	false,
			'className' :	'dt-body-center',
			'render' :	function(data, type, row) {
							return '<input name="col" type="radio" id='	+ row.id + ' value=' + row.id + ' >';
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
	
	clientsTable = $('#clientsTable')
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

	exerciseTable = $('#exerciseTable')
			.DataTable({
				'processing': false,
				'serverSide': false,
				'sAjaxSource': '/routines/exerciseList',
				'bJQueryUI': true,
				'autoWidth': true,
				'pageLength': 5,
				"bLengthChange" : false,   //Desactiva la posibilidad de cambiar la cant. elementos en la vista
				'order': [
					[1, "asc"]
				],
				'aoColumns': [{
					'mData': 'id'
				}, {
					'mData': 'exerciseName'
				}, {
					'mData': 'sets'
				}, {
					'mData': 'reps'
				}, {
					'mData': 'rest'
				}, {
					'mData': 'weigth'
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
				}],
				'language': {
					"lengthMenu": "_MENU_ elementos por p&aacute;gina",
					"zeroRecords": "No se obtuvieron resultados",
					"info": "P&aacute;gina _PAGE_ de _PAGES_",
					"infoEmpty": "No se obtuvieron resultados",
					"infoFiltered": "(de _MAX_ elementos)"
				}
			});
				
});
