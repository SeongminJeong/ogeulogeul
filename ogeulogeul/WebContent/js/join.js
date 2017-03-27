function confirmNull() {
	
	var input = document.getElementsByTagName("INPUT");
	var length = input.length;
	
	for (i=0 ; i<length ; i++)
		if (input[i].getAttribute("type", "text") && input[i].value == "")
			input[i].style.borderColor = "red";
	
	if (document.getElementById("name").value=="") {
			alert("이름을 입력해주세요");
			document.getElementById("name").focus();
			return;
			
	}
	
	else if (document.getElementById("id").value=="") {
		alert("아이디를 입력해주세요");
		document.getElementById("id").focus();
		return;
	}	

	else if (document.getElementById("id").value=="") {
		alert("패스워드를 입력해주세요");
		document.getElementById("password").focus();
		return;
	}

	else if (document.getElementById("id").value=="") {
		alert("패스워드 확인을 입력해주세요");
		document.getElementById("password2").focus();
		return;
	}
	
	else if (document.getElementById("email_id").value=='' || 
			document.getElementById("email_domain").value=='') {
		
		alert("이메일을 올바르게 입력해주세요");
		
		if (document.getElementById("email_id").value=='') 
			document.getElementById("email_id").focus();
		if (document.getElementById("email_domain").value=='')
			document.getElementById("email_domain").focus();
		
		return;
	}
	
	else if (document.getElementById("year").value=='' || 
			document.getElementById("month").value=='' ||
			document.getElementById("date").value=='') {
		
		alert("생년월일를 올바르게 입력해주세요");
		
		if (document.getElementById("year").value=='')
			document.getElementById("year").focus();
		if (document.getElementById("month").value=='')
			document.getElementById("month").focus();
		if (document.getElementById("date").value=='')
			document.getElementById("date").focus();
		
		return;
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
		
		return;
	}
	
	
}

function removeBorder(node) {
	if (!(node.value == "") && node.style.borderColor == "red") {
		node.setAttribute("style", "border-color: none");
	}
	
 }

function emailSetting() {
	var email_select_var = document.getElementById("email_select");
	var text = email_select_var[email_select_var.selectedIndex].text;
	var email_domain_var = document.getElementById("email_domain");
	
	if (text == '직접 입력') {
		email_domain_var.value = '';
		email_domain_var.focus();
	}
	else
		email_domain_var.value = text;
}