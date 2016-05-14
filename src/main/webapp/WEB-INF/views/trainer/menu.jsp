<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/login">HERACLES</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="j_spring_security_logout"><i class="fa fa-sign-out fa-fw"></i>
						Logout</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
<!-- 				<li class="sidebar-search"> -->
<!-- 					<div class="input-group custom-search-form"> -->
<!-- 						<input type="text" class="form-control" placeholder="Search..."> -->
<!-- 						<span class="input-group-btn"> -->
<!-- 							<button class="btn btn-default" type="button"> -->
<!-- 								<i class="fa fa-search"></i> -->
<!-- 							</button> -->
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 				</li> -->
				<li><a href="/exercises" id="exercises"><i
						class="fa fa-check fa-fw"></i> Ejercicios</a></li>
				<li><a href="/routines" id="routines"><i
						class="fa fa-dashboard fa-fw"></i> Rutinas</a></li>
				<li><a href="index"><i class="fa fa-users fa-fw"></i>Usuarios<span
						class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a id="clients" href="/client">Clientes</a></li>
						<li><a id="trainers" href="/trainer">Profesores</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side --> </nav>
</body>
