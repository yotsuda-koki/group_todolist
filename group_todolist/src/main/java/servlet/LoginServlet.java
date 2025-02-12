package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.UserLogic;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = (String) request.getSession().getAttribute("message");
		request.setAttribute("message", message);
		request.getSession().removeAttribute("message");
		
		RequestDispatcher rd = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = (String) request.getSession().getAttribute("message");
		request.setAttribute("message", message);
		request.getSession().removeAttribute("message");
		
		//フォームからのリクエストパラメーターを取得
		String emailOrAccountId = request.getParameter("emailOrAccountId");
		String password = request.getParameter("password");
		
		try {
			//ユーザーを検索（ログイン）
			User user = new User();
			UserLogic ul = new UserLogic();
			user = ul.find(emailOrAccountId, password);
			
			if(user == null) {
				//ログインに失敗時、エラーメッセージをリクエストスコープに保存
				request.setAttribute("error", MessageSettings.MSG_LOGIN_FAILURE);
				
				//失敗時、入力した情報を再表示するためにリクエストスコープに保存
				user = new User();
				user.setEmail(emailOrAccountId);
				user.setPassword(password);
				request.setAttribute("user", user);
				
				RequestDispatcher rd = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
				rd.forward(request, response);
				return;
				
			}
			
			//ログインに成功時、ユーザーをセッションに保存
			request.getSession().setAttribute("user", user);
			response.sendRedirect("Top");
			return;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
			String errorMessage = e.getMessage();
			request.setAttribute("errorMessage", errorMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);
			
			return;
		}		
	}
}
