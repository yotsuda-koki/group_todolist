<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>個人用TOP</title>
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
            <div class="card-header bg-primary text-white text-center fw-bold">
                個人用メニュー
            </div>

            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <a href="UserTodo" class="list-group-item list-group-item-action">TODOリストへ</a>
                    <a href="UserMemo" class="list-group-item list-group-item-action">メモリストへ</a>
                </ul>
            </div>

            <div class="card-footer text-center">
                <a class="btn btn-secondary" href="Top" role="button">TOPへ</a>
            </div>
        </div>
    </div>

</body>
</html>
