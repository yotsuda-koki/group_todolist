<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループの編集</title>
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
            <div class="card-header bg-primary text-white fw-bold text-center">
                グループの編集
            </div>

            <div class="card-body">
                <!-- エラーメッセージの表示 -->
                <c:if test="${errorMessage != null}">
                    <div class="alert alert-danger" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>

                <form action="GroupUpdate" method="post">
                    <div class="mb-3">
                        <label for="groupName" class="form-label">グループ名</label>
                        <input type="text" class="form-control" id="groupName" name="groupName" 
                            value="${group.groupName}" required maxlength="50" placeholder="グループ名を入力">
                    </div>
                    <div class="mb-3">
                        <label for="groupInfo" class="form-label">グループ情報</label>
                        <textarea class="form-control" id="groupInfo" rows="3" name="groupInfo"
                            maxlength="200" placeholder="グループの詳細を入力">${group.groupInfo}</textarea>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">編集を保存</button>
                </form>

                <!-- 戻るボタン -->
                <div class="d-flex justify-content-center mt-3">
                    <a class="btn btn-secondary" href="Top" role="button">TOPへ</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
