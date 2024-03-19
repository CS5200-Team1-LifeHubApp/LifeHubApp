package lifeHub.model;

//CREATE TABLE Pet (
//	    LicenseId VARCHAR(255) PRIMARY KEY,
//	    Name VARCHAR(255),
//	    Species VARCHAR(255),
//	    PrimaryBreed VARCHAR(255),
//	    NeighborZipId INT,
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class Pet {
    private int licenseId;
    private String name;
    private String species;
    private String primaryBreed;
    private int neighborZipId;


    // Constructor
    public Pet(int licenseId, String name, String species, String primaryBreed, int neighborZipId) {
		super();
		this.licenseId = licenseId;
		this.name = name;
		this.species = species;
		this.primaryBreed = primaryBreed;
		this.neighborZipId = neighborZipId;
	}

    // Getters and Setters
	public int getLicenseId() {
		return licenseId;
	}


	public void setLicenseId(int licenseId) {
		this.licenseId = licenseId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public String getPrimaryBreed() {
		return primaryBreed;
	}


	public void setPrimaryBreed(String primaryBreed) {
		this.primaryBreed = primaryBreed;
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
		return "Pet [licenseId=" + licenseId + ", name=" + name + ", species=" + species + ", primaryBreed="
				+ primaryBreed + ", neighborZipId=" + neighborZipId + "]";
	}
}
