<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループの作成</title>
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
            <div class="card-body">
                <h2 class="text-center mb-4">グループの作成</h2>

                <!-- エラーメッセージの表示 -->
                <c:if test="${errorMessage != null}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>

                <form action="GroupCreate" method="post">
                    <!-- グループ名 -->
                    <div class="mb-3">
                        <label for="groupName" class="form-label">グループ名</label>
                        <input type="text" class="form-control" id="groupName" name="groupName" 
                            required maxlength="50" placeholder="グループ名を入力してください">
                    </div>

                    <!-- グループ情報 -->
                    <div class="mb-3">
                        <label for="groupInfo" class="form-label">グループ情報</label>
                        <textarea class="form-control" id="groupInfo" rows="3" name="groupInfo"
                            maxlength="200" placeholder="グループの詳細情報を入力してください"></textarea>
                    </div>

                    <!-- ボタン -->
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-primary">作成</button>
                        <a class="btn btn-secondary" href="Top" role="button">キャンセル</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
