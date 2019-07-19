<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Vue Bootstrap Calendar Example</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/calendar.css" />

</head>

<body>

<div style="position:fixed; width:100%; height:80px; z-index: 2 " class="navbar navbar-expand-sm navbar-dark bg-info mb-3">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav4" aria-controls="navbarNav4" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
       <h1 class="text-white">有給申請</h1>
        <div style="position:absolute;right:0;" class="collapse navbar-collapse justify-content-end">
            <ul  class="navbar-nav">
                <li class="nav-item active">
                    <a style=" font-size:20px ;font-weight:bold; ;" class="nav-link" href="myPage.jsp">マイページ</a>
                </li>
            </ul>
        </div>
    </div>

<!-- 	<div class="page-header bg-info border-bottom border-dark"> -->
<!-- 		<h1 class="text-white">有給申請</h1> -->
<!-- 	</div> -->
<!-- 	<br> -->


	<div style="padding-top:90px;" class="calendar">
		<form action=YukyuSinseiServlet>
			<table class="calendar-table">
				<thead class="calendar-head">
					<tr>
						<th class="calendar-title" colspan="7"><span class="prev">&lt;&lt;</span>
							<span class="calendar-year"></span> 年 <span
							class="calendar-month"></span> 月 <span class="next">&gt;&gt;</span>
							<h4 style="text-align: right;">
								有給 残り <span style="font-size: 20px;" class="text-info">${yukyu}</span>
								日
							</h4></th>
					</tr>
					<tr class="calendar-week">
						<th>日</th>
						<th>月</th>
						<th>火</th>
						<th>水</th>
						<th>木</th>
						<th>金</th>
						<th>土</th>
					</tr>
				</thead>

				<tbody class="calendar-body"></tbody>
			</table>
		</form>
	</div>

	<script src="js/calendar.js"></script>

	<script>
		new $$CALENDAR({});
	</script>

<!-- 	<div -->
<!-- 		style="position: relative; text-align: left; left: 0%; bottom: 0%;"> -->
<!-- 		<a href="myPage.jsp" class="btn btn-primary active" role="button" -->
<!-- 			aria-pressed="true">マイページ</a> -->

<!-- 	</div> -->

</body>
</html>