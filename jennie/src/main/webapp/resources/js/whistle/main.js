$(document).ready(function(){
	// 회원가입 버튼 클릭 이벤트
	$('#jbtn').click(function(){
		$(location).attr('href', '/whistle/member/join.blp');
	});
	
	//로그인 버튼 클릭 이벤트
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/login.blp');
	});
	
	//로그아웃 버튼 클릭 이벤트
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});
	
	$('#mlbtn').click(function(){
		//alert(sessionId); // el, 즉 ${SID}는 안 먹는다
		$(location).attr('href', '/whistle/member/memberList.blp');
	});
	
	$('#ibtn').click(function(){
		$(location).attr('href', '/whistle/member/myInfo.blp');
	});
	
	$('#gbtn').click(function(){
		$(location).attr('href', '/whistle/guestBoard/gBoardList.blp');
	});

	$('#rbtn').click(function(){
		$(location).attr('href', '/whistle/reboard/reboardList.blp');
	});
});

/*$(document).ready(function(){
	$('#ibtn').click(function(){
		var sid = $('#id').val();
		alert(sid + ' 님의 정보');
	});
});*/