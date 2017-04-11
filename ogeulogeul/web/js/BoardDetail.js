
var Ca = /\+/g;

function requestList() {
	$.ajax({
		url: "/ogeul/ReplyListServlet",
		data: { boardNum: boardNum},
		dataType: "json",
		success: function(data) {
			callbackList(data);
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
}

function callbackList(data) {
	
	var jsonObj = JSON.stringify(data);
	var jsonArr = JSON.parse(jsonObj);
	
	var tr;
	
	var declare;
	var deleteIcon;
	
	while(document.getElementById("listbody").rows.length > 0)
		document.getElementById("listbody").deleteRow(0);
	
	if (jsonArr.list.length == 0){
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		td.innerHTML = "댓글이 없습니다.";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
		return;
	}
	
	for (var i in jsonArr.list) {

		var faceImage;
		var replyMemberId = jsonArr.list[i].memberId;
		
		$.ajax({
			url: "/ogeul/myinfo",
			data: { flag: 1,
				memberId: replyMemberId,
			},
			success: function(data) {
				if (data == "default.png") {
				 faceImage = "default.png";
					alert(faceImage);
				}
				/*
				faceImage =
					(data == "default.png" ? "/ogeul/images/" : "/ogeul/face_uploadImage/")
					+ data + "";
					*/
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
		
		tr = document.createElement("tr");

		var replyNum = jsonArr.list[i].replyNum;
		declare = "<a data-fancybox data-src='declare.jsp?"
			+ "memberId=" + memberId + "&"
			+ "replyNum=" + replyNum + "'>"
			+ "<i class='declare-icon'></i></a>&nbsp";
		deleteIcon = "<a href='javascript:deleteReply(" + replyNum + ");'>"
			+ "<i class='delete-icon'></i></a>&nbsp";
		
		var td = document.createElement("td");
		
		td.innerHTML = "<a href=''>"
			+ "<img width='45' height='45' class='img-circle' align='left' src='"
			+ "/ogeul/images/default.png'>"
			+ "<b>&nbsp" + jsonArr.list[i].memberId + "</a></b>" + " : "
			+ decodeURIComponent((jsonArr.list[i].reply).replace(Ca, " "))
			+ "&nbsp<a href='javascript:replyTag(\"" + replyMemberId + "\")'>답글달기</a>&nbsp"
			+ (memberId == replyMemberId ? deleteIcon : declare)
			+ jsonArr.list[i].replyDate;
		
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
	}
}

function insertReply() {
	
	if (document.getElementById("reply").value == "") {
		alert("댓글을 입력해주세요");
		return;
	}
		
	
	$.ajax({
		url: "/ogeul/ReplyInsertServlet",
		data: { boardNum: boardNum,
			memberId: memberId,
			reply: document.getElementById("reply").value
			},
		success: function(data) {
			alert(data);
			requestList();
			document.getElementById("reply").value = "";
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
}

function deleteReply(replyNum) {
	
	if (confirm("댓글을 삭제하시겠습니까?") == true)
		$.ajax({
			url: "/ogeul/ReplyDeleteServlet",
			data: { replyNum: replyNum
				},
			success: function(data) {
				alert(data);
				requestList();
			},
			error: function(data,status,error){
				console.log("error : " + error);
			}
		});
}

function replyTag(replyMemberId) {
	document.getElementById("reply").value = "@" + replyMemberId + " ";
	document.getElementById("reply").focus();
}