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

@WebServlet("/add/*")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo().contains("HQL")) {
            req.setAttribute("QL", "HQL");
            getServletContext().getRequestDispatcher("/AddUserPage.jsp").forward(req, resp);
        }

        if (req.getPathInfo().contains("SQL")) {
            req.setAttribute("QL", "SQL");
            getServletContext().getRequestDispatcher("/AddUserPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String age = req.getParameter("age");
        String gender = req.getParameter("gender");

        if (req.getPathInfo().contains("HQL")) {
            if (!password.equals(confirmPassword)) {
                req.setAttribute("message", "Error: Entered passwords do not match!");
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else if (firstName.equals("") ||
                    secondName.equals("") ||
                    userName.equals("") ||
                    password.equals("") ||
                    age.equals("") ||
                    gender == null) {
                req.setAttribute("message", "Error: All fields are required!");
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {

                User newUser = new User(firstName, secondName, userName, password, Long.parseLong(age), gender);

                String result = UserServiceHQL.getInstance().addUser(newUser);
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

        if (req.getPathInfo().contains("SQL")) {
            if (!password.equals(confirmPassword)) {
                req.setAttribute("message", "Error: Entered passwords do not match!");
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else if (firstName.equals("") ||
                    secondName.equals("") ||
                    userName.equals("") ||
                    password.equals("") ||
                    age.equals("") ||
                    gender == null) {
                req.setAttribute("message", "Error: All fields are required!");
                getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {

                User newUser = new User(firstName, secondName, userName, password, Long.parseLong(age), gender);

                String result = new UserServiceSQL().addUser(newUser);
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
}
