package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

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
import model.User;
import model.UserTodo;
import settings.MessageSettings;
import settings.PageSettings;


@WebServlet("/TodoUpdate")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TodoUpdateServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションスコープからグループを取得
		Group group = (Group) request.getSession().getAttribute("group");
		
		if(group != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				GroupTodoLogic gtl = new GroupTodoLogic();
				request.setAttribute("groupTodo", gtl.find(id));
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/todoUpdate.jsp");
				rd.forward(request, response);
				
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
				int id = Integer.parseInt(request.getParameter("id"));
				UserTodoLogic utl = new UserTodoLogic();
				request.setAttribute("userTodo", utl.findOne(id));
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/todoUpdate.jsp");
				rd.forward(request, response);
				
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Instant now = Instant.now();
		
		String registrationAt = request.getParameter("registrationDate");
		String todoItem = request.getParameter("todoItem");
		String expirationAt = request.getParameter("expirationDate");
		int id = Integer.parseInt(request.getParameter("id"));
		
		//セッションスコープからグループを取得
		Group group = (Group) request.getSession().getAttribute("group");
		
		if(group != null) {
			try {
				
				GroupTodo todo = new GroupTodo();
				todo.setGroupId(group.getId());
				todo.setRegistrationAt(Date.valueOf(registrationAt));
				todo.setExpirationAt(Date.valueOf(expirationAt));
				todo.setFinishedAt(null);
				todo.setTodoItem(todoItem);
				todo.setIsDeleted(0);
				todo.setUpdatedAt(Timestamp.from(now));
				todo.setId(id);
				//グループTODOリストを１件更新
				GroupTodoLogic gtl = new GroupTodoLogic();
				gtl.update(todo);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_TODO_UPDATE);
				response.sendRedirect(PageSettings.COMPLETED_SERVLET);
				
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
				//セッションスコープからユーザーを取得
				User user = (User) request.getSession().getAttribute("user");
				
				UserTodo todo = new UserTodo();
				todo.setUserId(user.getId());
				todo.setRegistrationAt(Date.valueOf(registrationAt));
				todo.setExpirationAt(Date.valueOf(expirationAt));
				todo.setFinishedAt(null);
				todo.setTodoItem(todoItem);
				todo.setIsDeleted(0);
				todo.setUpdatedAt(Timestamp.from(now));
				todo.setId(id);
				//個人TODOリストを１件更新
				UserTodoLogic utl = new UserTodoLogic();
				utl.update(todo);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_TODO_UPDATE);
				response.sendRedirect(PageSettings.COMPLETED_SERVLET);
				
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
