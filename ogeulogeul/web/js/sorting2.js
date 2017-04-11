var gJsonRows;
var favoriteIsChecked;
var likeIsChecked;
var Ca = /\+/g;
// /[변경될 문자열 패턴]/g 에서 g는 모든 문자열 패턴을 검색하라는 의미

function requestList() {
	
	$.ajax({
		url: "/ogeul/BoardListServlet",
		data: { flag: "3", memberId: " ",
			type: type },
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
	
	//var cols = new Array("boardNum", "memberId", "likeCount", "category", "uploadDate", "boardWarning", "title", "content", "stillcut", "producer");
	
	//받은 문자열을 json 객체로 변환함
	var jsonObj = JSON.stringify(data);
	//변환된 json 객체를 json 배열로 변환함
	var jsonArr = JSON.parse(jsonObj);
	gJsonRows = jsonArr.rows;
	
	var tr = document.createElement("tr");
	
	for (var i in jsonArr.list) {
		
		/*
		if (document.getElementById("list_body").rows.length > 0 ) {
			tr = document.createElement("tr"); // <tr> 생성
		}
		*/
		var td = document.createElement("td"); // <td> 생성
		var boardNum = jsonArr.list[i].boardNum;
		var category = jsonArr.list[i].category;
		var content = decodeURIComponent((jsonArr.list[i].content).replace(Ca, " "));
		var stillcut = jsonArr.list[i].stillcut;
		var boardMemberId = jsonArr.list[i].memberId;
		var favorite_like_declare = "";
		
		$.ajax({
			url: "/ogeul/MemberFavoriteServlet",
			data : { memberId : memberId,
				favoriteMemberId : boardMemberId,
				flag : 0},
				async : false,
				success : function(data) {
					if (data == "like")
						favoriteIsChecked = "checked";
					else
						favoriteIsChecked = "";
				}
		});
		
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
		
		var favorite = " <label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='favorite(this, \"" + boardMemberId + "\");' "
			+ favoriteIsChecked + "><i class='favorite-icon'></i></label>";
		var declare_like = "<a data-fancybox data-src='declare.jsp?"
			+ "memberId=" + memberId + "&"
			+ "boardNum=" + boardNum + "'>"
			+ "<i class='declare-icon'></i></a>"
			+ "<label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='like(" + category + ", "
			+ boardNum + ", " + jsonArr.list[i].likeCount + ");' "
			+ likeIsChecked + "><i class='like-icon'></i></label>&nbsp;";
		var deleteIcon = "<a href='javascript:deleteBoard(" + boardNum + ");'>"
			+ "<i class='delete-icon'></i></a>";
		
		if (jsonArr.list[i].category == "1") {
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + jsonArr.list[i].memberId + "</a>&nbsp;"
				+ (memberId == boardMemberId ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ (memberId==null ? "" : declare_like)
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden;z-index: 2'/></div>"
				+ "<p><a data-fancybox data-src='"
				+ "http://movie.naver.com/movie/search/result.nhn?query="
				+ encodeURI(decodeURIComponent(jsonArr.list[i].title))
				+ "&section=all&ie=utf8'>"
				+ (content == " " ? "<img src='/ogeul/board_uploadFiles/" + stillcut + "'>" : content)
				+ "</a></p></td></tr></table>";
			
			if (content != " " && stillcut != null) {
				td.children[0].children[0].children[0].nextSibling.style.background = "url(/ogeul/board_uploadFiles/" + stillcut + ") no-repeat";
				td.children[0].children[0].children[0].nextSibling.style.backgroundSize = "contain";
				td.children[0].children[0].children[0].nextSibling.style.backgroundPosition = "center"; 
			}
		}

		else if (jsonArr.list[i].category == "2")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + jsonArr.list[i].memberId + "</a>&nbsp;" 
				+ (memberId == boardMemberId ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ (memberId==null ? "" : declare_like)
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden'/></div>"
				+ "<p><a data-fancybox data-src='"
				+ "http://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "3")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + jsonArr.list[i].memberId + "</a>&nbsp;"
				+ (memberId == boardMemberId ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ (memberId==null ? "" : declare_like)
				+ "<label id='p" + boardNum + "'>"+ jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden'/></div>"
				+ "<p><a data-fancybox data-src='"
				+ "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "4")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + jsonArr.list[i].memberId + "</a>&nbsp;"
				+ (memberId == boardMemberId ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ (memberId==null ? "" : declare_like)
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden'/></div>"
				+ "<p><a data-fancybox data-src='" +
				+ "http://music.naver.com/search/search.nhn?query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "&target=track'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "5")
			td.innerHTML = " <table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + jsonArr.list[i].memberId + "</a>&nbsp;"
				+ (memberId == boardMemberId ? "" : favorite) + "</td>" 
				+ "<td id='td' class='right'>"
				+ (memberId==null ? "" : declare_like)
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "&nbsp;</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden'/></div>"
				+ "<p><a data-fancybox data-src=''>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		tr.appendChild(td);

		if (tr.childNodes.length == 4) {
			document.getElementById("listbody").appendChild(tr);
			tr = document.createElement("tr");
		}
		
			
				/*
				$("#p6").html($("#p6").html() + 
					jsonArr.list[i].boardNum + ", " + 
					jsonArr.list[i].memberId + ", " + 
					jsonArr.list[i].likeCount + ", " + 
					jsonArr.list[i].category + ", " + 
					jsonArr.list[i].uploadDate + ", " + 
					jsonArr.list[i].boardWarning + ", " + 
					decodeURIComponent(jsonArr.list[i].title) +
					decodeURIComponent(jsonArr.list[i].stillcut) +
					decodeURIComponent(jsonArr.list[i].producer) +
					);
					*/
	}

	document.getElementById("listbody").appendChild(tr);
}

function music() {
	//alert(unescape("%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%A2%8B%EC%9D%80%EB%82%A0"));
	window.open("http://music.naver.com/search/search.nhn?query=" + escape("아이유+좋은날") + "&x=0&y=0", "_blank");
}

function favorite(obj, boardMemberId) {
	
	if (confirm(boardMemberId + "님을 즐겨찾는 오글러"
			+ (obj.checked == true ? "로 추가하시겠습니까?" : "에서 삭제하시겠습니까?") ) == true) {
		
		$.ajax({
			url: "/ogeul/MemberFavoriteServlet",
			data: { memberId : memberId,
				favoriteMemberId : boardMemberId,
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

function declare(category, boardNum, likeCount) {
	$.ajax({
		url: "/ogeul/BoardWarningServlet",
		data: { memberId : memberId,
			boardNum : boardNum,
			category : category,
			flag : 1},
		success: function(data) {
			if (data == "warning") {
				alert("신고  ");
				$("#p" + boardNum).text(likeCount + 1);
			}
			else if (data == "cancel") {
				alert("신고  취소 ");
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
	
	location.href="/ogeul/sorting.jsp";
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

/*
http://music.naver.com/search/search.nhn?query=%uC544%uC774%uC720+%uC88B%uC740%uB0A0&x=0&y=0
http://music.naver.com/search/search.nhn?query=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%A2%8B%EC%9D%80%EB%82%A0&x=0&y=0
*/