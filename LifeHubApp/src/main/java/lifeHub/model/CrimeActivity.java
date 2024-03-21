package lifeHub.model;

public class CrimeActivity {
    private String caseId;
    private String city;
    private String state;
    private int neighborZipId;
    private String crimeName;

    // Constructor
    public CrimeActivity(String caseId, String city, String state, int neighborZipId, String crimeName) {
        this.caseId = caseId;
        this.city = city;
        this.state = state;
        this.neighborZipId = neighborZipId;
        this.crimeName = crimeName;
    }

    // Getters and Setters
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNeighborZipId() {
        return neighborZipId;
    }

    public void setNeighborZipId(int neighborZipId) {
        this.neighborZipId = neighborZipId;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }
}