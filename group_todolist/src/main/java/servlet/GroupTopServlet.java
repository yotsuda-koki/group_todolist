package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GroupLogic;
import model.Group;
import settings.PageSettings;


@WebServlet("/GroupTop")
public class GroupTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String idValue = request.getParameter("id");
			
			if(idValue != null) {
				
				int id = Integer.parseInt(idValue);
				GroupLogic gl = new GroupLogic();
				Group group = gl.find(id);
				//セッションスコープにグループを保存
				request.getSession().setAttribute("group", group);
				
			}
			
			String message = (String) request.getSession().getAttribute("message");
			request.setAttribute("message", message);
			request.getSession().removeAttribute("message");
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupTop.jsp");
			rd.forward(request, response);
			
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
