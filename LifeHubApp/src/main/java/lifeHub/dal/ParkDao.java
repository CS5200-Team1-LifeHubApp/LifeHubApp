package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Park;

public class ParkDao {
    protected ConnectionManager connectionManager;

    private static ParkDao instance = null;
    protected ParkDao() {
        connectionManager = new ConnectionManager();
    }
    public static ParkDao getInstance() {
        if(instance == null) {
            instance = new ParkDao();
        }
        return instance;
    }

    // CREATE
    public Park create(Park park) throws SQLException {
        String insertPark = "INSERT INTO Park(ParkId, NeighborZipId, Name, FeatureId, Hours, FeatureDesc) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPark);
            // Set parameters
            insertStmt.setInt(1, park.getParkId());
            insertStmt.setInt(2, park.getNeighborZipId());
            insertStmt.setString(3, park.getName());
            insertStmt.setInt(4, park.getFeatureId());
            insertStmt.setString(5, park.getHours());
            insertStmt.setString(6, park.getFeatureDesc());
            insertStmt.executeUpdate();
            
            // Normally, you would return a new object or update the existing one with any new data (e.g., auto-generated ID)
            return park;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    // READ
    public Park getParkById(int parkId, int featureId) throws SQLException {
        String selectPark = "SELECT ParkId, NeighborZipId, Name, FeatureId, Hours, FeatureDesc FROM Park WHERE ParkId=? AND FeatureId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPark);
            selectStmt.setInt(1, parkId);
            selectStmt.setInt(2, featureId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                int neighborZipId = results.getInt("NeighborZipId");
                String name = results.getString("Name");
                int retrievedFeatureId = results.getInt("FeatureId");
                String hours = results.getString("Hours");
                String featureDesc = results.getString("FeatureDesc");
                
                Park park = new Park(parkId, neighborZipId, name, retrievedFeatureId, hours, featureDesc);
                return park;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE
    public Park update(Park park) throws SQLException {
        String updatePark = "UPDATE Park SET NeighborZipId=?, Name=?, Hours=?, FeatureDesc=? WHERE ParkId=? AND FeatureId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePark);
            // Set parameters for the update
            updateStmt.setInt(1, park.getNeighborZipId());
            updateStmt.setString(2, park.getName());
            updateStmt.setString(3, park.getHours());
            updateStmt.setString(4, park.getFeatureDesc());
            updateStmt.setInt(5, park.getParkId());
            updateStmt.setInt(6, park.getFeatureId());

            updateStmt.executeUpdate();
            
            // Return the updated park
            return park;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    // DELETE
    public Park delete(Park park) throws SQLException {
        String deletePark = "DELETE FROM Park WHERE ParkId=? AND FeatureId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePark);
            deleteStmt.setInt(1, park.getParkId());
            deleteStmt.setInt(2, park.getFeatureId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}

