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
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	
	function product_write() {
		
		var cateCode = document.form1.category.value;
		
		
		var product_name = document.form1.product_name.value;
		var price = document.form1.price.value;
		var description = document.form1.description.value;

		if (product_name == "") {
			alert("상품명을 입력하세요.");
			document.form1.product_name.focus();
			return;
		}
		if (price == "") {
			alert("가격을 입력하세요.");
			document.form1.price.focus();
			return;
		}
		if (description == "") {
			alert("상품설명을 입력하세요.");
			document.form1.description.focus();
			return;
		}
		document.form1.action = "/prodc/insert.do";
		document.form1.submit();
	}
</script>
</head>
<body>

	<h2>상품 등록</h2>
	<form name="form1" method="post" enctype="multipart/form-data" action="">
		
		<table>
		<tr>
			<td>Category:</td>
			<td>
			<select name="category" id="category">
				<c:forEach items="${listCategory}" var="category">
					<option value="${category.code}" selected>${category.code}</option>
				</c:forEach>
			</select>
			</td>
		</tr>
			<tr>
				<td>상품명</td>
				<td><input name="product_name"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input name="price"></td>
			</tr>
			<tr>
				<td>상품설명</td>
				<td><textarea rows="5" cols="60" name="description"></textarea></td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button" value="등록" onclick="product_write()"> <input type="button" value="목록" onclick="location.href='/prodc/list.do'"></td>
			</tr>
		</table>
	</form>

<script>

//컨트롤러에서 데이터 받기
var jsonData = JSON.parse('${category}');
console.log(jsonData);

var cate1Arr = new Array();
var cate1Obj = new Object();

// 분류 셀렉트 박스에 삽입할 데이터 준비
for(var i = 0; i < jsonData.length; i++) {
 
  cate1Obj = new Object();  //초기화
  cate1Obj.cateCode = jsonData[i]['code'];
  cate1Obj.cateName = jsonData[i]['codename'];
  cate1Arr.push(cate1Obj);
  console.log(cate1Obj);
}


var cate1Select = $("select.cateCode")

for(var i = 0; i < cate1Arr.length; i++) {
	cate1Select.append("<option value='" + cate1Arr[i].cateCode + "' selected>"
      + cate1Arr[i].cateName + "</option>"); 
}




</script>
</body>
</html>