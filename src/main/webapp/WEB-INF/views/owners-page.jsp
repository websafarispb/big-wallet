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

<title>Groups</title>
</head>
<body>
	<h1>Owners</h1>
	<hr>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
		crossorigin="anonymous"></script>

	Welcome to "Owners" page.
	<div id="container">
		<div class="alert alert-danger" role="alert"
			th:if="${message != null}">
			<span th:text="${message}"></span> <br> <a th:href="@{/}"
				class="btn btn-info btn-sm mb-3">Back to Menu</a>
		</div>
		<div th:if="${not #lists.isEmpty(owners)}">
			<a th:href="@{/}" class="btn btn-info btn-sm mb-3">Back to
				Menu</a>
			<a th:href="@{/owners/add}" class="btn btn-info btn-sm mb-3">New owner</a>
			<table class="table table-striped">
				<thead class="thead-dark">
					<tr>
						<th>Id</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Action</th>
					</tr>
				</thead>
				<tr th:each="owner: ${owners}">
					<td th:text="${owner.id}" />
					<td th:text="${owner.firstName}" />
					<td th:text="${owner.lastName}" />
					<td><a th:href="@{/owners/{ownerId}/(ownerId=${owner.id})}"
						class="btn btn-info">Show</a>
					<a th:href="@{/owners/update/{ownerId}/(ownerId=${owner.id})}"
						class="btn btn-warning">Update</a>
					<a th:href="@{/owners/delete/{ownerId}/(ownerId=${owner.id})}"
						class="btn btn-danger" onclick="if (!(confirm('Are you sure you want to delete this owner?'))) return false">Delete</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>