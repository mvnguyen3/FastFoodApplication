/**
 * Food.js for food.jsp
 */

function addToCart(param) {
	var plainTofuQuantity = document.getElementById(param).value;
	console.log("Making ajax call...");
	
}

// Function will get invoke after dom is loaded !!!
document.addEventListener("DOMContentLoaded", function() {
	var cartNotification = document.getElementById("cartLength");
	// Make an ajax call to show number of items in cart
	console.log("Making an ajax call");
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			cartNotification.innerText = this.responseText;
		}
	};
	xhttp.open("GET", "http://localhost:8085/vntofu/ajax/getCartLength", true);
	xhttp.send();

});
