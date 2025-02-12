<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>用途選択</title>
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
                用途選択
            </div>

            <div class="card-body">
                <div class="alert alert-primary text-center" role="alert">
                    <h5>${user.userName} さん、ようこそ！</h5>
                </div>

                <h5 class="text-center mb-3">用途を選択してください</h5>

                <ul class="list-group">
                    <a href="UserTop" class="list-group-item list-group-item-action">個人用TODOリストを利用</a>
                    <a href="GroupSelect" class="list-group-item list-group-item-action">グループの選択</a>
                    <a href="GroupSearch" class="list-group-item list-group-item-action">グループの検索および加入申請</a>
                    <a href="GroupCreate" class="list-group-item list-group-item-action">グループの作成</a>
                </ul>
            </div>
        </div>
    </div>

</body>
</html>
