
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
<title>Cart</title>
</head>
<body>
	<div class="Navigation-bar">
		<p>WELCOME TO VNTOFU</p>
		<p>
			<a href="/vntofu/customer/food"><button class="btn btn-primary">F
					O O D</button></a>
		</p>
	</div>
	<c:if test="${not empty foods}">
		<div style="overflow: auto; height: 500px; width: 1000px" >
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Sub Total</th>
					<th>Actions</th>
				<tr>
					<c:forEach items="${foods}" var="food">
						<form:form action="/vntofu/customer/updateCart"
							modelAttribute="food" method="POST">
							<tr>
								<td>${food.foodName}</td>
								<td><form:input type="number" path="foodQuantity"
										value="${food.foodQuantity}" min="1" max="100"/></td>
								<td>$<fmt:formatNumber type="number" maxIntegerDigits="10"
										value="${food.foodSubTotal}" /></td>
								<td><form:button class="btn btn-primary" type="submit" value="save">update</form:button>

									<!-- Left Off code: as!@#*&^R --> <a
									href="/vntofu/customer/removeCartItem?foodName=${food.foodName}">remove</a>
								</td>
								<!-- Embedded data -->
								<form:input type="hidden" path="foodName"
									value="${food.foodName}" />
								<form:input type="hidden" path="foodPrice"
									value="${food.foodSubTotal}" />

							</tr>
						</form:form>
					</c:forEach>
			</table>
		</div>
	</c:if>
	<div>
		<a href="/vntofu/customer/ordersummary"><button
				class="btn btn-primary">Order Summary</button></a>
	</div>


	<%-- <form action="checkout" method="post">
		<table>
			<tr>
				<td><button type="submit">Total</button></td>

			</tr>
			<tr>
				<td>${total}</td>
			</tr>
			<tr>
				<td>${checkoutStatus}</td>
			</tr>
		</table>

	</form> --%>
	${status}


</body>
</html>