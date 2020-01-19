package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/info/*")
public class InfoUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        User changedUser = UserService.getInstance().getUserByID(id);

        req.setAttribute("id", id);
        req.setAttribute("firstName", changedUser.getFirstName());
        req.setAttribute("secondName", changedUser.getSecondName());
        req.setAttribute("userName", changedUser.getUserName());
        req.setAttribute("age", changedUser.getAge());
        req.setAttribute("gender", changedUser.getGender());
        req.setAttribute("role", changedUser.getRole());

        if (changedUser.getGender().equals("male")) {
            req.setAttribute("agender", "female");
        } else {
            req.setAttribute("agender", "male");
        }

        if (changedUser.getRole().equals("user")) {
            req.setAttribute("arole", "admin");
        } else {
            req.setAttribute("arole", "user");
        }

        getServletContext().getRequestDispatcher("/InfoUserPage.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
