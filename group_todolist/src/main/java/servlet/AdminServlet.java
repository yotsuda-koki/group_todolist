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

import logic.GroupMemberLogic;
import logic.GroupRequestLogic;
import logic.UserLogic;
import model.Group;
import model.GroupMember;
import model.GroupRequest;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;


@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			Group group = (Group) request.getSession().getAttribute("group");
			GroupMemberLogic gml = new GroupMemberLogic();
			
			if(gml.findAdmin(group.getId(), user.getId()) == 1) {
				//アドミンのときグループIDからグループリクエストを検索してリクエストスコープに保存
				GroupRequestLogic grl = new GroupRequestLogic();
				request.setAttribute("groupRequest", grl.findByGroup(group.getId()));
				//グループIDからグループメンバーのユーザーIDを取得しユーザーIDからユーザーリストを取得してリスエストスコープに保存
				UserLogic ul = new UserLogic();
				request.setAttribute("users", ul.find(gml.findMem(group.getId())));
				//admin.jspにフォワード
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/admin.jsp");
				rd.forward(request, response);
			}else {
				
				request.getSession().setAttribute("message", MessageSettings.MSG_NOT_ADMIN);
				response.sendRedirect("GroupTop");
			}
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
		
		Group group = (Group) request.getSession().getAttribute("group");
		User user = (User) request.getSession().getAttribute("user");
		GroupMember groupMember = new GroupMember();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String isDeletedValue = request.getParameter("isDeleted");
		String adminValue = request.getParameter("admin");
		
		GroupRequest groupRequest = new GroupRequest();
		String permissionValue = request.getParameter("permission");
		
		GroupMemberLogic gml = new GroupMemberLogic();
		GroupRequestLogic grl = new GroupRequestLogic();
		
		try {
			if(isDeletedValue != null) {
				//グループメンバーを更新してメンバーを削除（削除フラグを１に）
				groupMember.setAdmin(0);
				groupMember.setIsDeleted(1);
				groupMember.setUpdatedAt(Timestamp.from(now));
				groupMember.setId(gml.findId(group.getId(), id));
				gml.update(groupMember);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_DELETED_MEMBER);
				response.sendRedirect(PageSettings.COMPLETED_SERVLET);
			}
			
			if(adminValue != null) {
				//アドミンを譲渡
				//グループメンバーを更新してアドミンを１に
				groupMember.setAdmin(1);
				groupMember.setIsDeleted(0);
				groupMember.setUpdatedAt(Timestamp.from(now));
				groupMember.setId(gml.findId(group.getId(), id));
				gml.update(groupMember);
				//グループメンバーを更新してアドミンを０に
				groupMember.setAdmin(0);
				groupMember.setIsDeleted(0);
				groupMember.setUpdatedAt(Timestamp.from(now));
				groupMember.setId(gml.findId(group.getId(), user.getId()));
				gml.update(groupMember);
				
				request.getSession().setAttribute("message", MessageSettings.MSG_PASSED_ADMIN);
				response.sendRedirect(PageSettings.COMPLETED_SERVLET);
			}
			
			if(permissionValue != null) {
				//グループリクエストを更新（承認、拒否）
				groupRequest.setPermission(Integer.parseInt(permissionValue));
				groupRequest.setId(id);
				grl.update(groupRequest);
				
				groupRequest = grl.find(id);
				
				if(groupRequest.getPermission() == 1) {
					int userId = Integer.parseInt(request.getParameter("userId"));
					//グループメンバーを追加してグループメンバーを作成
					groupMember.setGroupId(group.getId());
					groupMember.setUserId(userId);
					groupMember.setAdmin(0);
					groupMember.setIsDeleted(0);
					groupMember.setCreatedAt(Timestamp.from(now));
					gml.create(groupMember);
					
					request.getSession().setAttribute("message", MessageSettings.MSG_ACCEPTED_REQUEST);
					response.sendRedirect(PageSettings.COMPLETED_SERVLET);
				}else if(groupRequest.getPermission() == 2) {
					
					request.getSession().setAttribute("message", MessageSettings.MSG_REFUSED_REQUEST);
					response.sendRedirect(PageSettings.COMPLETED_SERVLET);
				}
				
			}
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
