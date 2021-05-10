<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
<jsp:include page="includes/imports.jsp" />
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h2 class="card-title text-center">Register User</h2>
						<form class="form-group" action="/user-register" method="post"  enctype="multipart/form-data">
							<c:if test="${message != null}">
								<div class="alert alert-danger alert-dismissible fade in">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${message}</strong>
								</div>
							</c:if>
							
							<div class="form-label-group">
								<label for="inputUserName">User Name</label> <input
									class="form-control" type="text" name="userFullName"
									id="inputUserName" value="${user.userFullName}" required>
							</div>

							<c:if test="${userFullNameValidation != null}">
								<div class="alert alert-danger alert-dismissible fade in">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${userFullNameValidation}</strong>
								</div>
							</c:if>
							
							<div class="form-label-group">
								<label for="inputUserEmail">User Email</label> <input
									class="form-control" type="text" name="userEmailId"
									id="inputUserEmail" value="${user.userEmailId}" required>
							</div>

							<c:if test="${userEmailIdValidation != null}">
								<div class="alert alert-danger alert-dismissible fade in">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${userEmailIdValidation}</strong>
								</div>
							</c:if>

							<div class="form-label-group">
								<label for="inputUserPassword">User Password</label> <input
									class="form-control" type="password" name="userPassword"
									id="inputUserPassword" required>
							</div>

							<div class="form-label-group">
								<label for="inputUserPhoneNumber">User Phone Number</label> <input
									class="form-control" type="text" name="userPhoneNumber"
									id="inputUserPhoneNumber" value="${user.userPhoneNumber}" required>

							</div>

							<c:if test="${ userPhoneNumberValidation != null}">
								<div class="alert alert-danger alert-dismissible fade in">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${ userPhoneNumberValidation }</strong>
								</div>
							</c:if>
							
							<div class="form-label-group">
								<label for="userDisplayPicture">User Display Picture</label> 
								<input type="file" id="userDisplayPicture" class="form-control"
		                        accept="image/x-png,image/gif,image/jpeg" name="userImageFile" />
							</div>
							
							<c:if test="${ userDisplayPictureValidation != null}">
								<div class="alert alert-danger alert-dismissible fade in">
									<a href="#" class="close" data-dismiss="alert"
										aria-label="close">&times;</a> <strong>${ userDisplayPictureValidation }</strong>
								</div>
							</c:if>

							<div style="margin-top: 1%;" class="form-label-group">
								<button class="btn btn-lg btn-primary btn-block text-uppercase"
									type="submit">Register</button>
							</div>


							<div style="margin-top: 1%;" class="form-label-group">
								<a class="btn btn-lg btn-primary btn-block text-uppercase"
									href="/login">Login</a>
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