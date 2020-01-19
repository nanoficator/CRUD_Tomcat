package servlet;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        User loggedUser = (User) httpSession.getAttribute("loggedUser");
        if (loggedUser == null) {
            servletRequest.getRequestDispatcher("index.jsp");
        } else {
            String userRole = loggedUser.getRole();
            if (userRole.equalsIgnoreCase("admin")) {
                servletRequest.getRequestDispatcher("/admin/main").forward(servletRequest, servletResponse);
            }
            if (userRole.equalsIgnoreCase("user")) {
                servletRequest.getRequestDispatcher("/user/info/id=" + loggedUser.getId()).forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}