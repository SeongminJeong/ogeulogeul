function confirmNull() {
	
	var input = document.getElementsByTagName("INPUT");
	var length = input.length;
	
	for (i=0 ; i<length ; i++)
		if (input[i].getAttribute("type", "text") && input[i].value == "")
			input[i].style.borderColor = "red";
	
	if (document.getElementById("name").value=="") {
		alert("이름을 입력해주세요");
		document.getElementById("name").focus();
	}
	
	else if (document.getElementById("id").value=="") {
		alert("아이디를 입력해주세요");
		document.getElementById("id").focus();
	}
	
	else if (document.getElementById("phone_pre").value=="" ||
			document.getElementById("phone_middle").value=="" ||
			document.getElementById("phone_tail").value=="" ) {
		
		alert("핸드폰 번호를 올바르게 입력해주세요");
		
		if (document.getElementById("phone_pre").value=="")
			document.getElementById("phone_pre").focus();
		if (document.getElementById("phone_middle").value=="")
			document.getElementById("phone_middle").focus();
		if (document.getElementById("phone_tail").value=="")
			document.getElementById("phone_tail").focus();
	}
}

function removeBorder(node) {
	if (!(node.value == "") && node.style.borderColor == "red") {
		node.setAttribute("style", "border-color: none");
	}
	
 }
