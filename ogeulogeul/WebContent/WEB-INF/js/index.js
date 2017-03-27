function choice() {
	if (document.getElementById("category").value == "movie")
		document.getElementById("tdFile").innerHTML = "<input type='file'>";
	else 
		document.getElementById("tdFile").innerHTML = "";
}

function music() {
	//alert(unescape("%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%A2%8B%EC%9D%80%EB%82%A0"));
	window.open("http://music.naver.com/search/search.nhn?query=" + escape("아이유+좋은날") + "&x=0&y=0", "_blank");
}

function movie() {
	
	window.open("http://movie.naver.com/movie/search/result.nhn?query=" + escape("이터널+선샤인") + "&section=all&ie=utf8", "_blank");
}

/*
http://music.naver.com/search/search.nhn?query=%uC544%uC774%uC720+%uC88B%uC740%uB0A0&x=0&y=0
http://music.naver.com/search/search.nhn?query=%EC%95%84%EC%9D%B4%EC%9C%A0+%EC%A2%8B%EC%9D%80%EB%82%A0&x=0&y=0
*/