<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lifeHub.model.User" %>
<%@ page import="lifeHub.dal.UserDao" %>
<%@ page import="java.sql.SQLException" %>

<%
    // Retrieve user ID from request parameter
    int userId = Integer.parseInt(request.getParameter("userId"));

    // Create a UserDao instance
    UserDao userDao = UserDao.getInstance();

    // Try to find the user by ID
    try {
        User user = userDao.getUserById(userId);
        if (user != null) {
            // User found, display user information
%>
            <h1>User Found</h1>
            <p>User ID: <%= user.getUserId() %></p>
            <p>Username: <%= user.getUserName() %></p>
            <p>First Name: <%= user.getFirstName() %></p>
            <p>Last Name: <%= user.getLastName() %></p>
            <p>Email: <%= user.getEmail() %></p>
<%
        } else {
            // User not found
%>
            <h1>User Not Found</h1>
<%
        }
    } catch (SQLException e) {
        // Error occurred during user retrieval
%>
        <h1>Error Finding User</h1>
        <p><%= e.getMessage() %></p>
<%
    }
%>
