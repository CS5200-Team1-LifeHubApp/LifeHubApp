package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Bookmark;

public class BookmarkDao {
    protected ConnectionManager connectionManager;

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

    //CREATE
    public Bookmark create(Bookmark bookmark) throws SQLException {
        String insertBookmark = "INSERT INTO Bookmark(Created, UserId, Description, NeighborZipId) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertBookmark, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            insertStmt.setTimestamp(1, bookmark.getCreated());
            insertStmt.setInt(2, bookmark.getUserId());
            insertStmt.setString(3, bookmark.getDescription());
            insertStmt.setInt(4, bookmark.getNeighborZipId());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int bookmarkId = -1;
            if(resultKey.next()) {
                bookmarkId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            bookmark.setBookmarkId(bookmarkId);
            return bookmark;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }

    public Bookmark getBookmarkById(int bookmarkId) throws SQLException {
        String selectBookmark = "SELECT BookmarkId, Created, UserId, Description, NeighborZipId FROM Bookmark WHERE BookmarkId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectBookmark);
            selectStmt.setInt(1, bookmarkId);

            results = selectStmt.executeQuery();
            if(results.next()) {
                Timestamp created = results.getTimestamp("Created");
                int userId = results.getInt("UserId");
                String description = results.getString("Description");
                int neighborZipId = results.getInt("NeighborZipId");

                Bookmark bookmark = new Bookmark(bookmarkId, created, userId, description, neighborZipId);
                return bookmark;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }

    //UPDATE
    public Bookmark update(Bookmark bookmark) throws SQLException {
        String updateBookmark = "UPDATE Bookmark SET Description=?, NeighborZipId=? WHERE BookmarkId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateBookmark);
            // Set parameters for the update
            updateStmt.setString(1, bookmark.getDescription());
            updateStmt.setInt(2, bookmark.getNeighborZipId());
            updateStmt.setInt(3, bookmark.getBookmarkId());
            
            updateStmt.executeUpdate();
            
            // Return the updated bookmark
            return bookmark;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }


    //DELETE
    public Bookmark delete(Bookmark bookmark) throws SQLException {
        String deleteBookmark = "DELETE FROM Bookmark WHERE BookmarkId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteBookmark);
            deleteStmt.setInt(1, bookmark.getBookmarkId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
