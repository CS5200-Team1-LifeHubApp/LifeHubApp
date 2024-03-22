package lifeHub.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lifeHub.model.Restaurant.CuisineType;

public class FilterDao {

  // Assuming you have a ConnectionManager class that handles your database connections
  private ConnectionManager connectionManager;

  public FilterDao() {
    this.connectionManager = new ConnectionManager();
  }

  public Set<String> getCitiesWithCriteria(int parkFeatureId, CuisineType restaurantCuisine) throws SQLException {
    Set<String> cities = new HashSet<>();
    String sql =
        "SELECT DISTINCT nz.City " +
            "FROM NeighborhoodbyZip nz " +
            "WHERE EXISTS (" +
            "    SELECT * FROM Park p " +
            "    WHERE p.NeighborZipId = nz.NeighborZipId " +
            "    AND p.FeatureId = ?" +
            ") " +
            "AND EXISTS (" +
            "    SELECT 1 FROM Restaurant r " +
            "    WHERE r.NeighborZipId = nz.NeighborZipId " +
            "    AND r.CuisineType = ?" +
            ");";

    try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, parkFeatureId);
      preparedStatement.setString(2, restaurantCuisine.name());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          String city = resultSet.getString("City");
          cities.add(city);
        }
      }
    }
    return cities;
  }

    public Set<Integer> getZipcodeWithCriteria(int parkFeatureId, CuisineType restaurantCuisine) throws SQLException {
    Set<Integer> zipcodes = new HashSet<>();
    String sql =
        "SELECT DISTINCT nz.NeighborZipId " +
            "FROM NeighborhoodbyZip nz " +
            "WHERE EXISTS (" +
            "    SELECT * FROM Park p " +
            "    WHERE p.NeighborZipId = nz.NeighborZipId " +
            "    AND p.FeatureId = ?" +
            ") " +
            "AND EXISTS (" +
            "    SELECT 1 FROM Restaurant r " +
            "    WHERE r.NeighborZipId = nz.NeighborZipId " +
            "    AND r.CuisineType = ?" +
            ");";

    try (Connection connection = connectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
      preparedStatement.setInt(1, parkFeatureId);
      preparedStatement.setString(2, restaurantCuisine.name());

      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
          int zipcode = resultSet.getInt("NeighborZipId");
          zipcodes.add(zipcode);
        }
      }
    }
    return zipcodes;
  }
}
