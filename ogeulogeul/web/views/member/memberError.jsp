<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%
	String errorMessage = (String)request.getAttribute("message");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberError</title>
</head>
<body>
<% if(exception != null){ %>
<h1>exception 발생 : <%= exception.getMessage() %></h1>
<% } %>
<% if(errorMessage != null){ %>
	<h1><%= errorMessage %></h1>
<% } %>	
<h1><a href="/ogeul/index.jsp">메인 화면으로 이동</a></h1>
</body>
</html>