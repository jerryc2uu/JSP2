<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Whistle Edit Info</title>
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/whistle/resources/css/user.css">
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/whistle/resources/js/whistle/edit.js"></script>
<style type="text/css">
	.avtimg {
		width: 100px;
		height: 100px;
	}
	.avtbox {
		display: inline-block;
		width: 102px;
		height: 117px;
	}
	#pwmsg, #repwmsg {
		display: none;
	}
	
	label.s3 {
		position: relative;
		top: 5px;
		font-size: 14pt;
		font-weight: normal;
		opacity: 0.7;
	}
	
	#gen label {
		position: relative;
		top: 2px;
		left: 7px;
	}
	
	h4 {
		margin: 0px;
	}
</style>
<script type="text/javascript">
	function getSid() {
		return '${SID}';
	}
</script>
</head>
<body>
	<div class="w3-content w3-margin-top mxw700">
		<!-- 타이틀 -->
		<h1 class="w3-pink w3-center w3-padding w3-card-4">Whistle 회원 정보 수정</h1>
		<form method="POST" action="" name="frm" id="frm" class="w3-col w3-margin-top w3-margin-bottom w3-padding w3-card-4">
			<input type="hidden" id="tmail" value="${DATA.mail}">
			<input type="hidden" id="ttel" value="${DATA.tel}">
			<input type="hidden" id="tano" value="${DATA.ano}">
			<input type="hidden" name="mno" value="${DATA.mno}">
			<div>
				<label class="w3-col s3 w3-right-align w3-margin-top clrgrey ft14 mgb10">회원이름 : </label>
				<h4 class="w3-col s8 w3-margin-top mgl10 mgb10 w3-center">${DATA.name}</h4>
			</div>
			<div>
				<label for="id" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">아 이 디 : </label>
				<h4 class="w3-col s8 mgb10 w3-center">${DATA.id}</h4>
			</div>
			<div>
				<label for="pw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">비밀번호 : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" name="pw" id="pw" class="w3-col w3-input w3-border">
					<span class="w3-col w3-text-red w3-center" id="pwmsg"># 비밀번호는 12345 만 가능합니다.</span>
				</div>
			</div>
			<div>
				<label for="repw" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">pw check : </label>
				<div class="w3-col s8 mgl10 mgb10">
					<input type="password" id="repw" class="w3-col w3-input w3-border">
					<span class="w3-col w3-text-red w3-center" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>
			<div>
				<label for="mail" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">회원메일 : </label>
				<input type="text" name="mail" id="mail" class="w3-col s8 mgl10 w3-input w3-border mgb10" value="${DATA.mail}">
			</div>
			<div>
				<label for="tel" class="w3-col s3 w3-right-align clrgrey ft14 mgb10">전화번호 : </label>
				<input type="text" name="tel" id="tel" class="w3-col s8 mgl10 w3-input w3-border mgb10" value="${DATA.tel}">
			</div>
			<div>
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10">회원성별 : </label>
				<h4 class="w3-col s8 mgl10 mgb10 w3-center">${DATA.gen eq "M" ? "남자" : "여자" }</h4>
			</div>
			<div class="w3-col" id="avtfr">
				<label class="w3-col s3 w3-right-align clrgrey ft14 mgb10">아 바 타 : </label>
				<div class="w3-col s8 mgl10 mgb10 w3-center">
						<div class="avtboxfr w3-center w3-margin-top" id="avt">
		<c:forEach var="data" items="${LIST}">
			<c:if test="${data.gen eq DATA.gen}">
						 	<div class="avtbox">
						 		<label for="avt${data.ano}">
						 			<img src="/whistle/resources/img/avatar/${data.savename}" class="w3-col avtimg">
						 		</label>
					<c:if test="${data.ano eq DATA.ano}">					
						 		<input type="radio" name="ano" id="avt${data.ano}" value="${data.ano}" checked>
					</c:if>
					<c:if test="${data.ano ne DATA.ano}">					
						 		<input type="radio" name="ano" id="avt${data.ano}" value="${data.ano}">
					</c:if>
						 	</div>
			</c:if>
		</c:forEach>
						</div>
				</div>
			</div>
		</form>
		
		<!-- 버튼 태그 -->
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-deep-orange w3-hover-orange w3-button" id="rbtn">reset</div> 
			<div class="w3-third w3-green w3-hover-lime w3-button" id="hbtn">home</div> 
			<div class="w3-third w3-blue w3-hover-aqua w3-button" id="ebtn">edit</div> 
		</div>
	</div>
</body>
</html>