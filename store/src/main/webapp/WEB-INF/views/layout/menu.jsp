<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="/prodc/">Home</a> |
	<a href="#">한줄메모장</a> |
	<a href="/prodc/list.do">상품목록</a> |
	
	<c:if test="${sessionScope.admin_userid != null }">
		<a href="/prodc/write.do">상품등록</a> |
	</c:if>
	<a href="/prodc/cart/list.do">장바구니</a> |
	<div style="“text-align: right;">

		<c:choose>
			<c:when test="${sessionScope.userid == null }">
				<a href="/prodc/login.do">로그인</a> |
			<a href="/prodc/admin/login">관리자 로그인</a>
			</c:when>
			<c:otherwise>
			${sessionScope.name}님이 로그인중입니다.
			<a href="/prodc/logout.do">로그아웃</a> |
			<a href="/prodc/admin/logout">관리자 로그아웃</a>
			</c:otherwise>
		</c:choose>
	</div>

	<hr>

</body>
</html>