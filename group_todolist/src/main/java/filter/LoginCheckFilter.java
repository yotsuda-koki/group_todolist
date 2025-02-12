package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import settings.PageSettings;

@WebFilter(filterName = "LoginCheckFilter")
public class LoginCheckFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(true);

		if (session.getAttribute("user") == null) {

			res.sendRedirect(PageSettings.LOGIN_SERVLET);

			return;
		}
		
		chain.doFilter(request, response);
	}
}
