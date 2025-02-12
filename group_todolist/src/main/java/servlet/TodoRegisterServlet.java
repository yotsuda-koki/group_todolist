package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

@WebServlet("/TodoRegister")
public class TodoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TodoRegisterServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = now.format(formatter);
		request.setAttribute("todaysDate", formattedDate);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/todoRegister.jsp");
		rd.forward(request, response);
		
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//現在の日付を取得
		Instant now = Instant.now();
		
		String registrationAt = request.getParameter("registrationDate");
		String todoItem = request.getParameter("todoItem");
		String expirationAt = request.getParameter("expirationDate");
		
		//セッションスコープからグループを取得
		Group group = (Group) request.getSession().getAttribute("group");
		User user = (User) request.getSession().getAttribute("user");
		
		
		if(group != null) {
			try {
				
				GroupTodo todo = new GroupTodo();
				todo.setGroupId(group.getId());
				todo.setAccountId(user.getAccountId());
				todo.setRegistrationAt(Date.valueOf(registrationAt));
				todo.setExpirationAt(Date.valueOf(expirationAt));
				todo.setFinishedAt(null);
				todo.setTodoItem(todoItem);
				todo.setIsDeleted(0);
				todo.setCreatedAt(Timestamp.from(now));
				//グループTODOリストを１件作成
				GroupTodoLogic gtl = new GroupTodoLogic();
				gtl.create(todo);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_TODO_CREATE);
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
				
				UserTodo todo = new UserTodo();
				todo.setUserId(user.getId());
				todo.setRegistrationAt(Date.valueOf(registrationAt));
				todo.setExpirationAt(Date.valueOf(expirationAt));
				todo.setFinishedAt(null);
				todo.setTodoItem(todoItem);
				todo.setIsDeleted(0);
				todo.setCreatedAt(Timestamp.from(now));
				//個人TODOリストを１件作成
				UserTodoLogic utl = new UserTodoLogic();
				utl.create(todo);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_TODO_CREATE);
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
