package lifeHub.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import lifeHub.dal.UserDao;
import lifeHub.model.User;

public class FindUser extends HttpServlet {

    protected UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = UserDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the user ID from the request parameters
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            // Retrieve the user from the database
            User user = userDao.getUserById(userId);
            
            // Set the user as an attribute in the request
            request.setAttribute("user", user);

            // Forward to a JSP page to display user information
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            // Handle errors
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
