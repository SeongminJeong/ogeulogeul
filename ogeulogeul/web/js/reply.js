
function requestList() {
	$.ajax({
		url: "BoardListServlet",
		data: { flag: "0",
			memberId: " "  },
		dataType: "json",
		success: function(data) {
			callbackList(data);
		},
		error: function(data,status,error){
			console.log("error : " + error);
		}
	});
}
