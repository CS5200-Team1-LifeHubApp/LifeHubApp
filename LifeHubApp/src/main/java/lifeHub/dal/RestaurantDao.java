package lifeHub.dal;

import lifeHub.model.*;

// TODO -- be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

	// TODO -- CRUD statements below (create, read, update, delete)
	
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
	
	
	
	
	// TODO -- READ and UPDATE methods here!!!
	
	
	
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
//			super.delete(crimeActivity); // TODO -- this may or many NOT be needed based on instance

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
