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
 * Data access object (DAO) class to interact with the underlying Bookmark table in your MySQL
 * instance. This is used to store {@link Bookmark} into your MySQL instance and retrieve 
 * {@link Bookmark} from MySQL instance.
 */
public class BookmarkDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static BookmarkDao instance = null;
	protected BookmarkDao() {
		connectionManager = new ConnectionManager();
	}
	public static BookmarkDao getInstance() {
		if(instance == null) {
			instance = new BookmarkDao();
		}
		return instance;
	}

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Bookmark instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Bookmark create(Bookmark bookmark) throws SQLException {
		String insertBookmark = 
				"INSERT INTO CrimeActivity(BookmarkId,Created,UserId,Description,NeighborZipId) VALUES(?,?,?,?,?);";
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
	
	
	
	
	// TODO -- READ and UPDATE methods here!!!
	
	
	
	/**
	 * Delete the Bookmark instance.
	 * This runs a DELETE statement.
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
//			super.delete(bookmark); // TODO -- this may or many NOT be needed based on instance

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
