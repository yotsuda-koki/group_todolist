package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.UserTodoLogic;
import model.User;
import model.UserTodo;
import settings.PageSettings;


@WebServlet("/UserTodo")
public class UserTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//セッションスコープからユーザーを取得
			User user = (User) request.getSession().getAttribute("user");
			//ユーザーIDからTODOリストを取得
			int userId = user.getId();
			UserTodoLogic utl = new UserTodoLogic();
			List<UserTodo> userTodo =  utl.findAll(userId);
			//リクエストスコープにTODOリストを保存
			request.setAttribute("userTodo", userTodo);
			// 今日の日付を取得
			LocalDate now = LocalDate.now();
			java.sql.Date today = java.sql.Date.valueOf(now);
			request.setAttribute("today", today);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userTodo.jsp");
			rd.forward(request, response);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			String errorMessage = e.getMessage();
			request.setAttribute("errorMessage", errorMessage);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);
			
			return;
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int id = Integer.parseInt(request.getParameter("id"));
		//セッションスコープからグループを取得
		try {

				UserTodoLogic utl = new UserTodoLogic();
				UserTodo todo = utl.findOne(id);
				
				todo.setFinishedAt(Date.valueOf(format.format(date)));
				todo.setIsDeleted(1);
				
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
