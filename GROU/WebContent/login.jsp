<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>ログイン</title>

<style>
.cp_iptxt {
	position: relative;
	width: 40%;
	margin: 100px 10px 10px 450px;
	width: 500px;
}

.cp_iptxt input[type=text] {
	font: 15px/24px sans-serif;
	box-sizing: border-box;
	width: 40%;
	margin: 8px;
	padding: 0.3em;
	transition: 0.3s;
	border: 1px solid #1b2538;
	border-radius: 4px;
	outline: none;


}

.cp_iptxt input[type=text]:focus {
	border-color: #da3c41;
}

.cp_iptxt input[type=text] {
	padding-left: 10px;
}

.cp_iptxt input[type=password] {
	width: 300px;
	font: 15px/24px sans-serif;
	box-sizing: border-box;
	width: 40%;
	margin: 8px;
	padding: 0.3em;
	transition: 0.3s;
	border: 1px solid #1b2538;
	border-radius: 4px;
	outline: none;
}

.cp_iptxt i {
	position: absolute;
	top: 8px;
	left: 0;
	padding: 10px 8px;
	transition: 0.3s;
	color: #aaaaaa;

}

.cp_iptxt input[type=text]:focus+i {
	color: #da3c41;
}

.btn {
	margin: 10px 10px 0px 615px;
}

.message {
	position: relative;
	top: 60px;
	margin: 10px 10px 0px 550px;
	color: red;
}

.top {
	margin: 10px 10px 0px 623px;
}
</style>

</head>
<body>
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

	<div style="margin: auto;"
		class="page-header bg-info border-bottom border-dark">
		<h1 style="margin-top: 0px;" class="text-white">ログイン</h1>
	</div>



	<c:if test="${not empty errMsg}">
		<p class="message">${errMsg}</p>
	</c:if>


	<form action="logInServlet" method="post">

		<div class="cp_iptxt">

			<input type="text" placeholder="ID" name="id">
			<i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
		    <input type="password"  placeholder="Password" name="pass">
		     <i	class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>

		</div>

		<input type="submit" class="btn btn-primary btn-lg" value="ログイン">

	</form>

	<form action="./topPage.jsp" method="post">
		<div class="top">
			<br> <a href="./topPage.jsp">トップページ</a>
		</div>
	</form>
</body>
</html>