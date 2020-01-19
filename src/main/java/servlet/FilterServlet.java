package servlet;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/main")
public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userName = servletRequest.getParameter("username");
        String password = servletRequest.getParameter("password");
        User userByUserName = UserService.getInstance().getUserByUserName(userName);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        if (userByUserName.getPassword().equals(password)) {
            String userRole = userByUserName.getRole();
            if (userRole.equalsIgnoreCase("admin")) {

            }
            if (userRole.equalsIgnoreCase("user")) {

            }
        } else {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}