<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
<jsp:include page="includes/imports.jsp" />
<jsp:include page="includes/navbar.jsp" />
</head>
<body>
	<div align = "center">
		<img src=${user.userDisplayPicture } width="200px" height="200px"/><br><br>
		Name :- ${user.userFullName }<br><br>
		Email ID  :- ${user.userEmailId }<br><br>
		Phone Number  :- ${user.userPhoneNumber }<br><br>
	</div>
	<div align = "center" style="margin-top: 1%;" class="form-label-group">
							<a class="btn btn-lg btn-primary btn-block text-uppercase"
								href="/user-api/list-users">All Users</a>
						</div>
</body>
</html>