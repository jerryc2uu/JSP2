<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackPink 댓글 게시판 글작성</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/whistle/reboard.js"></script>
<style type="text/css">
	.w3-button {
		padding: 1px 16px;
	}
	.box120 {
		width: 135px;
		height: auto;
	}
	.mid {
		position: relative;
		top: 5px;
		right: 5px;
	}
</style>
</head>
<body>
	<div class="w3-content mxw750 w3-margin-top">
		<!-- 페이지 헤더 -->
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-pink w3-center w3-padding mg0">BlackPink 댓글 게시글 수정</h1>
			<nav class="w3-bar w3-amber">
				<div class="w3-col w150 w3-button w3-small w3-green menubtn" id="hbtn">home</div>
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-button w3-small w3-deep-orange w3-right menubtn" id="lbtn">login</div>
				<div class="w3-col w150 w3-button w3-small w3-orange w3-right menubtn" id="jbtn">join</div>
</c:if>
<c:if test="${not empty SID}">
				<div class="w3-col w150 w3-button w3-small w3-red w3-right menubtn" id="obtn">logout</div>
</c:if>
			</nav>
		</header>
		
		<!-- 페이지 본문 -->
		<div class="w3-col">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col box120 pdAll10 w3-border-right">
					<img src="/whistle/resources/img/avatar/${requestScope.DATA.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
					<span class="w3-col w3-center ft10 mid"><b>${sessionScope.SID}</b></span>
				</div>
				<div class="w3-rest w3-padding">
					<div class="w3-col w3-border-bottom">
						<span class="w3-col w3-left mgb10 ft10 "><strong>${DATA.sdate}</strong></span>
					</div>
					<input type="hidden" id="obody" value="${DATA.body}">
					<form method="POST" action="/whistle/reboard/reboardEditProc.blp" 
											id="frm" name="frm" class="w3-col w3-margin-top">
						<input type="hidden" id="nowPage" name="nowPage" value="${param.nowPage}">
						<input type="hidden" id="bno" name="bno" value="${DATA.bno}">
						<textarea class="w3-col w3-padding ft12" id="body" name="body"
									style="resize: none;">${DATA.body}</textarea>
					</form>
				</div>
			</div>
		</div>
		
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-button w3-green" id="listbtn">리스트</div>
			<div class="w3-third w3-button w3-blue" id="rbtn">리셋</div>
			<div class="w3-third w3-button w3-deep-orange" id="editbtn">글수정</div>
		</div>	
</body>
</html>