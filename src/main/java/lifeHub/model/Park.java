package lifeHub.model;

//CREATE TABLE Park (
//	    ParkId INT,
//	    NeighborZipId INT,
//	    Name VARCHAR(255),
//	    FeatureId INT,
//	    Hours Text,
//	    FeatureDesc VARCHAR(255),
//	    PRIMARY KEY (ParkId, FeatureID),
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class Park {
    private int parkId;
    private int neighborZipId;
    private String name;
    private int featureId;
    private String hours;
    private String featureDesc;
	
    // Constructor
    public Park(int parkId, int neighborZipId, String name, int featureId, String hours, String featureDesc) {
		super();
		this.parkId = parkId;
		this.neighborZipId = neighborZipId;
		this.name = name;
		this.featureId = featureId;
		this.hours = hours;
		this.featureDesc = featureDesc;
	}

    // Getters and Setters
	public int getParkId() {
		return parkId;
	}

	public void setParkId(int parkId) {
		this.parkId = parkId;
	}

	public int getNeighborZipId() {
		return neighborZipId;
	}

	public void setNeighborZipId(int neighborZipId) {
		this.neighborZipId = neighborZipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFeatureId() {
		return featureId;
	}

	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getFeatureDesc() {
		return featureDesc;
	}

	public void setFeatureDesc(String featureDesc) {
		this.featureDesc = featureDesc;
	}

    // toString
	@Override
	public String toString() {
		return "Park [parkId=" + parkId + ", neighborZipId=" + neighborZipId + ", name=" + name + ", featureId="
				+ featureId + ", hours=" + hours + ", featureDesc=" + featureDesc + "]";
	}
}
