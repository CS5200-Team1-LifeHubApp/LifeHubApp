package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Recommendation;

public class RecommendationDao {
    protected ConnectionManager connectionManager;

    private static RecommendationDao instance = null;
    protected RecommendationDao() {
        connectionManager = new ConnectionManager();
    }
    public static RecommendationDao getInstance() {
        if(instance == null) {
            instance = new RecommendationDao();
        }
        return instance;
    }

    // CREATE
    public Recommendation create(Recommendation recommendation) throws SQLException {
        String insertRecommendation = "INSERT INTO Recommendation(UserId, NeighborZipId) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            insertStmt.setInt(1, recommendation.getUserId());
            insertStmt.setInt(2, recommendation.getNeighborZipId());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int recommendationId = -1;
            if(resultKey.next()) {
                recommendationId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            recommendation.setRecommendationId(recommendationId);
            return recommendation;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    // READ (example method, adjust according to your needs)
    public Recommendation getRecommendationById(int recommendationId) throws SQLException {
        String selectRecommendation = "SELECT RecommendationId, UserId, NeighborZipId FROM Recommendation WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRecommendation);
            selectStmt.setInt(1, recommendationId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int userId = results.getInt("UserId");
                int neighborZipId = results.getInt("NeighborZipId");
                
                Recommendation recommendation = new Recommendation(recommendationId, userId, neighborZipId);
                return recommendation;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE 
    public Recommendation update(Recommendation recommendation) throws SQLException {
        String updateRecommendation = "UPDATE Recommendation SET UserId=?, NeighborZipId=? WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRecommendation);
            // Set parameters for the update
            updateStmt.setInt(1, recommendation.getUserId());
            updateStmt.setInt(2, recommendation.getNeighborZipId());
            updateStmt.setInt(3, recommendation.getRecommendationId());

            updateStmt.executeUpdate();
            
            // Return the updated recommendation
            return recommendation;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }


    // DELETE
    public boolean delete(Recommendation recommendation) throws SQLException {
        String deleteRecommendation = "DELETE FROM Recommendation WHERE RecommendationId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRecommendation);
            deleteStmt.setInt(1, recommendation.getRecommendationId());
            int affectedRows = deleteStmt.executeUpdate();

            // Return true if a row was deleted
            return affectedRows > 0;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
