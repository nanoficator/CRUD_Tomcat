package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit/*")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        User changedUser = UserService.getInstance().getUserByID(id);

        req.setAttribute("id", id);
        req.setAttribute("firstName", changedUser.getFirstName());
        req.setAttribute("secondName", changedUser.getSecondName());
        req.setAttribute("userName", changedUser.getUserName());
        req.setAttribute("password", changedUser.getPassword());
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

        getServletContext().getRequestDispatcher("/EditUserPage.jsp").forward(req, resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
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

            User changedUser = new User(id, firstName, secondName, userName, password, Long.parseLong(age), gender, role);

            String result = UserService.getInstance().changeUser(changedUser);
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