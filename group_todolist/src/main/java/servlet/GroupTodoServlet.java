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

import logic.GroupTodoLogic;
import model.Group;
import model.GroupTodo;
import settings.PageSettings;


@WebServlet("/GroupTodo")
public class GroupTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//セッションスコープからユーザーを取得
			Group group = (Group) request.getSession().getAttribute("group");
			//ユーザーIDからTODOリストを取得
			int groupId = group.getId();
			GroupTodoLogic gtl = new GroupTodoLogic();
			List<GroupTodo> groupTodo =  gtl.findAll(groupId);
			
			//リクエストスコープにTODOリストを保存
			request.setAttribute("groupTodo", groupTodo);
			// 今日の日付を取得
			LocalDate now = LocalDate.now();
			java.sql.Date today = java.sql.Date.valueOf(now);
			request.setAttribute("today", today);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/groupTodo.jsp");
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
				
		try {
			
			GroupTodoLogic gtl = new GroupTodoLogic();			
			GroupTodo todo = gtl.find(id);
			
			todo.setFinishedAt(Date.valueOf(format.format(date)));
			todo.setIsDeleted(1);

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
	}
}
