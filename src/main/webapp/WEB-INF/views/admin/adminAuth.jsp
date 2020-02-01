<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon"/>	
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
</style>
<title>Admin</title>
</head>
<body>
	<h2>VN TOFU <b>Admin mode</b></h2>
	<p>
		<a href="/vntofu/adminLogin/login?logout">Logout
			${pageContext.request.userPrincipal.authorities[0]}</a>
	</p>
	<a href="/vntofu/viewCustomer"><button class="btn btn-primary">Customer</button></a>
	
</body>
</html>