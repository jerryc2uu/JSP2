<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/whistle/reboard.js"></script>
<style type="text/css">
	html, body {
		height: 100%;
		margin: 0px;
		padding: 0px;
	}
	
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
<script type="text/javascript">
	$(document).ready(function(){
		$('#wbtn').click(function(){
			$(location).attr('href', '/whistle/board/boardWrite.blp');	
		});
	});
</script>
</head>
<body class="w3-light-grey">
	<div class="w3-content mxw700">
	<!-- 페이지 헤더 -->
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-pink w3-center w3-padding w3-card-4 mg0">파일게시판</h1>
			
			<nav class="w3-bar w3-amber">
					<div class="w3-col w150 w3-button w3-small w3-green menubtn" id="hbtn">home</div>
	<c:if test="${empty SID}">
					<div class="w3-col w150 w3-button w3-small w3-deep-orange w3-right menubtn" id="lbtn">login</div>
					<div class="w3-col w150 w3-button w3-small w3-orange w3-right menubtn" id="jbtn">join</div>
	</c:if>
	<c:if test="${not empty SID}">
					<div class="w3-col w150 w3-button w3-small w3-red w3-right menubtn" id="obtn">logout</div>
					<div class="w3-col w150 w3-button w3-small w3-orange w3-right menubtn" id="wbtn">글작성</div>
	</c:if>
				</nav>
		</header>
		<div class="w3-col w3-white w3-padding w3-card-4">
			<div class="w3-col w3-light-grey w3-center w3-border">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">글번호</div>
					<div class="w3-col m7 w3-border-right">작성자</div>
				</div>
				<div class="w3-col m4 w3-border-right">글제목</div>
				<div class="w3-col m3 w3-border-right">작성일</div>
				<div class="w3-col m1 w3-border-right">클릭수</div>
				<div class="w3-col m1">파일</div>
			</div>
			<div class="w3-col w3-white w3-center w3-border-bottom w3-border-left w3-border-right">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">100001</div>
					<div class="w3-col m7 w3-border-right">작성자</div>
				</div>
				<div class="w3-col m4 w3-border-right">글제목</div>
				<div class="w3-col m3 w3-border-right">작성일</div>
				<div class="w3-col m1 w3-border-right">클릭수</div>
				<div class="w3-col m1">파일</div>
			</div>
			
			<!-- 페이지 처리 시작 -->
			<div class="w3-center">
				<div class="w3-bar w3-border w3-round-medium w3-card w3-margin-top w3-margin-bottom">
		<c:if test="${PAGE.startPage eq 1}">
					<div class="w3-bar-item w3-light-grey">&laquo;</div>
		</c:if>
		<c:if test="${PAGE.startPage ne 1}">
					<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.startPage - 1}">&laquo;</div>
		</c:if>
		<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
				<c:if test="${page eq PAGE.nowPage}">
					<div class="w3-bar-item w3-orange">${page}</div>
				</c:if>
				<c:if test="${page ne PAGE.nowPage}">
					<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${page}">${page}</div>
				</c:if>
		</c:forEach>
				<c:if test="${PAGE.endPage eq PAGE.totalPage}">
					<div class="w3-bar-item w3-light-grey">&raquo;</div>
				</c:if>
				<c:if test="${PAGE.endPage ne PAGE.totalPage}">
					<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.endPage + 1}">&raquo;</div>
				</c:if>
				</div>
			</div>
			<!-- 페이지 처리 태그 끝 -->
		</div>
	</div>
</body>
</html>