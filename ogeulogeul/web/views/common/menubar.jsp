<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member, java.util.*"%>

<%
   //scriptlet tag 영역 : 일반 자바 로직 코드가 작성되는 구역
   Member loginUser = (Member)session.getAttribute("loginUser");
%>
<!DOCTYPE html >
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 디바이스 크기에 맞게 해상도 조절 */ -->
<title>Insert title here</title>

<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script type="text/javascript" src="/ogeul/js/menubar.js"></script>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>

<style>
@import url(http://fonts.googleapis.com/earlyaccess/jejumyeongjo.css);

 header nav { align: left; position: absolute;} 
   header nav ul li{ list-style: none; float:none; text-align: center;} 
   /* header nav ul li a { 
   float : none;
      text-decoration: none;
      display: block; 
      width: 150px;
      height: 40px;
      font-weight: bold;
      padding-top: 5px;
   } */
header nav ul li a {
   color: white;
   width: 170px;
   height: 55px;
   text-align: center;
   font-size: 14pt;
/*     padding-left:100px;   */
   font-family: 'Jeju Myeongjo', serif;
}

.container-fluid {
   float: none;
   margin-left: auto;
   margin-right: auto;
   background: rgba(0, 0, 0, 0.3);
}

.navbar .navbar-nav {
   display: inline-block;
 /*   float: right; */
    height: auto; 
   margin-left: auto;
   vertical-align:top;
   
}
/* .navbar-inverse {
background-color : transparent !important;

}  */

.dropdown:hover .dropdown-menu {
display: block;
margin-top: 0;
}

</style>
</head>
<body>
   <header>


      <!-- 
<h1 align="center" style="clear:both;">first web project</h1>
 -->

      <nav class="navbar navbar-fixed-top  hidden-xs ">
         <div class="container-fluid">
            
               <ul class="nav navbar-nav" style="float:right;">
               <%
                  if(loginUser == null){  //로그인하지 않았다면
               %>
                  <li><a href="/ogeul/views/member/join.html"
                     style="text-align: center">JOIN</a></li>
                 <li><a href="javascript:popupOpen();"
                     style="text-align: center">LOG IN</a></li>
                 <li class="dropdown"><a class="dropdown-toggle"
                     data-toggle="dropdown" href="#">CATEGORY <span class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="/ogeul/views/board/sorting.jsp?type=movie">MOVIE</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=drama">DRAMA</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=comic">COMICS</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=music">MUSIC</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=book">BOOK</a></li>
                        <li><a href="#">TOP8</a></li>
                     </ul></li>
               <%
                  }else{  //로그인 되었다면
               %>
                  <li><a data-fancybox data-src="/ogeul/views/member/memberDetailView.jsp?" style="text-align:center; color: white"; ><%= loginUser.getName() %> 님</a></li>
                  <li><a href="#"
                     style="text-align: center">게시글 등록</a></li>
                   <li class="dropdown"><a class="dropdown-toggle"
                     data-toggle="dropdown" href="#">나만의 OGEUL<span class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a data-fancybox data-src="/ogeul/views/board/myogeul.jsp?memberId=<%=loginUser.getMemberId() %>&memberId2=<%=loginUser.getMemberId() %>">나의 OGEUL</a></li>
                        <li><a data-fancybox data-src="/ogeul/views/board/yourOgeul.jsp?memberId=<%=loginUser.getMemberId() %>">너의 OGEUL</a></li>
                        <li><a data-fancybox data-src="/ogeul/views/board/favoriteMember.jsp?memberId=<%=loginUser.getMemberId() %>" >즐겨찾는 OGEULER</a></li>
                     </ul></li>
                         <li><a href="/ogeul/logout"
                     style="text-align: center">LOG OUT</a></li>
                 <li class="dropdown"><a class="dropdown-toggle"
                     data-toggle="dropdown" href="#">CATEGORY <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                        <li><a href="/ogeul/views/board/sorting.jsp?type=movie">MOVIE</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=drama">DRAMA</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=comic">COMICS</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=music">MUSIC</a></li>
                        <li><a href="/ogeul/views/board/sorting.jsp?type=book">BOOK</a></li>
                        <li><a href="#">TOP8</a></li>
                     </ul></li>
               <% } %>   
               </ul>
            
         </div>
      </nav>


		<!-- 
모바일 용 -->
		<nav
			class="navbar navbar-fixed-top col-xs-12 hidden-md hidden-sm hidden-lg">
			<div class="container-fluid col-xs-12 hidden-md hidden-sm hidden-lg">
				<div>
					<ul class="nav navbar-nav">
						<li><a href="#" style="text-align: center">JOIN</a></li>

					</ul>
				</div>
			</div>

			<div class="container-fluid col-xs-12 hidden-md hidden-sm hidden-lg">
				<div>
					<ul class="nav navbar-nav">
						<li><a href="#" style="text-align: center">LOG IN</a></li>
					</ul>
				</div>
			</div>
			<div class="container-fluid col-xs-12 hidden-md hidden-sm hidden-lg">
				<div>
					<ul class="nav navbar-nav">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">= <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">MOVIE</a></li>
								<li><a href="#">DRAMA</a></li>
								<li><a href="#">COMICS</a></li>
								<li><a href="#">POET</a></li>
								<li><a href="#">BOOK</a></li>
								<li><a href="#">TOP8</a></li>
							</ul></li>
					</ul>
				</div>
			</div>

		</nav>
	</header>
</body>
</html>