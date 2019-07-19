<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>出退勤登録</title>

<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<meta charset="UTF-8">

<style>
.message {
	line-height: 30px;
	text-align: center;
}

.center-block {
	line-height: 30px;
	text-align: center;
}

#break {
	position: relative;
	left: 3%;
}

#pulldown_div {
	margin-left: 42%;
	margin-top: 3%;
}

.date {
	font-size: 150%;
	position: relative;
	top: 20px;
	text-align: center;
}

#RealtimeClockArea2 {
	font-size: 500%;
	top: 900px;
	left: 100px;
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
		<h1 class="text-white">出退勤</h1>
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="myPage.jsp">マイページ</a></li>
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="syuttaikin.jsp">戻る</a></li>

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

	<!-- 	<div style=" margin: auto;" -->
	<!-- 		class="page-header bg-info border-bottom border-dark"> -->
	<!-- 		<h1 style="margin-top: 0px;" class="text-white">出退勤</h1> -->
	<!-- 	</div> -->

	<div style="padding-top: 90px;" class="date">
		<script>
			var hiduke = new Date();
			var year = hiduke.getFullYear();
			var month = hiduke.getMonth() + 1;
			var week = hiduke.getDay();
			var day = hiduke.getDate();
			document.write(year + "/" + month + "/" + day);
		</script>
	</div>

	<p id="RealtimeClockArea2"></p>

	<script>
		function set2fig(num) {
			// 桁数が1桁だったら先頭に0を加えて2桁に調整する
			var ret;
			if (num < 10) {
				ret = "0" + num;
			} else {
				ret = num;
			}
			return ret;
		}
		function showClock2() {
			var nowTime = new Date();
			var nowHour = set2fig(nowTime.getHours());
			var nowMin = set2fig(nowTime.getMinutes());
			var nowSec = set2fig(nowTime.getSeconds());
			var msg = nowHour + ":" + nowMin + ":" + nowSec;
			document.getElementById("RealtimeClockArea2").innerHTML = msg;
		}
		setInterval('showClock2()', 1000);
	</script>

	<c:if test="${not empty message}">
		<p class="message">${message}</p>
	</c:if>


	<form action="SyukkinServlet" method="post">
		<div class="center-block">
			<!-- 			<button type="button" class="btn btn-primary">出勤</button> -->
			<!-- 			<button type="button" class="btn btn-success">退勤</button> -->
			<INPUT class="btn btn-primary" type="submit" name="syukkin"
				value="出勤"> <INPUT class="btn btn-primary" type="submit"
				name="syukkin" value="退勤">
		</div>



		<div id="pulldown_div">

			<select name="kyuukei">
				<option value="0">選択してください</option>
				<option value="15">15分</option>
				<option value="30">30分</option>
				<option value="45">45分</option>
				<option value="60">60分</option>
				<option value="75">75分</option>
				<option value="90">90分</option>
				<option value="105">105分</option>
				<option value="120">120分</option>
			</select>
			<!-- 			<button id="break" type="button" class="btn btn-info">休憩</button> -->
			<INPUT id="break" class="btn btn-primary" type="submit"
				name="syukkin" value="休憩">
		</div>
	</form>

	<!-- 	<button class="btn btn-primary" -->
	<!-- 		onclick="location.href='myPage.jsp'; return false;" -->
	<!-- 		class="btn btn-primary" -->
	<!-- 		style="font-weight: bold; position: absolute; text-align: left; left: 0%; bottom: 0%;">マイページ</button> -->

	<script>
		function clock() {
			var nowTime = new Date();
			var nowHour = nowTime.getHours();
			var nowMin = nowTime.getMinutes();
			var nowSec = nowTime.getSeconds();
			var msg = nowHour + ":" + nowMin + ":" + nowSec;
			document.getElementById("RealtimeClockArea").innerHTML = msg;
		}
		setInterval('clock()', 1000);
	</script>

</body>
</html>