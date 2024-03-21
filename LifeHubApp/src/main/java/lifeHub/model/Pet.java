package lifeHub.model;

// Class Instance
public class Pet {
    private String licenseId;
    private String name;
    private String species;
    private String primaryBreed;
    private int neighborZipId;

    // Constructor
    public Pet(String licenseId, String name, String species, String primaryBreed, int neighborZipId) {
        this.licenseId = licenseId;
        this.name = name;
        this.species = species;
        this.primaryBreed = primaryBreed;
        this.neighborZipId = neighborZipId;
    }

    // Getters and Setters
    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
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
}
