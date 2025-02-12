<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>検索結果および加入申請</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
    .container {
        max-width: 700px;
        margin-top: 50px;
    }
    .list-group-item {
        border-radius: 10px; 
        margin-bottom: 10px; 
        padding: 15px; 
        box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1); 
    }
    .group-info {
        font-size: 0.9rem;
        color: #666;
        white-space: pre-wrap;
    }
    .group-title {
        background: #f8f9fa; 
        padding: 5px 10px;
        border-radius: 5px;
        font-weight: bold;
    }
</style>
</head>
<body>

    <%@ include file="/WEB-INF/jsp/action/navbar.jsp"%>

    <div class="container">
        <div class="card shadow">
            <div class="card-header bg-primary text-white fw-bold text-center">
                グループ検索結果
            </div>

            <div class="card-body">
                <ul class="list-group">
                    <c:forEach var="search" items="${search}">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-8">
                                    <h5 class="group-title">${search.groupName}</h5>
                                    <p class="group-info">${search.groupInfo}</p>
                                </div>

                                <div class="col-md-4 text-end">
                                    <form action="Result" method="post">
                                        <input type="hidden" name="groupId" value="${search.id}">
                                        <input type="hidden" name="userName" value="${user.userName}">
                                        <input type="hidden" name="accountId" value="${user.accountId}">
                                        <input type="hidden" name="userId" value="${user.id}">

                                        <c:set var="isMember" value="false" />
                                        <c:set var="isPending" value="false" />
                                        <c:set var="isRejected" value="false" />

                                        <c:forEach var="groupId" items="${groupIds}">
                                            <c:if test="${search.id == groupId}">
                                                <c:set var="isMember" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:forEach var="requestId" items="${requestIds0}">
                                            <c:if test="${search.id == requestId}">
                                                <c:set var="isPending" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:forEach var="requestId" items="${requestIds2}">
                                            <c:if test="${search.id == requestId}">
                                                <c:set var="isRejected" value="true" />
                                            </c:if>
                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${isMember}">
                                                <button type="button" class="btn btn-success w-100" disabled>参加済み</button>
                                            </c:when>
                                            <c:when test="${isPending}">
                                                <button type="button" class="btn btn-secondary w-100" disabled>申請中</button>
                                            </c:when>
                                            <c:when test="${isRejected}">
                                                <button type="submit" class="btn btn-primary w-100">再申請</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" class="btn btn-primary w-100">加入申請</button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>

                <!-- 戻るボタン -->
                <div class="d-flex justify-content-center mt-4">
                    <a class="btn btn-secondary" href="Top" role="button">TOPへ</a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
