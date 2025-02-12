package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GroupLogic;
import logic.GroupMemberLogic;
import model.GroupMember;
import model.User;
import settings.PageSettings;


@WebServlet("/GroupSelect")
public class GroupSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupSelectServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//ユーザーIDからGroupMemberのリストを受け取る
			User user = (User) request.getSession().getAttribute("user");
			GroupMemberLogic gml = new GroupMemberLogic();
			List<GroupMember> list = gml.findByUserId(user.getId());
			//受け取ったGroupMemberのリストをGroupDAOに渡しリクエストスコープに保存
			GroupLogic gl = new GroupLogic();
			request.setAttribute("grouplist", gl.findbyGroupId(list));
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupSelect.jsp");
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

