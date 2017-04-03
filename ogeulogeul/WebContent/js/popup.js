
function choice() {
	if (document.getElementById("category").value == "1") { // 영화 
		document.getElementById("tdFile").innerHTML = "<input type='file' id='stillcut' name='stillcut'>";
		document.getElementById("tdProducer").innerHTML = "감독";
		document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer' name='producer'>";
	}
	
	else if (document.getElementById("category").value == "5") { // 만화 
		document.getElementById("tdFile").innerHTML = "<input type='file' id='stillcut' name='stillcut'>";
		document.getElementById("tdProducer").innerHTML = "";
		document.getElementById("tdProducerInput").innerHTML = "";
	}
	
	else {
		document.getElementById("tdFile").innerHTML = "";
	
		if (document.getElementById("category").value == "3") { // 책 
			document.getElementById("tdProducer").innerHTML = "작가";
			document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer' name='producer'>";
		}
		else if (document.getElementById("category").value == "4") { // 음악 
			document.getElementById("tdProducer").innerHTML = "가수";
			document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer' name='producer'>";
		}
		
		else if (document.getElementById("category").value == "2") { // 드라마 
			document.getElementById("tdProducer").innerHTML = "";
			document.getElementById("tdProducerInput").innerHTML = "";
		}
	}
}


function popupClose() {
	alert("창을 닫습니다.");
	parent.$.fancybox.close();
}

function movie() {
	window.open("http://movie.naver.com/movie/search/result.nhn?query=" + escape("이터널+선샤인") + "&section=all&ie=utf8", "_blank");
}

function sendOgeul() {
	
	var stillcut;
	var producer = "";
	
	if (document.getElementById("title").value == "") {
		alert("제목을 입력해주세요! ");
		document.getElementById("title").focus();
		return;
	}
	
	if (document.getElementById("category").value == "1" 
		|| document.getElementById("category").value == "5") { // 영화 || 만화 
		if (document.getElementById("stillcut").value)
			//alert("스틸컷 유 ")
			;
		else {
			stillcut = "";
			//alert("스틸컷 무 ");
		}
	}

	if (!(document.getElementById("category").value == "5" 
		|| document.getElementById("category").value == "2"))
		 // !만화 || 드라마 
		producer = document.getElementById("producer").value;
	
	$.ajax({
		url: "/ogeulogeul/BoardInsertServlet",
		data: { category : document.getElementById("category").value,
			title : document.getElementById('title').value,
			content : document.getElementById('content').value,
			stillcut : stillcut,
			producer : producer,
			memberId : "test"},
		type : "post",
		//timeout: 8000,
		success: function(data) {
			alert(data);
			window.parent.location.href = "index.jsp";
			popupClose();
		},
		error: function(data,status,error){
			if(data.readyState == 0 && data.status == 0)
				alert("에러???");
			console.log("error : " + error);
			console.log("status : " + status);
		}
	});
	
}

