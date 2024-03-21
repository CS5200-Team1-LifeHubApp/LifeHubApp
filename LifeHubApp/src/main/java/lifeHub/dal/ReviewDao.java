package lifeHub.dal;

import lifeHub.model.*;

// TODO -- be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;


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


	/**
	 * Save the Review instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	// CREATE
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


	// READ
	public Review getReviewById(int reviewId) throws SQLException {
		String selectReview = "SELECT Created, Content, Rating, UserId, NeighborZipId FROM Review WHERE ReviewId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				Timestamp created = results.getTimestamp("Created");
				String content = results.getString("Content");
				float rating = results.getFloat("Rating");
				int userId = results.getInt("UserId");
				int neighborZipId = results.getInt("NeighborZipId");

				Review review = new Review(reviewId, created, content, rating, userId, neighborZipId);

				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}

	// UPDATE
	public Review updateReview(Review review) throws SQLException {
		String updateReview = "UPDATE Review SET Created = ?, Content = ?, Rating = ?, UserId = ?, NeighborZipId = ? WHERE ReviewId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);

			updateStmt.setTimestamp(1, review.getCreated());
			updateStmt.setString(2, review.getContent());
			updateStmt.setFloat(3, review.getRating());
			updateStmt.setInt(4, review.getUserId());
			updateStmt.setInt(5, review.getNeighborZipId());
			updateStmt.setInt(6, review.getReviewId());

			updateStmt.executeUpdate();

			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}


	/**
	 * Delete the Review instance.
	 * This runs a DELETE statement.
	 */
	// DELETE
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
