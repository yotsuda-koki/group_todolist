package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GroupTodoLogic;
import logic.UserTodoLogic;
import model.Group;
import model.GroupTodo;
import model.UserTodo;
import settings.PageSettings;


@WebServlet("/TodoNotFinish")
public class TodoNotFinishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		//セッションスコープからグループを取得
		Group group = (Group) request.getSession().getAttribute("group");
		
		if(group != null) {
			try {
				
				GroupTodoLogic gtl = new GroupTodoLogic();			
				GroupTodo todo = gtl.find(id);
				
				todo.setFinishedAt(null);

				//グループTODOリストを１件更新

				gtl.update(todo);
				
				response.sendRedirect("GroupTodo");
				
				return;
			}catch(Exception e) {
				
				e.printStackTrace();
				
				String errorMessage = e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
				dispatcher.forward(request, response);
				
				return;
			}
		}else {
			try {

				UserTodoLogic utl = new UserTodoLogic();
				UserTodo todo = utl.findOne(id);
				
				todo.setFinishedAt(null);
				
				//個人TODOリストを１件更新
				
				utl.update(todo);
				
				response.sendRedirect("UserTodo");
				
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


}
