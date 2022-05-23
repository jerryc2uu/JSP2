<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackPink 댓글게시판</title>
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
			<h1 class="w3-pink w3-center w3-padding mg0">BlackPink 댓글 게시판</h1>
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
		
		<!-- 페이지 본문 -->
<c:forEach var="data" items="${LIST}">
		<div class="w3-col" style="padding-left: ${data.step * 70}px">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col box120 pdAll10 w3-border-right">
					<img src="/whistle/resources/img/avatar/${data.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
					<span class="w3-col w3-center ft10 mid"><b>${data.id}</b></span>
				</div>
				<div class="w3-rest w3-padding">
					<div class="w3-col w3-border-bottom">
						<div class="w3-col w3-twothird w3-right" id="${data.bno}">
							<div class="w3-col w3-button w3-small w70 w3-blue w3-right">댓글</div>
		<c:if test="${SID eq data.id}">
							<div class="w3-col w3-button w3-small w70 w3-orange w3-right">수정</div>
							<div class="w3-col w3-button w3-small w70 w3-red w3-right">삭제</div>
		</c:if>
						</div>
						<span class="w3-third w3-left mgb10 ft10"><small>${data.sdate}</small></span>
					</div>
					<div class="w3-col w3-margin-top">
						<span class="w3-col w3-padding ft12">${data.body}</span>
					</div>
				</div>
			</div>
		</div>
</c:forEach>
		
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
	
	<!-- 메세지 출력 모달창 -->
<c:if test="${not empty MSG}"> <%-- MSG라는 키값으로 기억된 데이터 있으면 보여줌 --%>
	<div id="modal" class="w3-modal" style="display:block;">
	    <div class="w3-modal-content mxw650 w3-animate-top w3-card-4">
	      <header class="w3-container w3-blue"> 
	        <span onclick="document.getElementById('modal').style.display='none'" 
	        class="w3-button w3-display-topright">&times;</span>
	        <h2>BlackPink Message</h2>
	      </header>
	      <div class="w3-container w3-center">
	        <h4>${MSG}</h4>
	      </div>
	    </div>
 	</div>
</c:if>
	
	<!-- 데이터 전송용 form 태그 -->
	<form method="POST" action="/whistle/reboard/reboardList.blp" id="frm" name="frm">
		<input type="hidden" id="nowPage" name="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" id="bno" name="bno">
	</form>
</body>
</html>