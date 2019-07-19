<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>給与計算画面</title>

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
		<h1 class="text-white">給与計算</h1>
		<div class="collapse navbar-collapse justify-content-end">
			<ul class="navbar-nav">
				<li class="nav-item active"><a
					style="font-size: 20px; font-weight: bold" class="nav-link"
					href="myPage.jsp">マイページ</a></li>
				<li class="nav-item active">
				<a style="font-size: 20px; font-weight: bold" class="nav-link"
					href="userListByAccounting.jsp">戻る</a></li>

			</ul>
		</div>
	</div>

<div style="width: 80%; text-align: center; position: relative; left: 10%; top: 130px;">

<h2 style="text-align: center; margin-bottom: 4%;">
  <span style="color: darkcyan; font-weight: bold;">${userInfo.user_name}</span>さんの給与情報
</h2>

  <form action="salaryCalculate" method="post">

      <div>
        <label style="font-weight: bold; margin-bottom:2%;">基本給：</label>
        <input type="text" name="basicSalary"
          value="￥${salaryConfirm.basic}" style="text-align:center;" readonly>
      </div>

      <div>
        <label style="font-weight: bold; margin: 2% 0;">残業代：</label>
        <input type="text" name="overTimeWork"
          value="￥${salaryConfirm.overtime_work}" style="text-align:center;" readonly>
      </div>

      <div>
        <label style="font-weight: bold; margin: 2% 0;">控除額：</label>
        <input type="text" name="deductionAmount"
          value="￥${salaryConfirm.deduction_amount}" style="text-align:center;" readonly>
      </div>

      <div style=" border-top: 3px black solid; width: 50%; margin: 0 auto;">
        <label style="font-weight: bold; margin: 3% 0; margin-top: 4%;">支給額：</label>
         <input type="text" name="paymentAmount"
        value="￥${salaryCalculationResult}" style="color:red; text-align:center; font-weight: bold;" readonly>
      </div>

      <div style="color: red; margin-left: 5%; margin-top: 2%; font-weight: bold;">${complete}</div>

    <a href="userListOfSelectLink?salaryCalculationAction=${salaryConfirm.user_id}&hiddenYearStr=${year}&hiddenMonthStr=${month}" class="btn btn-primary" name="selectPage" style="margin-top: 2%; margin-left: 4%; font-weight: bold;">確定</a>

  </form>

</div>

</body>
</html>