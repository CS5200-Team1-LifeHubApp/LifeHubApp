package lifeHub.tools;

import java.sql.*;

/**
 * Analyzer: Which cities have the most all-year open farmers markets? (Farmers market Lover neighborhood)
 */
public class FarmersMarketAnalyzer {
    // Define your database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/LifeHubApp";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    public void analyzeFarmersMarkets() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT NeighborhoodbyZip.City, COUNT(*) AS MarketCount " +
                         "FROM FarmersMarket " +
                         "INNER JOIN NeighborhoodbyZip " +
                         "ON FarmersMarket.NeighborZipId = NeighborhoodbyZip.NeighborZipId " +
                         "WHERE FarmersMarket.MarketType = 'ALLYEAR' " +
                         "GROUP BY NeighborhoodbyZip.City " +
                         "ORDER BY MarketCount DESC";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String city = resultSet.getString("City");
                    int marketCount = resultSet.getInt("MarketCount");

                    System.out.println("City: " + city + ", Farmers Market Count: " + marketCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FarmersMarketAnalyzer analyzer = new FarmersMarketAnalyzer();
        analyzer.analyzeFarmersMarkets();
    }
}
