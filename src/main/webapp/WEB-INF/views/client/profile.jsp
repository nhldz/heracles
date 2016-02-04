<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<div id="wrapper">
	<jsp:include page="../client/menu.jsp" />
</div>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header"></h1>
		</div>
	</div>
	<jsp:include page="../user/user_profile.jsp" >
		<jsp:param value="${client}" name="user"/>
	</jsp:include>
</div>
<jsp:include page="../templates/includes/common_foot.jsp"/>
</body>
</html>