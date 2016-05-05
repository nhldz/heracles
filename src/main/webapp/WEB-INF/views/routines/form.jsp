<form id="routineForm" method="POST" data-toggle="validator">
		<div id="myContent" class="row hidden">
			<div class="col-lg-4">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<input type="hidden" name="id" />
							<input type="hidden" name="enabled" value="true"/>
						</div>
						<div class="form-group">
							<label for="name">Nombre:</label> <input name="name"
								class="form-control" required />
						</div>
						<div class="form-group">
							<label for="type">Cliente:</label> 
							<select name="type" required>
								<option value="">Seleccione..</option>
								<c:forEach items="${clients}" var="client">
									<option value="${client.id}">${client.name}</option>
								</c:forEach>
							</select>
						</div>
<!-- 						<div class="form-group"> -->
<!-- 							<label for="equipment">Herramienta:</label>  -->
<!-- 							<select	name="equipment" required> -->
<!-- 								<option value="">Seleccione..</option> -->
<%-- 								<c:forEach items="${equipments}" var="equipment"> --%>
<%-- 									<option value="${equipment}">${equipment.name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="bodyParts">Partes Trabajadas:</label>  -->
<!-- 							<select	name="bodyParts" multiple="multiple" required> -->
<%-- 								<c:forEach items="${bodyParts}" var="bodyPart"> --%>
<%-- 									<option value="${bodyPart}">${bodyPart.name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
<!-- 						</div> -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="description">Descripci&oacute;n:</label> -->
<!-- 							<textarea name="description" class="form-control" rows="3" required></textarea> -->
<!-- 						</div> -->
						<button type="submit" class="btn btn-success">Aceptar</button>
						<button type="button" class="btn btn-default" onclick="toogle('myContent'); toogleButtons();">Cerrar</button>
					</div>
				</div>
			</div>
		</div>
	</form>