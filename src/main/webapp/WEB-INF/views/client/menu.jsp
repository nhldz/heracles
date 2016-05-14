<%@ include file="../templates/includes/taglibs.jsp"%>
<jsp:include page="../templates/includes/common_head.jsp" />
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/prueba">HERACLES</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="/client/${userName}/profile"><i class="fa fa-user fa-fw"></i> User
						Profile</a></li>
				<li><a href="j_spring_security_logout"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li><a href="/client/${userName}/profile"><i class="fa fa-dashboard fa-fw"></i>
						Mi perfil</a></li>
				<li><a href="/client/${userName}/routine"><i class="fa fa-table fa-fw"></i>
						Rutinas</a></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side --> 
	</nav>
</body>	