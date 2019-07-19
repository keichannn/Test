<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>給与明細</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

	<style>
a:hover {
	background-color: rgba(0,0,255,0.3);
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
		<h1 class="text-white">給与明細</h1>
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
<!-- 		<h1 class="text-white">給与明細</h1> -->
<!-- 	</div> -->
	<br>



	<form style="padding-top:90px" action="MeisaiServlet">
		<div style="text-align: center;">

			<span style="font-weight: bold; font-size: 30px;">
				${yearMeisai }年<br>
			</span>

			<button class="btn btn-info btn-lg" style="font-weight: bold; font-size: 30px;"
				name="yearDown" value="${year }">&lt;&lt;</button>
			<button class="btn btn-dark btn-lg" name="month" value="1">1月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="2">2月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="3">3月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="4">4月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="5">5月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="6">6月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="7">7月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="8">8月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="9">9月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="10">10月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="11">11月分</button>
			<button class="btn btn-dark btn-lg" name="month" value="12">12月分</button>

			<c:if test="${yearMeisai < 2019}">
					<button class="btn btn-info btn-lg" style="font-weight: bold; font-size: 30px;"
				name="yearUp" value="${year }">&gt;&gt;</button>
			</c:if>

		</div>
	</form>



	<div style="text-align: center; margin-top: 110px; font-size: 130%;">
		<div class="col-md-4 mx-auto bg-info text-white rounded-circle p-3"
			style="width: 350px; height: 400px;">

			<c:if test="${not empty salary}">
				<h2 style="margin-top: 60px;">${payMonth }月分</h2>
			</c:if>
			<c:if test="${not empty error}">
				<h2 style="margin-top: 60px;">${payMonth }月分</h2>
				<div style="text-align:center; margin-top: 80px; font-size: 130%;">${error}</div>
			</c:if>

			<c:if test="${not empty salary}">
				<strong>基本給 : &yen ${salary.basic }</strong>
				<br>
				<strong>残業代: &yen ${salary.overtime_work } </strong>
				<br>
				<strong>控除額 : &yen${salary.deduction_amount }</strong>
				<br>
				<hr size="10" color="#2f4f4f" width="200">
				<strong>支給額 : &yen ${salary.payment_amount }</strong>
			</c:if>
		</div>
	</div>

<!-- 	<div -->
<!-- 		style="position: relative; text-align: left; left: 0%; bottom: 0%;"> -->
<!-- 		<a href="myPage.jsp" class="btn btn-primary active" role="button" -->
<!-- 			aria-pressed="true">マイページ</a> <a href="kyuyo.jsp" -->
<!-- 			class="btn btn-primary active" role="button" aria-pressed="true">戻る</a> -->
<!-- 	</div> -->

	<script>
		var num = $
		{
			year
		};

		function up() {
			num++;
		}
	</script>

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