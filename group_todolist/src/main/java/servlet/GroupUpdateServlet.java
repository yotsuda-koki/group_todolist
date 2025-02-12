package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.GroupLogic;
import model.Group;
import settings.MessageSettings;
import settings.PageSettings;


@WebServlet("/GroupUpdate")
public class GroupUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupUpdateServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			GroupLogic gl = new GroupLogic();
			request.setAttribute("group", gl.find(id));
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupUpdate.jsp");
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Instant now = Instant.now();
		
		String groupName = request.getParameter("groupName");
		String groupInfo = request.getParameter("groupInfo");
		Group group = new Group();
		Group g = (Group) request.getSession().getAttribute("group");
		
		try {
			//グループを更新
			group.setGroupName(groupName);
			group.setGroupInfo(groupInfo);
			group.setIsDeleted(0);
			group.setUpdatedAt(Timestamp.from(now));
			group.setId(g.getId());
			GroupLogic gl = new GroupLogic();
			gl.update(group);
			
			request.getSession().setAttribute("message", MessageSettings.MSG_GROUP_UPDATE);
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
