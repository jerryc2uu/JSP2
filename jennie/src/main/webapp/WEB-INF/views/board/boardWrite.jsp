<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackPink 게시판 글쓰기</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/whistle/board.js"></script>
<style type="text/css">
	label {
		font-size: 14pt;
		/* font-weight: bold; */
		color: gray;
		padding-right: 20px;
		text-align: right;
	}
	input[type="file"].w3-margin-bottom {
		margin-bottom: 5px!important;
	}
</style>
</head>
<body>
	<div class="w3-content mxw700">
		<h1 class="w3-blue w3-padding w3-center w3-card-4">게시글 작성</h1>
		<form method="POST" action="/whistle/board/boardWriteProc.blp" encType="multipart/form-data"
				id="frm" name="frm" class="w3-col w3-card-4 frmpadding">
			<input type="hidden" name="nowPage" id="nowPage" value="1">
			<%-- <input type="hidden" name="nowPage" id="nowPage" value="${param.nowPage}"> --%>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label for="title" class="w3-col s2">Title</label>
				<input type="text" id="title" name="title" class="w3-col m10 w3-input w3-border">
			</div>
			<div class="w3-col w3-margin-bottom">
				<label class="w3-col s2">File</label>
				<div class="w3-col m10" id="filebox">
					<input type="file" class="w3-input w3-border w3-margin-bottom upfile">
				</div>
			</div>
			<div class="w3-col w3-margin-bottom" id="previewbox" style="display: none;">
				<label class="w3-col s2">Preview</label>
				<div class="w3-col m10 w3-center" id="preview">
				</div>
			</div>
			<div class="w3-col">
				<label for="body" class="w3-col s2">글본문</label>
				<div class="w3-col m10">
					<textarea class="w3-col w3-input w3-padding w3-border w3-margin-bottom" 
							id="body" name="body" rows="10" style="resize: none;"></textarea>
				</div>
			</div>
		</form>
		
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-button w3-green" id="listbtn">리스트</div>
			<div class="w3-third w3-button w3-blue" id="rbtn">리셋</div>
			<div class="w3-third w3-button w3-deep-orange" id="wpbtn">글등록</div>
		</div>
	</div>
</body>
</html>