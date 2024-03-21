package lifeHub.model;

import java.util.List;
import java.util.ArrayList;


public class CrimeActivity {
	private int caseId;
	private String city;
	private String state;
	private int neighborZipId;
	private String crimeName;

	public CrimeActivity(int caseId, String city, String state, int neighborZipId, String crimeName) {
		this.caseId = caseId;
		this.city = city;
		this.state = state;
		this.neighborZipId = neighborZipId;
		this.crimeName = crimeName;
	}

	public static List<CrimeActivity> getCrimeActivityById(int caseId) {
		List<CrimeActivity> crimeActivities = new ArrayList();
		return crimeActivities;
	}

	public static boolean delete(CrimeActivity crimeActivity) {
		// Implement method body
		return false;
	}

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

	@Override
	public String toString() {
		return "CrimeActivity [caseId=" + caseId + ", city=" + city + ", state=" + state
				+ ", neighborZipId=" + neighborZipId + ", crimeName=" + crimeName + "]";
	}

	public void updateCrimeName(String newCrimeName) {
		this.crimeName = newCrimeName;
	}

	public boolean delete() {
		// Implement method body
		return false;
	}
}