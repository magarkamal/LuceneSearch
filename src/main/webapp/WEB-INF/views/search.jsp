<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto">
<style>
* {
	margin: 0;
	padding: 0;
}

body {
	background: #0069cc;
	font-family: Roboto;
}

.wrapper {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.container {
	position: relative;
	padding: 20px 50px;
}

.input {
	width: 400px;
	border: 3px solid #fff;
	background: transparent;
	padding: 15px 30px;
	border-radius: 50px;
	outline: none;
	font-size: 18px;
	color: #fff;
}

::-webkit-input-placeholder { /* Chrome/Opera/Safari */
	color: #fff;
	font-family: Roboto;
	text-transform: uppercase;
}

::-moz-placeholder { /* Firefox 19+ */
	color: #fff;
}

:-ms-input-placeholder { /* IE 10+ */
	color: #fff;
}

.close-btn {
	position: absolute;
	top: 21px;
	right: 50px;
	cursor: pointer;
	color: #0069cc;
	background: #fff;
	border: 0px;
	width: 120px;
	height: 55px;
	border-radius: 50px;
	outline: none;
	text-transform: uppercase;
	font-weight: bold;
}
</style>
<title>Search</title>
</head>
<body>


	<div class="wrapper">
		<div class="container">
			<form class="example" action="searchdata" method="get">
				<input type="text" class="input" name="search"
					placeholder="What are you looking for?" /> <input type="submit"
					value="search" class="close-btn" />
			</form>
		</div>
	</div>

</body>
</html>