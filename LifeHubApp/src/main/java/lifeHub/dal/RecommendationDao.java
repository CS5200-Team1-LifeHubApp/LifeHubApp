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
 * Data access object (DAO) class to interact with the underlying Recommendation table in your MySQL
 * instance. This is used to store {@link Recommendation} into your MySQL instance and retrieve 
 * {@link Recommendation} from MySQL instance.
 */
public class RecommendationDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
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

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Recommendation instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Recommendation create(Recommendation recommendation) throws SQLException {
		String insertRecommendation = 
				"INSERT INTO Recommendation(RecommendationId,UserId,NeighborZipId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendation);

			insertStmt.setInt(1, recommendation.getRecommendationId());
			insertStmt.setInt(2, recommendation.getUserId());
			insertStmt.setInt(3, recommendation.getNeighborZipId());

			insertStmt.executeUpdate();
			
			return recommendation;
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
	public Recommendation delete(Recommendation recommendation) throws SQLException {
		String deleteRecommendation = 
				"DELETE FROM Recommendation WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecommendation);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();
//			super.delete(recommendation); // TODO -- this may or many NOT be needed based on instance

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
