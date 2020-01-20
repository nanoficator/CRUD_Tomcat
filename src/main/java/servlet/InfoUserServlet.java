package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/info/*")
public class InfoUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User userFromDB = UserService.getInstance().getUserByID(id);

        req.setAttribute("id", userFromDB.getId());
        req.setAttribute("firstName", userFromDB.getFirstName());
        req.setAttribute("secondName", userFromDB.getSecondName());
        req.setAttribute("userName", userFromDB.getUserName());
        req.setAttribute("age", userFromDB.getAge());
        req.setAttribute("gender", userFromDB.getGender());
        req.setAttribute("role", userFromDB.getRole());

        getServletContext().getRequestDispatcher("/InfoUserPage.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("loggedUser");

        req.setAttribute("id", user.getId());
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("secondName", user.getSecondName());
        req.setAttribute("userName", user.getUserName());
        req.setAttribute("age", user.getAge());
        req.setAttribute("gender", user.getGender());
        req.setAttribute("role", user.getRole());

        getServletContext().getRequestDispatcher("/InfoUserPage.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}