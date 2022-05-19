//gBoardWrite.js
$(document).ready(function(){
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/');
	});
	$('#obtn').click(function(){
		location.href = '/whistle/member/logout.blp';
	});
	$('#lbtn').click(function(){
		//$(location).attr('href', '/whistle/guestBoard/gBoardList.blp');
		//본문태그 비활성화
		$('#body').prop('disabled', 'true');
		$('#frm').attr('action', '/whistle/guestBoard/gBoardList.blp');
		$('#frm').submit();
	});
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	$('#wbtn').click(function(){
		//입력 데이터 유효성 확인
		var body = $('#body').val();
		body = body.trim(); // trim() : 앞뒤 공백문자 지움
		
		if(!body || body.length == 0) {
			$('#body').val('');
			$('#body').focus();
			return;
		}	
		
		//폼 전송
		$('#frm').submit();	
	});
});