<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>TODO登録</title>
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
                TODO登録
            </div>

            <div class="card-body">
                <form class="needs-validation" action="TodoRegister" method="post" novalidate>
                    <!-- 登録日 -->
                    <div class="mb-3">
                        <label for="registrationDate" class="form-label">登録日</label>
                        <input type="date" class="form-control-plaintext" name="registrationDate"
                            id="registrationDate" value="${todaysDate}" readonly required>
                        <div class="invalid-feedback">登録日は必ず入力してください。</div>
                    </div>

                    <!-- TODO項目 -->
                    <div class="mb-3">
                        <label for="todoItem" class="form-label">項目</label>
                        <input type="text" class="form-control" name="todoItem" id="todoItem"
                            required placeholder="TODOの内容を入力してください">
                        <div class="invalid-feedback">TODO項目は必ず入力してください。</div>
                    </div>

                    <!-- 期限日 -->
                    <div class="mb-3">
                        <label for="expirationDate" class="form-label">期限日</label>
                        <input type="date" class="form-control" name="expirationDate"
                            id="expirationDate" min="${todaysDate}" required>
                        <div class="invalid-feedback">期限日は必ず入力してください。</div>
                    </div>

                    <!-- ボタン -->
                    <div class="d-flex justify-content-between">
                        <button type="submit" class="btn btn-success">追加</button>

                        <c:choose>
                            <c:when test="${group != null}">
                                <form action="GroupTop" method="get">
                                    <a class="btn btn-primary" href="GroupTop?id=${group.id}">戻る</a>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-secondary" href="UserTodo" role="button">戻る</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // フォームバリデーション
        (function() {
            'use strict';
            var forms = document.querySelectorAll('.needs-validation');
            Array.prototype.slice.call(forms).forEach(function(form) {
                form.addEventListener('submit', function(event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>

</body>
</html>
