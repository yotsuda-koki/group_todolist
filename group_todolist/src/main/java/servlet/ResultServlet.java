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
import logic.GroupRequestLogic;
import model.GroupRequest;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;

@WebServlet("/Result")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResultServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		String searchValue = request.getParameter("searchValue");

		try {
			GroupLogic gl = new GroupLogic();
			//検索結果をリクエストスコープに保存
			request.setAttribute("search", gl.search(searchValue));
			
			request.setAttribute("group", gl.find());
			
			GroupMemberLogic gml = new GroupMemberLogic();
			request.setAttribute("groupIds", gml.findGroupId(user.getId()));
			
			GroupRequestLogic grl = new GroupRequestLogic();
			request.setAttribute("requestIds0", grl.findByPermission0(user.getId()));
			
			request.setAttribute("requestIds2", grl.findByPermission2(user.getId()));

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/result.jsp");
			rd.forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();
			
			String errorMessage = e.getMessage();
			request.setAttribute("errorMessage", errorMessage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			dispatcher.forward(request, response);

			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Instant now = Instant.now();

		User user = (User) request.getSession().getAttribute("user");

		int groupId = Integer.parseInt(request.getParameter("groupId"));
		String userName = request.getParameter("userName");
		String accountId = request.getParameter("accountId");
		String userIdValue = request.getParameter("userId");
		String searchIdValue = request.getParameter("searchId");
		
		if(userIdValue != null && searchIdValue != null) {
			
			try {
				int userId = Integer.parseInt(userIdValue);
				int serchId = Integer.parseInt(searchIdValue);
								
				GroupRequest groupRequest = new GroupRequest();
				GroupRequestLogic grl = new GroupRequestLogic();
				
				groupRequest.setId(grl.find(userId, serchId).getId());
				groupRequest.setPermission(0);
				grl.update(groupRequest);
				
				//完了メッセージをリクエストスコープに保存
				request.getSession().setAttribute("message", MessageSettings.MSG_RE_REQUEST_COMPLETED);
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
				//加入申請を１件追加
				GroupRequest groupRequest = new GroupRequest();
				groupRequest.setGroupId(groupId);
				groupRequest.setUserId(user.getId());
				groupRequest.setUserName(userName);
				groupRequest.setAccountId(accountId);
				groupRequest.setPermission(0);
				groupRequest.setIsDeleted(0);
				groupRequest.setRequestedAt(Timestamp.from(now));
				GroupRequestLogic grl = new GroupRequestLogic();
				grl.create(groupRequest);

				//完了メッセージをリクエストスコープに保存
				request.getSession().setAttribute("message", MessageSettings.MSG_REQUEST_COMPLETED);
				response.sendRedirect(PageSettings.COMPLETED_SERVLET);

			} catch (Exception e) {

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
