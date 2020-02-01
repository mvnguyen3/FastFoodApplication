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
<link href="${favico }" rel="shorcut icon" />
<title>Summary</title>
</head>
<body>
	<div class="Navigation-bar">
		<p>WELCOME TO VNTOFU</p>
	</div>
	<a href="/vntofu/customer/cart"><button class="btn btn-primary">C A R T</button></a>
	<h2>Hi ${customerName }!</h2>
	<h3>Below is Your Order Summary</h3>
	<c:if test="${not empty foods}">
		<div style="overflow: auto">
			<table border="1">
				<tr>
					<th>Food</th>
					<th>Quantity</th>
					<th>Price</th>
				</tr>
				<c:forEach items="${foods}" var="food">
					<tr>
						<td>${food.foodName}</td>
						<td>${food.foodQuantity}</td>
						<td>$<fmt:formatNumber type="number" maxIntegerDigits="10"
								value="${food.foodSubTotal}" /></td>
					</tr>
				</c:forEach>

				<tr>
					<th>Sub total</th>
					<th></th>
					<td>${subtotal}</td>
				</tr>
				<tr>
					<th>Tax</th>
					<th></th>
					<td>${tax }</td>
				</tr>
				<tr>
					<th>Total:</th>
					<th></th>
					<td style="background: lightgreen; font-weight: bold">${total}</td>
				</tr>
			</table>
		</div>

	</c:if>
	<div>&nbsp;</div>
	<a href="/vntofu/customer/checkout"><button class="btn btn-primary">C
			H E C K &nbsp; O U T</button></a>

</body>
</html>