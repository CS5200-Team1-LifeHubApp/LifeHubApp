package lifeHub.dal;

import java.util.HashMap;
import java.util.Map;
import lifeHub.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lifeHub.model.Restaurant.CuisineType;

/** class reference
 * public class Restaurant {
 private int restaurantId;
 private String name;
 private String description;
 private String hours;
 private String webSite;
 private CuisineType cuisineType;
 private int neighborZipId;
 */

/**
 * Data access object (DAO) class to interact with the underlying Restaurant table in your MySQL
 * instance. This is used to store {@link Restaurant} into your MySQL instance and retrieve
 * {@link Restaurant} from MySQL instance.
 */
public class RestaurantDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RestaurantDao instance = null;
	protected RestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static RestaurantDao getInstance() {
		if(instance == null) {
			instance = new RestaurantDao();
		}
		return instance;
	}

	/**
	 * Save the Restaurant instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Restaurant create(Restaurant restaurant) throws SQLException {
		String insertRestaurant =
				"INSERT INTO Restaurant(RestaurantId,Name,Description,Hours,WebSite,CuisineType,NeighborZipId) VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRestaurant);

			insertStmt.setInt(1, restaurant.getRestaurantId());
			insertStmt.setString(2, restaurant.getName());
			insertStmt.setString(3, restaurant.getDescription());
			insertStmt.setString(4, restaurant.getHours());
			insertStmt.setString(5, restaurant.getWebSite());
			insertStmt.setString(6, restaurant.getCuisineType().name()); // TODO -- double check this is correct
			insertStmt.setInt(7, restaurant.getNeighborZipId());

			insertStmt.executeUpdate();

			return restaurant;
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
	// - get by name
	// - get by neighborZipId
	// - get by RestaurantById
	// - get by CuisineType enum

	/**
	 * Retrieve Restaurant instances by their name.
	 * This runs a SELECT statement.
	 */
	public List<Restaurant> getRestaurantsByName(String name) throws SQLException {
		List<Restaurant> restaurants = new ArrayList<>();
		String selectRestaurants =
				"SELECT RestaurantId, Name, Description, Hours, Website, CuisineType, NeighborZipId " +
						"FROM Restaurant " +
						"WHERE Name LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, "%" + name + "%"); // Using LIKE for partial matches
			results = selectStmt.executeQuery();

			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String resultName = results.getString("Name");
				String description = results.getString("Description");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				Restaurant.CuisineType cuisineType = Restaurant.CuisineType.valueOf(results.getString("CuisineType"));
				int neighborZipId = results.getInt("NeighborZipId");
				Restaurant restaurant = new Restaurant(restaurantId, resultName, description, hours, website, cuisineType, neighborZipId);
				restaurants.add(restaurant);
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
		return restaurants;
	}

	/**
	 * Retrieve Restaurant instances by the NeighborZipId.
	 * This runs a SELECT statement.
	 */
	public List<Restaurant> getRestaurantsByNeighborZipId(int neighborZipId) throws SQLException {
		List<Restaurant> restaurants = new ArrayList<>();
		String selectRestaurants =
				"SELECT RestaurantId, Name, Description, Hours, Website, CuisineType, NeighborZipId " +
						"FROM Restaurant " +
						"WHERE NeighborZipId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setInt(1, neighborZipId);
			results = selectStmt.executeQuery();

			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				Restaurant.CuisineType cuisineType = Restaurant.CuisineType.valueOf(results.getString("CuisineType"));

				Restaurant restaurant = new Restaurant(restaurantId, name, description, hours, website, cuisineType, neighborZipId);
				restaurants.add(restaurant);
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
		return restaurants;
	}

	/**
	 * Retrieve Restaurant instances by the restaurantId.
	 * This runs a SELECT statement.
	 */
	public Restaurant getRestaurantsById(int restaurantId) throws SQLException {
		String selectRestaurants =
				"SELECT RestaurantId, Name, Description, Hours, Website, CuisineType, NeighborZipId " +
						"FROM Restaurant " +
						"WHERE RestaurantId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setInt(1, restaurantId);
			results = selectStmt.executeQuery();

			if (results.next()) {
				int resultRestaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				Restaurant.CuisineType cuisineType = Restaurant.CuisineType.valueOf(results.getString("CuisineType"));
				int neighborZipId = results.getInt("NeighborZipId");

				Restaurant restaurant = new Restaurant(resultRestaurantId, name, description, hours, website, cuisineType, neighborZipId);
				return restaurant;
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
		return null;
	}

	/**
	 * Retrieve Restaurant instances by the CuisineType.
	 * This runs a SELECT statement.
	 */
	public List<Restaurant> getRestaurantsByCuisineType(Restaurant.CuisineType cuisineType) throws SQLException {
		List<Restaurant> restaurants = new ArrayList<>();
		String selectRestaurants =
				"SELECT RestaurantId, Name, Description, Hours, Website, CuisineType, NeighborZipId " +
						"FROM Restaurant " +
						"WHERE CuisineType = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;

		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			selectStmt.setString(1, cuisineType.name());
			results = selectStmt.executeQuery();

			while (results.next()) {
				int restaurantId = results.getInt("RestaurantId");
				String name = results.getString("Name");
				String description = results.getString("Description");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				int neighborZipId = results.getInt("NeighborZipId");

				Restaurant restaurant = new Restaurant(restaurantId, name, description, hours, website, cuisineType, neighborZipId);
				restaurants.add(restaurant);
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
		return restaurants;
	}

	public Map<String, List<Restaurant>> getRestaurantsGroupedByCityByCuisineType(Restaurant.CuisineType cuisineType) throws SQLException {
		Map<String, List<Restaurant>> restaurantsByCity = new HashMap<>();
		String selectRestaurants =
				"SELECT Restaurant.*, NeighborhoodbyZip.City " +
						"FROM Restaurant " +
						"INNER JOIN NeighborhoodbyZip ON Restaurant.NeighborZipId = NeighborhoodbyZip.NeighborZipId " +
						"WHERE CuisineType = ?;";

		try (Connection connection = connectionManager.getConnection();
				PreparedStatement selectStmt = connection.prepareStatement(selectRestaurants)) {
			selectStmt.setString(1, cuisineType.name());

			try (ResultSet results = selectStmt.executeQuery()) {
				while (results.next()) {
					int restaurantId = results.getInt("RestaurantId");
					String name = results.getString("Name");
					String description = results.getString("Description");
					String hours = results.getString("Hours");
					String website = results.getString("Website");
					String city = results.getString("City");

					Restaurant restaurant = new Restaurant(restaurantId, name, description, hours, website, cuisineType, results.getInt("NeighborZipId"));

					restaurantsByCity.computeIfAbsent(city, k -> new ArrayList<>()).add(restaurant);
				}
			}
		}
		return restaurantsByCity;
	}

	// READ
	public Restaurant getRestaurantById(int restaurantId) throws SQLException {
		String selectRestaurant = "SELECT RestaurantId, Name, Description, Hours, Website, CuisineType, NeighborZipId FROM Restaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, restaurantId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				String name = results.getString("Name");
				String description = results.getString("Description");
				String hours = results.getString("Hours");
				String website = results.getString("Website");
				CuisineType cuisineType = CuisineType.valueOf(results.getString("CuisineType"));
				int neighborZipId = results.getInt("NeighborZipId");

				Restaurant restaurant = new Restaurant(restaurantId, name, description, hours, website, cuisineType, neighborZipId);
				return restaurant;
			}
		} finally {
			if(results != null) results.close();
			if(selectStmt != null) selectStmt.close();
			if(connection != null) connection.close();
		}
		return null;
	}

	// UPDATE
	public Restaurant update(Restaurant restaurant) throws SQLException {
		String updateRestaurant = "UPDATE Restaurant SET Name=?, Description=?, Hours=?, Website=?, CuisineType=?, NeighborZipId=? WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRestaurant);
			// Set parameters
			updateStmt.setString(1, restaurant.getName());
			updateStmt.setString(2, restaurant.getDescription());
			updateStmt.setString(3, restaurant.getHours());
			updateStmt.setString(4, restaurant.getWebsite());
			updateStmt.setString(5, restaurant.getCuisineType().toString());
			updateStmt.setInt(6, restaurant.getNeighborZipId());
			updateStmt.setInt(7, restaurant.getRestaurantId());

			updateStmt.executeUpdate();

			// Return the updated restaurant
			return restaurant;
		} finally {
			if(updateStmt != null) updateStmt.close();
			if(connection != null) connection.close();
		}
	}

	// UPDATE methods
	// - update the description
	/**
	 * Update the Description of the Restaurant instance.
	 * This runs an UPDATE statement.
	 */
	public Restaurant updateRestaurantDescription(int restaurantId, String newDescription) throws SQLException {
		String updateRestaurant = "UPDATE Restaurant SET Description = ? WHERE RestaurantId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;

		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRestaurant);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, restaurantId);
			updateStmt.executeUpdate();

			return getRestaurantsById(restaurantId);
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
	 * Delete the Restaurant instance.
	 * This runs a DELETE statement.
	 */
	public Restaurant delete(Restaurant restaurant) throws SQLException {
		String deleteRestaurant =
				"DELETE FROM Restaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRestaurant);
			deleteStmt.setInt(1, restaurant.getRestaurantId());
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
}
