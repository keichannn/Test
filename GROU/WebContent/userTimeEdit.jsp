<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>時刻編集画面</title>

<style>
a:hover {
	background-color: rgba(0,0,255,0.3);
}
</style>
</head>
<body>

<div style="position:fixed; width:100%; height:80px; z-index: 2 " class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav4" aria-controls="navbarNav4" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
       <h1 class="text-white">時刻編集画面</h1>
        <div style="position:absolute;right:0;" class="collapse navbar-collapse justify-content-end">
            <ul  class="navbar-nav">
                <li class="nav-item active">
                    <a style=" font-size:20px ;font-weight:bold; ;" class="nav-link" href="myPage.jsp">マイページ</a>
                </li>
                <li class="nav-item active">
                    <a style=" font-size:20px ;font-weight:bold; ;" class="nav-link" href="userList?selectButton=timeEdit">戻る</a>
                </li>
            </ul>
        </div>
    </div>

	<div style="margin: auto;"
		class="page-header bg-info border-bottom border-dark">
		<h1 style="margin-top: 0px;" class="text-white">時刻編集</h1>
	</div>

	<div style="text-align: center; position: relative; top: 200px;">
		<c:if test="${not empty message}">
			<p class="message">${message}</p>

		</c:if>


		<form action="userTimeEditServlet" method="post">


			<label style="font-weight: bold;">西暦：</label> <select name="year">
				<option value="2019">2019</option>
				<option value="2018">2018</option>
			</select>

			<label style="font-weight: bold;">月：</label> <select name="month">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select> <label style="margin-left: 1%; font-weight: bold;">日：</label> <select
				name="day">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
				<option value="21">21</option>
				<option value="22">22</option>
				<option value="23">23</option>
				<option value="24">24</option>
				<option value="25">25</option>
				<option value="26">26</option>
				<option value="27">27</option>
				<option value="28">28</option>
				<option value="29">29</option>
				<option value="30">30</option>
				<option value="31">31</option>
			</select> <label style="font-weight: bold;"> 休憩(合計)：</label> <select
				name="breakTime">
				<option value="0">0</option>
				<option value="15">15</option>
				<option value="30">30</option>
				<option value="45">45</option>
				<option value="60">60</option>
				<option value="75">75</option>
				<option value="90">90</option>
				<option value="105">105</option>
				<option value="120">120</option>
			</select> <label style="font-weight: bold;"> 出勤：</label> <input type="text"
				onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"
				name="startTime1" maxlength="2" placeholder="09" style="width: 30px"></input><a>
				: </a> <input type="text"
				onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"
				name="startTime2" maxlength="2" placeholder="00" style="width: 30px"></input>
			<label style="font-weight: bold;"> 退勤：</label> <input type="text"
				onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"
				name="endTime1" maxlength="2" placeholder="18" style="width: 30px"></input><a>
				: </a> <input type="text"
				onKeyup="this.value=this.value.replace(/[^0-9]+/,'')"
				name="endTime2" maxlength="2" placeholder="05" style="width: 30px"></input>

			<INPUT style="margin-left: 3%; font-weight: bold;"
				class="btn btn-primary" type="submit" name="send" value="送信">
			<!--     <button onclick="location.href='userTimeEditConfirm.jsp'; return false;" class="btn btn-primary" style="margin-left: 3%; font-weight: bold;">送信</button> -->
		</form>
	</div>
<!-- 	<button class="btn btn-primary" -->
<%-- 		onclick="location.href='mypage?selectPage=${authority.manager}'; return false;" --%>
<!-- 		style="font-weight: bold; font-weight: bold; position: absolute; text-align: left; left: 0%; bottom: 0%;">マイページ</button> -->
<!-- 	<button class="btn btn-primary" -->
<!-- 		onclick="location.href='window.history.back();'; return false;" -->
<!-- 		class="btn btn-primary" -->
<!-- 		style="font-weight: bold; position: absolute; text-align: left; left: 8%; bottom: 0%;">戻る</button> -->
</body>
</html>