<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="routineId" value="${actualRoutine.id}"/>
<c:set var="userName" value="${actualRoutine.client.username}"/>
<div id="wrapper">
	<jsp:include page="../client/menu.jsp" />
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
			<!-- 		<div class="col-lg-3"> -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Rutina Actual - ${actualRoutine.name}</h3>
					</div>
					<div class="panel-body">
						<div class="cont3">
							<p>
								<ok>Fecha creaci&oacute;n:</ok>
								${actualRoutine.createDate}
							</p>
							<p>
								<ok>Entrenador:</ok>
								${actualRoutine.trainer.username}
							</p>
							<p>
								<ok>Progreso:</ok>
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: ${actualRoutineProgress}%;">
									${actualRoutineProgress}</div>
								</div>
							</p>
						</div>
					</div>
<!-- 					<div class="btn-group" role="group" aria-label="..."> -->
<!-- 						<button type="button" class="btn btn-default">Ver Detalle</button> -->
<!-- 					</div> -->
					<div class="panel-footer">
					</div>
				</div>
			</div>
		</div>
		<div class="row" >
			<div class="col-lg-12" >
				<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Actividad realizandose - ${runActivity.name}</h3>
				</div>
				<div class="panel-body">
					<div class="cont3">
						<p>
							<ok>Descrpci&oacute;n:</ok>
							${runActivity.description}
						</p>
						<p>
							<ok>Ejercicio que se esta realizando:</ok>
							${runActivity.runExercise.exercise.name}
						</p>
					</div>
				</div>
				<div class="btn-group" role="group" aria-label="...">
						<a class="btn btn-default" href="/client/${actualRoutine.client.username}/routine/${actualRoutine.id}/activity/${runActivity.id}" role="button">Ver Actividad</a>
					</div>
				<div class="panel-footer">
				</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Actividades de la Rutina</div>
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table id="activityTable"
								class="table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="no-sort id"></th>
										<th>Nombre</th>
										<th>Descripci&oacute;n</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<button type="button" class="btn btn-success" id="btnView">Ver</button>
			</div>
		</div>
		<br>
	</div>
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script src="${contextPath}/resources/js/client-routines.js"></script>
<<script type="text/javascript">
var routineId = ${routineId};
var userName = "${userName}";
</script>
<script src="${contextPath}/resources/js/client-activities.js"></script>
