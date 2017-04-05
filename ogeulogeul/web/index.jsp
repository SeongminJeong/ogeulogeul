<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ogeul Project</title>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<style type="text/css">
.page {
  height : 500px;  
}

</style>
</head>
<body>
   <center>




      <div style="display:inline">
         <div class="menu">
         <%@ include file="views/common/menubar.jsp"%>
         </div>
         <div class="page">
            <%@ include file="views/common/page.jsp"%>
         </div>
         <div>
            <%@ include file="index2.jsp" %>
         </div>
      
      </div>

   </center>
</body> 
</html>