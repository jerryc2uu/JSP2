<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/whistle/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#frm').submit();
	});
</script>
</head>
<body>
<c:if test="${not empty VIEW}">
	<form method="POST" action="${VIEW}" id="frm" name="frm">
<c:if test="${not empty NOWPAGE}">
		<input type="hidden" name="nowPage" value="${NOWPAGE}">
</c:if>		
	</form> 
	
	<%-- 
	GET방식
	<c:redirect url="">
		<c:param var="">
	</c:redirect> --%>
</c:if>		
</body>
</html>