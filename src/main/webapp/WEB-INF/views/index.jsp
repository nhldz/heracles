<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="templates/includes/common_head.jsp" />
</head>
<body>
	<div id="wrapper">
		<c:choose>
			<c:when test="${menu == 'client'}">
				<%@include file="client/menu.jsp"%>
			</c:when>
			<c:otherwise>
				<%@include file="trainer/menu.jsp"%>
			</c:otherwise>
		</c:choose>
		<div id="page-wrapper"></div>
	</div>
	<jsp:include page="templates/includes/common_foot.jsp" />
</body>
</html>
