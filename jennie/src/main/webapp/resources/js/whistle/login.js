$(document).ready(function(){
	$('#lbtn').click(function(){
		
		var sid = $('#id').val();
		var spw = $('#pw').val();
		
		/*if(!(sid && spw)) {
			
			return;
		}	*/
		
		if(!sid) {
			$('#id').focus();
			return;
		}
		
		if(!spw) {
			$('#pw').focus();
			return;
		}
		
		$('#frm').attr('action', '/whistle/member/loginProc.blp');
		$('#frm').submit();
		
	});	
	
	//홈버튼 클릭 이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');
	});
});