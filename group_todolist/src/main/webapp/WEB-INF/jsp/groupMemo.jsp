<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループ用メモ</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 800px;
        margin-top: 50px;
    }
</style>
</head>
<body>

    <%@ include file="/WEB-INF/jsp/action/navbar.jsp"%>

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                <h5 class="mb-0">所属グループ: <c:out value="${group.groupName}"/></h5>
                <form action="GroupTop" method="get">
                    <input type="hidden" name="id" value="${group.id}">
                    <button class="btn btn-light">戻る</button>
                </form>
            </div>

            <div class="card-body">
                <h2 class="text-center mb-4">メモリスト</h2>

                <!-- メモ入力フォーム -->
                <form class="row g-3 align-items-center" action="GroupMemo" method="post">
                    <div class="col-md-3">
                        <label for="accountId" class="form-label">アカウントID</label>
                        <input type="text" class="form-control-plaintext" name="accountId"
                            id="accountId" value="${user.accountId}" readonly>
                    </div>

                    <div class="col-md-6">
                        <label for="memoItem" class="form-label">メモ</label>
                        <input type="text" class="form-control" name="memoItem" id="memoItem"
                            placeholder="メモを入力してください" required maxlength="100">
                    </div>

                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary w-100">追加</button>
                    </div>
                </form>

                <!-- メモリスト -->
                <table class="table table-striped table-hover mt-4">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">アカウントID</th>
                            <th scope="col">項目</th>
                            <th scope="col">日時</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="memo" items="${groupMemo}">
                            <c:if test="${memo.isDeleted != 1}">
                                <tr>
                                    <td><c:out value="${memo.accountId}" /></td>
                                    <td><c:out value="${memo.memoItem}" /></td>
                                    <td><fmt:formatDate value="${memo.createdAt}" pattern="yyyy/MM/dd HH:mm" /></td>
                                    <td>
                                        <form action="GroupMemo" method="post">
                                            <input type="hidden" name="id" value="${memo.id}">
                                            <button type="submit" class="btn btn-danger btn-sm">削除</button>
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
