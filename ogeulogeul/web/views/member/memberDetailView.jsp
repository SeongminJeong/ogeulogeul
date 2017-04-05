<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" 
    errorPage="memberError.jsp" %>
    
<%
   //scriptlet tag 영역 : 일반 자바 로직 코드가 작성되는 구역
   Member loginUser = (Member)session.getAttribute("loginUser");
%>
<%
	Member m = (Member)request.getAttribute("member");
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberDetailView</title>
</head>
<body>
<br style="clear:both;">
<h1 align="center"><%=loginUser.getName() %> 회원님의 상세 정보</h1>
<table align="center" width="500" cellspacing="5">
<tr><td bgcolor="pink" width="200" >이 름</td>
   <td><%= loginUser.getName() %></td></tr>
<tr><td bgcolor="pink">아이디</td>
   <td ><%= loginUser.getMemberId() %></td></tr>
<tr><td bgcolor="pink">성 별</td>
   <td><%
   		if(loginUser.getGender().equals("M")){
       %>남자 
       <% }else{ %> 
          여자 
       <% } %></td></tr>
<tr><td bgcolor="pink">생년월일</td>
   <td><%= loginUser.getBirth() %></td></tr>
<tr><td bgcolor="pink">이메일</td>
   <td><%= loginUser.getEmail() %></td></tr>
<tr><td bgcolor="pink">연락처</td>
   <td><%= loginUser.getPhone() %></td></tr>
  

</table>
<form action="/ogeul/minsert" method="post" enctype="multipart/form-data">
<table align="center" width="500" cellspacing="5">

<tr><td  bgcolor="pink">작성자</td>
	<td><input type="text" name="bwriter" 
		value="<%= loginUser.getMemberId() %>" readonly>	
	</td></tr>
<tr><td  bgcolor="pink">메 모</td>
	<td  bgcolor="pink"><textarea rows="5" cols="50" name="mcontent"></textarea></td></tr>
	<tr><td bgcolor="pink" colspan="2" align="center">
	<a href="/ogeul/views/member/memberUpdate.jsp">수정페이지로 이동</a> 	
</td></tr>
<tr><td bgcolor="pink" colspan="2" align="center">
	<a href="/ogeul/index.jsp">시작페이지로 이동</a> 
</td></tr>
<tr><td  bgcolor="pink" colspan="2" align="center">
<input type="submit" value="등록"> &nbsp;
</td></tr>
</table>
</form>
</body>
</html>