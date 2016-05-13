<%@ include file="../templates/includes/common_head.jsp"%>
<div id="wrapper">
  <%@ include file="../trainer/menu.jsp" %>
  <div id="page-wrapper">
    <div class="row">
      <div class="panel panel-primary">
        <div class="panel-heading">Alta de Rutina</div>
        <div class="panel-body">
          <ul class="nav nav-tabs" role="tablist">
            <li id="routuineList" role="presentation" class="active">
              <a href="#routine" aria-controls="home" role="tab" data-toggle="tab">Rutina</a>
            </li>
            <li id="activityList" role="presentation">
              <a href="#activity" aria-controls="profile" role="tab" data-toggle="tab">Actividades</a>
            </li>
            <li id="excerciseList" role="presentation" class="disabled">
              <a href="#excercise" aria-controls="profile" role="tab" >Detalle Actividad</a>
            </li>
          </ul>
<!--           <form id="routineForm" method="POST" data-toggle="validator" action="/routines"> -->
            <div class="tab-content" style="min-height:700px">
              <div role="tabpanel" class="tab-pane active" id="routine">
                <div id="myContent" class="row">
                  <div class="col-xs-offset-2 col-xs-8">
                    <div class="panel-body">
                      <div class="form-group">
                        <label for="name">Nombre Rutina:</label>
                        <input type="text" name="name" class="form-control col-xs-4"/>
                      </div>
                      <div class="form-group">
                        <label for="clientid">Alumno:</label>
                        <div class="panel panel-default">
                          <div class="panel-body">
                            <div class="dataTable_wrapper">
                              <table id="clientsTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                <thead>
                                  <tr>
                                    <th class="no-sort id"></th>
                                    <th>Apellido y Nombre</th>
                                    <th>Usuario</th>
                                    <th>Email</th>
                                    <th>Tel&eacute;fono</th>
                                  </tr>
                                </thead>
                                <tbody></tbody>
                              </table>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div role="tabpanel" class="tab-pane" id="activity">
                <div id="groupActivity" class="form-group">
                  <div class="col-md-offset-2 col-md-8">
                    <div class="panel-body">
                      <div class="dataTable_wrapper">
                        <table id="activityTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                          <thead>
                            <tr>
                              <th class="no-sort id"></th>
                              <th>Nombre</th>
                              <th>Descripcion</th>
                              <th>Cant. de Ejercicios</th>
                            </tr>
                          </thead>
                          <tbody></tbody>
                        </table>
                      </div>
                      <div id="btnsTable">
                        <button type="button" class="btn btn-success" id="btnAdd">Agregar</button>
                        <button type="button" class="btn btn-default" id="btnEdit">Editar</button>
                        <button type="button" class="btn btn-warning" id="btnDelete">Borrar</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div role="tabpanel" class="tab-pane" id="excercise">
                <div id="myContent" class="row">
                  <div class="col-md-offset-2 col-md-8">
                    <div class="panel-body">
                      <div class="form-group">
                        <label for="name">Nombre Actividad:</label>
                        <input type="text" name="name" class="form-control col-xs-4"/>
                      </div>
                      <div class="form-group">
                        <label for="description">Descripci&oacute;n:</label>
                        <input type="text" name="description" class="form-control col-xs-4"/>
                      </div>
                      <div class="form-group">
                        <label for="clientid">Alumno:</label>
                        <div class="panel panel-default">
                          <div class="panel-body">
                            <div class="dataTable_wrapper">
                              <table id="excerciseTable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
                                <thead>
                                  <tr>
                                    <th class="no-sort id"></th>
                                    <th>Apellido y Nombre</th>
                                    <th>Usuario</th>
                                    <th>Email</th>
                                    <th>Tel&eacute;fono</th>
                                  </tr>
                                </thead>
                                <tbody></tbody>
                              </table>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="panel-footer">
            <div id="btnsForm" style="text-align: center;">
              <button type="button" id="save" class="btn btn-success">Aceptar</button>
              <button type="button" class="btn btn-danger">Cancelar</button>
            </div>
          </div>
<!--         </form> -->
      </div>
    </div>
  </div>
</div>

<%@ include file="../templates/includes/common_foot.jsp"%>
<script src="${contextPath}/resources/js/routines/create.js"></script>
