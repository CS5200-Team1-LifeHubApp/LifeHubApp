package lifeHub.dal;

import lifeHub.model.*;

// TODO -- be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data access object (DAO) class to interact with the underlying Bookmark table in your MySQL
 * instance. This is used to store {@link Bookmark} into your MySQL instance and retrieve
 * {@link Bookmark} from MySQL instance.
 */
public class BookmarkDao {
	protected ConnectionManager connectionManager = new ConnectionManager();

	// Single pattern: instantiation is limited to one object.
	private static BookmarkDao instance = null;
	protected BookmarkDao() {
	}
	public static BookmarkDao getInstance() {
		if(instance == null) {
			instance = new BookmarkDao();
		}
		return instance;
	}
	/**
	 * Save the Bookmark instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Bookmark create(Bookmark bookmark) throws SQLException {
		String insertBookmark =
				"INSERT INTO Bookmark(BookmarkId,Created,UserId,Description,NeighborZipId) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBookmark);

			insertStmt.setInt(1, bookmark.getBookmarkId());
			insertStmt.setTimestamp(2, bookmark.getCreated());
			insertStmt.setInt(3, bookmark.getUserId());
			insertStmt.setString(4, bookmark.getDescription());
			insertStmt.setInt(5, bookmark.getNeighborZipId());

			insertStmt.executeUpdate();

			return bookmark;
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
	public List<Bookmark> getBookmarkById(int bookmarkId) throws SQLException {
		List<Bookmark> bookmarks = new ArrayList(); // Corrected instantiation with diamond operator <>
		String selectBookmark = "SELECT BookmarkId, Created, UserId, Description, NeighborZipId FROM Bookmark WHERE BookmarkId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null; // Added ResultSet declaration
		try {
			connection = this.connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBookmark); // Corrected SQL statement variable name
			selectStmt.setInt(1, bookmarkId); // Corrected parameter index and variable name
			results = selectStmt.executeQuery();

			while (results.next()) {
				Bookmark bookmark = new Bookmark(
						results.getInt("BookmarkId"),
						results.getTimestamp("Created"),
						results.getInt("UserId"),
						results.getString("Description"),
						results.getInt("NeighborZipId")
				);
				bookmarks.add(bookmark);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// Close resources in finally block to ensure they are always closed
			try {
				if (results != null) {
					results.close();
				}
				if (selectStmt != null) {
					selectStmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				// Log or handle the exception accordingly
			}
		}
		return bookmarks;
	}

	public Bookmark updateBookmark(Bookmark bookmark) throws SQLException {
		String updateQuery = "UPDATE Bookmark SET Created=?, UserId=?, Description=?, NeighborZipId=? WHERE BookmarkId=?;";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = connectionManager.getConnection();
			statement = connection.prepareStatement(updateQuery);
			statement.setTimestamp(1, bookmark.getCreated());
			statement.setInt(2, bookmark.getUserId());
			statement.setString(3, bookmark.getDescription());
			statement.setInt(4, bookmark.getNeighborZipId());
			statement.setInt(5, bookmark.getBookmarkId());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated == 0) {
				throw new SQLException("Failed to update the bookmark. No rows affected.");
			}
			return bookmark;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// Handle the exception if needed
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// Handle the exception if needed
				}
			}
		}
	}

	/**
	 * Delete the Bookmark instance.
	 * This runs a DELETE statement.
	 *
	 * @return
	 */
	public Bookmark delete(Bookmark bookmark) throws SQLException {
		String deleteBookmark =
				"DELETE FROM Bookmark WHERE BookmarkId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBookmark);
			deleteStmt.setInt(1, bookmark.getBookmarkId());
			deleteStmt.executeUpdate();
//        super.delete(bookmark); // TODO -- this may or many NOT be needed based on instance

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