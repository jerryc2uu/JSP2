<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackPink 게시판 상세보기</title>
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
		<h1 class="w3-blue w3-padding w3-center w3-card-4">게시글 상세보기</h1>
		<div class="w3-col w3-card-4 frmpadding">
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label class="w3-col s2">글번호</label>
				<h4 id="bno" class="w3-col m10">${DATA.bno}</h4>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label class="w3-col s2">Writer</label>
				<h4 id="writer" class="w3-col m10">${DATA.id}</h4>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label class="w3-col s2">Title</label>
				<h4 id="title" class="w3-col m10">${DATA.title}</h4>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label class="w3-col s2">Content</label>
				<h4 id="body" class="w3-col m10">${DATA.body}</h4>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label class="w3-col s2">작성일</label>
				<h4 id="wdate" class="w3-col m10">${DATA.sdate}  ${DATA.stime}</h4>
			</div>
			
<c:if test="${not empty LIST}">			
			<div class="w3-col w3-margin-bottom" id="previewbox">
				<label class="w3-col s2">Image</label>
				<div class="w3-col m10 w3-center" id="preview">
		<c:forEach var="data" items="${LIST}">
			<c:if test="${not empty data.savename}"	>	
					<div class="inblock picbox">
						<img class="pic" src="/whistle${data.dir}/${data.savename}"> 
					</div>
			</c:if>
		</c:forEach>
				</div>
			</div>
</c:if>
		</div>
		
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-button w3-green" id="listbtn">리스트</div>
			<div class="w3-third w3-button w3-blue" id="rbtn">리셋</div>
			<div class="w3-third w3-button w3-deep-orange" id="edit">글수정</div>
		</div>
	</div>
	
	<form method="POST" action="/whistle/board/boardList.blp" id="pageFrm" name="pageFrm">
		<input type="hidden" name="nowPage" value="${NOWPAGE}">
<%-- 		<input type="hidden" name="nowPage" value="${param.nowPage}"> --%>
	</form>
</body>
</html>