package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GroupMemberLogic;
import logic.UserLogic;
import model.Group;
import settings.PageSettings;




@WebServlet("/GroupMember")
public class GroupMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GroupMemberServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//グループIDからグループメンバーのユーザーIDを取得しユーザーIDからユーザーリストを取得
			Group group = (Group) request.getSession().getAttribute("group");
			GroupMemberLogic gml = new GroupMemberLogic();
			UserLogic ul = new UserLogic();
			request.setAttribute("users", ul.find(gml.findMem(group.getId())));
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupMember.jsp");
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
