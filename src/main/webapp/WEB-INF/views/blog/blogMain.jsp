<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<h1>나만의 여행 이야기</h1>
		<div style="margin: 1rem" class="float-right">
			<form class="form-inline" action="" id="saerchForm">
				<div class="form-group">
					<label for="sel1"></label> <select class="form-control" id="sel1">
						<option>검색</option>
						<option>모두</option>
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
		
		<button class="btn btn-success btn-sm "
						onclick="location='/blog/blogForm'">블로그 쓰기</button>
	
	
	
	
</div>