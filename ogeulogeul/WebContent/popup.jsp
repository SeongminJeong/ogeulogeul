<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/popup.js"> </script>
<title>Insert title here</title>
</head>
<body>
<div>
<form>
	<table width="100%" border="1">
	<colgroup>
		<col width="25%" />
		<col width="25%" />
		<col width="25%" />
		<col width="25%" />
	</colgroup>
		<tr>
			<td class="pad">
				<select id="category" onchange="choice();">
					<option selected="true" value="movie">영화</option>
					<option value="drama">드라마</option>
					<option value="book">책</option>
					<option value="music">음악</option>
					<option value="cartoon">만화</option>
				</select>
			</td>
			<td colspan="3" id="tdFile" class="pad">
				<input type='file' id="file">
			</td>
		</tr>
		<tr>
			<td class="pad">제목</td>
			<td><input type="text" id="title"></input></td>
			<td id="tdProducer">감독 </td>
			<td id="tdProducerInput"><input type="text" id="producer"></td>
		</tr>
		<tr>
			<td>내용 </td>
			<td colspan="3"><textarea id="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center" class="pad">
				<button id="ok" onclick="sendOgeul();">완료</button>
				<button onclick="popupClose();">취소</button>
				<p id="p"></p>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>