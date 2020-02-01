<%@page import="com.vntofu.fastfood.domain.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.vntofu.fastfood.domain.Food"%>
<%@page import="java.util.HashSet"%>
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
<c:url value="/css/common.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" type="text/css" />
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon" />
<c:url value="/script/food.js" var="script"></c:url>
<script src="${script}"></script>

<title>Product</title>
</head>
<body>
	<div class="Navigation-bar">
		<%
			Customer customer = (Customer) session.getAttribute("customer");
			session.setAttribute("customerName", customer.getCustomerName());
		%>
		<p>WELCOME TO VNTOFU</p>
		<p>
			Hi <i><%=session.getAttribute("customerName")%></i> !
		</p>
		<div>
			<a href="/vntofu/customer/food"><button class="btn btn-primary">F
					O O D</button></a>
			<!-- Display Cart only if the cart is not empty -->
			<% 
						HashSet<Food> foods = (HashSet<Food>) session.getAttribute("foods");						
						if(!foods.isEmpty()){ %>
			<a href="/vntofu/customer/cart"><button class="btn btn-primary">C
					A R T</button></a>
			<%}%>

			<% 
						try{
							boolean secondTransaction = (boolean) session.getAttribute("secondTransaction");					
							if(session.getAttribute("secondTransaction").equals(true))
					%>
			<a href="/vntofu/customer/thankyou"><button
					class="btn btn-primary">E Z I T</button></a>  &nbsp;   

			<%}catch(NullPointerException ne){}%> <span id="cartLength"></span>

			<!-- 	<a href="/vntofu/customer/addTofuToCart2">test</a> -->


		</div>

	</div>
	<div style="overflow: auto; height: 500px; width: 1000px">
		<table border="1">
			<thead>
				<tr>
					<th>Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Actions</th>
					<!-- <th>Status</th> -->
				</tr>
			</thead>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Plain Tofu</td>
						<td>6 for $1.00</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>

						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button>

						</td>
						<td><p id="plainTofuStatus"></p></td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Plain Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.00" />
						<form:input type="hidden" path="foodCategory" value="by pieces" />
					</tr>
				</form:form>
			</tbody>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Big Plain Tofu</td>
						<td>$1.75 each</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${bigTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Big Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.75" />
						<form:input type="hidden" path="foodCategory" value="by each" />
					</tr>
				</form:form>
			</tbody>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Spicy Tofu</td>
						<td>6 for $1.00</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${spicyTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Spicy Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.00" />
						<form:input type="hidden" path="foodCategory" value="by pieces" />
					</tr>
				</form:form>
			</tbody>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Onion Tofu</td>
						<td>6 for $1.00</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${onionTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Onion Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.00" />
						<form:input type="hidden" path="foodCategory" value="by pieces" />
					</tr>
				</form:form>
			</tbody>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Cilantro Tofu</td>
						<td>6 for $1.00</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${cilantroTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Cilantro Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.00" />
						<form:input type="hidden" path="foodCategory" value="by pieces" />
					</tr>
				</form:form>
			</tbody>
			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Mushroom Tofu</td>
						<td>$1.00 each</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${mushroomTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Mushroom Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.00" />
						<form:input type="hidden" path="foodCategory" value="by each" />
					</tr>
				</form:form>
			</tbody>

			<!-- asdfwaef123 -->

			<tbody>
				<form:form action="addTofuToCart" method="post"
					modelAttribute="food">
					<tr>
						<td>Ky Tofu</td>
						<td>$1.50 each</td>
						<td><form:input type="number" min="1" max="100" path="foodQuantity" value="1" />
							<form:errors cssClass="error" path="foodQuantity">
							</form:errors></td>
						<td><form:button class="btn-primary" type="submit">Add To Cart</form:button></td>
						<td>${kyTofuQuantity}</td>
						<!-- Embedded data -->
						<form:input type="hidden" path="foodName" value="Ky Tofu" />
						<form:input type="hidden" path="foodPrice" value="1.50" />
						<form:input type="hidden" path="foodCategory" value="by each" />
					</tr>
				</form:form>
			</tbody>
		</table>
	</div>


	${error} ${status}
</body>
</html>