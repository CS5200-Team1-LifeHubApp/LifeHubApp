package lifeHub.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import lifeHub.dal.UserDao;
import lifeHub.model.User;

public class UserUpdate extends HttpServlet {

    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user information from the request parameters
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String passwordHash = request.getParameter("passwordHash");

        try {
            // Retrieve the user from the database
            User user = userDao.getUserById(userId);
            
            // Update user information
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPasswordHash(passwordHash);
            
            // Update user in the database
            userDao.updateUser(user);

            // Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (SQLException e) {
            // Handle errors
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
