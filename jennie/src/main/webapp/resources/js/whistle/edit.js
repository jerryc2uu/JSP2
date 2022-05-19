$(document).ready(function(){
	//reset 버튼
	$('#rbtn').click(function(){
		document.frm.reset();
	});	
	
	//홈 버튼
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');
	});
	
	//수정 버튼
	$('#ebtn').click(function(){
		//할일
		//수정된 데이터를 알아낸다.
		//받은 데이터를 꺼낸다
		var tmail = $('#tmail').val();
		var ttel = $('#ttel').val();
		var tano = $('#tano').val();
		
		//입력한 데이터 받아오고
		var pw = $('#pw').val();
		var mail = $('#mail').val();
		var tel = $('#tel').val();
		var ano = $('[name="ano"]:checked').val();
		
		if(!pw) {
			//비번 수정 안 된 경우
			$('#pw').prop('disabled', true);//데이터 전송 안되게 막는다.
		}               
		
		if(tmail == mail) {
			//메일 수정 안 된 경우
			$('#mail').prop('disabled', true);
		}
		
		if(ttel == tel) {
			$('#tel').prop('disabled', true);
		}
		
		if(tano == ano) {
			$('[name="ano"]').prop('disabled', true);			
		}
		
		//아무것도 수정 안 됨
		if(!pw && (tmail == mail) && (ttel == tel) && (tano == ano)) {
			alert('아무것도 수정 안함');
			return;
		}
		
		//보낼 주소 설정
		$('#frm').attr('action', '/whistle/member/editProc.blp');
		$('#frm').submit();
	});
});