<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>공지 사항</h1>
	<br>
	
	<c:choose>
	<c:when test="${principal.user.role == 'ROLE_ADMIN'}">
	<div>
	<button class="btn btn-dark btn-sm btn-block"
		onclick="location='/board/noticeForm'">공지 쓰기</button>
	</div>
	<br>
	
	<c:forEach var="board" items="${boards.content}">
		<div class="card">
			<div class="card-body justify-content-between">
			<span class="text-muted">${board.id}.</span>
				<span class="m-auto">${board.title}</span>
				<button class="btn btn-secondary btn-sm float-right"
					onclick="location='/board/${board.id}'">상세 보기</button>
			</div>
		</div>
		<br>
	</c:forEach>
	</c:when>	
		<c:otherwise>
		<c:forEach var="board" items="${boards.content}">
		<div class="card m-2">
			<div class="card-body justify-content-between">
			<span class="text-muted">${board.id}.</span>
				<span class="m-auto">${board.title}</span>
				<button class="btn btn-secondary btn-sm float-right"
					onclick="location='/board/${board.id}'">상세 보기</button>
			</div>
		</div>
	</c:forEach>
	</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${boards.first}">
			<ul class="pagination justify-content-center">
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number-1}">이전</a></li>
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number+1}">다음</a></li>
			</ul>
		</c:when>
		<c:when test="${boards.last}">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number-1}">이전</a></li>
				<li class="page-item disabled"><a class="page-link"
					href="?page=${boards.number+1}">다음</a></li>
			</ul>
		</c:when>
		<c:otherwise>
					<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number-1}">이전</a></li>
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number+1}">다음</a></li>
			</ul>
		</c:otherwise>
	</c:choose>
</div>
