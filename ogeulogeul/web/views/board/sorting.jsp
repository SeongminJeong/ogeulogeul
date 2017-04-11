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
<% 
String type = request.getParameter("type");
%>
   <center>

      <div style="display:inline">
         <div class="menu">
         <%@ include file="../common/menubar.jsp"%>
         </div>
         <div class="page">
            <%@ include file="../common/page.jsp"%>
         </div>
         <div>
            <jsp:include page="sorting2.jsp" >
            	<jsp:param name="type" value="<%= type %>"></jsp:param>
            </jsp:include>
         </div>
      
      </div>

   </center>
</body> 
</html>