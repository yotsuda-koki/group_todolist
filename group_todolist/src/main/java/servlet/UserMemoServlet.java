package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.UserMemoLogic;
import model.User;
import model.UserMemo;
import settings.PageSettings;


@WebServlet("/UserMemo")
public class UserMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserMemoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//セッションスコープからユーザーを取得
			User user = (User) request.getSession().getAttribute("user");
			//ユーザーIDからメモリストを取得
			int userId = user.getId();
			UserMemoLogic utl = new UserMemoLogic();
			List<UserMemo> userMemo =  utl.findAll(userId);
			//リクエストスコープにメモリストを保存
			request.setAttribute("userMemo", userMemo);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/userMemo.jsp");
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

		//現在の日付を取得
		Instant now = Instant.now();
		//セッションスコープからユーザーを取得
		User user = (User) request.getSession().getAttribute("user");
		String memoItem = request.getParameter("memoItem");
		String idValue = request.getParameter("id");
		
		//メモを追加時
		if(memoItem != null) {
			try {
				
				UserMemo memo = new UserMemo();
				
				memo.setUserId(user.getId());
				memo.setMemoItem(memoItem);
				memo.setIsDeleted(0);
				memo.setCreatedAt(Timestamp.from(now));
				//メモを１件作成
				UserMemoLogic uml = new UserMemoLogic();
				uml.create(memo);
				
				response.sendRedirect("UserMemo");
				
			}catch(Exception e) {
				
				e.printStackTrace();
				
				String errorMessage = e.getMessage();
				request.setAttribute("errorMessage", errorMessage);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
				dispatcher.forward(request, response);
				
				return;
			}
		}
		
		//メモを削除時
		if(idValue != null) {
			try {
				int id = Integer.parseInt(idValue);
				
				UserMemo memo = new UserMemo();
				
				memo.setIsDeleted(1);
				memo.setUpdatedAt(Timestamp.from(now));
				memo.setId(id);
				//メモを１件削除（削除フラグを１にして非表示に）
				UserMemoLogic uml = new UserMemoLogic();
				uml.update(memo);
				
				response.sendRedirect("UserMemo");
				
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
