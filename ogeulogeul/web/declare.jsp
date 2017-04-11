<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/declare.js"></script>
<title>Insert title here</title>
<script>
	var memberId = '<%= request.getParameter("memberId") %>';
	var boardNum = '<%= request.getParameter("boardNum") %>';
</script>
</head>
<body>
<div style="text-align:center;height:500px;overflow:auto">
<h2>나쁜 오글러 신고하기 </h2>
<p>허위 신고 3회 이상인 경우 경고 횟수가 1회 증가됩니다 </p>
<p>신고가 정상 접수되면 해당 회원의 경고 횟수가 1회 증가되며,</p>
<p>누적 경고 3회 이상일 시 게시글 및 댓글 등록이 제한됩니다.</p>
<form action="" method="post">
<table align="center">
	<tr>
		<td>신고사유 </td>
		<td>
			<select id="reason" name="" onchange="choice();">
				<option value="" selected>잘못된 정보 게시 </option>
				<option value="" >지나친 스포성 게시글  </option>
				<option value="" >욕설/음란물  </option>
				<option value="etc" >기타 </option>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2" id="td"> </td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="hidden" name="" value="">
			<input type="button" onclick="declare();" value="신고 ">
			<button onclick="popupClose();">취소 </button>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>