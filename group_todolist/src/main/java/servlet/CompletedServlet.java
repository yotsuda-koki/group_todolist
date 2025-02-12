package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Completed")
public class CompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String message = (String) request.getSession().getAttribute("message");
		request.setAttribute("message", message);
		request.getSession().removeAttribute("message");
    	
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/completed.jsp");
		rd.forward(request, response);
		
		return;
	}


}
