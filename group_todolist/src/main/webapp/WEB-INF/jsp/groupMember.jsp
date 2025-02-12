<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループメンバー一覧</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 600px;
        margin-top: 50px;
    }
</style>
</head>
<body>

    <%@ include file="/WEB-INF/jsp/action/navbar.jsp"%>

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white fw-bold">
                所属グループ: <c:out value="${group.groupName}"></c:out>
            </div>

            <div class="card-body">
                <h2 class="text-center mb-3">グループメンバー</h2>
                <p class="text-center text-muted">メンバー数: <c:out value="${fn:length(users)}"/></p>

                <ul class="list-group list-group-flush">
                    <c:forEach var="users" items="${users}">
                        <li class="list-group-item list-group-item-action">
                            ${users.userName}
                        </li>
                    </c:forEach>
                </ul>

                <!-- 戻るボタン -->
                <div class="d-flex justify-content-center mt-3">
                    <form action="GroupTop" method="get">
                        <input type="hidden" name="id" value="${group.id}">
                        <button class="btn btn-primary">戻る</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
