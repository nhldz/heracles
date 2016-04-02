
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${contextPath}/resources/js/jquery.loadJSON.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/sb-admin-2.js"></script>
<script src="${contextPath}/resources/js/metisMenu.min.js"></script>
<script src="${contextPath}/resources/js/dataTables/jquery.dataTables.min.js"></script>
<script src="${contextPath}/resources/js/dataTables/dataTables.bootstrap.min.js"></script>
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

	function formatDate(date) {
		var javascriptDate = new Date(date);
		javascriptDate = javascriptDate.getDate() + "/"
				+ (javascriptDate.getMonth() + 1) + "/"
				+ javascriptDate.getFullYear();
		return javascriptDate;
	};

	function clearForm(frm) {
		for (i = 0; i < frm.length; i++) {
			field_type = frm[i].type.toLowerCase();
			switch (field_type) {
			case "text":
			case "password":
			case "textarea":
			case "hidden":
			case "email":
				frm[i].value = "";
				break;
			case "radio":
			case "checkbox":
				if (frm[i].checked) {
					frm[i].checked = false;
				}
				break;
			case "select-one":
			case "select-multi":
				frm[i].selectedIndex = 0;
				break;
			default:
				break;
			}
		}
	};
</script>
