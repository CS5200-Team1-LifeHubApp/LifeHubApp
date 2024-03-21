package lifeHub.model;

//CREATE TABLE Restaurant (
//      RestaurantId INT AUTO_INCREMENT PRIMARY KEY,
//      Name VARCHAR(255),
//      Description TEXT,
//      Hours VARCHAR(255),
//      Website VARCHAR(255),
//      CuisineType ENUM('AFRICAN', 'AMERICAN', 'ASIAN', 'EUROPEAN', 'HISPANIC'),
//      NeighborZipId INT,
//      FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//  );

// Class Instance
public class Restaurant {
	private int restaurantId;
	private String name;
	private String description;
	private String hours;
	private String webSite;
	private CuisineType cuisineType;
	private int neighborZipId;

	//ENUM
	public enum CuisineType {
		AFRICAN,
		AMERICAN,
		ASIAN,
		EUROPEAN,
		HISPANIC
	}

	// Constructor
	public Restaurant(int restaurantId, String name, String description, String hours, String webSite,
					  CuisineType cuisineType, int neighborZipId) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.hours = hours;
		this.webSite = webSite;
		this.cuisineType = cuisineType;
		this.neighborZipId = neighborZipId;
	}

	public Restaurant(int restaurantId) {
		this.restaurantId = restaurantId;
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
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


	// toString
	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", name=" + name + ", description=" + description
				+ ", hours=" + hours + ", webSite=" + webSite + ", cuisineType=" + cuisineType + ", neighborZipId="
				+ neighborZipId + "]";
	}
}
