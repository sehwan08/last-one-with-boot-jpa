<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>나만의 여행 이야기</h1>
	<br>
	<div class="form-group">
		<label for="id">글 번호:</label> <input type="text" name="id"
			class="form-control" value="${blog.id}" disabled="disabled">
	</div>

	<div class="form-group">
		<label for="username">글 작성자:</label> <input type="text"
			name="username" class="form-control" value="${blog.user.username}"
			disabled="disabled">
	</div>

	<div class="form-group">
		<label for="title">제목: </label> <input type="text" name="title"
			class="form-control" value="${blog.title}" disabled="disabled">
	</div>
	<div class="form-group">
		<label for="content">내용: </label>
		<div
			style="border: 2px solid #d3d3d3; border-width: 1px; border-radius: 5px; padding: 10px">
			${blog.content}</div>
	</div>
	<hr>

	<div class="card">
		<form>
			<input type="hidden" id="userId" value="${principal.user.id}" /> <input
				type="hidden" id="blogId" value="${blog.id}" />
			<div class="card-body">
				<textarea id="reply-content" class="form-control" rows="1"></textarea>
			</div>
			<div class="card-footer">
				<button type="button" id="btn-reply-save" class="btn btn-warning">댓글
					쓰기</button>
			</div>
		</form>
	</div>
	<br>

	<div class="card">
		<div class="card-header">댓글</div>
		<ul id="reply--box" class="list-group">
			<c:forEach var="reply" items="${blog.replies}">
				<li id="reply--${reply.id}"
					class="list-group-item d-flex justify-content-between">
					<div>
						${reply.content} <span class="text-primary">(<javatime:format
								value="${reply.createDate}" pattern="yyyy.MM.dd HH:mm" />)
						</span>
					</div>


					<div class="d-flex">
						<div class="font-weight-bold">작성자: ${reply.user.username }
							&nbsp;</div>
						<c:if test="${reply.user.id == principal.user.id }">
							<button onClick="index.replyDelete(${blog.id}, ${reply.id})"
								class="btn btn-danger btn-sm badge" style="font-size: 13px">삭제</button>
						</c:if>
					</div>

				</li>
			</c:forEach>
		</ul>
	</div>


	<br>
	<div class="float-right">
		<c:choose>
			<c:when test="${principal.user.id == blog.user.id}">
				<button class="btn btn-primary"
					onclick="location.href='/blog/blogUpdate/${blog.id}'">수정</button>
				<button type="button" class="btn btn-primary"
					onclick="location.href='/blog/blogMain'">블로그로 이동</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-primary"
					onclick="location.href='/blog/blogMain'">블로그로 이동</button>
			</c:otherwise>
		</c:choose>

	</div>
</div>
<br>
<br>


<script src="/js/blog.js"></script>
