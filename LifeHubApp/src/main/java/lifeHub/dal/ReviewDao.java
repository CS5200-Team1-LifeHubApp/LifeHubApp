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
 * Data access object (DAO) class to interact with the underlying Review table in your MySQL
 * instance. This is used to store {@link Review} into your MySQL instance and retrieve 
 * {@link Review} from MySQL instance.
 */
public class ReviewDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
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

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Review instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Review create(Review review) throws SQLException {
		String insertReview = 
				"INSERT INTO Review(ReviewId,Created,Content,Rating,UserId,NeighborZipId) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);

			insertStmt.setInt(1, review.getReviewId());
			insertStmt.setTimestamp(2, review.getCreated());
			insertStmt.setString(3, review.getContent());
			insertStmt.setFloat(4, review.getRating());
			insertStmt.setInt(5, review.getUserId());
			insertStmt.setInt(6, review.getNeighborZipId());
		
			insertStmt.executeUpdate();
			
			return review;
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
	 * Delete the Review instance.
	 * This runs a DELETE statement.
	 */
	public Review delete(Review review) throws SQLException {
		String deleteReview = 
				"DELETE FROM Review WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
