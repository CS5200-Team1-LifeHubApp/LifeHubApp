package lifeHub.dal;

import java.sql.*;
import lifeHub.model.Restaurant;
import lifeHub.model.Restaurant.CuisineType;

public class RestaurantDao {
    protected ConnectionManager connectionManager;

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

    // CREATE
    public Restaurant create(Restaurant restaurant) throws SQLException {
        String insertRestaurant = "INSERT INTO Restaurant(Name, Description, Hours, Website, CuisineType, NeighborZipId) VALUES(?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRestaurant, Statement.RETURN_GENERATED_KEYS);
            // Set parameters
            insertStmt.setString(1, restaurant.getName());
            insertStmt.setString(2, restaurant.getDescription());
            insertStmt.setString(3, restaurant.getHours());
            insertStmt.setString(4, restaurant.getWebsite());
            insertStmt.setString(5, restaurant.getCuisineType().name());
            insertStmt.setInt(6, restaurant.getNeighborZipId());
            insertStmt.executeUpdate();
            
            resultKey = insertStmt.getGeneratedKeys();
            int restaurantId = -1;
            if(resultKey.next()) {
                restaurantId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            restaurant.setRestaurantId(restaurantId);
            return restaurant;
        } finally {
            if(resultKey != null) resultKey.close();
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
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

    //DELETE
    public Restaurant delete(Restaurant restaurant) throws SQLException {
        String deleteRestaurant = "DELETE FROM Restaurant WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRestaurant);
            deleteStmt.setInt(1, restaurant.getRestaurantId());
            deleteStmt.executeUpdate();

            // Return null to indicate successful deletion
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
