package lifeHub.model;

//CREATE TABLE FarmersMarket (
//	    MarketId INT AUTO_INCREMENT PRIMARY KEY,
//	    NeighborZipId INT,
//	    Name VARCHAR(255),
//	    Dates VARCHAR(255),
//	    Hours VARCHAR(255),
//	    Website VARCHAR(255),
//	    MarketType ENUM('ALLYEAR', 'SEASONAL'),
//	    FOREIGN KEY (NeighborZipId) REFERENCES NeighborhoodbyZip(NeighborZipId)
//	);

// Class Instance
public class FarmersMarket {
    private int marketId;
    private int neighborZipId;
    private String name;
    private String dates;
    private String hours;
    private String website;
    private MarketType marketType;
    
    //ENUM
    public enum MarketType {
    	ALLYEAR,
    	SEASONAL
    }

    // Constructor
	public FarmersMarket(int marketId, int neighborZipId, String name, String dates, String hours, String website,
			lifeHub.model.FarmersMarket.MarketType marketType) {
		super();
		this.marketId = marketId;
		this.neighborZipId = neighborZipId;
		name = name;
		dates = dates;
		hours = hours;
		website = website;
		marketType = marketType;
	}

    // Getters and Setters
	public int getMarketId() {
		return marketId;
	}

	public void setMarketId(int marketId) {
		this.marketId = marketId;
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
		name = name;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		dates = dates;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		hours = hours;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		website = website;
	}

	public MarketType getMarketType() {
		return marketType;
	}

	public void setMarketType(MarketType marketType) {
		marketType = marketType;
	}

    // toString
	@Override
	public String toString() {
		return "FarmersMarket [marketId=" + marketId + ", neighborZipId=" + neighborZipId + ", Name=" + name
				+ ", Dates=" + dates + ", Hours=" + hours + ", Website=" + website + ", MarketType=" + marketType + "]";
	}
}
