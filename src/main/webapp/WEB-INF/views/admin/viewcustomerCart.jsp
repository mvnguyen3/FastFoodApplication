<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@  taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<c:url value="/css/common.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" type="text/css" />
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon"/>
<title>Admin</title>
</head>
<body>
	<h2>
		VN TOFU <b>Admin mode</b>
	</h2>
	<p>
		<a href="/vntofu/adminLogin/login?logout">Logout
			${pageContext.request.userPrincipal.authorities[0]}</a>
	</p>
	<div class="Navigation-bar">
		<table>
			<tr>
				<td><a href="/vntofu/viewCustomer"><button
							class="btn btn-primary">B A C K</button></a></td>
							
			</tr>
		</table>
	</div>


	<div style="overflow: auto; height: 600px; width: 800px">
		<table border="1">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Sub Total</th>
				</tr>
			</thead>
			<tbody>
				<fmt:setLocale value="en_US" />
				<c:if test="${not empty foods}">
					<c:forEach items="${foods}" var="food">
						<tr>
							<td>${food.foodName}</td>
							<td>${food.foodQuantity}</td>
							<td><fmt:formatNumber type="currency" currencySymbol="$">${food.foodSubTotal}</fmt:formatNumber></td>
						</tr>
					</c:forEach>
				</c:if>

			</tbody>
		</table>

	</div>




</body>
</html>





























































