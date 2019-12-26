package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/")
public class LogServlet  implements Filter {

    private FilterConfig filterConfig = null;
    private boolean active = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String act = filterConfig.getInitParameter("active");
        if (act != null) {
            active = act.toUpperCase().equals("TRUE");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (active) {

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}