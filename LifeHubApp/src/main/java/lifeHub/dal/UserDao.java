package lifeHub.dal;

import java.sql.*;
import lifeHub.model.User;

public class UserDao {
    protected ConnectionManager connectionManager;

    private static UserDao instance = null;
    protected UserDao() {
        connectionManager = new ConnectionManager();
    }
    public static UserDao getInstance() {
        if(instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    //CREATE
    public User createUser(User user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName, FirstName, LastName, Email, PasswordHash) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getFirstName());
            insertStmt.setString(3, user.getLastName());
            insertStmt.setString(4, user.getEmail());
            insertStmt.setString(5, user.getPasswordHash());
            insertStmt.executeUpdate();
            
            // Retrieve the auto-generated key
            resultKey = insertStmt.getGeneratedKeys();
            int userId = -1;
            if (resultKey.next()) {
                userId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            user.setUserId(userId);
            return user;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    public User getUserById(int userId) throws SQLException {
        String selectUser = "SELECT UserName, FirstName, LastName, Email, PasswordHash FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setInt(1, userId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String userName = results.getString("UserName");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String email = results.getString("Email");
                String passwordHash = results.getString("PasswordHash");

                User user = new User(userId, userName, firstName, lastName, email, passwordHash);
                return user;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    //UPDATE
    public User updateUser(User user) throws SQLException {
        String updateUser = "UPDATE Users SET UserName=?, FirstName=?, LastName=?, Email=?, PasswordHash=? WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateUser);
            // Set parameters for the update based on the User object's current state
            updateStmt.setString(1, user.getUserName());
            updateStmt.setString(2, user.getFirstName());
            updateStmt.setString(3, user.getLastName());
            updateStmt.setString(4, user.getEmail());
            updateStmt.setString(5, user.getPasswordHash());
            updateStmt.setInt(6, user.getUserId());
            
            updateStmt.executeUpdate();
            
            // Return the updated user
            return user;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    //DELETE
    public void deleteUser(int userId) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setInt(1, userId);
            deleteStmt.executeUpdate();
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
