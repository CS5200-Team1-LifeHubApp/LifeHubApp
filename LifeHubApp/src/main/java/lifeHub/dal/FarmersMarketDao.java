package lifeHub.dal;

import lifeHub.model.*;
import lifeHub.model.FarmersMarket.MarketType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Object field reference
 *
 * public class FarmersMarket {
 private int marketId;
 private int neighborZipId;
 private String name;
 private String dates;
 private String hours;
 private String website;
 private MarketType marketType;
 */

// DTO of farmersMarket and neighborhood Object
// 	Used for advanced query which joins farmers market table and neighborhood table
// package-private
class MarketZipDTO {
	private int marketId;
	private int neighborZipId;
	private String marketName;
	private String dates;
	private String hours;
	private String website;
	private FarmersMarket.MarketType marketType;
	private String cityName;

	public MarketZipDTO(int marketId, int neighborZipId, String marketName,
						String dates, String hours, String website, MarketType marketType,
						String cityName) {
		this.marketId = marketId;
		this.neighborZipId = neighborZipId;
		this.marketName = marketName;
		this.dates = dates;
		this.hours = hours;
		this.marketType = marketType;
		this.cityName = cityName;
	}

	public int getMarketId() {
		return marketId;
	}

	public String getCity(){
		return cityName;
	}

	public void setCity(String cityName) {
		this.cityName = cityName;
	}

}
/**
 * Data access object (DAO) class to interact with the underlying FarmersMarket table in your MySQL
 * instance. This is used to store {@link FarmersMarket} into your MySQL instance and retrieve
 * {@link FarmersMarket} from MySQL instance.
 */
public class FarmersMarketDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static FarmersMarketDao instance = null;
	protected FarmersMarketDao() {
		connectionManager = new ConnectionManager();
	}
	public static FarmersMarketDao getInstance() {
		if(instance == null) {
			instance = new FarmersMarketDao();
		}
		return instance;
	}

	/**
	 * Save the FarmersMarket instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public FarmersMarket create(FarmersMarket farmersMarket) throws SQLException {
		String insertFarmersMarket =
				"INSERT INTO FarmersMarket(MarketId,NeighborZipId,Name,Dates,Hours,website,marketType) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFarmersMarket);

			insertStmt.setInt(1, farmersMarket.getMarketId());
			insertStmt.setInt(2, farmersMarket.getNeighborZipId());
			insertStmt.setString(3, farmersMarket.getName());
			insertStmt.setString(4, farmersMarket.getDates());
			insertStmt.setString(5, farmersMarket.getHours());
			insertStmt.setString(6, farmersMarket.getWebsite());
			insertStmt.setString(7, farmersMarket.getMarketType().name());

			insertStmt.executeUpdate();

			return farmersMarket;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	// READ methods
	// - get by marketId, based on the id
	// - get by neighborZipId, based on the location
	// - get by name, based on the market name
	// - get by marketType, based on the market opening type enum

	/**
	 * Retrieve the FarmersMarket instance by marketId
	 * This runs a SELECT statement.
	 */
	// may mainly used internally
	public FarmersMarket getFarmersMarketById(int marketId) throws SQLException {
		String selectFarmersMarket = "SELECT * FROM FarmersMarket WHERE MarketId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFarmersMarket);
			selectStmt.setInt(1, marketId);
			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultMarketId = results.getInt("MarketId");
				int neighborZipId = results.getInt("NeighborZipId");
				String name = results.getString("Name");
				String dates = results.getString("Dates");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				MarketType marketType = MarketType.valueOf(results.getString("MarketType"));
				FarmersMarket farmersMarket = new FarmersMarket(resultMarketId, neighborZipId, name, dates, hours, website, marketType);
				return farmersMarket;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Retrieve the FarmersMarket instance by neighborZipId
	 * This runs a SELECT statement.
	 */
	public List<FarmersMarket> getFarmersMarketsByNeighborZipId(int neighborZipId) throws SQLException {
		List<FarmersMarket> markets = new ArrayList<>();
		String selectMarkets = "SELECT * FROM FarmersMarket WHERE NeighborZipId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMarkets);
			selectStmt.setInt(1, neighborZipId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int marketId = results.getInt("MarketId");
				String name = results.getString("Name");
				String dates = results.getString("Dates");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				MarketType marketType = MarketType.valueOf(results.getString("MarketType"));
				FarmersMarket market = new FarmersMarket(marketId, neighborZipId, name, dates, hours, website, marketType);
				markets.add(market);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (results != null) results.close();
			if (selectStmt != null) selectStmt.close();
			if (connection != null) connection.close();
		}
		return markets;
	}

	/**
	 * Retrieve the FarmersMarket instance by name
	 * This runs a SELECT statement.
	 */
	public List<FarmersMarket> getFarmersMarketsByName(String name) throws SQLException {
		List<FarmersMarket> markets = new ArrayList<>();
		// Using LIKE for partial matches
		String selectMarkets = "SELECT * FROM FarmersMarket WHERE Name LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMarkets);
			selectStmt.setString(1, "%" + name + "%");
			results = selectStmt.executeQuery();

			while (results.next()) {
				int marketId = results.getInt("MarketId");
				int neighborZipId = results.getInt("NeighborZipId");
				String dates = results.getString("Dates");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				MarketType marketType = MarketType.valueOf(results.getString("MarketType"));
				FarmersMarket market = new FarmersMarket(marketId, neighborZipId, name, dates, hours, website, marketType);
				markets.add(market);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (results != null) results.close();
			if (selectStmt != null) selectStmt.close();
			if (connection != null) connection.close();
		}
		return markets;
	}


	/**
	 * Retrieve the FarmersMarket instance by marketType (enum)
	 * This runs a SELECT statement.
	 */
	public List<FarmersMarket> getFarmersMarketsByMarketType(MarketType marketType) throws SQLException {
		List<FarmersMarket> markets = new ArrayList<>();
		String selectMarkets = "SELECT * FROM FarmersMarket WHERE MarketType = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMarkets);
			selectStmt.setString(1, marketType.name().toString());
			results = selectStmt.executeQuery();

			while (results.next()) {
				int marketId = results.getInt("MarketId");
				int neighborZipId = results.getInt("NeighborZipId");
				String name = results.getString("Name");
				String dates = results.getString("Dates");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				FarmersMarket market = new FarmersMarket(marketId, neighborZipId, name, dates, hours, website, marketType);
				markets.add(market);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (results != null) results.close();
			if (selectStmt != null) selectStmt.close();
			if (connection != null) connection.close();
		}
		return markets;
	}

	// UPDATE methods
	/**
	 * Update the FarmersMarket instance with newDates and newHours strings
	 * This runs a UPDATE statement
	 */
	// Implement of this method is based on how farmers' market works in real context
	// The opening time may change by year, but the other info usually remains the same
	public FarmersMarket updateFarmersMarketDatesAndHours(FarmersMarket farmersMarket, String newDates, String newHours) throws SQLException {
		String updateMarket = "UPDATE FarmersMarket SET Dates = ?, Hours = ? WHERE MarketId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateMarket);
			updateStmt.setString(1, newDates);
			updateStmt.setString(2, newHours);
			updateStmt.setInt(3, farmersMarket.getMarketId());
			updateStmt.executeUpdate();
			// Update object fields before returning
			farmersMarket.setDates(newDates);
			farmersMarket.setHours(newHours);
			return farmersMarket;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (updateStmt != null) {
				updateStmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	/**
	 * Delete the FarmersMarket instance.
	 * This runs a DELETE statement.
	 */
	public FarmersMarket delete(FarmersMarket farmersMarket) throws SQLException {
		String deleteFarmersMarket =
				"DELETE FROM FarmersMarket WHERE MarketId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFarmersMarket);
			deleteStmt.setInt(1, farmersMarket.getMarketId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	// Advanced Query
	//	-- Which farmers markets open on Saturday located within the top 5 highest-rated neighborhoods?

	/**
	 * Retrieve FarmersMarket instances by business logic.
	 * This runs a SELECT statement.
	 */
	// this requires:
	// 	public String getCityById(int neighborZipId), from NeighborhoodDao.java
	public List<MarketZipDTO> findMarketsOpenOnSaturdaysInTopRatedNeighborhoods() throws SQLException {
		List<MarketZipDTO> markets = new ArrayList<>();
		String query =
				"SELECT FarmersMarket.MarketId, FarmersMarket.Name, FarmersMarket.NeighborZipId, FarmersMarket.Dates, FarmersMarket.Hours, FarmersMarket.Website, FarmersMarket.MarketType " +
						"FROM FarmersMarket " +
						"INNER JOIN NeighborhoodbyZip ON FarmersMarket.NeighborZipId = NeighborhoodbyZip.NeighborZipId " +
						"INNER JOIN ( " +
						"    SELECT N2.NeighborZipId, AVG(R.Rating) AS CityRatings " +
						"    FROM Review R " +
						"    INNER JOIN NeighborhoodbyZip N2 ON R.NeighborZipId = N2.NeighborZipId " +
						"    GROUP BY N2.NeighborZipId " +
						"    ORDER BY CityRatings DESC " +
						"    LIMIT 5 " +
						") AS TopFiveCities ON NeighborhoodbyZip.NeighborZipId = TopFiveCities.NeighborZipId " +
						"WHERE FarmersMarket.Dates LIKE '%Sat%';";

		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(query);
			results = selectStmt.executeQuery();

			NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();

			while (results.next()) {
				int marketId = results.getInt("MarketId");
				int neighborZipId = results.getInt("NeighborZipId");
				String name = results.getString("Name");
				String dates = results.getString("Dates");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				FarmersMarket.MarketType marketType = FarmersMarket.MarketType.valueOf(results.getString("MarketType"));

				// this dao method shoud be included in NeighborhoodDao.java
//				String city = neighborhoodDao.getCityById(neighborZipId);
				String city = "Dallas";

				MarketZipDTO market = new MarketZipDTO(marketId, neighborZipId, name, dates, hours, website, marketType, city);
				markets.add(market);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(results != null) {
				results.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return markets;
	}
}