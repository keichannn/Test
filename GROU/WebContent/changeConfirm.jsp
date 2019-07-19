<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更確認ページ</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/botstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<div class="page-header bg-info border-bottom border-dark">
		<h1 class="text-white">変更確認</h1>
	</div>

	<c:if test="${not empty duplicate}">
		<p class="error">${duplicate}</p>
	</c:if>

	<form style="text-align: center; margin-top: 150px;" class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputName">名前</label>
			<div style="margin:auto;">
				<input style="margin:auto;" type="text" class="form-control col-md-4" id="InputName"
					value="${sessionInfo.updateUser.user_name}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputAge">生年月日</label>
			<div style="margin: auto;">
				<input style="margin: auto;" type="text" class="form-control col-md-4" id="InputAge"
					value="${sessionInfo.updateUser.birthday}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputLoginId">ログインID</label>
			<div style="margin:auto;" >
				<input style="margin:auto;" type="text" class="form-control col-md-4" id="InputLoginId"
					value="${sessionInfo.updateUser.login_id}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputPass">パスワード</label>
			<div style="margin:auto;">
				<input style="margin:auto;" type="password" class="form-control col-md-4" id="InputPass"
					value="${sessionInfo.updateUser.password}" readonly>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="InputPass">パスワード(再入力)</label>
			<div style="margin:auto;">
				<input style="margin:auto;" type="password" class="form-control col-md-4" id="InputPass" name="rePassword" value="${rePassword}">
			</div>
		</div>

		<div class="form-group">
			<div  class="col-sm-offset-2">
				<button type="submit" class="btn btn-primary center-block" style="margin:auto;">登録</button>
			</div>
		</div>
	</form>


		<div style="position: relative; text-align: left; left: 0%; bottom: 0%;">
	<a href="myPage.jsp" class="btn btn-primary" role="button"
			aria-pressed="true">マイページ</a>

		<a href="userInfoChange.jsp" class="btn btn-primary" role="button"
			aria-pressed="true">戻る</a>
	</div>


		<!-- Optiona JavaScript -->
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