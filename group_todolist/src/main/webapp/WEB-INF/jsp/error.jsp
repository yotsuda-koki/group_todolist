<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>エラーページ</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            min-height: 50vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <h1 class="text-danger">エラーが発生しました</h1>
        <p>申し訳ありませんが、問題が発生しました。<br>時間をおいて再試行してください。</p>
        <a href="Top" class="btn btn-primary">トップページへ戻る</a>
    </div>
</body>
</html>
