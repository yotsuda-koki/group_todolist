package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import logic.GroupMemoLogic;
import logic.GroupTodoLogic;
import logic.UserLogic;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;

@WebServlet("/UserUpdate")
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserUpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションからユーザーを取得
            User sessionUser = (User) request.getSession().getAttribute("user");

            if (sessionUser == null) {
                response.sendRedirect("Login");
                return;
            }

            int userId = sessionUser.getId();
            String oldAccountId = sessionUser.getAccountId();

            // フォームデータの取得
            String newAccountId = request.getParameter("accountId");
            String newUserName = request.getParameter("userName");
            String newEmail = request.getParameter("email");
            String newPassword = request.getParameter("password"); // 新しいパスワード（任意）
            String currentPassword = request.getParameter("currentPassword"); // 現在のパスワード（必須）

            UserLogic userLogic = new UserLogic();

            // DBから最新のユーザーデータを取得
            User dbUser = userLogic.find(userId);
            if (dbUser == null) {
                request.setAttribute("errorMessage", "ユーザーが見つかりません。");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
                rd.forward(request, response);
                return;
            }

            String storedHashedPassword = dbUser.getPassword();
            

            // 現在のパスワードが正しいか検証
            if (!BCrypt.checkpw(currentPassword, storedHashedPassword)) {
                request.setAttribute("errorMessage", "現在のパスワードが間違っています。");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userUpdate.jsp");
                rd.forward(request, response);
                return;
            }

            // 新しいパスワードが入力された場合のみ、更新
            String updatedPassword = storedHashedPassword; // デフォルトは既存のパスワードを使用
            if (newPassword != null && !newPassword.isEmpty()) {
                updatedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                userLogic.updatePassword(userId, updatedPassword);
            }

            // ユーザー情報を更新
            User updatedUser = new User();
            updatedUser.setId(userId);
            updatedUser.setAccountId(newAccountId);
            updatedUser.setUserName(newUserName);
            updatedUser.setEmail(newEmail);
            updatedUser.setPassword(updatedPassword);
            updatedUser.setIsDeleted(0);

            userLogic.update(updatedUser);

            // セッションのユーザー情報も更新
            request.getSession().setAttribute("user", updatedUser);

            // アカウント ID の変更を反映
            if (!oldAccountId.equals(newAccountId)) {
                GroupTodoLogic gtl = new GroupTodoLogic();
                gtl.update(oldAccountId, newAccountId);
                GroupMemoLogic gml = new GroupMemoLogic();
                gml.update(oldAccountId, newAccountId);
            }

            // セッションをリセットし、ログイン画面へ
            request.getSession().invalidate();
            request.getSession().setAttribute("message", MessageSettings.MSG_USER_UPDATE);
            response.sendRedirect("Login");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "エラーが発生しました。");
            RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
            dispatcher.forward(request, response);
        }
    }
}
