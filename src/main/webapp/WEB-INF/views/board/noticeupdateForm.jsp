<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<h1>공지사항</h1>
	<br>
	<div class="form-group">
		<label for="id">글 번호:</label> <input type="text" id="id"
			class="form-control" value="${board.id }" disabled="disabled">
	</div>

	<div class="form-group">
		<label for="username">글 작성자:</label> <input type="text"
			name="username" class="form-control" value="${board.user.username}"
			disabled="disabled">
	</div>

	<form>
		<div class="form-group">
			<label for="title">제목:</label> <input type="text"
				class="form-control" value="${board.title}" id="title">
		</div>
		<div class="form-group">
			<label for="comment">내용:</label>
			<textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
		</div>
	</form>


	<br>
	<div class="float-right">
				<button class="btn btn-primary" id="btn-update">수정 완료</button>
				<button type="button" id="btnDelete" class="btn btn-danger">삭제</button>
				<button type="button" class="btn btn-primary"
					onclick="history.back()">이전</button>
	</div>
</div>


<script>
	$("#btnDelete").click(function() {
		if (!confirm("정말 삭제할까요?"))
			return false
		$.ajax({
			type : "DELETE",
			url : "/board/notice/"+${board.id},
			success : function(resp) {
				console.log(resp);
				if (resp == "success") {
					alert("삭제성공");
					location.href = "/board/notice"
				}
			} //success
		})//ajax
	}) //btnDelete
</script>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>

<script src="/js/notice.js"></script>