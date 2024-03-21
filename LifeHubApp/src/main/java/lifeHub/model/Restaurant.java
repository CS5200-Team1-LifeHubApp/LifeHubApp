package lifeHub.model;

public class Restaurant {
    private int restaurantId;
    private String name;
    private String description;
    private String hours;
    private String website;
    private CuisineType cuisineType;
    private int neighborZipId;

    // Enum for CuisineType
    public enum CuisineType {
        AFRICAN, AMERICAN, ASIAN, EUROPEAN, HISPANIC
    }

    // Constructor
    public Restaurant(int restaurantId, String name, String description, String hours, 
                      String website, CuisineType cuisineType, int neighborZipId) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.hours = hours;
        this.website = website;
        this.cuisineType = cuisineType;
        this.neighborZipId = neighborZipId;
    }

    // Getters and Setters
    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public int getNeighborZipId() {
        return neighborZipId;
    }

    public void setNeighborZipId(int neighborZipId) {
        this.neighborZipId = neighborZipId;
    }
}
