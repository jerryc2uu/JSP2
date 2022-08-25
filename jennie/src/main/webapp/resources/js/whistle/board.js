$(document).ready(function(){
	/* 페이지 버튼 클릭이벤트 처리 */
	$('.pbtn').click(function(){
		// 페이지번호 읽고
		var pno = $(this).attr('id');
		// 페이지 번호 셋팅하고
		$('#nowPage').val(pno);
		// 폼 태그 전송
		$('#pageFrm').submit();
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
		$('#pageFrm').attr('action', '/whistle/board/boardWrite.blp');
		$('#pageFrm').submit();
	});
	
	$('#filebox').on('change', '.upfile', function(e){
		var txt = $(this).val();
		var len = $('.upfile').length;
		if(!txt && len > 1){
			$('#img' + $(this).attr('id').substring(4)).remove();
			$(this).remove();
			if($('.pic').length == 0){
				$('#previewbox').slideUp(100);
			}
		} else {
			$('#filebox').append('<input type="file" class="w3-input w3-border w3-margin-bottom upfile">');
			
			$('#previewbox').stop().slideUp(300, function(){
				
				var box = document.createElement('div');
				$(box).attr('class', 'inblock picbox');
				var img = document.createElement('img');
				$(img).attr('class', 'pic');
				var path = URL.createObjectURL(e.target.files[0]);
				$(img).attr('src', path);
				$(box).append($(img));
				$('#preview').append($(box));
				
				var cnt = $('.picbox').length;
				for(i = 1; i <= cnt ; i++ ){
					$('.picbox').eq(i-1).attr('id', 'img' + i);
				}
				$('#previewbox').stop().slideDown(300);
			});
		}
		len = $('.upfile').length;
		for(i = 1; i < len ; i++ ){
			$('.upfile').eq(i-1).attr('id', 'file' + i);
			$('.upfile').eq(i-1).attr('name', 'file' + i);
		}
	});
	
	$('#wpbtn').click(function(){
		var title = $('#title').val();
		if(!title) {
			$('#title').focus();
			return;
		}
		var body = $('#body').val();
		if(!body){
			$('#body').focus();
			return;
		}
		
		var el = $('input[type="file"]');
		
		for(i = 0 ; i < $(el).length ; i++ ){
			var tmp = $(el).eq(i).val();
			if(!tmp){
				$(el).eq(i).prop('disabled', true);
			}
		}
		
		$('.upfile').last().prop('disabled', true);
		
		$('#frm').submit();
	});
	
	$('.brdList').click(function(){
		var sno = $(this).attr('id');//${data.bno}
		
		$(document.frm.bno).val(sno);//name 속성값으로 태그 선택... <input type="hidden" name="bno" >
		
		//document 객체 아래 => 폼, 이미지 객체 등
		
		$('#frm').submit();
	});
});