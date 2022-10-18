<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../layout/menu.jsp" %>
	
	<c:if test="${message == 'success' }">
		<h2>${sessionScope.admin_name} ( ${sessionScope.admin_userid})님 환영합니다.</h2>
	</c:if>
	<ul>
		<li><a href="/prodc/category/get">상품 카테고리 관리</a> </li><br/>
		<li><a href="#">회원관리</a> </li><br/>
		<li><a href="/prodc/list.do">상품목록</a></li>
	</ul>

	
</body>
</html>