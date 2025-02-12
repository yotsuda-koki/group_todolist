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

import logic.GroupMemoLogic;
import model.Group;
import model.GroupMemo;
import settings.PageSettings;


@WebServlet("/GroupMemo")
public class GroupMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GroupMemoServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//グループIDからグループのメモリスト検索し保存
			Group group = (Group) request.getSession().getAttribute("group");
			GroupMemoLogic gml = new GroupMemoLogic();
			request.setAttribute("groupMemo", gml.findAll(group.getId()));
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/groupMemo.jsp");
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
		
		//現在の日付を取得
		Instant now = Instant.now();
		
		Group group = (Group) request.getSession().getAttribute("group");
		String accountId = request.getParameter("accountId");
		String memoItem = request.getParameter("memoItem");
		String idValue = request.getParameter("id");
		
		//グループメモ追加時
		if(memoItem != null) {
			try {
				
				GroupMemo groupMemo = new GroupMemo();
				
				groupMemo.setGroupId(group.getId());
				groupMemo.setAccountId(accountId);
				groupMemo.setMemoItem(memoItem);
				groupMemo.setIsDeleted(0);
				groupMemo.setCreatedAt(Timestamp.from(now));
				//グループメモを１件作成
				GroupMemoLogic gml = new GroupMemoLogic();
				gml.create(groupMemo);
				
				response.sendRedirect("GroupMemo");
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				String errorMessage = e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
				dispatcher.forward(request, response);
				
				return;
			}
		}
		
		//グループメモ削除時
		if(idValue != null) {
			try {
				int id = Integer.parseInt(idValue);
				
				GroupMemo groupMemo = new GroupMemo();
				
				groupMemo.setGroupId(group.getId());
				groupMemo.setIsDeleted(1);
				groupMemo.setUpdatedAt(Timestamp.from(now));
				groupMemo.setId(id);
				//グループメモを１件削除（削除フラグを１にして非表示に）
				GroupMemoLogic gml = new GroupMemoLogic();
				gml.update(groupMemo);
				
				response.sendRedirect("GroupMemo");
				
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
