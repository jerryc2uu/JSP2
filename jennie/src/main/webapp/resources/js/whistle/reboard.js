$(document).ready(function(){
	//페이지 버튼 클릭 이벤트 처리
	$('.pbtn').click(function(){
		var pno = $(this).attr('id');//this : 클릭된 태그, 아이디값(페이지 번호)
		//페이지 번호 셋팅
		$('#nowPage').val(pno);
		//폼 태그 전송
		$('#frm').submit();
		
	});
	
	/*//홈버튼 클릭 이벤트 처리
	$('#hbtn').click(function(){
		$(location).attr('href', '/whistle/main.blp');	
	});
	
	//로그인 버튼 클릭 이벤
	$('#lbtn').click(function(){
		$(location).attr('href', '/whistle/member/login.blp');
	});
	
	//join 버튼
	$('#jbtn').click(function(){
		$(location).attr('href', '/whistle/member/join.blp');
	});
	//로그아웃 버튼
	$('#obtn').click(function(){
		$(location).attr('href', '/whistle/member/logout.blp');
	});*/
	
	$('.menubtn').click(function(){
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
		case 'wbtn':
			//addr = '/whistle/reboard/reboardWrite.blp';
			$('#frm').attr('action', '/whistle/reboard/reboardWrite.blp');
			$('#frm').submit();// 이 순간 request 객체 하나 생성
			return;
		}
		
		$(location).attr('href', addr);
	});
	
	//댓글 등록 버튼 클릭 이벤
	$('#cmtbtn').click(function(){
		var btxt = $('#body').val();
		btxt = btxt.trim();
		
		if(!btxt) {
			$('#body').focus();
			return;
		}
		
		if(btxt.length > 200) {
			btxt = btxt.subString(0, 200);
			$('#body').val(btxt);
			alert('코멘트의 글자수는 200자를 초과할 수 없습니다.');
			return;
		}
		
		$('#frm').submit();
	});
	//댓글 대댓글/수정/삭제
	$('.w3-button.w70').click(function(){
		var btxt = $(this).html();
		
		//글번호
		var sno = $(this).parent().attr('id');

		$('#bno').val(sno);
		
		if(btxt == '댓글') {
			/*
				질의명령에서 뽑아오므로 무쓸모
			var tbody = $('#bd'+sno).html();
				//bno == 1001 ==> #db1001
			if(tbody.length >= 10) {
				tbody = tbody.subString(0, 10) + '...';
			}*/
			
			//$('#body').val(tbody); 질의명령에서 뽑아올것			
			$('#frm').attr('action', '/whistle/reboard/reboardComment.blp');
		} else if(btxt == '삭제') {
			$('#frm').attr('action', '/whistle/reboard/reboardDel.blp');
		} else if(btxt == '수정') {
			$('#frm').attr('action', '/whistle/reboard/reboardEdit.blp');
		}
		
		$('#frm').submit();
	});
	
	//글쓰기 페이지
	$('#listbtn').click(function(){
		// form 태그의 액션 속성값 변경
		$('#frm').attr('action', '/whistle/reboard/reboardList.blp');
		// 사용하지 않는 태그 비활성시키고
		$('#mno').prop('disabled', true);
		$('#body').prop('disabled', true);
		
		$('#frm').submit();
	});

	
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	$('#wpbtn').click(function(){
		//입력된 글 유효성 검사
		var txt = $('#body').val();
		txt = txt.trim();
		if(!txt || txt.length == 0) {
			$('#body').val('');
			$('#body').focus();
			return;
		}
		
		alert(txt);
		$('#body').val(txt);
		
		$('#frm').submit();
	});
	
	//글수정 버튼 이벤트
	$('#editbtn').click(function(){
		var txt = $('#body').val();
		var otxt = $('#obody').val();
		
		if(txt == otxt) {
			return;
		}
		
		if(txt.length > 200) {
			txt = txt.subString(0, 200);
			$('#body').val(txt);
			alert('코멘트의 글자수는 200자를 초과할 수 없습니다.');
			return;
		}
		
		$('#frm').submit();
	});
});