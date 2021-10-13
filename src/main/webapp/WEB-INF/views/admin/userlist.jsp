<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<h1>회원 관리</h1>
	<br>
	<div>

		<div style="margin: 1rem" class="float-right">
			<form class="form-inline" action="" id="saerchForm">
				<div class="form-group">
					<label for="sel1"></label> <select class="form-control" id="sel1">
						<option>검색</option>
						<option>전체</option>
						<option>아이디</option>
						<option>이메일</option>
					</select>
				</div>
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					id="search" name="">
				<button class="btn btn-success" type="submit"
					onclick="onSearch(event)">Search</button>
			</form>
		</div>
		
		
		
		
		<div class="clearfix"></div>
		<c:forEach var="user" items="${lists.content}">
			
				<div class="card m-3">
					<div class="card-body">
						<p>
							회원 번호:<span id="id"> ${user.id}</span>
						</p>
						<p>회원 아이디: ${user.username}</p>
						<p>회원 이메일: ${user.email}</p>
						<c:choose>
							<c:when test="${user.id eq 1 }">
							</c:when>
							<c:otherwise>
							<button type="button" name="btnDelete" class="btn btn-danger">회원
							삭제</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			
		</c:forEach>
		
		
			<ul class="pagination justify-content-center">
				<c:choose>
					<c:when test="${lists.first}">
						<li class="page-item disabled"><a class="page-link"
							href="?page=${lists.number-1}&gubun=${param.gubun}&text=${param.text}">이전</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item "><a class="page-link"
							href="?page=${lists.number-1}&gubun=${param.gubun}&text=${param.text}">이전</a></li>
					</c:otherwise>
				</c:choose>


				<c:forEach var="i" begin="1" end="${lists.totalPages}" step="1">
					<c:choose>
						<c:when test="${i eq lists.number+1}">
							<li class="page-item active"><a class="page-link"
								href="?page=${i-1}&gubun=${param.gubun}&text=${param.text}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item "><a class="page-link"
								href="?page=${i-1}&gubun=${param.gubun}&text=${param.text}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${lists.last}">
						<li class="page-item disabled"><a class="page-link"
							href="?page=${lists.number+1}&gubun=${param.gubun}&text=${param.text}">다음</a></li>

					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="?page=${lists.number+1}&gubun=${param.gubun}&text=${param.text}">다음</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
	</div>
</div>
<script>
	$("button[name='btnDelete']").click(function() {
		if (!confirm("정말 삭제할까요?"))
			return false
		$.ajax({
			type : "DELETE",
			url : "/admin/delete/" + $("#id").text()
		}).done(function(resp) {
			alert("삭제성공")
			location.href = "/admin/userlist"
		})
	})
</script>


<script>
	function onSearch(event) {
		event.preventDefault()

		let gubun = $("#sel1 option:selected").val() //sel 값을 받아옴
		console.log(gubun)
		if (gubun == "검색") {
			alert("검색 구분자를 선택하세요!")
			return false;
		}

		let text = $("#search").val()

		if (text == "") {
			alert("검색어를 입력 하세요");
			$("#search").focus();
			return false;
		}

		window.location = "/admin/findbytext" + "?page=0&gubun=" + gubun
				+ "&text=" + text

		/* switch (gubun){
		case "아이디":
			 window.location="/admin/searchUsername/"+text+"?page=0"
			 break;
		case "이메일":
			 window.location="/admin/searchEmail/"+text+"?page=0"
			 break; 
		}*/

		/* let text = $("#search").val() */
		/* console.log(text) */
		/* $("#saerchForm").submit(); */
		/* window.location="/admin/searchUsername/"+text+"?page=0" */

	}
</script>