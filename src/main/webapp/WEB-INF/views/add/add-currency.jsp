<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">

<title>Create currency</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

	<div class="container">
		<form th:action="@{/currencies/create}" th:object="${currency}" method="post">
		<h1>Create currency</h1>
			<div class="form-row">
				<div class="col-md-6 form-group">
					<label>Currency Name</label> <input type="text" class="form-control"
						th:field="*{name}">
					<div class="alert alert-danger" role="alert"
						th:if="${#fields.hasErrors('name')}" th:errors="*{name}"></div>
				</div>
			</div>
			<div style="margin-top: 10px" class="form-group">
				<div class="col-sm-6 controls">
					<button type="submit" class="btn btn-success">Create</button>
				</div>
			</div>
		</form>
		<hr>
		<a th:href="@{/currencies}">Back to currencies list</a>
	</div>

</body>
</html>
