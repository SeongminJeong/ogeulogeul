<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member" 
    errorPage="memberError.jsp" %>
<%
	Member m = (Member)request.getAttribute("member");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberDetailView</title>
<script>    
        //$(document.ready(function(){ .. });
        $(function(){
            $("#dialog").dialog();
        });
    </script>

</head>
<body>
<div id=dialog>
<br style="clear:both;">
<h1 align="center"><%= m.getName() %> 회원님의 상세 정보</h1>
<table align="center" width="500" cellspacing="5">
<tr><td bgcolor="#ccff00" width="200">이 름</td>
   <td><%= m.getName() %></td></tr>
<tr><td bgcolor="#ccff00">아이디</td>
   <td><%= m.getMemberId() %></td></tr>
<tr><td bgcolor="#ccff00">생년월일</td>
   <td><%= m.getBirth() %></td></tr>
<tr><td bgcolor="#ccff00" colspan="2" align="center">
	<a href="/ogeul/index.jsp">시작페이지로 이동</a> 
</td></tr>
</table>
</div>

</body>
</html>