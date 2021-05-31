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

<title>Update wallet</title>
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
		<form th:action="@{/wallets/create}" th:object="${wallet}" method="post">
		<h1>Update wallet</h1>
			<div class="form-row">
				<div class="col-md-6 form-group">
					<input type="hidden" th:field="*{id}" />
					

			<input type="date" th:field="*{createDate}" th:value="*{createDate}"
				class="form-control mb-4 col-4" placeholder="Date DD.MM.YYYY">
					
			<input type="time" th:field="*{createTime}"
					class="form-control mb-4 col-4" placeholder="Time">
					
			<label>Select currency</label>
			<select
				th:name="currencyId">
				<option value="">--</option>
				<option th:each="currency : ${currencies}" th:value="${currency.id}"
					th:utext="${currency.name}" />
			</select>
					<br>
			<label>Select new owner</label>
				
				<select
				th:name="ownerId">
				<option value="">--</option>
				<option th:each="owner : ${owners}" th:value="${owner.id}"
					th:utext="${owner.lastName}" />
			</select>
				<br>
				
				</div>
			</div>
			<div style="margin-top: 10px" class="form-group">
				<div class="col-sm-6 controls">
					<button type="submit" class="btn btn-success">Update</button>
				</div>
			</div>
		</form>
		<hr>
		<a th:href="@{/wallets}">Back to wallets list</a>
	</div>

</body>
</html>
