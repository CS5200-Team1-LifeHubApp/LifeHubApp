package lifeHub.model;

public class Recommendation {
    private int recommendationId;
    private int userId;
    private int neighborZipId;

    // Constructor
    public Recommendation(int recommendationId, int userId, int neighborZipId) {
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
        this.neighborZipId = neighborZipId;
    }
}
