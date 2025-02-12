<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>個人用メモ</title>
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
            <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                <span class="fw-bold">メモリスト</span>
                <a class="btn btn-light btn-sm" href="UserTop">戻る</a>
            </div>

            <div class="card-body">
                <!-- メモ追加フォーム -->
                <form class="row g-3" action="UserMemo" method="post">
                    <div class="col-8">
                        <input type="text" class="form-control" name="memoItem" placeholder="メモを入力" required>
                    </div>
                    <div class="col-4">
                        <button type="submit" class="btn btn-outline-primary w-100">追加</button>
                    </div>
                </form>

                <!-- メモ一覧 -->
                <table class="table table-striped mt-4">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">項目</th>
                            <th scope="col">日時</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="memo" items="${userMemo}">
                            <c:if test="${memo.isDeleted != 1}">
                                <tr>
                                    <td><c:out value="${memo.memoItem}"/></td>
                                    <td><fmt:formatDate value="${memo.createdAt}" pattern="yyyy/MM/dd HH:mm" /></td>
                                    <td>
                                        <form action="UserMemo" method="post" class="d-inline">
                                            <input type="hidden" name="id" value="${memo.id}">
                                            <button type="submit" class="btn btn-outline-danger btn-sm">削除</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
