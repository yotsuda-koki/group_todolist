<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>個人用TODOリスト</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 800px;
        margin-top: 50px;
    }
    .finished {
        text-decoration: line-through double;
        color: gray;
    }
    .expired {
        background-color: #f8d7da;
    }
</style>
</head>
<body>

    <%@ include file="/WEB-INF/jsp/action/navbar.jsp"%>

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                <span class="fw-bold">TODOリスト</span>
                <div>
                    <a class="btn btn-success btn-sm" href="TodoRegister">追加</a>
                    <a class="btn btn-light btn-sm" href="UserTop">戻る</a>
                </div>
            </div>

            <div class="card-body">
                <table class="table table-striped">
                    <thead class="table-dark">
                        <tr>
                            <th scope="col">登録日</th>
                            <th scope="col">項目</th>
                            <th scope="col">期限日</th>
                            <th scope="col" class="text-center">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todo" items="${userTodo}">
                            <c:if test="${todo.isDeleted != 1}">
                                <tr class="<c:if test='${todo.finishedAt != null}'>finished</c:if>
                                           <c:if test='${todo.expirationAt.before(today)}'>expired</c:if>">
                                    <td class="align-middle">
                                        <fmt:formatDate value="${todo.registrationAt}" pattern="yyyy/MM/dd" />
                                    </td>
                                    <td class="align-middle"><c:out value="${todo.todoItem}" /></td>
                                    <td class="align-middle">
                                        <fmt:formatDate value="${todo.expirationAt}" pattern="yyyy/MM/dd" />
                                    </td>
                                    <td class="align-middle">
                                        <div class="d-flex justify-content-center">
                                            <form action="TodoUpdate" method="get" class="me-1">
                                                <input type="hidden" name="id" value="${todo.id}">
                                                <button type="submit" class="btn btn-outline-primary btn-sm">編集</button>
                                            </form>
                                            <form action="UserTodo" method="post" class="me-1">
                                                <input type="hidden" name="id" value="${todo.id}">
                                                <button type="submit" class="btn btn-outline-danger btn-sm">削除</button>
                                            </form>
                                            <c:choose>
                                                <c:when test="${todo.finishedAt == null}">
                                                    <form action="TodoFinish" method="get">
                                                        <input type="hidden" name="id" value="${todo.id}">
                                                        <button type="submit" class="btn btn-outline-success btn-sm">完了</button>
                                                    </form>
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="TodoNotFinish" method="get">
                                                        <input type="hidden" name="id" value="${todo.id}">
                                                        <button type="submit" class="btn btn-outline-secondary btn-sm">未完了</button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
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
