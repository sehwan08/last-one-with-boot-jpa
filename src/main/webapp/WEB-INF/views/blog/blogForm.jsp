<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
<h1>공지사항 쓰기</h1>
<br>
	<form>
		<div class="form-group">
			<input type="text"
				class="form-control" placeholder="Enter title" id="title">
		</div>
		<hr>
		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<hr>
	<p style="font-size:12px; color:red;">※사진 첨부시 사진을 클릭하여 사이즈를 50%이하로 줄여 주세요! </p>
	<div class="float-right">
		<button id="btn-save" class="btn btn-info">글쓰기</button>
		<button type="button" class="btn btn-info" onclick="history.back()">이전</button>
	</div>
</div>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300,
		placeholder: 'Enter Content'
/* 		toolbar: [
	          ['style', ['style']],
	          ['font', ['bold', 'underline', 'clear']],
	          ['color', ['color']],
	          ['para', ['ul', 'ol', 'paragraph']],
	          ['table', ['table']],
	          ['insert', ['link', 'video']],
	          ['view', ['fullscreen', 'codeview', 'help']]
	        ] */
	});
</script>

<script src="/js/blog.js"></script>