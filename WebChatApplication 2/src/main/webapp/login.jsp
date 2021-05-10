<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<jsp:include page="includes/imports.jsp" />
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h2 class="card-title text-center">Sign In</h2>
						<form class="form-signin" action="/user-login" method="post">

							<div class="form-label-group">


								
								<c:if test="${registrationSucessfull != null}">
									<div class="alert alert-success alert-dismissible fade in">
										<a href="#" class="close" data-dismiss="alert"
											aria-label="close">&times;</a> <strong>${ registrationSucessfull }</strong>
									</div>
								</c:if>
								
								<c:if test="${message != null}">
									<div class="alert alert-danger alert-dismissible fade in">
										<a href="#" class="close" data-dismiss="alert"
											aria-label="close">&times;</a> <strong>${message}</strong>
									</div>
								</c:if>

							</div>

							<div class="form-label-group">
								<label for="inputUserEmail">User Email</label> <input type="text"
									id="inputUserEmail" class="form-control" placeholder="User Email"
									name="userEmailId" value="${user.userEmailId}" required autofocus>

							</div>

							<div class="form-label-group">
								<label for="inputPassword">Password</label> <input
									type="password" id="inputPassword" class="form-control"
									placeholder="Password" name="userPassword" required>
							</div>
							<div style="margin-top: 1%;" class="form-label-group">
								<button class="btn btn-lg btn-primary btn-block text-uppercase"
									type="submit">Log in</button>
							</div>
							<div style="margin-top: 1%;" class="form-label-group">
								<a class="btn btn-lg btn-primary btn-block text-uppercase"
									href="/register">Register</a>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
</body>
</html>