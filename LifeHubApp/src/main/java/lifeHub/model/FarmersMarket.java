package lifeHub.model;

public class FarmersMarket {
    private int marketId;
    private int neighborZipId;
    private String name;
    private String dates;
    private String hours;
    private String website;
    private MarketType marketType;

    // Enum for MarketType
    public enum MarketType {
        ALLYEAR, SEASONAL
    }

    // Constructor
    public FarmersMarket(int marketId, int neighborZipId, String name, String dates, 
                         String hours, String website, MarketType marketType) {
        this.marketId = marketId;
        this.neighborZipId = neighborZipId;
        this.name = name;
        this.dates = dates;
        this.hours = hours;
        this.website = website;
        this.marketType = marketType;
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
        this.name = name;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public void setMarketType(MarketType marketType) {
        this.marketType = marketType;
    }
}
