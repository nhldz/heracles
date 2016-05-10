<jsp:include page="../templates/includes/common_head.jsp" />
<jsp:include page="../templates/includes/taglibs.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="wrapper">
	<jsp:include page="../client/menu.jsp" />
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header"></h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Rutina Actual - ${actualRoutine.name}</h3>
					</div>
					<div class="panel-body">
						<div class="cont3">
							<p>
								<ok>Fecha creacion:</ok>
								${actualRoutine.createDate}
							</p>
							<p>
								<ok>Entrenador:</ok>
								${actualRoutine.trainer.username}
							</p>
							<ok>Progreso:</ok>
								<div class="progress">
									<div class="progress-bar" role="progressbar" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100" style="width: ${actualRoutineProgress}%;">
									${actualRoutineProgress}</div>
								</div>
							</p>
						</div>
					</div>
					<div class="btn-group" role="group" aria-label="...">
						<a class="btn btn-default" href="/client/${actualRoutine.client.username}/routine/${actualRoutine.id}" role="button">Actividades de la Rutina</a>
					</div>
					<div class="panel-footer">
					</div>
				</div>
			</div>
			<div class="col-md-6" >
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Rutinas Finalizadas</div>
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<table id="routineTable"
								class="table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th class="no-sort id"></th>
										<th>Nomber de rutina</th>
										<th>Entrenador</th>
										<th>Fecha de inicio</th>
										<th>Fecha de fin</th>
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
<script src="${contextPath}/resources/js/client-routines.js"></script>
