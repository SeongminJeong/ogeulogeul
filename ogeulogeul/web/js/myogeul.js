
var likeIsChecked;
var Ca = /\+/g;

function requestList() {
	$.ajax({
		url: "/ogeul/BoardListServlet",
		data: { flag: 1,
			memberId: memberId2 },
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

	var favorite = " <label class='checkbox-wrap'>"
		+ "<input type='checkbox' "
		+ "onclick='favorite(this, \"" + memberId2 + "\");' checked>"
		+ "<i class='favorite-icon'></i></label>";
	
	if (memberId == memberId2)
		alert("나의 오글");
	else {
		alert("즐겨찾는 오글러 정보");
		document.getElementById("p").innerHTML = memberId2
			+ " 님을 즐겨찾기에서 삭제하기 "
			+ favorite;
	}


	var tr = document.createElement("tr");
	var td = document.createElement("td");

	if (jsonArr.list.length == 0) {
		if (memberId == memberId2)
			td.innerHTML = "게시한 오글이 없습니다.";
		else
			td.innerHTML = "즐겨찾는 오글러가 없습니다.";
		td.className = "tdCenter";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
		return;
	}
	
	for (var i in jsonArr.list) {

		tr = document.createElement("tr");
		td = document.createElement("td");

		var category = jsonArr.list[i].category;
		var boardMemberId = jsonArr.list[i].memberId;
		var boardNum = jsonArr.list[i].boardNum;
		var content = decodeURIComponent((jsonArr.list[i].content).replace(Ca, " "));
		var stillcut = jsonArr.list[i].stillcut;
		var favoriteMemberId = jsonArr.list[i].memberId;
		var like = "<label class='checkbox-wrap'>"
		+ "<input type='checkbox' "
		+ "onclick='like(" + jsonArr.list[i].category + ", "
		+ boardNum + ", " + jsonArr.list[i].likeCount + ");' "
		+ likeIsChecked + "><i class='like-icon'></i></label>&nbsp;";
		var declare = "<a data-fancybox data-src='declare.jsp?"
			+ "memberId=" + boardMemberId + "&"
			+ "boardNum=" + boardNum + "'>"
			+ "<i class='declare-icon'></i></a>";
		var deleteIcon = "<a href='javascript:deleteBoard(" + boardNum + ");'>"
			+ "<i class='delete-icon'></i></a>";

		$.ajax({
			url: "/ogeul/BoardLikeServlet",
			data : { memberId : memberId,
				boardNum : boardNum,
				flag : 0},
				async : false,
				success : function(data) {
					if (data == "like")
						likeIsChecked = "checked";
					else
						likeIsChecked = "";
				}
		});
		
		if (jsonArr.list[i].category == "1") {
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ jsonArr.list[i].memberId
				+ (memberId == memberId2 ? "" : declare) + like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == memberId2 ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden'/>"
				+ "<p><a data-fancybox data-src='"
				+ "http://movie.naver.com/movie/search/result.nhn?query="
				+ encodeURI(decodeURIComponent(jsonArr.list[i].title))
				+ "&section=all&ie=utf8'>"
				+ (content == " " ? "<img src='/ogeul/board_uploadFiles/" + stillcut + "'>" : content)
				+ "</a></p></div></td></tr></table>";
			
			if (content != " " && stillcut != null) {
				td.children[0].children[0].children[0].nextSibling.style.background = "url(/ogeul/board_uploadFiles/" + stillcut + ")";
				td.children[0].children[0].children[0].nextSibling.style.backgroundSize = "contain";
			}

			$("#innerTable td").css("border", "1px solid white");
		}

		else if (jsonArr.list[i].category == "2")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ jsonArr.list[i].memberId 
				+ (memberId == memberId2 ? "" : declare) + like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == memberId2 ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;visibility:hidden'/>"
				+ "<p><a data-fancybox data-src='"
				+ "http://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "3")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId == memberId2 ? "" : declare) + like
				+ "<label id='p" + boardNum + "'>"+ jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == memberId2 ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;visibility:hidden'/>"
				+ "<p><a data-fancybox data-src='"
				+ "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "4")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId == memberId2 ? "" : declare) + like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == memberId2 ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;visibility:hidden'/>"
				+ "<p><a data-fancybox data-src='" +
				+ "http://music.naver.com/search/search.nhn?query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "&target=track'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "5")
			td.innerHTML = " <table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : declare) + like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == memberId2 ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;visibility:hidden'/>"
				+ "<p><a data-fancybox data-src=''>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
	}
}

function signiture(object, category, flag) {
	if (flag == 0) {
		if (category == 1)
			object.children[0].children[0].src = '/ogeul/images/f01714817750f2b3d22c5ab81dc53ddf[1].png';
		else if (category == 2)
			object.children[0].children[0].src = '/ogeul/images/tv[1].png';
		else if (category == 3) 
			object.children[0].children[0].src = '/ogeul/images/5020-200[1].png';
		else if (category == 4)
			object.children[0].children[0].src = '/ogeul/images/music-headphones-icon-coloring-book-colouring-scallywag-coloring-5aNjee-clipart[1].png';
		else if (category == 5)
			object.children[0].children[0].src = '/ogeul/images/tv[1].png';
		
		object.children[0].children[0].style.visibility = 'visible';
	}
	else if (flag == 1)
		object.children[0].children[0].style.visibility = 'hidden';
}

function favorite(obj, favoriteMemberId) {
	
	if (confirm(favoriteMemberId + "님을 즐겨찾는 오글러"
			+ (obj.checked == true ? "로 추가하시겠습니까?" : "에서 삭제하시겠습니까?") ) == true) {
		
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
	else
		obj.checked = ((obj.checked == true) ? false : true);
}

function deleteBoard(boardNum) {
	if (confirm("정말 삭제하시겠습니까?") == true)
		$.ajax({
			url: "/ogeul/BoardDeleteServlet",
			data : { boardNum : boardNum },
				async : false,
				success : function(data) {
					alert(data);
				}
		});
}

function like(category, boardNum, likeCount) {
	
	$.ajax({
		url: "/ogeul/BoardLikeServlet",
		data: { memberId : memberId,
			boardNum : boardNum,
			category : category,
			flag : 1},
		success: function(data) {
			if (data == "like") {
				alert("좋아함 ");
				$("#p" + boardNum).text(likeCount + 1);
			}
			else if (data == "unlike") {
				alert("좋아요 취소 ");
				$("#p" + boardNum).text(likeCount);
			}
		}
	});
}

function deleteBoard(boardNum) {
	if (confirm("정말 삭제하시겠습니까?") == true)
		$.ajax({
			url: "BoardDeleteServlet",
			data: { boardNum : boardNum
				},
			success: function(data) {
				alert(data);
			}
		});
	
	location.href="/ogeul/index.jsp";
}
