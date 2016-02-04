<div class="container">
	<div class="row">

		<div class="col-lg-6">

			<div class="register-info-wraper">
				<div id="register-info">
					<div class="cont2">
						<div class="thumbnail">
							<img src="${contextPath}/resources/img/profile-img.png" alt="profile-img"
								class="img-circle">
						</div>
						<!-- /thumbnail -->
						<h2>${user.name}${user.surname}</h2>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="cont3">
								<p>
									<ok>Username:</ok>
									${user.name}
								</p>
								<p>
									<ok>Mail:</ok>
									${user.mail}
								</p>
								<p>
									<ok>Location:</ok>
									Madrid, Spain
								</p>
								<p>
									<ok>Address:</ok>
									Blahblah Ave. 879
								</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="cont3">
								<p>
									<ok>Registered:</ok>
									April 9, 2010
								</p>
								<p>
									<ok>Last Login:</ok>
									January 29, 2013
								</p>
								<p>
									<ok>Phone:</ok>
									+34 619 663553
								</p>
								<p>
									<ok>Mobile</ok>
									+34 603 093384
								</p>
							</div>
						</div>
					</div>
					<!-- /inner row -->
					<hr>
					<button type="button" class="btn btn-primary">Editar</button>
				</div>
			</div>
		</div>
	</div>
</div>