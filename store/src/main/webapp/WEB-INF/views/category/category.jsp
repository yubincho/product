<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/checkout/">
<link href="/docs/5.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
 <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      
      #paginationBox{
		padding : 10px 0px;
	}
    </style>
</head>
<body>

	<%@ include file="../layout/menu.jsp" %>
	<article>
	<div class="container">
		
		<!-- Menu form {s} -->
		<h4 class="mb-3">Menu Info</h4>
		<div>
			<form name="form" id="form" role="form" modelAttribute="categoryVO" method="post" action="${pageContext.request.contextPath}/category/save">
				<input type="hidden" name="idx" value="" />
				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="code">Code</label>
						<input path="code" id="code" class="form-control" name="code" placeholder="" value="" required="" />
					</div>
					<div class="col-md-5 mb-3">
						<label for="codename">Code name</label>
						<input path="codename" class="form-control" id="codename" name="codename" placeholder="" value="" required="" />
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 mb-3">
						<label for="comment">Comment</label>
						<input path="comment" class="form-control" id="comment" name="comment" placeholder="" value="" required="" />
					</div>
				</div>
				
			</form>
		</div>
		<!-- Menu form {e} -->
		
		
		
		<div>
			<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
			<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
			<button type="button" class="btn btn-sm btn-primary" id="btnInit">초기화</button>
		</div>
		
		<h4 class="mb-3" style="padding-top:15px">Menu List</h4>
		
		<!-- List{s} -->
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<colgroup>
					<col style="width:10%;" />
					<col style="width:15%;" />
					<col style="width:15%;" />
					<col style="width:10%;" />
					<col style="width:auto;" />
				</colgroup>
				<thead>
					<tr>
						<th>menu id</th>
						<th>code</th>
						<th>codename</th>
						<th>sort</th>
						<th>comment</th>
						<th>reg_date</th>
					</tr>
				</thead>
				
				<tbody id="categoryList" >

				</tbody>
			
			</table>
		</div>
		<!-- List{e} -->
		
		
	</div>
</article>

<script>

$(document).ready(function(){
	fn_showList();
	
	
	// 등록
	$("#btnSave").click(function(){
    	
    	let code = $("input[name=code]").val();
    	let codename = $("input[name=codename]").val();
    	let comment = $("input[name=comment]").val();
    	
    	if(code.trim() == ''){
    		$("input[name=code]").focus()
    		alert("code를 입력해 주세요.");
    		return false;
    	}
    	
    	if(codename.trim() == ''){
    		$("input[name=codename]").focus()
    		alert("codename을 입력해 주세요.");
    		return false;
    	}
    	
    	if(comment.trim() == ''){
    		$("input[name=comment]").focus()
    		alert("comment를 입력해 주세요.");
    		return false;
    	}
    	
    	$.ajax({
            type:'POST',       // 요청 메서드 
            url: '/prodc/category/save',  // 요청 URI  site/comments?bno=3
            headers : { "content-type": "application/json"}, // 요청 헤더
            data : JSON.stringify({code:code, codename:codename, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
            success : function(result){
            	fn_showList(result); 
            	//초기화 이벤트 호출
				$("#btnInit").trigger("click");
            },
            error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
        });
    	
    	

    });
	
	
	// 수정 
	
	
	
	
	
	
	//초기화 버튼 이벤트 부분 추가
	$(document).on('click', '#btnInit', function(e){
		$('#code').val('');
		$('#codename').val('');
		$('#comment').val('');
	});
	
	

	
	function fn_showList(data) {

		$.ajax({
			url : '/prodc/category/list',
			type: "GET",
			data:JSON.stringify(data), // 생성을 위한 정보의 객체를 JSON으로 바꿔서 데이터로 보냄
            contentType:"application/json; charset=utf-8", 
			success : function(result){
				
				$('#categoryList').html(toHtmls(result));
				
			}, error   : function(){ alert("error") }
		});
	}
	
	

	let toHtmls = function(data) {
		var htmls = "";
   	 
   		data.forEach(function(e){
   			
   			htmls += '<tr>';
   			htmls += '<td id="idx">' + e.idx + '</td>';
			htmls += '<td>';
			htmls += '<a href="#" onClick="fn_menuInfo(' + e.idx +',\'' + e.code +'\',\'' + e.codename + '\', '  + e.comment + '\')" >';
			htmls += e.code;
			htmls += '</a>';
			htmls += '</td>';
			htmls += '<td>' + e.codename + '</td>';
			htmls += '<td>' + e.sort_num + '</td>';
			htmls += '<td>' + e.comment + '</td>';
			htmls += '<td>' + e.reg_date + '</td>';
			htmls += '</tr>';
   		})
   		return htmls;
	}
	
	
	// 수정 
	function fn_menuInfo(idx, code, codename, comment) {
		$("#idx").val(idx);
		$("#code").val(code);
		$("#codename").val(codename);
		$("#comment").val(comment);
        
	        //코드 부분 읽기 모드로 전환
	        $("#code").attr("readonly", true);
	}
});



	
	
</script>
