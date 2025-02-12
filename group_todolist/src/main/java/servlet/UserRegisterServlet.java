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

import logic.UserLogic;
import model.User;
import settings.MessageSettings;
import settings.PageSettings;

@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegisterServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/userRegister.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Instant now = Instant.now();
		
		String accountId = request.getParameter("accountId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			
			User user = new User();
			user.setAccountId(accountId);
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(password);
			user.setIsDeleted(0);
			user.setCreatedAt(Timestamp.from(now));
			//アカウントを作成する
			UserLogic ul = new UserLogic();
			int result = ul.create(user);
			
			if(result == 1062) {
				
				request.setAttribute("errorMessage", "そのアカウントID" + MessageSettings.MSG_DUPLICATION);

			    RequestDispatcher dispatcher = request.getRequestDispatcher(PageSettings.PAGE_ERROR);
			    dispatcher.forward(request, response);
			    
			}
			
			request.getSession().setAttribute("message", MessageSettings.MSG_USER_CREATE);
			response.sendRedirect(PageSettings.LOGIN_SERVLET);
			
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

