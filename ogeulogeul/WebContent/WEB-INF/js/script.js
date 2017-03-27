var $=jQuery.noConflict();

$(function() {
	// 전체선택 자동 해제
	$('.list table tbody th, .list table tbody td').children('input[type=checkbox]').click(function() {
		$(this).closest('table').children('thead').children('tr').children('th').children('input[type=checkbox]').prop('checked', false);
		if ($(this).prop('checked')==true) {
			$(this).closest('tr').addClass('on');
		} else {
			$(this).closest('tr').removeClass('on');
		}
	});

	// 달력
	$.datepicker.regional['ko'] = {
		closeText:'닫기',
		prevText:'이전달',
		nextText:'다음달',
		currentText:'오늘',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNames:['일','월','화','수','목','금','토'],
		dayNamesShort:['일','월','화','수','목','금','토'],
		dayNamesMin:['일','월','화','수','목','금','토'],
		showMonthAfterYear:true,
		yearSuffix:'년'
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$('.datepicker').datepicker({dateFormat:'yy-mm-dd',constrainInput:false});

	// Layer Popup
	$(".layerIframe").each(function() {
		var chkMinW=$(this).attr('data-fancybox-width');
		var chkMinH=0;
		if ($(this).attr('data-fancybox-height')>0) {
			chkMinH=$(this).attr('data-fancybox-height');
		}
		if (chkMinW) {
			$(this).fancybox({
				padding:'0',
				closeBtn:false,
				minHeight:chkMinH,
				width:chkMinW
			});
		} else {
			$(this).fancybox({
				padding:'0',
				closeBtn:false,
				minHeight:chkMinH
			});
		}
	});
});

// 자식 팝업 띄울 경우
function chkParentLayer(tThis) {
	$(tThis).closest('.popContents').css('height', '700px');
	parent.$.fancybox.update();
}
function closeParentLayer() {
	parent.$('.popContents').css('height', 'auto');
	parent.parent.$.fancybox.update();
	parent.$.fancybox.close();
}
function confirmParentLayer() {
	var checkedVal=$('.list table tbody th input:checked').val();
	if (checkedVal) {
		parent.$('input[name=ex]').val(checkedVal);
		closeParentLayer();
	} else {
		alert("사용자를 선택해주세요.");
	}
}

// 전체선택
function selectAll(tThis) {
	if ($(tThis).prop('checked')==false) {
		$(tThis).closest('.list').find('table').children('tbody').children('tr').children('input[type=checkbox]').prop('checked', false);
		$(tThis).closest('.list').find('table').children('tbody').children('tr').removeClass('on');
	} else {
		$(tThis).closest('.list').find('table').children('tbody').children('tr').children('input[type=checkbox]').prop('checked', true);
		$(tThis).closest('.list').find('table').children('tbody').children('tr').addClass('on');
	}
}

// 달력 오픈
function openCalendar(tThis) {
	$(tThis).prev('.datepicker').datepicker('show');
}

// 예약방송 disabled 설정/해제
function onairReserve(tThis) {
	if ($(tThis).prop('checked')==true) {
		$('.reserving .inp, .reserving .calendar').prop('disabled', false);
	} else {
		$('.reserving .inp, .reserving .calendar').prop('disabled', true);
	}
}

// SMS 내용 채우기
function fillSmsContents(val) {
	$('.smsTexting textarea').val(val);
}

// 폼 변수 저장
function returnFormVal(tThis) {
	return false;
}

function openFancy(url, w, h)
{
	var minH = (h == null ? 0 : h);
	if (w != null) {
		$.fancybox({
			padding:'0',
			closeBtn:false,
			minHeight:minH,
			width:w
		});
	} else {
		$.fancybox({
			padding:'0',
			closeBtn:false,
			minHeight:minH
		});
	}
	$.fancybox.open({href:url, type:"iframe", padding:'0', closeBtn:false, minHeight:minH});
}
