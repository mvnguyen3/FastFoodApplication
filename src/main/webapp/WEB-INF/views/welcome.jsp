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
<script type="text/javascript">
	function addDashesPhone(f) {
		var r = /(\D+)/g, npa = '', nxx = '', last4 = '';
		f.value = f.value.replace(r, '');
		npa = f.value.substr(0, 3);
		nxx = f.value.substr(3, 3);
		last4 = f.value.substr(6, 4);
		f.value = npa + '-' + nxx + '-' + last4;
	}
</script>
<%-- <c:url value="/css/common.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" type="text/css" /> --%>
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon"/>
<title>Welcome</title>
<style>
body {
	background-color: whitesmoke;
	max-width: 700px;
	margin: auto;
}
table {
	border-collapse: collapse;
	width: 50%;
}
</style>
</head>
 <!-- sdfdsfsdfsdf -->
<body>
	<h2>Welcome to VNTOFU</h2>
	<div>
		<form:form action="/vntofu/customer/saveCustomer" method="post"
			modelAttribute="customer">
			<table>
				<tr>
					<td>Name:</td>
					<td class="cellspacing"><form:input class="inputBox"
							type="text" path="customerName" value="Zoey" />
							<form:errors cssClass="error" path="customerName"></form:errors>${customerNameError}
							</td>
					
				</tr>
				<tr>
					<td>Phone:</td>
					<td class="cellspacing"><form:input class="inputBox"
							type="text" onkeypress="addDashesPhone(this)" maxlength="12"
							path="customerPhone" value="312-208-0096" />
							<form:errors cssClass="error" path="customerPhone"></form:errors>${customerPhoneError}
							</td>
					
				</tr>
				<tr>
					<td><button class="btn btn-primary" type="submit" value="save">Login</button></td>
					<td class="cellspacing"><a href="/vntofu/adminLogin">Admin
							Use</a></td>
				</tr>
			</table>
		</form:form>

	</div>
</body>
</html>