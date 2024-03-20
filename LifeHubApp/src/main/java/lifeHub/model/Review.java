package lifeHub.model;

import java.sql.Timestamp;

//CREATE TABLE Review (
//	    ReviewId INT AUTO_INCREMENT PRIMARY KEY,
//	    Created TIMESTAMP,
//	    Content TEXT,
//	    Rating DECIMAL(3, 2),
//	    UserId INT,
//	    NeighborZipId INT,
//	    FOREIGN KEY (UserId) REFERENCES Users(UserId),
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class Review {
    private int reviewId;
    private Timestamp created;
    private String content;
    private double rating;
    private int userId;
    private int neighborZipId;
	
    // Constructor
    public Review(int reviewId, Timestamp created, String content, double d, int userId, int neighborZipId) {
		super();
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.rating = d;
		this.userId = userId;
		this.neighborZipId = neighborZipId;
	}

    // Getters and Setters
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNeighborZipId() {
		return neighborZipId;
	}

	public void setNeighborZipId(int neighborZipId) {
		this.neighborZipId = neighborZipId;
	}

    // toString
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", created=" + created + ", content=" + content + ", rating=" + rating
				+ ", userId=" + userId + ", neighborZipId=" + neighborZipId + "]";
	}
}
