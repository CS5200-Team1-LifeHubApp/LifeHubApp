package lifeHub.dal;

import java.sql.*;
import lifeHub.model.CrimeActivity;

public class CrimeActivityDao {
    protected ConnectionManager connectionManager;

    private static CrimeActivityDao instance = null;
    protected CrimeActivityDao() {
        connectionManager = new ConnectionManager();
    }
    public static CrimeActivityDao getInstance() {
        if(instance == null) {
            instance = new CrimeActivityDao();
        }
        return instance;
    }

    // CREATE
    public CrimeActivity create(CrimeActivity crimeActivity) throws SQLException {
        String insertCrimeActivity = "INSERT INTO CrimeActivity(CaseId, City, State, NeighborZipId, CrimeName) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertCrimeActivity);
            // Set parameters
            insertStmt.setString(1, crimeActivity.getCaseId());
            insertStmt.setString(2, crimeActivity.getCity());
            insertStmt.setString(3, crimeActivity.getState());
            insertStmt.setInt(4, crimeActivity.getNeighborZipId());
            insertStmt.setString(5, crimeActivity.getCrimeName());
            insertStmt.executeUpdate();
            
            // No auto-generated key to retrieve since CaseId is the primary key and provided by the caller
            return crimeActivity;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    // READ
    public CrimeActivity getCrimeActivityByCaseId(String caseId) throws SQLException {
        String selectCrimeActivity = "SELECT CaseId, City, State, NeighborZipId, CrimeName FROM CrimeActivity WHERE CaseId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectCrimeActivity);
            selectStmt.setString(1, caseId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                String city = results.getString("City");
                String state = results.getString("State");
                int neighborZipId = results.getInt("NeighborZipId");
                String crimeName = results.getString("CrimeName");

                CrimeActivity crimeActivity = new CrimeActivity(caseId, city, state, neighborZipId, crimeName);
                return crimeActivity;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    // UPDATE
    // Update the crime's details except the case ID
    public CrimeActivity update(CrimeActivity crimeActivity) throws SQLException {
        String updateCrimeActivity = "UPDATE CrimeActivity SET City=?, State=?, NeighborZipId=?, CrimeName=? WHERE CaseId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateCrimeActivity);
            // Set parameters for the update
            updateStmt.setString(1, crimeActivity.getCity());
            updateStmt.setString(2, crimeActivity.getState());
            updateStmt.setInt(3, crimeActivity.getNeighborZipId());
            updateStmt.setString(4, crimeActivity.getCrimeName());
            updateStmt.setString(5, crimeActivity.getCaseId());
            
            updateStmt.executeUpdate();
            
            // Return the updated CrimeActivity
            return crimeActivity;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    // DELETE
    public CrimeActivity delete(CrimeActivity crimeActivity) throws SQLException {
        String deleteCrimeActivity = "DELETE FROM CrimeActivity WHERE CaseId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteCrimeActivity);
            deleteStmt.setString(1, crimeActivity.getCaseId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
