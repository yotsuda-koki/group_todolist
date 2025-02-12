<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container">

    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><span class="nav-link id">ユーザー名:<c:out value="${user.userName}"></c:out></span></li>
            <li class="nav-item"><span class="nav-link id">アカウントID:<c:out value="${user.accountId}"></c:out></span></li>
            <li class="nav-item"><a href="UserUpdate" class="btn btn-outline-primary me-2" role="button"">登録情報の変更</a></li>
            <li class="nav-item"><a href="Logout" class="btn btn-outline-success" role="button">ログアウト</a></li>
        </ul>
      </header>

</div>


      <!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>