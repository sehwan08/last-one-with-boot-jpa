<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="title">제목:</label> <input type="text"
				class="form-control" placeholder="Enter title" id="title">
		</div>
		<div class="form-group">
			<label for="comment">내용:</label>
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<p style="font-size:12px; color:red;">※사진 첨부시 사진을 클릭하여 사이즈를 50%이하로 줄여 주세요! </p>
	<div class="float-right">
		<button id="btn-save" class="btn btn-info">글쓰기</button>
		<button type="button" class="btn btn-info" onclick="history.back()">이전</button>
	</div>
</div>
<script>
	$('.summernote').summernote({
		tabsize : 2,
		height : 300
	});
</script>

<script src="/js/notice.js"></script>
