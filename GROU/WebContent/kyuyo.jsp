
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給与</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>
a:hover {
	background-color: rgba(0, 0, 255, 0.3);
}
</style>

</head>
<body>

	<div
		style="position: fixed; width: 100%; height: 80px; z-index: 2; margin-top: -150px;"
		class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav4" aria-controls="navbarNav4"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<h1 class="text-white">給与</h1>
		<div style="position: absolute; right: 0;"
			class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold;" class="nav-link"
					href="myPage.jsp">マイページ</a></li>
			</ul>
		</div>
	</div>

	<!-- 	<div class="page-header bg-info border-bottom border-dark"> -->
	<!-- 		<h1 class="text-white">給与</h1> -->
	<!-- 	</div> -->

	<div style="text-align: center; margin-top: 150px; padding-top: 90px;">
		<c:if test="${userinfo.role_id == 2}">

		<a href="KyuyokeisanServlet"
				class="btn btn-primary btn-lg active Top m-5" role="button"
				aria-pressed="true">給与計算</a>
		</c:if>

		<a href="KyuyoServlet" class="btn btn-primary btn-lg active"
			role="button" aria-pressed="true">給与明細</a>
	</div>

	<!-- 	<div style="position: absolute; text-align: left; left: 0%; bottom: 0%;"> -->
	<!-- 	<a href="myPage.jsp" class="btn btn-primary active" role="button" -->
	<!-- 			aria-pressed="true">マイページ</a> -->

	<!-- 	</div> -->

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
</body>
</html>