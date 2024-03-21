package lifeHub.tools;

import java.sql.*;

/**
 * Calculator: What is the average restaurant rating of each neighborhood? (Best Restaurants neighborhood)
 */
public class RestaurantRatingCalculator {
    // Define your database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/LifeHubApp";
    private static final String USERNAME = "your_username";//TODO: replace with your MySQL username
    private static final String PASSWORD = "your_password";//TODO: replace with your MySQL password

    public void calculateAverageRatings() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT NeighborZipId, AVG(Rating) AS AvgRating FROM Restaurant GROUP BY NeighborZipId";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int neighborZipId = resultSet.getInt("NeighborZipId");
                    double avgRating = resultSet.getDouble("AvgRating");

                    System.out.println("Neighborhood ID: " + neighborZipId + ", Average Rating: " + avgRating);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RestaurantRatingCalculator calculator = new RestaurantRatingCalculator();
        calculator.calculateAverageRatings();
    }
}
