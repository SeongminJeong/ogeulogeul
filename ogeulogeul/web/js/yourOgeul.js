
var favoriteIsChecked;
var likeIsChecked;
var Ca = /\+/g;


function requestList() {
	$.ajax({
		url: "/ogeul/BoardListServlet",
		data: { flag: 2,
			memberId: memberId },
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

	var tr = document.createElement("tr");
	var td = document.createElement("td");
	
	if (jsonArr.list.length == 0) {
		td.innerHTML = "좋아한 오글이 없습니다.";
		td.className = "tdCenter";
		tr.appendChild(td);
		document.getElementById("listbody").appendChild(tr);
	}
	
	for (var i in jsonArr.list) {

		tr = document.createElement("tr");
		td = document.createElement("td");
		
		var boardNum = jsonArr.list[i].boardNum;
		var content = decodeURIComponent((jsonArr.list[i].content).replace(Ca, " "));
		var category = jsonArr.list[i].category;
		var stillcut = jsonArr.list[i].stillcut;
		var boardMemberId = jsonArr.list[i].memberId;
		var deleteIcon = "<a href='javascript:deleteBoard(" + boardNum + ");'>"
		+ "<i class='delete-icon'></i></a>";

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
		var like = "<label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='like(" + category + ", "
			+ boardNum + ", " + jsonArr.list[i].likeCount + ");' "
			+ likeIsChecked + "><i class='like-icon'></i></label>&nbsp;";
		
		if (jsonArr.list[i].category == "1") {
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + boardMemberId + "</a>&nbsp;"
				+ (memberId==null ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</label>"
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
				td.children[0].children[0].children[0].nextSibling.style.background = "url(/ogeul/board_uploadFiles/" + stillcut + ")";
				td.children[0].children[0].children[0].nextSibling.style.backgroundSize = "cover";
			}

			$("#innerTable td").css("border", "1px solid white");
		}

		else if (jsonArr.list[i].category == "2")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + boardMemberId + "</a>&nbsp;"
				+ (memberId==null ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden;z-index: 2'/></div>"
				+ "<p><a data-fancybox data-src='"
				+ "http://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "3")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + boardMemberId + "</a>&nbsp;"
				+ (memberId==null ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ like
				+ "<label id='p" + boardNum + "'>"+ jsonArr.list[i].likeCount + "</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden;z-index: 2'/></div>"
				+ "<p><a data-fancybox data-src='"
				+ "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "4")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + boardMemberId + "</a>&nbsp;"
				+ (memberId==null ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden;z-index: 2'/></div>"
				+ "<p><a data-fancybox data-src='" +
				+ "http://music.naver.com/search/search.nhn?query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "&target=track'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "5")
			td.innerHTML = " <table id='innerTable'><tr><td id='td'>"
				+ "<a href=''>"
				+ "<img width='45' height='45' class='img-circle' align='left' src='/ogeul/images/default.png'>"
				+ "&nbsp;" + boardMemberId + "</a>&nbsp;" 
				+ (memberId==null ? "" : favorite) + "</td>"
				+ "<td id='td' class='right'>"
				+ like
				+ "<label id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</label>"
				+ (memberId == boardMemberId ? deleteIcon : "") + "</td></tr>"
				+ "<tr><td colspan='2' id='td2' onmouseover='signiture(this, " + category + ", 0);' onmouseout='signiture(this, " + category + ", 1);'>"
				+ "<div style='position:relative;'>"
				+ "<img width='30px' height='30px' style='position:absolute;top:0px;visibility:hidden;z-index: 2'/></div>"
				+ "<p><a data-fancybox data-src=''>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		tr.appendChild(td);

		/*
		if (tr.childNodes.length == 4) {
			document.getElementById("listbody").appendChild(tr);
			tr = document.createElement("tr");
		}
		*/

		document.getElementById("listbody").appendChild(tr);
		
	}
	
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
