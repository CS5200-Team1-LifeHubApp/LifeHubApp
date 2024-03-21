package lifeHub.model;

//CREATE TABLE Recommendation (
//	    RecommendationId INT AUTO_INCREMENT PRIMARY KEY,
//	    UserId INT,
//	    NeighborZipId INT,
//	    FOREIGN KEY (UserId) REFERENCES Users(UserId),
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class Recommendation {
    private int recommendationId;
    private int userId;
    private int neighborZipId;
	
    // Constructor
    public Recommendation(int recommendationId, int userId, int neighborZipId) {
		super();
		this.recommendationId = recommendationId;
		this.userId = userId;
		this.neighborZipId = neighborZipId;
	}

    // Getters and Setters
	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
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
		neighborZipId = neighborZipId;
	}

    // toString
	@Override
	public String toString() {
		return "Recommendation [recommendationId=" + recommendationId + ", userId=" + userId + ", NeighborZipId="
				+ neighborZipId + "]";
	}  
}
