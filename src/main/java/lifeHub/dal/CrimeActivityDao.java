package lifeHub.dal;

import lifeHub.model.*;

// TODO -- be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying CrimeActivity table in your MySQL
 * instance. This is used to store {@link CrimeActivity} into your MySQL instance and retrieve 
 * {@link CrimeActivity} from MySQL instance.
 */
public class CrimeActivityDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
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

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the CrimeActivity instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public CrimeActivity create(CrimeActivity crimeActivity) throws SQLException {
		String insertCrimeActivity = 
				"INSERT INTO CrimeActivity(CaseId,City,State,NeighborZipId,CrimeName) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrimeActivity);

			insertStmt.setInt(1, crimeActivity.getCaseId());
			insertStmt.setString(2, crimeActivity.getCity());
			insertStmt.setString(3, crimeActivity.getState());
			insertStmt.setInt(4, crimeActivity.getNeighborZipId());
			insertStmt.setString(5, crimeActivity.getCrimeName());
		
			insertStmt.executeUpdate();
			
			return crimeActivity;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	
	
	
	// TODO -- READ and UPDATE methods here!!!
	
	
	
	/**
	 * Delete the CrimeActivety instance.
	 * This runs a DELETE statement.
	 */
	public CrimeActivity delete(CrimeActivity crimeActivity) throws SQLException {
		String deleteCrimeActivity = 
				"DELETE FROM CrimeActivity WHERE CaseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCrimeActivity);
			deleteStmt.setInt(1, crimeActivity.getCaseId());
			deleteStmt.executeUpdate();
//			super.delete(crimeActivity); // TODO -- this may or many NOT be needed based on instance

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
