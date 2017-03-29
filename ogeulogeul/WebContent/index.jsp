<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.net.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/index.css" rel="stylesheet" type="text/css">
<link href="css/jquery.fancybox.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<!-- <script src="js/jquery.fancybox-1.3.4.pack.js"></script> -->
<script src="js/jquery.fancybox.js"></script>
<!-- <script type="text/javascript" src="js/script.js"></script> -->
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
	$(function() {
		//$("#table").css("padding", "10");
		//$("#innerTable td").css("border", "1px solid white");
		$("#login").click(function() {
			location.href = "loginForm.html";
		});
	});
	
	$(document).ready(function() {
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
    background: url(images/13d71e59547054f229d25f87caba6e23.jpg) no-repeat;
    background-size: cover;
    }
   
</style>
<title>오글</title>
</head>
<body>
<div id="div">
	<div align="center">
		<br><h1 style="color:white;font-size:4rem;">5geul</h1>
	</div>
	<div id="login" class="borderround">
		<font color="Red"><b>오글</b></font> <font color="#4C4C4C">로그인</font>
	</div>
	<a data-fancybox data-src="popup.jsp">
	<div id="upload" class="borderround">
		<font color="Red"><b>오글</b></font> <font color="#4C4C4C">업로드</font>
	</div>
	</a>
	<br>
	<br>
	<div>
		<table id="table" width="100%" 
		 border="1" style="BORDER-COLLAPSE: collapse;border-style:dotted;" bordercolor="white">
			<tr>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/tv[1].png">
							</td>
						</tr>
						<tr>
							<td>
								<p>
									<!-- <a href="http://www.naver.com" data-fancybox-type="iframe" class="layerIframe">사랑은 돌아오는거야ㅋ</a> -->
									<a data-fancybox data-src="popup.jsp">사랑은 돌아오는거야</a>
								</p>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/tv[1].png">
							</td>
						</tr>
						<tr>
							<td><p><a href="">밥 먹을래 나랑 살래</a></p></td>
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
							<td><p><a href="">미래에서 기다릴게</a></p></td>
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
							<td><p><a href="">사랑해 이 길 함께 걷는 그대여,<br> 굳이 고된 나를 택한 그대여</a></p></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table id="innerTable">
						<tr>
							<td id="td">
								<img src="images/music-headphones-icon-coloring-book-colouring-scallywag-coloring-5aNjee-clipart[1].png">
							</td>
						</tr>
						<tr>
							<td><p><a href="">어리다고 놀리지 말아요</a></p></td>
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