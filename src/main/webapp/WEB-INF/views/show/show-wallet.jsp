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

<title>Wallet</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

	<div id="container">
		<div class="card">
		<hr>
		<p class="h4 mb-4">Wallet</p>
		<a th:href="@{/wallets/}" class="btn btn-info btn-sm mb-3">Back to
					wallets list</a> 
	
		<form action="#" th:object="${wallet}" >
			<table id="selectedColumn" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
				<tr>
					<td>Id</td>
					<td  th:text="*{id}"></td>
				</tr>
				<tr>
					<td>First name</td>
					<td th:text="*{currencyId}"></td>
				</tr>
				<tr>
					<td>Date of create</td>				
					<td  th:text="*{createDate}"></td>
				</tr>
				<tr>
					<td>Time of create</td>
					<td  th:text="*{createTime}"></td>
				</tr>
				<tr >
					<td>Owner</td>
					<td th:text="${owner == null ? '' : owner.firstName + ' ' +  owner.lastName}"></td>
				</tr>
					<tr>
						<td>Expenses</td>
						<td>
							<ul>
								<li th:each="expense: *{expenses}">
									<label th:text="${expense.id}" />
									<label th:text="${expense.price}" />
									<label th:text="${expense.groupId}" />
									<label th:text="${expense.date}" />
									<label th:text="${expense.time}" />
								</li>
							</ul>
						</td>
					</tr>
				</table>					
		</form>
		<hr>
	</div>
	</div>

</body>
</html>