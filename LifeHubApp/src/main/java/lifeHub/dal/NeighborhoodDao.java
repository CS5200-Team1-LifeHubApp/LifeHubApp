package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Neighborhood;

public class NeighborhoodDao {
    protected ConnectionManager connectionManager;

    private static NeighborhoodDao instance = null;
    protected NeighborhoodDao() {
        connectionManager = new ConnectionManager();
    }
    public static NeighborhoodDao getInstance() {
        if(instance == null) {
            instance = new NeighborhoodDao();
        }
        return instance;
    }

    // CREATE
    public Neighborhood create(Neighborhood neighborhood) throws SQLException {
        String insertNeighborhood = "INSERT INTO NeighborhoodbyZip(NeighborZipId, City) VALUES(?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertNeighborhood);
            // Set parameters
            insertStmt.setInt(1, neighborhood.getNeighborZipId());
            insertStmt.setString(2, neighborhood.getCity());
            insertStmt.executeUpdate();

            // Since NeighborZipId is a PK and expected to be unique, no need to retrieve auto-generated keys here
            return neighborhood;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    // READ
    public Neighborhood getNeighborhoodByZipId(int neighborZipId) throws SQLException {
        String selectNeighborhood = "SELECT NeighborZipId, City FROM NeighborhoodbyZip WHERE NeighborZipId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectNeighborhood);
            selectStmt.setInt(1, neighborZipId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                String city = results.getString("City");
                Neighborhood neighborhood = new Neighborhood(neighborZipId, city);
                return neighborhood;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE
    public Neighborhood update(Neighborhood neighborhood) throws SQLException {
        String updateNeighborhood = "UPDATE NeighborhoodbyZip SET City=? WHERE NeighborZipId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateNeighborhood);
            updateStmt.setString(1, neighborhood.getCity());
            updateStmt.setInt(2, neighborhood.getNeighborZipId());
            
            updateStmt.executeUpdate();
            
            // Return the updated neighborhood
            return neighborhood;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    // DELETE
    public Neighborhood delete(Neighborhood neighborhood) throws SQLException {
        String deleteNeighborhood = "DELETE FROM NeighborhoodbyZip WHERE NeighborZipId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteNeighborhood);
            deleteStmt.setInt(1, neighborhood.getNeighborZipId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
