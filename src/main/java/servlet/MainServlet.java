package servlet;

import model.User;
import service.UserServiceHQL;
import service.UserServiceSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> allUsersHQL = UserServiceHQL.getInstance().getAllUsers();
        List<User> allUsersSQL = new UserServiceSQL().getAllUsers();

        req.setAttribute("allUsersHQL", allUsersHQL);
        req.setAttribute("allUsersSQL", allUsersSQL);
        getServletContext().getRequestDispatcher("/MainPage.jsp").forward(req, resp);
    }
}