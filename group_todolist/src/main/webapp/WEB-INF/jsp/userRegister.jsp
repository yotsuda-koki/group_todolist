<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<title>ユーザー登録</title>
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
<body class="text-center">

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white text-center fw-bold">
                ユーザー登録
            </div>

            <div class="card-body">
                <form action="UserRegister" method="post" class="needs-validation" novalidate>

                    <!-- アカウントID -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="accountId" id="accountId"
                            pattern=".{4,8}" title="4文字以上8文字以下で入力してください" required>
                        <label for="accountId">アカウントID</label>
                        <div class="invalid-feedback">4文字以上8文字以下で入力してください。</div>
                    </div>

                    <!-- ユーザー名 -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="userName" id="userName"
                            pattern=".{4,20}" title="4文字以上20文字以下で入力してください" required>
                        <label for="userName">ユーザー名</label>
                        <div class="invalid-feedback">4文字以上20文字以下で入力してください。</div>
                    </div>

                    <!-- メールアドレス -->
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" name="email" id="email"
                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                        <label for="email">メールアドレス</label>
                        <div class="invalid-feedback">正しいメールアドレスを入力してください。</div>
                    </div>

                    <!-- パスワード -->
                    <div class="form-floating password-wrapper mb-3">
                        <input type="password" class="form-control" name="password" id="password"
                            pattern="^[a-zA-Z0-9!@#$%^&*()_+,.?-]{8,20}$"
                            title="8文字以上20文字以下の半角英数字または記号を入力してください" required>
                        <label for="password">パスワード</label>
                        <button type="button" class="toggle-password" onclick="togglePassword()" id="toggleButton">
                            SHOW
                        </button>
                        <div class="invalid-feedback">8文字以上20文字以下の半角英数字または記号を入力してください。</div>
                    </div>

                    <button class="w-100 btn btn-lg btn-primary" type="submit">登録</button>
                </form>

                <!-- ログインページへのリンク -->
                <p class="mt-3"><a href="Login">ログインはこちら</a></p>
            </div>
        </div>
    </div>

    <script>
	    // パスワードの表示/非表示切り替え
	    function togglePassword() {
	        const passwordField = document.getElementById("password");
	        const toggleButton = document.getElementById("toggleButton");
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
