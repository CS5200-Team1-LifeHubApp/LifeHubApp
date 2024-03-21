<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="lifeHub.model.User" %>
<%@ page import="lifeHub.dal.UserDao" %>

<%
    // Retrieve form data
    String userName = request.getParameter("userName");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Hash the password
    String passwordHash = ""; // Implement password hashing here

    // Create a new User object
    User user = new User(0, userName, firstName, lastName, email, passwordHash);

    // Create a UserDao instance
    UserDao userDao = UserDao.getInstance();

    // Try to create the user
    try {
        userDao.createUser(user);
        // User created successfully
        out.println("<h1>User created successfully!</h1>");
        out.println("<p>User ID: " + user.getUserId() + "</p>");
    } catch (Exception e) {
        // Error occurred during user creation
        out.println("<h1>Error creating user</h1>");
        out.println("<p>" + e.getMessage() + "</p>");
    }
%>
