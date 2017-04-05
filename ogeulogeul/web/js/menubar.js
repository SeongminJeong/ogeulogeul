
function popupOpen(){
	
	
	window.name = "menubar.jsp";
	
	var popUrl = "/ogeul/views/member/loginForm.html";	//팝업창에 출력될 페이지 URL

	var popOption = "top=280%, left=750%, width=500, height=500, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
	
	var menubar = window.open(popUrl,"loginFormPop",popOption);
/* 	if (menubar.closed)
	loginFormPop.loginForm.submit();
	
	 */
	 return false;
	}

