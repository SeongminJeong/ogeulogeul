
function choice() {
	if (document.getElementById("reason").value == "etc")
		document.getElementById("td").innerHTML = "<textarea name='desc' placeholder='신고 사유를 입력해주세요'></textarea>";
}

function declare() {
	if (confirm("정말 신고하시겠습니까?") == true)
		document.form.submit();
}

function popupClose() {
	alert("창을 닫습니다.");
	parent.$.fancybox.close();
}
