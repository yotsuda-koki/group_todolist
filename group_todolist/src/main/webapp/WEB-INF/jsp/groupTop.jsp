<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループ用TOP</title>
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
                所属グループ: <c:out value="${group.groupName}"/>
            </div>

            <div class="card-body">
                <!-- メッセージ表示 -->
                <c:if test="${message != null}">
                    <div class="alert alert-danger mt-3" role="alert">
                        ${message}
                    </div>
                </c:if>

                <!-- メニューリスト -->
                <ul class="list-group">
                    <a href="GroupTodo" class="list-group-item list-group-item-action">TODOリストへ</a>
                    <a href="GroupMemo" class="list-group-item list-group-item-action">メモリストへ</a>
                    <a href="GroupMember" class="list-group-item list-group-item-action">メンバー一覧</a>
                    <a href="Admin" class="list-group-item list-group-item-action">管理者用</a>
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
