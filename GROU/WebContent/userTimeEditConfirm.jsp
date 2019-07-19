<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>時刻編集確認画面</title>
</head>
<body>

<div style="margin:auto;" class="page-header bg-info border-bottom border-dark">
<h1 style="margin-top:0px;" class="text-white">時刻編集確認</h1>
</div>

<div style="text-align: center; position: relative; top: 150px;">
  <h2 style="margin-bottom: 6%;">これでよろしいですか？</h2>
  <form>

    <label style="font-weight: bold;">月：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>

    <label style="font-weight: bold; margin-left: 2%;">日：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>

    <label style="font-weight: bold; margin-left: 2%;">休憩(1回目)：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>

    <label style="font-weight: bold; margin-left: 2%;">休憩(2回目)：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>


    <label style="font-weight: bold;">　出勤：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>

    <label style="font-weight: bold;">　退勤：</label>
    <input type="text" value="" style="width: 8%; text-align: center;" readonly>

  </form>

  <div>
        <button onclick="location.href='infomationChange.jsp'; return false;" class="btn btn-primary" style="margin-top: 5%; font-weight: bold;">更新</button>
  </div>

</div>
  <button class="btn btn-primary" onclick="location.href='mypage?selectPage=${authority.manager}'; return false;" style="font-weight: bold; font-weight: bold; position: absolute; text-align: left; left: 0%; bottom: 0%;">マイページ</button>
  <button class="btn btn-primary" onclick="location.href='window.history.back()'; return false;" class="btn btn-primary" style="font-weight: bold; position: absolute; text-align: left; left: 8%; bottom: 0%;">戻る</button>
</body>
</html>