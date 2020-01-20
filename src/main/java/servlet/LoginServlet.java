package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        User userFromDB = UserService.getInstance().getUserByUserName(userName);
        if (userFromDB.getPassword().equals(password)) {
            req.getSession().setAttribute("loggedUser", userFromDB);
            resp.setStatus(HttpServletResponse.SC_OK);
            String userRole = userFromDB.getRole();
            if (userRole.equalsIgnoreCase("admin")) {
                req.getRequestDispatcher("/admin/main").forward(req, resp);
            }
            if (userRole.equalsIgnoreCase("user")) {
                req.getRequestDispatcher("/user/info/id=" + userFromDB.getId()).forward(req, resp);
            }
        } else {
            req.setAttribute("message", "login or password is incorrect!");
            getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
