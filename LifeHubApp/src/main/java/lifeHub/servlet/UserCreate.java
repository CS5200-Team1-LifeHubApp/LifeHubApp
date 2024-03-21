package lifeHub.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import lifeHub.dal.UserDao;
import lifeHub.model.User;

public class UserCreate extends HttpServlet {

    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters from the request
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String passwordHash = request.getParameter("passwordHash");

        try {
            // Create a new User object
            User user = new User(0, userName, firstName, lastName, email, passwordHash);

            // Call UserDao to insert the user into the database
            userDao.createUser(user);

            // Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (SQLException e) {
            // Handle errors
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
