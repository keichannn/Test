<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>

<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>
.container {
	line-height: 200px;
	text-align: center;
}

.center-block {
	line-height: 500px;
	text-align: center;
}

.logout {
	position: relative;
	top: 60px;
	text-align: center;
}

a:hover {
	background-color: rgba(0, 0, 255, 0.3);
}
</style>

</head>
<body>

	<div style="position: fixed; width: 100%; height: 80px; z-index: 2"
		class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav4" aria-controls="navbarNav4"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<h1 class="text-white">マイページ</h1>
		<div style="position: absolute; right: 0;"
			class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold;" class="nav-link"
					href="logout">ログアウト</a></li>
			</ul>
		</div>
	</div>


	<!-- Bootstrap Javascript(jQuery含む) -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>


	<!-- 		<h1 class="text-white">マイページ</h1> -->
	<!-- 		<div class="collapse navbar-collapse justify-content-end"></div> -->
	<!-- 	</div> -->

	<div style="padding-top: 90px;">
		${userinfo.user_name} <a class="name">さん</a>
	</div>


	<div class="center-block">
		<a href="syuttaikin.jsp" class="btn btn-primary btn-lg">出退勤</a> <a
			href="CalendarServlet" class="btn btn-success btn-lg">有給</a> <a
			href="kyuyo.jsp" class="btn btn-info btn-lg">給与</a>

		<c:if test="${userinfo.role_id == 1}">
			<a href="userEdit.jsp" class="btn btn-warning btn-lg">編集</a>
		</c:if>
	</div>

	<!-- 	<form action="logout" method="post"> -->
	<!-- 		<button class="btn btn-primary btn-lg" -->
	<!-- 			style="position: absolute; text-align: left; left: 0%; bottom: 0%;">ログアウト</button> -->
	<!-- 	</form> -->
</body>
</html>