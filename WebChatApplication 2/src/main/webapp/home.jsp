<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home User</title>
<jsp:include page="includes/imports.jsp" />
</head>
<body>

	<div class="container">
		<div class="row">
		<br><br><br><br><br><br><br>
		<center><h1>Welcome to Chat Application</h1></center>
		<br>
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="card card-signin my-5">
					<div class="card-body">

						
						
						<div style="margin-top: 1%;" class="form-label-group">
							<a class="btn btn-lg btn-primary btn-block text-uppercase"
								href="/login">Login</a>
						</div>
						<br>
						<div style="margin-top: 1%;" class="form-label-group">
							<a class="btn btn-lg btn-primary btn-block text-uppercase"
								href="/register">Register</a>
						</div>
						
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>

</body>
</html>