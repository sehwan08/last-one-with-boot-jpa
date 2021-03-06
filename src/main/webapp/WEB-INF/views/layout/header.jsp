<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
<!DOCTYPE html>
<html>
<head>
<title>Your Busan!</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" href="/css/story.css">
	<link rel="stylesheet" href="/css/popular.css">
	<link rel="stylesheet" href="/css/profile.css">
	<link rel="stylesheet" href="/css/upload.css">
	<link rel="stylesheet" href="/css/update.css">
	<link rel="shortcut icon" href="/images/insta.svg">
		<!-- Fontawesome -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<!-- Fonts -->
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400;500;600;700&display=swap" rel="stylesheet">




<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>


<title></title>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<!-- <a class="navbar-brand" href="/">Home </a> -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<sec:authorize access="isAnonymous()">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="/" id="home">HOME</a>
					<li class="nav-item"><a class="nav-link" href="/login"
						id="login">?????????</a></li>
					<li class="nav-item"><a class="nav-link" href="/join"
						id="join">?????? ??????</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						id="popular1">?????? ?????????</a></li>
					<li class="nav-item"><a class="nav-link" href="#"
						id="popukar2">?????? ??????</a></li>
					<li class="nav-item"><a class="nav-link" href="/blog/blogMain"
						id="travel">?????? ?????????</a></li>
					<li class="nav-item"><a class="nav-link" href="/etc/info"
						id="corona">With Corona</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/board/boardMain" id="notice">????????????</a></li>
				</ul>
			</sec:authorize>
			<c:choose>
				<c:when test="${principal.user.role == 'ROLE_ADMIN'}">
					<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link"
								href="/admin/userlist" id="userManager">?????? ??????</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/blog/blogMain" id="travel">?????? ????????? ??????</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/board/boardMain" id="notice">???????????? ??????</a></li>
							<li class="nav-item"><a class="nav-link" href="/logout">????????????(<sec:authentication
										property="principal.user.username" />)
							</a></li>
						</ul>
					</sec:authorize>
				</c:when>
				<c:otherwise>
					<sec:authorize access="isAuthenticated()">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link"
								href="/user/${principal.user.id}/update" id="update">?????? ??????
									??????</a></li>
							<li class="nav-item"><a class="nav-link" href="#"
								id="popular1">?????? ?????????</a></li>
							<li class="nav-item"><a class="nav-link" href="#"
								id="popukar2">?????? ??????</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/blog/blogMain" id="travel">?????? ?????????</a></li>
							<li class="nav-item"><a class="nav-link" href="/etc/info"
								id="corona">With Corona</a></li>
							<li class="nav-item"><a class="nav-link"
								href="/board/boardMain" id="notice">????????????</a></li>
							<li class="nav-item"><a class="nav-link" href="/logout">????????????(<sec:authentication
										property="principal.user.username" />)
							</a></li>
						</ul>
					</sec:authorize>
				</c:otherwise>
			</c:choose>



		</div>
	</nav>
	<br>

	<script>
		console.log(window.location.pathname)

		$("#login").removeClass("active")
		$("#join").removeClass("active")
		$("#popular1").removeClass("active")
		$("#popukar2").removeClass("active")
		$("#travel").removeClass("active")
		$("#corona").removeClass("active")
		$("#notice").removeClass("active")
		$("#update").removeClass("active")
		$("#travel").removeClass("active")
		$("#userManager").removeClass("active")
		$("#home").removeClass("active")

		switch (window.location.pathname) {
		case "/login":
			$("#login").addClass("active")
			break;
		case "/join":
			$("#join").addClass("active")
			break;
		case "/":
			$("#home").addClass("active")
			break;
		case "/etc/info":
			$("#corona").addClass("active")
			break;
		case "/board/boardMain":
			$("#notice").addClass("active")
			break;
		case "/blog/blogMain":
			$("#travel").addClass("active")
			break;
		case "/user/${principal.user.id}/update":
			$("#update").addClass("active")
			break;
		case "/admin/userlist":
			$("#userManager").addClass("active")
			break;
		}
	</script>

</body>
</html>