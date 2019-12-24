package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo().contains("all")) {
            req.setAttribute("message", "all users");
            req.setAttribute("id", "all");
            getServletContext().getRequestDispatcher("/DeleteUserPage.jsp").forward(req, resp);
        } else if (req.getPathInfo().contains("user")) {
            String userName = UserService.getInstance().getUserByID(Long.parseLong(req.getParameter("id"))).getUserName();
            req.setAttribute("message", "user " + userName);
            req.setAttribute("id", "user?id=" + req.getParameter("id"));
            getServletContext().getRequestDispatcher("/DeleteUserPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo().contains("all")) {
            UserService.getInstance().deleteAllUsers();
            req.setAttribute("message", "Data Base is clear!");
            getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_OK);
        }

        if (req.getPathInfo().contains("user")) {
            Long userId = Long.parseLong(req.getParameter("id"));

            String result = UserService.getInstance().deleteUserById(userId);
            if (result.contains("Error:")) {
                req.setAttribute("message", result);
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                req.setAttribute("message", result);
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }
}
