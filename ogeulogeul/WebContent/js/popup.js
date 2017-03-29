
function choice() {
	if (document.getElementById("category").value == "movie") {
		document.getElementById("tdFile").innerHTML = "<input type='file' id='file'>";
		document.getElementById("tdProducer").innerHTML = "감독";
		document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer'>";
	}
	
	else if (document.getElementById("category").value == "cartoon") {
		document.getElementById("tdFile").innerHTML = "<input type='file' id='file'>";
		document.getElementById("tdProducer").innerHTML = "";
		document.getElementById("tdProducerInput").innerHTML = "";
	}
	
	else {
		document.getElementById("tdFile").innerHTML = "";
	
		if (document.getElementById("category").value == "book") {
			document.getElementById("tdProducer").innerHTML = "작가";
			document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer'>";
		}
		else if (document.getElementById("category").value == "music") {
			document.getElementById("tdProducer").innerHTML = "가수";
			document.getElementById("tdProducerInput").innerHTML = "<input type='text' id='producer'>";
		}
		
		else if (document.getElementById("category").value == "drama") {
			document.getElementById("tdProducer").innerHTML = "";
			document.getElementById("tdProducerInput").innerHTML = "";
		}
	}
}


function popupClose() {
	alert("창을 닫습니다.");
	parent.$.fancybox.close();
}


function sendOgeul() {
	
	var stillcut;
	var producer = "";
	
	if (document.getElementById("category").value == "movie"
		|| document.getElementById("category").value == "cartoon") {
		if (document.getElementById("file").value)
			//alert("스틸컷 유 ")
			;
		else {
			stillcut = "";
			//alert("스틸컷 무 ");
		}
	}

	if (!(document.getElementById("category").value == "cartoon"
		|| document.getElementById("category").value == "drama"))
		producer = document.getElementById("producer").value;
	
	$.ajax({
		url: "/ogeulogeul/BoardInsertServlet",
		data: { category : document.getElementById("category").value,
			title : document.getElementById('title').value,
			content : document.getElementById('content').value,
			stillcut : stillcut,
			producer : producer,
			memberId : "test"},
		type : "get",
		success: function(data) {
			alert(data);
			popupClose();
		}
	});
	
}

