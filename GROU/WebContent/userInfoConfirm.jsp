<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ登録確認</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

		<style>
a:hover {
	background-color: rgba(0,0,255,0.3);
}

.error {
	color: red;
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
		<h1 class="text-white">ユーザ登録確認</h1>
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

<!-- 	<div class="page-header bg-info border-bottom border-dark"> -->
<!-- 		<h1 class="text-white">ユーザ登録確認</h1> -->
<!-- 	</div> -->

	<form style="text-align: center; padding-top: 130px;" class="form-horizontal" action="UserInsertConfirmServlet" method="post">
		<c:if test="${not empty errMsg}">
			<p class="error">${fn:escapeXml(errMsg)}</p>
		</c:if><br><br>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputName">名前</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="text" class="form-control col-md-4" id="InputName"
					name="user_name" value="${fn:escapeXml(name)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputAge">生年月日</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="text" class="form-control col-md-4" name="birthday" id="InputAge"
					name="age" value="${fn:escapeXml(birth)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputLoginId">ログインID</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="text" class="form-control col-md-4" id="InputLoginId"
					name="login_id" value="${fn:escapeXml(loginId)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputPass">パスワード</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="password" class="form-control col-md-4" id="InputPass"
					name="password" value="${fn:escapeXml(pass)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputDate">入社日</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="text" class="form-control col-md-4"  id="InputAge"
					name="hiredate" value="${fn:escapeXml(hire)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputBasic">基本給</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="number" class="form-control col-md-4" id="InputBasic"
					name="basic" value="${fn:escapeXml(basic)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputAuthority">権限</label>
			<div style="margin: auto;">
				<input style="margin: auto;" class="form-control col-md-4" id="InputAuthority"
					name="role_name" value="${fn:escapeXml(roleName)}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputPass">パスワード(再入力)</label>
			<div style="margin:auto;">
				<input style="margin:auto;" type="password" class="form-control col-md-4" id="InputPass" name="rePassword"
				pattern="^[0-9A-Za-z]+$">
			</div>
		</div>

				<div class="form-group pull-right">
			<div style="margin:auto;" class="col-sm-offset-2 col-sm-10">
				<button style="margin:auto;" type="submit" class="btn btn-primary">登録</button>
			</div>
		</div>
	</form><br>


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