<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>グループ用TODOリスト</title>
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
                <div>
                    <a class="btn btn-success" href="TodoRegister" role="button">追加</a>
                    <form action="GroupTop" method="get" class="d-inline">
                        <input type="hidden" name="id" value="${group.id}">
                        <button class="btn btn-light">戻る</button>
                    </form>
                </div>
            </div>

            <div class="card-body">
                <h2 class="text-center mb-4">TODOリスト</h2>

                <!-- TODOリスト -->
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">登録日</th>
                            <th scope="col">項目</th>
                            <th scope="col">登録者ID</th>
                            <th scope="col">期限日</th>
                            <th scope="col">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todo" items="${groupTodo}">
                            <c:if test="${todo.isDeleted != 1}">
                                <tr class="
                                    <c:if test='${todo.finishedAt != null}'>bg-success text-white</c:if>
                                    <c:if test='${todo.expirationAt.before(today)}'>bg-danger text-white</c:if>
                                ">
                                    <td class="align-middle">
                                        <fmt:formatDate value="${todo.registrationAt}" pattern="yyyy/MM/dd"/>
                                    </td>
                                    <td class="align-middle"><c:out value="${todo.todoItem}" /></td>
                                    <td class="align-middle"><c:out value="${todo.accountId}" /></td>
                                    <td class="align-middle">
                                        <fmt:formatDate value="${todo.expirationAt}" pattern="yyyy/MM/dd"/>
                                    </td>
                                    <td>
                                        <div class="d-flex gap-2">
                                            <form action="TodoUpdate" method="get">
                                                <input type="hidden" name="id" value="${todo.id}">
                                                <button type="submit" class="btn btn-outline-primary btn-sm">編集</button>
                                            </form>
                                            <form action="GroupTodo" method="post">
                                                <input type="hidden" name="id" value="${todo.id}">
                                                <button type="submit" class="btn btn-danger btn-sm">削除</button>
                                            </form>
                                            <form action="<c:out value='${todo.finishedAt == null ? "TodoFinish" : "TodoNotFinish"}'/>" method="get">
                                                <input type="hidden" name="id" value="${todo.id}">
                                                <button type="submit" class="btn btn-outline-success btn-sm">
                                                    <c:out value="${todo.finishedAt == null ? '完了' : '未完了'}"/>
                                                </button>
                                            </form>
                                        </div>
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
