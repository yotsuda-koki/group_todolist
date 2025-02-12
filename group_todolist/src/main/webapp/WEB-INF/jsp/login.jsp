<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン</title>

<!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 400px;
        margin-top: 50px;
    }
    .form-signin {
        padding: 20px;
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
            <div class="card-body">
                <h2 class="mb-4">ログイン</h2>

                <!-- エラーメッセージの表示 -->
                <c:if test="${error != null}">
                    <div class="alert alert-danger" role="alert">${error}</div>
                </c:if>
                <c:if test="${success != null}">
                    <div class="alert alert-success" role="alert">${success}</div>
                </c:if>
                <c:if test="${message != null}">
                    <div class="alert alert-info" role="alert">${message}</div>
                </c:if>

                <form action="Login" method="post">
                    <!-- アカウントID or メールアドレス -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" name="emailOrAccountId"
                            id="floatingInput" required>
                        <label for="floatingInput">アカウントIDまたはメールアドレス</label>
                    </div>

                    <!-- パスワード -->
                    <div class="form-floating password-wrapper mb-3">
                        <input type="password" class="form-control" name="password"
                            id="floatingPassword"
                            pattern="^[a-zA-Z0-9!@#$%^&*()_+,.?-]{8,20}$"
                            title="8文字以上、20文字以下の半角英数字または記号を入力してください"
                            required>
                        <label for="floatingPassword">パスワード</label>
                        <button type="button" class="toggle-password" onclick="togglePassword()" id="toggleButton">
                            SHOW
                        </button>
                    </div>

                    <!-- ログインボタン -->
                    <button class="w-100 btn btn-lg btn-primary" type="submit">ログイン</button>
                </form>

                <!-- 新規登録リンク -->
                <p class="mt-3"><a href="UserRegister">新規登録はこちら</a></p>
            </div>
        </div>
    </div>

    <script>
        function togglePassword() {
            const passwordField = document.getElementById("floatingPassword");
            const toggleButton = document.getElementById("toggleButton");
            if (passwordField.type === "password") {
                passwordField.type = "text";
                toggleButton.textContent = "HIDE";
            } else {
                passwordField.type = "password";
                toggleButton.textContent = "SHOW";
            }
        }
    </script>

</body>
</html>
