<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>トップページ</title>
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

<style>
body {
  overflow: hidden;
  background: rgb(25,35,125);
}

div.drop-container {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  height: 200px;
  width: 200px;
}


div.drop {
  position: absolute;
  top: -25%;
  width: 100%;
  height: 100%;
  border-radius: 100% 5% 100% 100%;
  transform: rotate(-45deg);
  margin: 0px;
  background: deepskyblue;
  animation: drip 4s forwards;
}

h1 {
  color: white;
  position: absolute;
  font-size: 2.5em;
  height: 1em;
  top: 0; left: 0; right: 0; bottom: 0;
  z-index: 2;
  margin: auto;
  text-align: center;
  opacity: 0;
  animation: appear 2s 2.5s forwards;
}

@keyframes appear {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

div.drop-container:before,
div.drop-container:after {
  content: '';
  position: absolute;
  z-index: -1;
  top: 55%;
  right: 50%;
  transform: translate(50%) rotateX(75deg);
  border-radius: 100%;
  opacity: 0;
  width: 75%;
  height: 75%;
  border: 5px solid skyblue;
  animation: dripple 2s ease-out 1s;
}

div.drop-container:after {
  animation: dripple 2s ease-out 1.7s;
}

@keyframes drip {
  45% {
    top: 0;
    border-radius: 100% 5% 100% 100%;
    transform: rotate(-45deg);
  }
  100% {
    top: 0;
    transform: rotate(0deg);
    border-radius: 100%;
  }
}

@keyframes dripple {
  0% {
    width: 150px;
    height: 150px;
  }
  25% {
    opacity: 1;
  }
  100% {
    width: 500px;
    height: 500px;
    top: -20%;
    opacity: 0;
  }
}

 .centering {
 margin: 500px;
 text-align: center;

 }
}
</style>


<div class="centering">
		<input type="button" class="btn btn-primary"
			onClick="location.href='login.jsp'" value="始める">
	</div>

	<h1>勤怠管理システム</h1>
	<div class="drop-container">
		<div class="drop"></div>
	</div>


</body>
</html>