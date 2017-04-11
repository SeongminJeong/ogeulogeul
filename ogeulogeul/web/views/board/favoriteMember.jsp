<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/ogeul/css/favoriteMember.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/ogeul/js/favoriteMember.js"></script>
<script>
	var memberId = '<%=request.getParameter("memberId")%>';
	window.onload = function() {
		requestList();
	}
</script>
</head>
<body>
<div style="height:500px;overflow:auto;">
<table align="center">
	<tr>
		<th><b id="memberId"></b> 님이 즐겨찾는 오글러 <b id="favoriteMemberCnt"></b></th>
	</tr>
	<tbody id="listbody"></tbody>
</table>
<br>
<br>
</div>
</body>
</html>