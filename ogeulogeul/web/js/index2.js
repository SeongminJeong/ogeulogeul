var gJsonRows;
var favoriteIsChecked;
var likeIsChecked;
var Ca = /\+/g;
// /[변경될 문자열 패턴]/g 에서 g는 모든 문자열 패턴을 검색하라는 의미

function requestList() {
	$.ajax({
		url: "BoardListServlet",
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
	
	for (var i in jsonArr.list){
		
		/*
		if (document.getElementById("list_body").rows.length > 0 ) {
			tr = document.createElement("tr"); // <tr> 생성
		}
		*/
		var td = document.createElement("td"); // <td> 생성
		var boardNum = jsonArr.list[i].boardNum;
		var content = decodeURIComponent((jsonArr.list[i].content).replace(Ca, " "));
		var stillcut = jsonArr.list[i].stillcut;
		var favoriteMemberId = jsonArr.list[i].memberId;
		var favorite_like_declare = "";
		
		$.ajax({
			url: "BoardLikeServlet",
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
		
		$.ajax({
			url: "MemberFavoriteServlet",
			data : { memberId : memberId,
				favoriteMemberId : favoriteMemberId,
				flag : 0},
				async : false,
				success : function(data) {
					if (data == "like")
						favoriteIsChecked = "checked";
					else
						favoriteIsChecked = "";
				}
		});
		
		favorite_like_declare = "<label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='favorite(this, \'" + favoriteMemberId + "\');' "
			+ favoriteIsChecked + "><i class='favorite-icon'></i></label>"
			+ "<label class='checkbox-wrap'>"
			+ "<input type='checkbox' "
			+ "onclick='like(" + jsonArr.list[i].category + ", " + boardNum + ", " + jsonArr.list[i].likeCount + ");' "
			+ likeIsChecked + "><i class='check-icon'></i></label>"
			+ "<a data-fancybox data-src='declare.jsp?"
			+ "memberId=" + memberId + "&"
			+ "boardNum=" + boardNum + "'>"
			+ "<i class='declare-icon'></i></a>";
		
		if (jsonArr.list[i].category == "1") {
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : favorite_like_declare)
				+ "<p id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</p>"
				+ " <img src='images/f01714817750f2b3d22c5ab81dc53ddf[1].png'></td></tr><tr>"
				+ "<td><p><a data-fancybox data-src='"
				+ "http://movie.naver.com/movie/search/result.nhn?query="
				+ encodeURI(decodeURIComponent(jsonArr.list[i].title))
				+ "&section=all&ie=utf8'>"
				+ (content == " " ? "<img src='/ogeul/board_uploadFiles/" + stillcut + "'>" : content)
				+ "</a></p></td></tr></table>";
			
			if (content != " " && stillcut != null) {
				td.children[0].children[0].children[0].nextSibling.style.background = "url(/ogeul/board_uploadFiles/" + stillcut + ")";
				td.style.backgroundSize = "cover";
			}

			$("#innerTable td").css("border", "1px solid white");
		}

		else if (jsonArr.list[i].category == "2")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : favorite_like_declare)
				+ "<p id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</p>"
				+ "<img src='images/tv[1].png'></td></tr>"
				+ "<tr><td><p><a data-fancybox data-src='"
				+ "http://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "3")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : favorite_like_declare)
				+ "<p id='p" + boardNum + "'>"+ jsonArr.list[i].likeCount + "</p>"
				+ "<img src='images/5020-200[1].png'></td></tr>"
				+ "<tr><td><p><a data-fancybox data-src='"
				+ "http://book.naver.com/search/search.nhn?sm=sta_hty.book&sug=&where=nexearch&query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "4")
			td.innerHTML = "<table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : favorite_like_declare)
				+ "<p id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</p>"
				+ "<img src='images/music-headphones-icon-coloring-book-colouring-scallywag-coloring-5aNjee-clipart[1].png'></td></tr>"
				+ "<tr><td><p><a data-fancybox data-src='" +
				+ "http://music.naver.com/search/search.nhn?query="
				+ decodeURIComponent(jsonArr.list[i].producer) + "+"
				+ decodeURIComponent(jsonArr.list[i].title) + "&target=track'>"
				+ decodeURIComponent((jsonArr.list[i].content).replace(Ca, " ")) + "</a></p></td></tr></table>";
		
		else if (jsonArr.list[i].category == "5")
			td.innerHTML = " <table id='innerTable'><tr><td id='td'>" + jsonArr.list[i].memberId 
				+ (memberId==null ? "" : favorite_like_declare)
				+ "<p id='p" + boardNum + "'>" + jsonArr.list[i].likeCount + "</p>"
				+ "<img src='images/tv[1].png'></td></tr>"
				+ "<tr><td><p><a data-fancybox data-src=''>"
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

function favorite(obj, favoriteMemberId) {
	
	alert(memberId + ", " + favoriteMemberId);
	
	/*
	if (confirm(favoriteMemberId + "님을 즐겨찾는 오글러"
			+ (obj.checked == false ? "로 추가하시겠습니까?" : "에서 삭제하시겠습니까?") ) == true)
		$.ajax({
			url: "MemberFavoriteServlet",
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
			}
		});
		*/
}

function like(category, boardNum, likeCount) {
	
	$.ajax({
		url: "BoardLikeServlet",
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
		url: "BoardWarningServlet",
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

/*
http://music.naver.com/search/search.nhn?query=%uC544%uC774%uC720+%uC88B%uC740%uB0A0&x=0&y=0
http://music.naver.com/search/search.nhn?query=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%A2%8B%EC%9D%80%EB%82%A0&x=0&y=0
*/