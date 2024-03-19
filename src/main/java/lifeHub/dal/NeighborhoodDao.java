package lifeHub.dal;

import lifeHub.model.*;

// TODO: be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Neighborhood table in your MySQL
 * instance. This is used to store {@link Neighborhood} into your MySQL instance and retrieve 
 * {@link Neighborhood} from MySQL instance.
 */
public class NeighborhoodDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
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

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Neighborhood instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Neighborhood create(Neighborhood neighborhood) throws SQLException {
		String insertNeighborhood = 
				"INSERT INTO Neighborhood(NeighborZipId,City) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood);

			insertStmt.setInt(1, neighborhood.getNeighborZipId());
			insertStmt.setString(2, neighborhood.getCity());

			insertStmt.executeUpdate();
			
			return neighborhood;
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
	 * Delete the Neighborhood instance.
	 * This runs a DELETE statement.
	 */
	public Neighborhood delete(Neighborhood neighborhood) throws SQLException {
		String deleteNeighborhood = 
				"DELETE FROM Neighborhood WHERE NeighborZipId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setInt(1, neighborhood.getNeighborZipId());
			deleteStmt.executeUpdate();
//			super.delete(neighborhood); // TODO -- this may or many NOT be needed based on instance

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
