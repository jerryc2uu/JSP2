$(document).ready(function(){
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');	
	});	
	
	$('.lbtn').click(function(){
		//클릭된 회원의 회원번호를 알아낸다.
		//우리는 버튼태그의 아이디값을 넣어뒀으니 아이디값을 읽어온다
		var sno = $(this).attr('id');
		//get 방식 요청
		//$(location).attr('href', '/whistle/member/memberInfo.blp?mno=' + sno);
		
		//post 방식 
		//데이터 채우고
		$('#mno').val(sno);
		//폼태그 전송
		$('#frm').submit();
	});
});