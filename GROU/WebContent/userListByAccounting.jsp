<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザ一覧画面</title>
<link rel="stylesheet" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

<style>
a:hover {
	background-color: rgba(0,0,255,0.3);
}
</style>

<div style="position: fixed; width: 100%; height: 80px; z-index: 2"
		class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav4" aria-controls="navbarNav4"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<h1 class="text-white">ユーザ一覧</h1>
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="myPage.jsp">マイページ</a></li>
				<li class="nav-item active">
				<a style="font-size: 20px; font-weight: bold" class="nav-link"
					href="kyuyo.jsp">戻る</a></li>

			</ul>
		</div>
	</div>

<div style="width: 80%; text-align: center; position: relative; left: 10%; top: 100px;">

<c:if test="${not empty year}"><div style="margin:10px 0;"><span style="font-weight: bold; font-size: 120%;">${year}/${month}</span></div></c:if>

<form action="userList" method="post">

	<input type="hidden" name="userList" value="accounting">
	<input type="hidden" name="hiddenYear" value="${year}">
	<input type="hidden" name="hiddenMonth" value="${month}">
	<input type="hidden" name="push" value="${push}">

    <div style="margin-bottom:3%;">

      <label style="font-weight: bold;">年：</label>

      <select name="year">
      	  <option>年</option>
          <option value="2018">2018</option>
          <option value="2019">2019</option>
      </select>

      <label style="font-weight: bold; margin-left: 1%;">月：</label>
      <select name="month">
      	  <option>月　</option>
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
      </select>

    <input type="submit" class="btn btn-primary" style="font-weight: bold; margin-left: 1%;" name="selectYearAndMonth" value="決定">

    </div>

	<c:if test="${not empty uError}">
		<div style="color: red; margin-bottom: 2%; font-weight: bold;">${fn:escapeXml(uError)}</div>
	</c:if>

  <table class="table table-bordered" >
    <thead>

      <tr class="active">
        <th class="col-xs-3 col-ms-3 col-md-4 col-lg-1" style="width: 2%; font-weight: bold; text-align: center;" >　</th>
        <th class="col-xs-3 col-ms-3 col-md-4 col-lg-1" style="width: 2%; font-weight: bold; text-align: center;">ユーザ名</th>
        <th class="col-xs-3 col-ms-3 col-md-4 col-lg-1" style="width: 2%; font-weight: bold; text-align: center;">給与計算</th>
        <th class="col-xs-3 col-ms-3 col-md-4 col-lg-1" style="width: 2%; font-weight: bold; text-align: center;">給与計算結果</th>
        <th class="col-xs-3 col-ms-3 col-md-4 col-lg-1" style="width: 2%; font-weight: bold; text-align: center;">出退勤状況確認</th>
      </tr>
    </thead>

    <tbody>
      <c:forEach var="user" items="${sessionInfo.resisterUserList}">
        <tr>
          <td style="text-align: center;"><input type="checkbox" name="checkbox" value="${fn:escapeXml(user.user_id)}"></td>
          <td style="text-align: center;">${user.user_name}</td>
          <td style="text-align: center;"><a href="userListOfSelectLink?salaryCalculation=${fn:escapeXml(user.user_id)}&hiddenYearStr=${year}&hiddenMonthStr=${month}&push=${push}" style="font-weight: bold;">計算する</a></td>
          <td style="text-align: center;"><input type="hidden" name="salaryResult" value="${fn:escapeXml(user.user_id)}">
              <span <c:if test="${fn:escapeXml(user.salaryResult)=='×'}">style="color:blue; font-weight: bold;"</c:if>
                    <c:if test="${fn:escapeXml(user.salaryResult)=='○'}">style="color:red; font-weight: bold;"</c:if>>${fn:escapeXml(user.salaryResult)}</span></td>
          <td style="text-align: center;"><a href="userListOfSelectLink?syuttaikinjoukyou=${fn:escapeXml(user.user_id)}&hiddenYearStr=${year}&hiddenMonthStr=${month}&push=${push}" style="font-weight: bold;">出退勤状況</a></td>
        </tr>
        </c:forEach>
    </tbody>
  </table>

	<c:if test="${not empty salaryError}"><div style="color: red; font-weight: bold; margin-top: 3%;">${fn:escapeXml(salaryError)}</div></c:if>
	<c:if test="${not empty salaryErrorLater}"><div style="color: red; font-weight: bold; margin-top: 3%;">${fn:escapeXml(salaryErrorLater)}</div></c:if>
	<c:if test="${not empty salaryComplete}"><div style="color: red; font-weight: bold; margin-top: 3%;">${fn:escapeXml(salaryComplete)}</div></c:if>

  <input type="submit" class="btn btn-primary" style="font-weight: bold; margin-top: 3%;" name="selectSalaryCalculation" value="計算">

 </form>

</div>

</body>
</html>