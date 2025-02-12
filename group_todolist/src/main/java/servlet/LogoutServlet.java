package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import settings.MessageSettings;
import settings.PageSettings;


@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストスコープを破棄する
		request.getSession().invalidate();
		
		request.setAttribute("success", MessageSettings.MSG_LOOUT);
		
		RequestDispatcher rd = request.getRequestDispatcher(PageSettings.LOGIN_JSP);
		rd.forward(request, response);
	}

}
