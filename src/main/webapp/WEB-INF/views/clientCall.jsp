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
<c:url value="/css/common.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" type="text/css" />
<c:url value="/images/tofu-plate_23965-24.jpg" var="favico" />
<link href="${favico }" rel="shorcut icon" />



<title>Ajax call test</title>
</head>
<body>
	 CustomerId <input type="text" value="1" id="customerId"  /> 
	<button type="button" onclick="loadDoc('customerId')">Request data</button>
	
	<p id="responseText"> </p>
	<p>Click the button several times to see if the time changes, or if
		the file is cached.</p>
</body>
<script type="text/javascript">
function loadDoc(param){
	var customerId = document.getElementById(param).value;
	console.log("Clicked");
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "http://localhost:8085/vntofu/ajax/getFoods?customerId=" + customerId, true);
	xhr.onload = function () {
	console.log(xhr.responseText);
	document.getElementById("responseText").innerText = xhr.responseText;
	
};
xhr.send();
}
</script>

</html>












































