<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link href="/ogeul/css/yourOgeul.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/ogeul/js/yourOgeul.js"></script>
<script>
	var memberId = '<%=request.getParameter("memberId")%>';
	window.onload = function() {
		requestList();
	}
</script>
</head>
<body>
<div style="height:500px;overflow:auto;">
<table id="table" align="center" width="70%" border="1" style="margin-top:50px;">
	<tbody id="listbody"></tbody>
</table>
</div>
</body>
</html>