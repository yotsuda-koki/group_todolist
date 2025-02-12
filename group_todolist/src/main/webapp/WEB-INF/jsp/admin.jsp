<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>グループメンバー一覧</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        margin-top: 30px;
    }
    .table th, .table td {
        vertical-align: middle;
    }
    .btn {
        min-width: 120px;
    }
</style>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/action/navbar.jsp"%>

	<div class="container">
		<div class="row">
			<!-- グループメンバーリスト -->
			<div class="col-md-6 mb-4">
				<div class="card shadow">
					<div class="card-header bg-primary text-white fw-bold">
						所属グループ: <c:out value="${group.groupName}"></c:out>
					</div>
					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col">ユーザー名</th>
									<th scope="col">操作</th>
									<th scope="col">管理</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="users" items="${users}">
									<tr>
										<td>${users.userName}</td>
										<td>
											<c:choose>
												<c:when test="${user.id == users.id}">
													<button class="btn btn-outline-danger btn-sm" disabled>グループから削除</button>
												</c:when>
												<c:otherwise>
													<form action="Admin" method="post" class="d-inline">
														<input type="hidden" name="id" value="${users.id}">
														<input type="hidden" name="isDeleted" value="1">
														<button class="btn btn-outline-danger btn-sm">グループから削除</button>
													</form>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
											<c:choose>
												<c:when test="${user.id == users.id}">
													<button class="btn btn-outline-success btn-sm" disabled>権限を譲渡</button>
												</c:when>
												<c:otherwise>
													<form action="Admin" method="post" class="d-inline">
														<input type="hidden" name="id" value="${users.id}">
														<input type="hidden" name="admin" value="1">
														<button class="btn btn-outline-success btn-sm">権限を譲渡</button>
													</form>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- 操作ボタン -->
						<div class="d-flex justify-content-start">
							<form action="GroupUpdate" method="get" class="me-2">
								<input type="hidden" name="id" value="${group.id}">
								<button class="btn btn-success btn-sm">グループ編集</button>
							</form>
							<form action="GroupTop" method="get">
								<input type="hidden" name="id" value="${group.id}">
								<button class="btn btn-primary btn-sm">戻る</button>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- 加入申請リスト -->
			<div class="col-md-6 mb-4">
				<div class="card shadow">
					<div class="card-header bg-danger text-white fw-bold">
						${groupRequest.size()}件の加入申請があります
					</div>
					<div class="card-body">
						<table class="table table-striped table-hover">
							<thead class="table-dark">
								<tr>
									<th scope="col">ユーザー名</th>
									<th scope="col">アカウントID</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="request" items="${groupRequest}">
									<tr>
										<td>${request.userName}</td>
										<td>${request.accountId}</td>
										<td>
											<div class="d-flex">
												<form action="Admin" method="post" class="me-2">
													<input type="hidden" name="id" value="${request.id}">
													<input type="hidden" name="userId" value="${request.userId}">
													<input type="hidden" name="permission" value="1">
													<button class="btn btn-outline-primary btn-sm" type="submit">承認</button>
												</form>
												<form action="Admin" method="post">
													<input type="hidden" name="id" value="${request.id}">
													<input type="hidden" name="userId" value="${request.userId}">
													<input type="hidden" name="permission" value="2">
													<button class="btn btn-outline-danger btn-sm" type="submit">拒否</button>
												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
