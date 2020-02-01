<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@  taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon"/>	
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
</style>
<title>VNTOFU</title>
</head>
<body>
	<h2>Admin Use</h2>
	<c:if test="${not empty errorMessage}">
		${errorMessage}
	</c:if>
	<div class="form-group">
		<form name='login' action='/vntofu/adminLogin' method='POST'>
			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name="username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password'></td>
				</tr>

				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">login</button></td>
					<td><a href="/"> Customer Use</a></td>

				</tr>

			</table>
			<input type='hidden' name='${_csrf.parameterName}'
				value='${_csrf.token}' />
		</form>
	</div>







</body>
</html>










































