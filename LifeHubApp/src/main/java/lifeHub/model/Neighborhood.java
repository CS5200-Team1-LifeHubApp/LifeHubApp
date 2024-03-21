package lifeHub.model;

//CREATE TABLE NeighborhoodbyZip (
//	    NeighborZipId INT PRIMARY KEY,
//	    City VARCHAR(255)
//	);

// Class Instance
public class Neighborhood {
	private int neighborZipId;
	private String city;

	// Constructor
	public Neighborhood(int neighborZipId, String city) {
		super();
		this.neighborZipId = neighborZipId;
		this.city = city;
	}

	// Getters and Setters
	public int getNeighborZipId() {
		return neighborZipId;
	}

	public void getNeighborhoodByZip(int neighborZipId) {
		this.neighborZipId = neighborZipId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	// toString
	@Override
	public String toString() {
		return "Neighborhood [neighborZipId=" + neighborZipId + ", city=" + city + "]";
	}
}
