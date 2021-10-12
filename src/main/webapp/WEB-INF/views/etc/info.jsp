<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!LocalDate now = LocalDate.now();
	int month = now.getMonthValue();
	int day = now.getDayOfMonth();%>
	<div class="container">
		<h2>부산 코로나 현황</h2>
		<br>
		<ul class="list-group">
			<li class="list-group-item">부산시 누적 확진자 수 (<%=month%>월 <%=day%>일
				집계 기준)
			</li>
			<li class="list-group-item">확진자 수
				<p id="confirmed">
			</li>
			<li class="list-group-item">격리해제 수
				<p id="released">
			</li>
			<li class="list-group-item">사망 수
				<p id="death">
			</li>
		</ul>
		<br>
		<ul class="list-group">
			<li class="list-group-item">부산시 접종자 수 (<%=month%>월 <%=day%>일 집계
				기준)
			</li>
			<li class="list-group-item">1차 접종
				<div>
					어제 <span id="1st"></span>명
				</div>
				<div>
					누적 <span id="1st2"></span>명
				</div>
			</li>
			<li class="list-group-item">2차 접종
				<div>
					어제 <span id="2nd"></span>명
				</div>
				<div>
					누적 <span id="2nd2"></span>명
				</div>
			</li>
		</ul>
		<br>
		<ul class="list-group">
			<li class="list-group-item">부산시 날씨 현황 (<%=month%>월 <%=day%>일 집계
				기준)
			</li>
			<li class="list-group-item"><%=month%>월 <%=day%>일 예상 날씨
				<div>
					날씨: <span id="1stWeather"></span>
				</div>
				<div>
					온도: <span id="1stTmp"></span>
				</div>
				<div>
					습도: <span id="1stHmd"></span>
				</div>
				<div>
					바람: <span id="1stWind"></span>
				</div></li>
		</ul>
		<br>

		<ul class="list-group">
			<li class="list-group-item">부산시 날씨 현황 (<%=month%>월 <%=day + 1%>일
				집계 기준)
			</li>
			<li class="list-group-item"><%=month%>월 <%=day + 1%>일 예상 날씨
				<div>
					날씨: <span id="2stWeather"></span>
				</div>
				<div>
					온도: <span id="2stTmp"></span>
				</div>
				<div>
					습도: <span id="2stHmd"></span>
				</div>
				<div>
					바람: <span id="2stWind"></span>
				</div></li>
		</ul>

	</div>
	<!-- <div id="abc">

</div> -->

	<script>
		//$("#btn_covidInfo").click(function(){
		$(function() { //$(document).ready(function(){}) 방식의 short form
			$.ajax({
				type : "GET",
				url : "/etc/coronainfo",
				success : function(resp) {
					let result = JSON.parse(resp);
					//console.log(result.response.body.items.item);
					let dataArray = result.response.body.items.item; //js 로 배열 컨트롤

					for (i = 0; i < dataArray.length; i++) {
						//console.log(dataArray[i].gubun);
						if (dataArray[i].gubun == '부산') {
							console.log(dataArray[i])
							$("#confirmed").html(dataArray[i].defCnt)
							$("#released").html(dataArray[i].isolClearCnt)
							$("#death").html(dataArray[i].deathCnt)
							return;
						}
					}
				} //success
			})//ajax
		}) //btnDelete

		$(function() { //$(document).ready(function(){}) 방식의 short form
			$.ajax({
				type : "GET",
				url : "/etc/vcinfo",
				success : function(resp) {
					let result = JSON.parse(resp);
					console.log(result.response.body.items.item);
					let dataArray1 = result.response.body.items.item; //js 로 배열 컨트롤
					for (i = 0; i < dataArray1.length; i++) {
						//console.log(dataArray[i].gubun);
						if (dataArray1[i].sidoNm == '부산광역시') {
							console.log(dataArray1[i])
							$("#1st").html(dataArray1[i].firstCnt)
							$("#1st2").html(dataArray1[i].firstTot)
							$("#2nd").html(dataArray1[i].secondCnt)
							$("#2nd2").html(dataArray1[i].secondTot)
							return;
						}
					}
				} //success
			})//ajax
		}) //btnDelete

		var apiURI = "https://api.openweathermap.org/data/2.5/forecast?q=Busan&appid=51a9596817c1a197fb2e23f7a88ec9cd";
		$(function() {
			$.ajax({
				type : "GET",
				url : apiURI,
				success : function(resp) {
					let result = resp.list;
					for (i = 0; i < result.length; i++) {
						if (i % 8 == 3) {
							console.log(result[i]);
							$("#1stWeather").html(
									result[i].weather[0].main + "("
											+ result[i].weather[0].description
											+ ")")
							$("#1stTmp").html(
									Math.ceil(result[i].main.temp - 273.15)
											+ "˚")
							$("#1stHmd").html(result[i].main.humidity + "%")
							$("#1stWind").html(result[i].wind.speed + "(m/s)")
						}
					}
				}
			})
		})
	</script>
</body>
</html>

