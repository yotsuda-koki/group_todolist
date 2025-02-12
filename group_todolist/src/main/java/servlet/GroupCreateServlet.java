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
import logic.GroupMemberLogic;
import model.Group;
import model.GroupMember;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;


@WebServlet("/GroupCreate")
public class GroupCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupCreateServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupCreate.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Instant now = Instant.now();
		
		User user = (User) request.getSession().getAttribute("user");
		
		String groupName = request.getParameter("groupName");
		String groupInfo = request.getParameter("groupInfo");
		Group group = new Group();
		GroupMember groupMember = new GroupMember();
		
		try {
			//グループを追加
			group.setGroupName(groupName);
			group.setGroupInfo(groupInfo);
			group.setIsDeleted(0);
			group.setCreatedAt(Timestamp.from(now));
			GroupLogic gl = new GroupLogic();
			int result = gl.create(group);
			
			if(result == 1062) {
				
				request.setAttribute("errorMessage", "そのアカウントID" + MessageSettings.MSG_DUPLICATION);

			    RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			    dispatcher.forward(request, response);
			    
			}
			
			groupMember.setGroupId(gl.findByName(groupName).getId());
			groupMember.setUserId(user.getId());
			groupMember.setAdmin(1);
			groupMember.setIsDeleted(0);
			groupMember.setCreatedAt(Timestamp.from(now));
			GroupMemberLogic gml = new GroupMemberLogic();
			gml.create(groupMember);
			
			request.getSession().setAttribute("message", MessageSettings.MSG_GROUP_CREATE);
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
