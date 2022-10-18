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
<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

<%@ include file="../layout/menu.jsp" %>

<h2>상품목록</h2>

	<form method="get">
		상품명: <input type="text" name="product_name" value="${product_name}">
		<button type="submit" value="조회" style="width:30px; height:20px;">조회</button>
		
	</form>

<br>

	<button type="button" onclick="location.href='write'">상품등록</button>

<hr>

	등록된 상품개수: ${fn:length(list)}

	<table width="100%">
	
		<tr>
		<!--varStatus : 루프의 상태변수, index 0부터, count 1부터 -->
		<c:forEach var="row" items="${list}" varStatus="vs">
			<td style="width:20%; word-break: break-word; vertical-align: bottom;">
			<!-- break-word : 줄바꿈,  vertical-align: 세로정렬 -->
			<c:choose>
				<c:when test="${row.filename != '-' }">
					<img  src="/prodc/resources/images/${row.filename}" width="100px" height="100px"><br>
				</c:when>
				<c:otherwise>
				[상품 이미지 미등록]<br>
				</c:otherwise>
			</c:choose>
			
			<a href="<c:url value="/detail/${row.product_code}"/>">
					상품명: ${row.product_name}<br>
					가격: <fmt:formatNumber value="${row.price}" pattern="#,###" />원</a>
			</td>
			<c:if test="${vs.count mod 5 == 0}">
				</tr><tr>
			</c:if>
		</c:forEach>
		</tr>
		
	</table>

<script>
	$(document).ready(function(){

		$("#removeBtn").on("click", function(){
		    if(!confirm("정말로 삭제하시겠습니까?")) return;
		    let form = $("#form");
		    form.attr("action", "<c:url value='/board/remove?${searchCondition.queryString}'/>");
		    form.attr("method", "post");
		    form.submit();
		});
	}
</script>

</body>
</html>