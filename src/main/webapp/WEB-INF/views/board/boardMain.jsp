<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>공지 사항</h1>
	<br>
	<div style="margin: 1rem" class="float-right">
		<form class="form-inline" action="" id="saerchForm">
			<div class="form-group">
				<label for="sel1"></label> <select class="form-control" id="sel1">
					<option>검색</option>
					<option>제목+내용</option>
					<option>작성일</option>
				</select>
			</div>
			<input class="form-control mr-sm-2" type="text" placeholder="Search"
				id="search" name="">
			<button class="btn btn-success" type="submit"
				onclick="onSearch(event)">Search</button>
		</form>
	</div>
	
	
	
	<table class="table table-hover" style="text-align: center">
		<thead class="thead-light " style="font-wiehgt: bold;">
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<c:choose>
			<c:when test="${principal.user.role == 'ROLE_ADMIN'}">
				<div>
					<button class="btn btn-dark btn-sm btn-block"
						onclick="location='/board/noticeForm'">공지 쓰기</button>
				</div>
				<br>
				<tbody>
					<c:forEach var="board" items="${boards.content}">
						<tr>
							<td>${board.id}</td>
							<td><a href="/board/${board.id}"
								style="text-decoration: none">${board.title}</a></td>
							<td>${board.user.username}</td>
							<td><javatime:format value="${board.createDate}"
									pattern="yyyy.MM.dd" /></td>
							<td>${board.count}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:when>
			<c:otherwise>
				<tbody>
					<c:forEach var="board" items="${boards.content}">
						<tr>
							<td>${board.id}</td>
							<td><a href="/board/${board.id}"
								style="text-decoration: none">${board.title}</a></td>
							<td>${board.user.username}</td>
							<td><javatime:format value="${board.createDate}"
									pattern="yyyy.MM.dd" /></td>
							<td>${board.count}</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:otherwise>
		</c:choose>
	</table>

	<ul class="pagination justify-content-center">
		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number-1}&gubun=${param.gubun}&text=${param.text}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link"
					href="?page=${boards.number-1}&gubun=${param.gubun}&text=${param.text}">이전</a></li>
			</c:otherwise>
		</c:choose>


		<c:forEach var="i" begin="1" end="${boards.totalPages}" step="1">
			<c:choose>
				<c:when test="${i eq boards.number+1}">
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
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number+1}&gubun=${param.gubun}&text=${param.text}">다음</a></li>

			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number+1}&gubun=${param.gubun}&text=${param.text}">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>
<br>
<%@ include file="../layout/footer.jsp"%>
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

		window.location = "/board/findbytext" + "?page=0&gubun=" + gubun
				+ "&text=" + text
	}
</script>
