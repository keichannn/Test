<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出退勤状況</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<style>
a:hover {
	background-color: rgba(0,0,255,0.3);
}
</style>

</head>
<body>
	<!-- 	<div class="page-header bg-info border-bottom border-dark"> -->
	<!-- 		<h1 class="text-white">出退勤状況</h1> -->
	<!-- 	</div> -->

	<div style="position: fixed; width: 100%; height: 80px; z-index: 2"
		class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav4" aria-controls="navbarNav4"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<h1 class="text-white">出退勤状況</h1>
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="myPage.jsp">マイページ</a></li>

				<li class="nav-item active">
				<a style="font-size: 20px; font-weight: bold" class="nav-link"
					onclick="history.back(); return false;">戻る</a></li>
			</ul>
		</div>
	</div>

	<!-- 	<div style="margin-left:300px;"> -->
	<!-- 		<h3 style= "margin-top: 80px;"> -->
	<!-- 			<span>田中</span> さん -->
	<!-- 		</h3> -->
	<!-- 		<br> -->
	<!-- 	</div> -->

	<div style="margin: auto; padding-top: 70px; z-index: 1;"
		class="col-md-7">

		<c:if test="${not empty month }">
			<div
				style="margin-top: 40px; text-align: center; font-size: 40px; font-weight: bold;">${month }月</div>
		</c:if>

		<h3 style="margin-top: 20px; margin-bottom: 30px;">

			<span style="color: darkcyan; font-weight: bold;">${user}</span> さん
		</h3>

		<form style="text-align: left;" action="SyuttaikinServlet">

		<select name="year">
				<option value="${year }">年</option>
				<option value="2019">2019</option>
				<option value="2018">2018</option>

			</select>


			<select name="month">
				<option value="${month }">月</option>
				<option value="1">1月</option>
				<option value="2">2月</option>
				<option value="3">3月</option>
				<option value="4">4月</option>
				<option value="5">5月</option>
				<option value="6">6月</option>
				<option value="7">7月</option>
				<option value="8">8月</option>
				<option value="9">9月</option>
				<option value="10">10月</option>
				<option value="11">11月</option>
				<option value="12">12月</option>
			</select>

			<button class="btn btn-primary" type="submit">閲覧する</button>
		</form>


		<table class="table table-striped">

			<tr style="background-color: #33CCFF">
				<th>日</th>
				<th>曜日</th>
				<th>出勤</th>
				<th>退勤</th>
				<th>休憩</th>
			</tr>

			<c:forEach var="atd" items="${attendance}">
				<tr>

					<td>${atd.day }</td>
					<td>${atd.week }</td>
					<td>${atd.going_work }</td>
					<td>${atd.leaving_work }</td>
					<td>${atd.break_start }</td>
				</tr>
			</c:forEach>

		</table>
	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>