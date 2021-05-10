<%@ page isELIgnored="false" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User List</title>
<jsp:include page="includes/imports.jsp" />
<jsp:include page="includes/navbar.jsp" />
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h2 class="card-title text-center">Users</h2>

						<c:if test="${message != null}">
							<div class="alert alert-danger alert-dismissible fade in">
								<a href="#" class="close" data-dismiss="alert"
									aria-label="close">&times;</a> <strong>${message}</strong>
							</div>
						</c:if>

						<c:if test="${users.size() gt 0}">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>User ID</th>
										<th>User Display Picture</th>
										<th>User Name</th>									
										<th>User Email</th>
										<th>User Phone Number</th>									
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${users}">
										<tr>
											<td>${user.userId}</td>
											<td><img class="img-circle" src=${user.userDisplayPicture } width="50px" height="50px"/></td>
											<td>${user.userFullName}</td>
											<td>${user.userEmailId}</td>
											<td>${user.userPhoneNumber}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</c:if>
					</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
</body>
</html>