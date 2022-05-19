$(document).ready(function(){
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/memberList.blp');
	});
	
	$('#dbtn').click(function(){
		//보낼 데이터 읽고
		var sno = $('#mno').html();
		var sid = $('#id').html();
		//보낼 데이터 셋팅
		$('#smno').val(sno);
		$('#sid').val(sid);		
		//보낼 주소 수정
		$('#frm').attr('action', '/whistle/member/delInfo.blp');
		
		if(confirm('정말 탈퇴하시겠습니까?')){//확인-true, 취소-false
			//폼 태그 완성, 전송 ㄱ
			$('#frm').submit();	
		}
	});
	
	$('#ebtn').click(function(){
		$(location).attr('href', '/whistle/member/editInfo.blp');
	});
});