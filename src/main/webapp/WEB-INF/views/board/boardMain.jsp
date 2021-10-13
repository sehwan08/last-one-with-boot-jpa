<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>공지 사항</h1>
	<br>
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
					href="?page=${boards.number-1}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item "><a class="page-link"
					href="?page=${boards.number-1}">이전</a></li>
			</c:otherwise>
		</c:choose>


		<c:forEach var="i" begin="1" end="${boards.totalPages}" step="1">
			<c:choose>
				<c:when test="${i eq boards.number+1}">
					<li class="page-item active"><a class="page-link"
						href="?page=${i-1}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item "><a class="page-link"
						href="?page=${i-1}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${boards.last}">
						<li class="page-item disabled"><a class="page-link"
			href="?page=${boards.number+1}">다음</a></li>
					
			</c:when>
			<c:otherwise>
						<li class="page-item"><a class="page-link"
			href="?page=${boards.number+1}">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>
<script>
console.log(${boards.number})
</script>
