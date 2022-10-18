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

	<script>
	$(function() {
		$("#btnAdd").click(function() {
			location.href = "/prodc/write.do";
		});
		
		$("#serchBtn").click(function() {
			location.href = "/prodc/search.do";
		});
	});
</script>

</head>
<body>
	<%@ include file="../layout/menu.jsp"%>

	<h2>상품목록</h2>
	
		<form method="get" action="">
			상품명: <input type="text" name="product_name" value="${product_name}">
			<button type="submit" id="serchBtn" value="조회" style="width:30px; height:20px;">조회</button>
		</form>

<br>
	
	<!-- 세션 확인 -->
	<c:if test="${sessionScope.admin_userid != null }">
		<button type="button" id="btnAdd">상품등록</button>
	</c:if>
	<br/><br/>
	<table border="1" width="500px">
		<tr>
			<th>상품ID</th>
			<th>&nbsp;</th>
			<th>상품명</th>
			<th>가격</th>
		</tr>
		
		등록된 상품개수: ${fn:length(list)}
		<br/><br/>
		<!-- var="레코드변수" items="리스트변수" -->
		<c:forEach var="row" items="${list}">
			<tr>
				<td>${row.product_code}</td>
				<td>${row.cateCode}</td>
				<td>
					<img src="/prodc/resources/images/${row.filename}" width="100px" height="100px">
				</td>
				<td>
					<a href="/prodc/detail/${row.product_code}">${row.product_name}</a>
					<c:if test="${sessionScope.admin_userid != null }">
						<br>
						<a href="/prodc/edit/${row.product_code}">[편집] </a>
					</c:if>
				</td>
				<td>
					<fmt:formatNumber value="${row.price}" pattern="#, ###" />
				</td>
			</tr>
		</c:forEach>
	</table>



</body>
</html>