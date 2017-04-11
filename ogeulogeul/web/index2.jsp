<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*" %>
<%@ page import="member.model.vo.Member, java.util.*"%>
<%
	Member loginUser_index2 = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/index2.css" rel="stylesheet" type="text/css">
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<!-- <script src="js/jquery.fancybox-1.3.4.pack.js"></script> -->
<script src="js/jquery.fancybox.js"></script>
<!-- <script type="text/javascript" src="js/script.js"></script> -->
<script type="text/javascript" src="js/index2.js"></script>
<script type="text/javascript">
var memberId = null;
<%
if(loginUser_index2 != null){  //로그인 했다면
%>
memberId = '<%=loginUser_index2.getMemberId() %>';
<%}%>
	
	$(function() {
		requestList();
		//$("#table").css("padding", "10");
		//$("#innerTable td").css("border", "1px solid white");
		$("#login").click(function() {
			location.href = "loginForm.html";
		});
		
		/*
		$('input[type=checkbox]').each(function(){
			   $(this).wrap("<div>").css({display:"none"});
			   $(this).parent("div").addClass('check').css({
			    "background-image": "url('images/678087-heart-128.png')",
			    width: 34,
			    height: 34,
			    cursor: "pointer"
			   });
		});
		
		$('input[type=checkbox]').filter(":checked").each(function(){
			   $(this).wrap("<div>").css({display:"none"});
			   $(this).parent("div").addClass('check').css({
			    "background-image": "url('images/heart_256x256-32.png')",
			    width: 34,
			    height: 34,
			    cursor: "pointer"
			   });
		});
		*/
			   
		$(".fancybox").fancybox({
			/*
			openEffect	: 'none',
			closeEffect	: 'none'
			*/
		});
		
	});

</script>
<style type="text/css">
 #div {
 /*
    background: url(images/13d71e59547054f229d25f87caba6e23.jpg) no-repeat;
    background-size: cover;
    */
    }
</style>
<title>오글</title>
</head>
<body>
<div id="div">
	<div align="center">
		<br>
		<!--<h1 style="color:white;font-size:4rem;">5geul</h1>-->
	</div>
	<!-- 
	<div id="login" class="borderround">
		<font color="Red"><b>오글</b></font> <font color="#4C4C4C">로그인</font>
	</div>
	 -->
	 <br>
	<br>
	<div>
		<table class="" id="table" width="100%" 
		 border="1" style="BORDER-COLLAPSE: collapse;border-style:dotted;" bordercolor="white">
		<tbody id="listbody">
		</tbody>
			<tr>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/music-headphones-icon-coloring-book-colouring-scallywag-coloring-5aNjee-clipart[1].png">
							</td>
						</tr>
						<tr>
							<td><p><a data-fancybox data-src="">어리다고 놀리지 말아요</a></p></td>
						</tr>
					</table>
				</td>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/music-headphones-icon-coloring-book-colouring-scallywag-coloring-5aNjee-clipart[1].png">
							</td>
						</tr>
						<tr>
							<td><p><a href="javascript:music();">눈물이 차올라서 고갤 들어</a></p></td>
						</tr>
					</table>
				</td>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/f01714817750f2b3d22c5ab81dc53ddf[1].png">
							</td>
						</tr>
						<tr>
							<td>
								<% String str = URLEncoder.encode("이터널 선샤인", "UTF-8"); %>
								<a data-fancybox data-src="http://movie.naver.com/movie/search/result.nhn?query=<%= str %>&section=all&ie=utf8">
								<img src="images/2408384A5240FD0C39F5E9[1].jpg"></a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/5020-200[1].png">
							</td>
						</tr>
						<tr>
							<td><p><a href="">안녕하세오 신세많아오 주인님</a></p></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
<a href="#" style="display:scroll;position:fixed;bottom:10px;right:0px;">
<img src="images/top[1].png" width="100" height="60"></a>


</body>
</html>