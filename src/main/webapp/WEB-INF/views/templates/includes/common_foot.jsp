
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery.loadJSON.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/sb-admin-2.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/metisMenu.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/dataTables/jquery.dataTables.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/js/dataTables/dataTables.bootstrap.min.js"></script>
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
        javascriptDate = javascriptDate.getDate()+"/"+(javascriptDate.getMonth()+1)+"/"+javascriptDate.getFullYear();
        return javascriptDate;
	};
	
</script>
