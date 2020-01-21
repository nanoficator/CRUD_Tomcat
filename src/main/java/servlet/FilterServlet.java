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
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession httpSession = httpServletRequest.getSession();
        User loggedUser = (User) httpSession.getAttribute("loggedUser");
        if (loggedUser == null) {
            servletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, httpServletResponse);
        } else {
            String userRole = loggedUser.getRole();
            if (userRole.equalsIgnoreCase("user")) {
                String path = httpServletRequest.getRequestURI();
                if (path.contains("/admin/")) {
                    httpServletRequest.setAttribute("message", "Forbidden!");
                    httpServletRequest.getRequestDispatcher("/ResultPage.jsp").forward(httpServletRequest, httpServletResponse);
                }
                if (path.contains("/user/")) {
                    if (!path.equalsIgnoreCase("/user/info/user?id=" + loggedUser.getId()))
                    httpServletRequest.setAttribute("message", "Forbidden!");
                    httpServletRequest.getRequestDispatcher("/ResultPage.jsp").forward(httpServletRequest, httpServletResponse);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}