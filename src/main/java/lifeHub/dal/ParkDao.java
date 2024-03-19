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
 * Data access object (DAO) class to interact with the underlying Park table in your MySQL
 * instance. This is used to store {@link Park} into your MySQL instance and retrieve 
 * {@link Park} from MySQL instance.
 */
public class ParkDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
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

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Park instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Park create(Park park) throws SQLException {
		String insertCrimeActivity = 
				"INSERT INTO Park(ParkId,NeighborZipId,Name,FeatureId,Hours,FeatureDesc) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrimeActivity);

			insertStmt.setInt(1, park.getParkId());
			insertStmt.setInt(2, park.getNeighborZipId());
			insertStmt.setString(3, park.getName());
			insertStmt.setInt(4, park.getFeatureId());
			insertStmt.setString(5, park.getHours());
			insertStmt.setString(6, park.getFeatureDesc());
		
			insertStmt.executeUpdate();
			
			return park;
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
	 * Delete the Park instance.
	 * This runs a DELETE statement.
	 */
	public Park delete(Park park) throws SQLException {
		String deletePark = 
				"DELETE FROM Park WHERE ParkId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePark);
			deleteStmt.setInt(1, park.getParkId());
			deleteStmt.executeUpdate();
//			super.delete(park); // TODO -- this may or many NOT be needed based on instance

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
