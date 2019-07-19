<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>有給申請可否</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<style>

a:hover {
	background-color: rgba(0,0,255,0.3);
}

.message {
	position: relative;
	top: 60px;
	text-align: center;
	a: hover{
	background-color: rgba(0, 0, 255, 0.3);
}

</style>

</head>
<body>

	<!-- 	<div class="page-header bg-info border-bottom border-dark"> -->
	<!-- 		<h1 class="text-white">有給申請状況</h1> -->
	<!-- 	</div> -->

	<div
		style="position: fixed; width: 100%; height: 80px; z-index: 2; top: 0;"
		class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav4" aria-controls="navbarNav4"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<h1 class="text-white">有給申請状況</h1>
		<div style="position: absolute; right: 0;"
			class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold;" class="nav-link"
					href="myPage.jsp">マイページ</a></li>

				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="yukyusinsei.jsp">戻る</a></li>
			</ul>
		</div>
	</div>

	<h1 style="padding-top: 90px; font-weight: bold;" class="Left m-5">${paidDay }</h1>


	<h3 style="margin-bottom: 30px;" class="text-center">有給申請者</h3>

	<c:forEach var="puser" items="${paidUser}">
		<div style="text-align: center; width: 500px; margin: auto;">

			<form action="YukyuSinseiServlet" method="POST">
				<div
					style="font-size: 120%; text-align: left; width: 50px; display: inline;"
					class="font-weight-bold">${puser.user_name}</div>
				&nbsp

				<c:if test="${userinfo.role_id == 1}">
					<c:choose>
						<c:when test="${puser.appli_states == 1}">
							<button name="idbutton" value="${puser.user_id},2"
								class="btn btn-info btn-lg active Left m-5" role="button"
								aria-pressed="true">承認</button>
							<button name="idbutton" value="${puser.user_id},3"
								class="btn btn-info btn-lg active" role="button"
								aria-pressed="true">非承認</button>
							<br>
						</c:when>
					</c:choose>
				</c:if>

				<c:if test="${userinfo.role_id == 2 || userinfo.role_id == 3}">
					<c:choose>
						<c:when test="${puser.appli_states == 1}">
							<a style="margin-left: 20px;"
							class="btn btn-info btn-lg active Left m-5 col-md-5"
							role="button" aria-pressed="true">承認待ち</a>

						</c:when>
					</c:choose>
				</c:if>

				<c:choose>
					<c:when test="${puser.appli_states == 2}">
						<a style="margin-left: 20px;"
							class="btn btn-info btn-lg active Left m-5 col-md-5"
							role="button" aria-pressed="true">承認されています</a>
						<br>
					</c:when>

				</c:choose>


			</form>

			<span style="margin-top: -30px;"> 備考 : <span>${puser.remarks}</span>

			</span>

		</div>
	</c:forEach>

	<br>

	<br>
	<c:if test="${not empty errMsg}">
		<p class="message">${errMsg}</p>
	</c:if>
	<br>
	<form style="margin-top: 30px;" action="YukyuSinseiForm" method="POST">
		<div style="text-align: center;">
			<p style="font-size: 120%">備考:</p>
			<textarea name="bikou" rows="10" cols="110" maxlength="100"
				placeholder="100文字以内"></textarea>
		</div>
		<div style="text-align: right; margin-right: 250px; margin-top: 20px;">
			<button class="btn btn-primary">申請する</button>
		</div>
	</form>

	<!-- 	<div style="position: fixd; text-align: left; left: 0; bottom: 0;"> -->

	<!-- 		<a href="myPage.jsp" class="btn btn-primary active" role="button" -->
	<!-- 			aria-pressed="true">マイページ</a> <a href="yukyusinsei.jsp" -->
	<!-- 			class="btn btn-primary active" role="button" aria-pressed="true">戻る</a> -->
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
</html>