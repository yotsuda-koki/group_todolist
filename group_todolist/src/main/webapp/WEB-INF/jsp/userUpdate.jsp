<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ユーザー情報更新</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 400px;
        margin-top: 50px;
    }
    .password-wrapper {
        position: relative;
    }
    .toggle-password {
        position: absolute;
        right: 10px;
        top: 50%;
        transform: translateY(-50%);
        background: none;
        border: none;
        font-size: 0.9rem;
        font-weight: bold;
        color: #007bff;
        cursor: pointer;
        outline: none;
    }
    .toggle-password:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center fw-bold">
                アカウント情報変更
            </div>

            <div class="card-body">
                <!-- エラーメッセージの表示 -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger text-center">${errorMessage}</div>
                </c:if>

                <form action="UserUpdate" method="post" class="needs-validation" novalidate>
                    <!-- アカウントID -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="accountId" id="accountId"
                            value="${user.accountId}" pattern=".{4,8}"
                            title="4文字以上8文字以下で入力してください" required>
                        <label for="accountId">アカウントID</label>
                        <div class="invalid-feedback">4文字以上8文字以下で入力してください。</div>
                    </div>

                    <!-- ユーザー名 -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="userName" id="userName"
                            value="${user.userName}" pattern=".{4,20}"
                            title="4文字以上20文字以下で入力してください" required>
                        <label for="userName">ユーザー名</label>
                        <div class="invalid-feedback">4文字以上20文字以下で入力してください。</div>
                    </div>

                    <!-- メールアドレス -->
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" name="email" id="email"
                            value="${user.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                        <label for="email">メールアドレス</label>
                        <div class="invalid-feedback">正しいメールアドレスを入力してください。</div>
                    </div>
                    
                    <!-- 現在のパスワード (必須) -->
                    <div class="form-floating password-wrapper mb-3">
                        <input type="password" class="form-control" name="currentPassword" id="currentPassword" required>
                        <label for="currentPassword">現在のパスワード (必須)</label>
                        <button type="button" class="toggle-password" onclick="togglePassword('currentPassword', 'toggleButtonCurrent')"
                            id="toggleButtonCurrent">
                            SHOW
                        </button>
                    </div>

                    <!-- 新しいパスワード (任意) -->
                    <div class="form-floating password-wrapper mb-3">
                        <input type="password" class="form-control" name="password" id="password"
                            pattern="^[a-zA-Z0-9!@#$%^&*()_+,.?-]{8,20}$"
                            title="8文字以上20文字以下の半角英数字または記号を入力してください">
                        <label for="password">新しいパスワード (変更する場合のみ入力)</label>
                        <button type="button" class="toggle-password" onclick="togglePassword('password', 'toggleButtonNew')"
                            id="toggleButtonNew">
                            SHOW
                        </button>
                    </div>

                    <input type="hidden" name="userId" value="${user.id}">

                    <!-- ボタン -->
                    <div class="d-grid gap-2">
                        <button class="btn btn-primary" type="submit">送信</button>
                        <a class="btn btn-secondary" href="Top">キャンセル</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // 複数のパスワード入力欄の表示/非表示を切り替え
        function togglePassword(fieldId, buttonId) {
            const passwordField = document.getElementById(fieldId);
            const toggleButton = document.getElementById(buttonId);
            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleButton.textContent = "HIDE";
            } else {
                passwordField.type = "password";
                toggleButton.textContent = "SHOW";
            }
        }

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
