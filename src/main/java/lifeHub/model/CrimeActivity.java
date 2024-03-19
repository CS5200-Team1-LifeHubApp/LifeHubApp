package lifeHub.model;

//CREATE TABLE CrimeActivity (
//	    CaseId VARCHAR(255) PRIMARY KEY,
//	    City VARCHAR(255),
//	    State VARCHAR(255),
//	    NeighborZipId INT,
//	    CrimeName TEXT,
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class CrimeActivity {
    private int caseId;
    private String city;
    private String state;
    private int neighborZipId;
    private String crimeName;
	
    // Constructor
    public CrimeActivity(int caseId, String city, String state, int neighborZipId, String crimeName) {
		super();
		this.caseId = caseId;
		this.city = city;
		this.state = state;
		this.neighborZipId = neighborZipId;
		this.crimeName = crimeName;
	}

    // Getters and Setters
	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		state = state;
	}

	public int getNeighborZipId() {
		return neighborZipId;
	}

	public void setNeighborZipId(int neighborZipId) {
		neighborZipId = neighborZipId;
	}

	public String getCrimeName() {
		return crimeName;
	}

	public void setCrimeName(String crimeName) {
		crimeName = crimeName;
	}

	
    // toString@Override
	public String toString() {
		return "CrimeActivity [caseId=" + caseId + ", City=" + city + ", State=" + state + ", NeighborZipId="
				+ neighborZipId + ", CrimeName=" + crimeName + "]";
	}
}
