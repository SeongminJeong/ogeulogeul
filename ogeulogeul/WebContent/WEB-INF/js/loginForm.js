function confirmNull() {
	if (document.getElementById("id").value=="" && document.getElementById("pass").value=="") {
			alert("아이디와 비밀번호를 입력해주세요");
			
	}
	else if (document.getElementById("id").value=='') {
		alert("아이디를 입력해주세요");
	}
	else if (document.getElementById("pass").value=='') {
		alert("비밀번호를 입력해주세요");
	}
}