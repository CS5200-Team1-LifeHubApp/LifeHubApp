package lifeHub.model;

public class Neighborhood {
    private int neighborZipId;
    private String city;

    // Constructor
    public Neighborhood(int neighborZipId, String city) {
        this.neighborZipId = neighborZipId;
        this.city = city;
    }

    // Getters and Setters
    public int getNeighborZipId() {
        return neighborZipId;
    }

    public void setNeighborZipId(int neighborZipId) {
        this.neighborZipId = neighborZipId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
