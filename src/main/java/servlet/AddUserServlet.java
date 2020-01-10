package servlet;

import model.User;
import service.UserService;

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
        getServletContext().getRequestDispatcher("/AddUserPage.jsp").forward(req, resp);
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
        String role = req.getParameter("role");

        if (!password.equals(confirmPassword)) {
            req.setAttribute("message", "Error: Entered passwords do not match!");
            getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (firstName.equals("") ||
                secondName.equals("") ||
                userName.equals("") ||
                password.equals("") ||
                age.equals("") ||
                gender == null ||
                role == null) {
            req.setAttribute("message", "Error: All fields are required!");
            getServletContext().getRequestDispatcher("/ResultPage.jsp").forward(req, resp);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {

            User newUser = new User(firstName, secondName, userName, password, Long.parseLong(age), gender, role);

            String result = UserService.getInstance().addUser(newUser);
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