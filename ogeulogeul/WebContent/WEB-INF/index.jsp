<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<% 
String str = "%EC%9D%B4%ED%84%B0%EB%84%90";
str = URLDecoder.decode(str, "KSC5601");
str = new String(str.getBytes("KSC5601"), "8859_1");
%>
<%=str%>
</body>
</html>