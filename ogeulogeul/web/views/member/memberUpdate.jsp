<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="memberError.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateView</title>
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	function checkValue(){
		if($("input[name=userpwd]").val() == 
			$("input[name=userpwd2]").val())
			return true;
		else{
			alert("암호와 암호 확인의 값이 일치하지 않습니다.");
			$("input[name=userpwd]").select();
			return false;
		}
	}

</script>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>
	<br style="clear: both;">
	<h1 align="center" >회원 정보 수정 페이지</h1>
	<p />
	<form action="/ogeul/update" method="post"
		onsubmit="return checkValue();">
		<table align="center" top= "100px" width="370" cellspacing="5">
			<tr>
				<td bgcolor="purple" width="200">이 름</td>
				<td><input type="text" name="username" size="20" readonly
					value="<%= loginUser.getName() %>"></td>
			</tr>
			<tr>
				<td bgcolor="purple">아이디</td>
				<td><input type="text" name="userid" size="20" readonly
					value="<%= loginUser.getMemberId() %>"></td>
			</tr>
			<tr>
				<td bgcolor="purple">암 호</td>
				<td><input type="password" name="userpwd" size="20"
					value="<%= loginUser.getPassword() %>"></td>
			</tr>
			<tr>
				<td bgcolor="purple">암호확인</td>
				<td><input type="password" name="userpwd2" size="20"></td>
			</tr>
			<tr>
				<td bgcolor="purple">성 별</td>
				<td>
					<% if(loginUser.getGender().equals("M")){ %> <input type="radio"
					name="gender" value="M" checked> 남자 &nbsp; <input
					type="radio" name="gender" value="F"> 여자 <% }else{ %> <input
					type="radio" name="gender" value="M"> 남자 &nbsp; <input
					type="radio" name="gender" value="F" checked> 여자 <% } %>
				</td>
			</tr>
			<tr>
				<td bgcolor="purple">생년월일</td>
				<td><input type="text" name="birth" readonly
					value="<%=loginUser.getBirth() %>"></td>
			</tr>
		<tr><td bgcolor="purple">이메일</td>
   <td><input type="email" name="email" value="<%= loginUser.getEmail() %>"></td></tr>
		
        <tr><td bgcolor="purple">연락처</td>
   <td><input type="tel" name="phone" value="<%= loginUser.getPhone() %>"></td></tr>
			<tr>
				<td bgcolor="purple" colspan="2" align="center"><input
					type="submit" value="수정하기"> &nbsp; <a
					href="/ogeul/delete?userid=<%= loginUser.getMemberId() %>">탈퇴하기</a>
				</td>
			</tr>
			<tr>
				<td bgcolor="purple" colspan="2" align="center"><a
					href="/ogeul/index.jsp">시작페이지로 이동</a></td>
			</tr>
		</table>
	</form>

</body>
</html>