<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループの選択</title>
<!-- Bootstrap CSS -->
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
                所属グループ一覧
            </div>

            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <c:forEach var="gl" items="${grouplist}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <span class="fw-bold">${gl.groupName}</span>
                            <form action="GroupTop" method="get">
                                <input type="hidden" name="id" value="${gl.id}">
                                <button type="submit" class="btn btn-outline-primary btn-sm">選択</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>

                <!-- 戻るボタン -->
                <div class="d-flex justify-content-center mt-4">
                    <a class="btn btn-primary" href="Top" role="button">TOPへ</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
