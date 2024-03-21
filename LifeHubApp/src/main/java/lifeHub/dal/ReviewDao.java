package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Review;

public class ReviewDao {
    protected ConnectionManager connectionManager;

    private static ReviewDao instance = null;
    protected ReviewDao() {
        connectionManager = new ConnectionManager();
    }
    public static ReviewDao getInstance() {
        if(instance == null) {
            instance = new ReviewDao();
        }
        return instance;
    }

    // CREATE
    public Review create(Review review) throws SQLException {
        String insertReview = "INSERT INTO Review(Created, Content, Rating, UserId, NeighborZipId) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setTimestamp(1, review.getCreated());
            insertStmt.setString(2, review.getContent());
            insertStmt.setDouble(3, review.getRating());
            insertStmt.setInt(4, review.getUserId());
            insertStmt.setInt(5, review.getNeighborZipId());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if(resultKey.next()) {
                reviewId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);
            return review;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    // READ
    public Review getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT ReviewId, Created, Content, Rating, UserId, NeighborZipId FROM Review WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                Timestamp created = results.getTimestamp("Created");
                String content = results.getString("Content");
                double rating = results.getDouble("Rating");
                int userId = results.getInt("UserId");
                int neighborZipId = results.getInt("NeighborZipId");
                
                Review review = new Review(reviewId, created, content, rating, userId, neighborZipId);
                return review;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE
    public Review update(Review review) throws SQLException {
        String updateReview = "UPDATE Review SET Content=?, Rating=? WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            // Set parameters for the update based on the Review object's current state
            updateStmt.setString(1, review.getContent());
            updateStmt.setDouble(2, review.getRating());
            updateStmt.setInt(3, review.getReviewId());

            updateStmt.executeUpdate();

            // Return the updated review
            return review;
        } finally {
            if (updateStmt != null) updateStmt.close();
            if (connection != null) connection.close();
        }
    }

    // DELETE
    public Review delete(Review review) throws SQLException {
        String deleteReview = "DELETE FROM Review WHERE ReviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
