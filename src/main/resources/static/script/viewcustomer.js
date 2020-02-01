/**
 * This is a viewcustomer.js File
 */
// JavaScript code goes here

function getId(param){
	check(param);
}

function check(param){
	var idRow = param+"row";
	var btnDel = param+"delete";
	if(document.getElementById(param).innerText === "Paid!"){
		console.log("Restore to default");
		document.getElementById(param).style.color= "";
		document.getElementById(param).style.background= "";
		document.getElementById(param).innerText = "Check";
		document.getElementById(btnDel).style.display = "none";

		
	}else{
		
		console.log("Chaning color for id: " + param);
		console.log("Chaning color for id: " + idRow);
			
		document.getElementById(param).style.color= "black";
		document.getElementById(param).style.background= "white";
		document.getElementById(param).innerText = "Paid!";
		document.getElementById(btnDel).style.display = "inline-block";
		document.getElementById(btnDel).style.color = "red";
		document.getElementById(btnDel).style.background = "white";
		
		
		
	}				
}
function deleteCustomer(param){
	//alert("haha");
	var idRow = param+"row";
	var btnDel = param+"delete";
	
	document.getElementById(idRow).style.opacity = 0.5;
	document.getElementById(idRow).style.pointerEvents = "none";
	document.getElementById(btnDel).innerText= "Deleting...";
	document.getElementById(param).style.display= "none";
	console.log(idRow);		
}


$(document).ready(function(){
	// jQuery code goes here...
	
	
});






















