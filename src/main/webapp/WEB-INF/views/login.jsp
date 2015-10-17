<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/bootstrap-theme.css">
<link rel="stylesheet" href="resources/css/login.css">
<title>HERACLES</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">HERACLES</h1>
				<div class="account-wall">
					<form:form class="form-signin" action="/index" method="POST">
						<input type="text" class="form-control" placeholder="Usuario"
							required autofocus>
						</br>
						<input type="password" class="form-control"
							placeholder="Contraseña" required>
						</br>
						<button class="btn btn-lg btn-primary btn-block" type="submit">
							Entrar</button>
						<label class="checkbox pull-left"> <input type="checkbox"
							value="remember-me"> No cerrar sesión
						</label>
						<!--  <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span> -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/js/jquery-1.11.3.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>