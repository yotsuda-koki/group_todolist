<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループ検索</title>
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
        <h2 class="text-center mb-4">グループ検索</h2>

        <!-- 検索フォーム -->
        <form action="Result" method="get" class="input-group mb-3">
            <input type="text" class="form-control" name="searchValue" placeholder="グループ名やキーワードを入力" required>
            <button class="btn btn-primary" type="submit">検索</button>
        </form>

        <!-- 検索結果表示エリア -->
        <div id="searchResults" class="mt-4">
            <c:if test="${not empty searchResults}">
                <ul class="list-group">
                    <c:forEach var="group" items="${searchResults}">
                        <li class="list-group-item">
                            <c:out value="${group.groupName}" />
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>

        <!-- 戻るボタン -->
        <div class="d-flex justify-content-center mt-4">
            <a class="btn btn-secondary" href="Top">戻る</a>
        </div>
    </div>

</body>
</html>
