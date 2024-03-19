package lifeHub.model;

import java.sql.Timestamp;

//CREATE TABLE Bookmark (
//	    BookmarkId INT AUTO_INCREMENT PRIMARY KEY,
//	    Created TIMESTAMP,
//	    UserId INT,
//	    Description TEXT,
//	    NeighborZipId INT,
//	    FOREIGN KEY (UserId) REFERENCES Users(UserId),
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class Bookmark {
    private int bookmarkId;
    private Timestamp created;
    private int userId;
    private String description;
    private int neighborZipId;
	
    // Constructor  
    public Bookmark(int bookmarkId, Timestamp created, int userId, String description, int neighborZipId) {
		super();
		this.bookmarkId = bookmarkId;
		this.created = created;
		this.userId = userId;
		this.description = description;
		this.neighborZipId = neighborZipId;
	}

    // Getters and Setters
	public int getBookmarkId() {
		return bookmarkId;
	}

	public void setBookmarkId(int bookmarkId) {
		this.bookmarkId = bookmarkId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNeighborZipId() {
		return neighborZipId;
	}

	public void setNeighborZipId(int neighborZipId) {
		neighborZipId = neighborZipId;
	}

    // toString
	@Override
	public String toString() {
		return "Bookmark [bookmarkId=" + bookmarkId + ", created=" + created + ", userId=" + userId + ", description="
				+ description + ", NeighborZipId=" + neighborZipId + "]";
	}
}
