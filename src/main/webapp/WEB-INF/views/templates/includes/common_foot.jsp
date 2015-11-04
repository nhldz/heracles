
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery-1.11.3.min.js"></script>
<script src="resources/js/jquery.loadJSON.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/sb-admin-2.js"></script>
<script src="resources/js/metisMenu.min.js"></script>
<script src="resources/js/dataTables/jquery.dataTables.min.js"></script>
<script src="resources/js/dataTables/dataTables.bootstrap.min.js"></script>
<script>
	function toogle(idElement) {
		var elem = $('#' + idElement);
		if (elem.is(":visible")) {
			elem.attr('style', 'display:none !important');
		} else {
			elem.attr('style', 'display:block !important');
		}
	}

	function toogleButtons() {
		var bool = !$('#btnAdd').is(":disabled");
		$('#btnAdd').attr('disabled', bool);
		$('#btnDelete').attr('disabled', bool);
		$('#btnEdit').attr('disabled', bool);
	};

// 	$(document).ready(function() {
// 		$("#trainers").click(function() {
// 			$("#page-wrapper").load("/trainers");
// 			return false;
// 		});

// 		$("#exercises").click(function() {
// 			$("#page-wrapper").load("/exercises");
// 			return false;
// 		});

// 		$("#routines").click(function() {
// 			$("#page-wrapper").load("/routines");
// 			return false;
// 		});

// 		$("#clients").click(function() {
// 			$("#page-wrapper").load("/clients");
// 			return false;
// 		});
// 	});
</script>