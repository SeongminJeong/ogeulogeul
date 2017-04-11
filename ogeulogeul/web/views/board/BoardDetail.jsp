<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="member.model.vo.Member, java.util.*, java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<link href="/ogeul/css/BoardDetail.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/ogeul/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/ogeul/js/BoardDetail.js"></script>
<script>

var memberId = null;
var boardNum = '<%= Integer.parseInt(request.getParameter("boardNum")) %>';
var category = '<%= Integer.parseInt(request.getParameter("category")) %>';
var title = '<%= URLEncoder.encode(request.getParameter("title"), "UTF-8") %>';
var str;

<%
if (loginUser!=null) {
%>
memberId = '<%= loginUser.getMemberId() %>';
<%}%>

$(function() {
	if (memberId == null)
		document.getElementById("tr").style.visibility = "hidden";
	requestList();
});

<% 
int category = Integer.parseInt(request.getParameter("category"));
String title = URLEncoder.encode(request.getParameter("title"), "UTF-8");
String str = "";

if (category == 1)
	str = "http://search.maxmovie.com/search?sword="
		+ title;
else if (category == 2)
	str = "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
		+ title;
else if (category == 3)
	str = "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
		+ title;
else if (category == 4)
	str = "http://music.naver.com/search/search.nhn?query="
			+ title + "&target=track";
%>

/*
if (category == 1) {
	str = "http://movie.naver.com/movie/search/result.nhn?query="
			+ title + "&section=all&ie=utf8";
}
else if (category == 2)
	str = "http://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="
			+ title;
else if (category == 3)
	str = "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
			+ title;
else if (category == 4)
	str = "http://music.naver.com/search/search.nhn?query="
			+ title + "&target=track";
			
*/
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head> 
<body onKeyPress="if(event.keyCode==13) insertReply();">
	<div class="content" style="height:400px;overflow:auto;">
		<c:import url="<%=str %>"></c:import>
	</div>
	<div style="height:300px;overflow:auto;border:1px solid cyan">
		<table align="center">
		<tbody id="listbody">
		</tbody>
		<tr id="tr">
			<td>
				<input type="text" size="35" id="reply">
				<button onclick="insertReply();">댓글달기</button>
			</td>
		</tr>
		</table>
		
	</div>
</body>
</html>


