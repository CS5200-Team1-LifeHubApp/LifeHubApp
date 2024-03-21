package lifeHub.tools;

import java.sql.*;

/**
 * Top 10 neighborhoods have the highest average rating
 */
public class TopNeighborhoods {

    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/LifeHubApp";
        String username = "your_username";//TODO: replace with your MySQL username
        String password = "your_password";//TODO: replace with your MySQL password

        // SQL query to retrieve top 10 neighborhoods with highest average rating
        String query = "SELECT n.NeighborZipId, ROUND(AVG(r.Rating), 2) AS AverageRating " +
                       "FROM NeighborhoodbyZip n " +
                       "JOIN Review r ON n.NeighborZipId = r.NeighborZipId " +
                       "GROUP BY n.NeighborZipId " +
                       "ORDER BY AverageRating DESC " +
                       "LIMIT 10";

        // Database connection and statement objects
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Process the results
            while (rs.next()) {
                int neighborZipId = rs.getInt("NeighborZipId");
                double averageRating = rs.getDouble("AverageRating");
                // Display the results or perform further processing as needed
                System.out.println("Neighborhood Zip ID: " + neighborZipId + ", Average Rating: " + averageRating);
            }
        } catch (SQLException e) {
            // Handle any errors that occur during the query execution
            e.printStackTrace();
        }
    }
}
