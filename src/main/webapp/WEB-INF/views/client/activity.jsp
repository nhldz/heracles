<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="routineId" value="${actualRoutine.id}"/> --%>
<%-- <c:set var="userName" value="${actualRoutine.client.username}"/> --%>
<div id="wrapper">
	<jsp:include page="../client/menu.jsp" />
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
			<!-- 		<div class="col-lg-3"> -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Actividad - ${activity.name}</h3>
					</div>
					<div class="panel-body">
						<div class="cont3">
							<p>
								<ok>Fecha creaci&oacute;n:</ok>
								${activity.name}
							</p>
							<p>
								<ok>Descripci&oacute;:</ok>
								${activity.description}
							</p>
							<p>
								<ok>Ejercicios totales:</ok>
								${exerciesCount}
							</p>
							<p>
								<ok>Ejercicios realizados:</ok>
								${exercisesEndCount}
							</p>
							<p>
								<ok>Ejercicios cancelados:</ok>
								${exercisesCancelCount}
							</p>
						</div>
					</div>

					<div class="panel-footer">
					</div>
				</div>
			</div>
		</div>
		<c:if test="${(runExercise != null) && (runExercise.lastState == 'RUN')}">
		<div class="row" >
			<div class="col-lg-12" >
				<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Ejercicio iniciado  - ${runExercise.name}</h3>
				</div>
				<div class="panel-body">
					<div class="cont3">
						<p>
							<ok>Descrpci&oacute;n:</ok>
							${runExercise.exerciseName}
						</p>
						<p>
							<ok>Ejercicio que se esta realizando:</ok>
							${runExercise.type}
						</p>
						<p>
							<ok>Series:</ok>
							${runExercise.sets}
						</p>
						<p>
							<ok>Repeticiones:</ok>
							${runExercise.reps}
						</p>
						<p>
							<ok>Descanso:</ok>
							${runExercise.rest}
						</p>
						<p>
							<ok>Peso:</ok>
							${runExercise.weigth}
						</p>
					</div>
				</div>
				<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-primary btnStop" value=${runExercise.id} >Detener</button>
					</div>
				<div class="panel-footer">
				</div>
				</div>
			</div>
		</div>
		</c:if>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Ejercicios de la actividad</div>
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table id="exerciseTable"
								class="table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="no-sort id"></th>
										<th>Nombre</th>
										<th>Tipo</th>
										<th>Descripci&oacute;n</th>
										<th>Series</th>
										<th>Repeticiones</th>
										<th>Tiempo de descanso (min)</th>
										<th>Peso</th>
										<th>Ultimo estado</th>
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
		<br>
	</div>
</div>
<jsp:include page="../templates/includes/common_foot.jsp" />
<script type="text/javascript">
var routineId = ${routineId};
var userName = "${userName}";
var activityId = "${activity.id}";
</script>
<script src="${contextPath}/resources/js/client-exercises.js"></script>
