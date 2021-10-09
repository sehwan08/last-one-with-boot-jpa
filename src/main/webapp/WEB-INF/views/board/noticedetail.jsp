<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>공지사항</h1>
	<br>
	<div class="form-group">
		<label for="id">글 번호:</label> <input type="text" name="id"
			class="form-control" value="${board.id }" disabled="disabled">
	</div>

	<div class="form-group">
		<label for="username">글 작성자:</label> <input type="text"
			name="username" class="form-control" value="${board.user.username}"
			disabled="disabled">
	</div>

	<div class="form-group">
		<label for="title">제목: </label> <input type="text" name="title"
			class="form-control" value="${board.title}" disabled="disabled">
	</div>
	<div class="form-group">
		<label for="content">내용: </label>
		<div style="border: 2px solid #d3d3d3; border-width:1px; border-radius: 5px;  padding:10px">
			${board.content}
		</div>
	</div>


	<br> <br>
	<div class="float-right">
		<c:choose>
			<c:when test="${principal.user.role == 'ROLE_ADMIN'}">
				<button class="btn btn-primary" onclick="location.href='/board/noticeupdate/${board.id}'">수정</button>
				<button type="button" class="btn btn-primary"
					onclick="history.back()">이전</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-primary"
					onclick="history.back()">이전</button>
			</c:otherwise>
		</c:choose>

	</div>
</div>



