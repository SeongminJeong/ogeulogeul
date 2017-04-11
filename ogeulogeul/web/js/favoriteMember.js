var gJsonRows;

function requestList() {
	$.ajax({
		url: "/ogeul/FavoriteMemberListServlet",
		data: {memberId : memberId},
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
	
	var jsonObj = JSON.stringify(data)
	var jsonArr = JSON.parse(jsonObj);
	gJsonRows = jsonArr.rows;
	
	var tr = document.createElement("tr");

	document.getElementById("memberId").innerHTML = memberId;
	document.getElementById("favoriteMemberCnt").innerHTML = jsonArr.list.length;
	
	for (var i in jsonArr.list) {
		
		var td = document.createElement("td");
		var favoriteMemberId = jsonArr.list[i].favoriteMemberId;
		
		td.innerHTML = "<a href='myogeul.jsp?memberId="
			+ memberId + "&memberId2=" + favoriteMemberId + "'>"
			+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
			+ favoriteMemberId + "</a>"
			+ " <label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='favoriteCancel(\"" + favoriteMemberId + "\");' "
			+ "checked><i class='favorite-icon'></i></label>";
		
		tr.appendChild(td);
	}
	document.getElementById("listbody").appendChild(tr);
}

function favoriteCancel(favoriteMemberId) {
	
	$.ajax({
		url: "/ogeul/MemberFavoriteServlet",
		data: { memberId : memberId,
			favoriteMemberId : favoriteMemberId,
			flag : 1
			},
		success: function(data) {
			if (data == "like") {
				alert("좋아함 ");
			}
			else if (data == "unlike") {
				alert("좋아요 취소 ");
			}
			// 해당 멤버의 모든 게시글에 있는 즐겨찾기 아이콘이 갱신되지는 않음
			// 해당 게시글의 즐겨찾기 아이콘만 갱신됨
		}
	});
}