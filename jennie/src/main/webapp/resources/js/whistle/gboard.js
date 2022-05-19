$(document).ready(function(){
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/');
	});
	$('#jbtn').click(function(){
		$(location).attr('href', '/whistle/member/join.blp');
	});
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/login.blp');
	});
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});
	$('.pbtn').click(function(){
		//데이터 읽어오고
		var page = $(this).attr('id');
		//alert('# page : ' + page);
		
		//데이터 셋팅
		$('#nowPage').val(page);
		$('#frm').submit();
	});
	$('#wbtn').click(function(){
		/*
		//get 방식으로 요청
		$(location).attr('href', '/whistle/guestBoard/gBoardWrite.blp?nowPage=' + $('#nowPage').val());
		// ==> 3페이지 보는 경우 : /whistle/guestBoard/gBoardWrite.blp?nowPage=3
		*/
		
		//POST 방식으로 요청
		$('#frm').attr('action', '/whistle/guestBoard/gBoardWrite.blp');
		$('#frm').submit();
	});
});