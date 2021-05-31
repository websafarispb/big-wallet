<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

	<title>Update Expense</title>
</head>

<body>

	<div class="container">
	
		<h3>Update Expense</h3>
		<hr>
		
		<p class="h4 mb-4">Add Expense!</p>
	
		<form action="#" th:action="@{/expenses/save}"
						 th:object="${expense}" method="POST">
		
			<input type="hidden" th:field="*{id}" />
					
			<input type="text" th:field="*{price}"
					class="form-control mb-4 col-4" placeholder="Price">

			<input type="date" th:field="*{date}" th:value="*{date}"
				class="form-control mb-4 col-4" placeholder="Date DD.MM.YYYY">
					
			<input type="time" th:field="*{time}"
					class="form-control mb-4 col-4" placeholder="Time">
					
			<label>Select group of goods</label>
			<select
				th:name="groupId">
				<option value="">--</option>
				<option th:each="group : ${groups}" th:value="${group.id}"
					th:utext="${group.name}" />
			</select>
			<br>
			
			<label>Select wallet</label>
			<select
				th:name="walletId">
				<option value="">--</option>
				<option th:each="wallet : ${wallets}" th:value="${wallet.id}"
					th:utext="${wallet.id}" />
			</select>
				<br>
				<br>
			<button type="submit" class="btn btn-info col-2">Update</button>
						
		</form>
	
		<hr>
		<a th:href="@{/expenses}">Back to expenses List</a>
		
	</div>
</body>

</html>