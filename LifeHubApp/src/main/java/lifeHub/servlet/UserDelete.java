package lifeHub.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import lifeHub.dal.UserDao;

public class UserDelete extends HttpServlet {

    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get user ID from request parameter
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            // Delete user from the database
            userDao.deleteUser(userId);

            // Redirect to a success page
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (SQLException e) {
            // Handle errors
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
