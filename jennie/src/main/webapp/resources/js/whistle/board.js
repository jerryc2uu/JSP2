$(document).ready(function(){
	/* 페이지 버튼 클릭이벤트 처리 */
	$('.pbtn').click(function(){
		// 페이지번호 읽고
		var pno = $(this).attr('id');
		// 페이지 번호 셋팅하고
		$('#nowPage').val(pno);
		// 폼 태그 전송
		$('#frm').submit();
	});
	
	$('.menubtn').not('#wbtn').click(function(){
		var bid = $(this).attr('id');
		
		var addr = '/whistle/';
		switch(bid){
		case 'hbtn':
			// 기본 주소를 사용
			break;
		case 'lbtn':
			addr = '/whistle/member/login.blp';
			break;
		case 'jbtn':
			addr = '/whistle/member/join.blp';
			break;
		case 'obtn':
			addr = '/whistle/member/logout.blp';
			break;
		}
		
		$(location).attr('href', addr);
	});
	
	$('#wbtn').click(function(){
		$(location).attr('href', '/whistle/board/boardWrite.blp');
	});
	
	$('#wpbtn').click(function(){
		$('#frm').submit();
	});
});