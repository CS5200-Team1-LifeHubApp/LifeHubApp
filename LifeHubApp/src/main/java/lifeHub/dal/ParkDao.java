package lifeHub.dal;

import java.util.HashMap;
import java.util.Map;
import lifeHub.model.*;

// TODO -- be sure to remove unused imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lifeHub.model.Park;
import lifeHub.dal.ConnectionManager;

/**
 * Data access object (DAO) class to interact with the underlying Park table in your MySQL
 * instance. This is used to store {@link Park} into your MySQL instance and retrieve
 * {@link Park} from MySQL instance.
 */
public class ParkDao {
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static ParkDao instance = null;
	public ParkDao() {
		connectionManager = new ConnectionManager();
	}
	public static ParkDao getInstance() {
		if(instance == null) {
			instance = new ParkDao();
		}
		return instance;
	}

	// TODO -- CRUD statements below (create, read, update, delete)

	/**
	 * Save the Park instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Park create(Park park) throws SQLException {
		String insertCrimeActivity =
				"INSERT INTO Park(ParkId,NeighborZipId,Name,FeatureId,Hours,FeatureDesc) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCrimeActivity);

			insertStmt.setInt(1, park.getParkId());
			insertStmt.setInt(2, park.getNeighborZipId());
			insertStmt.setString(3, park.getName());
			insertStmt.setInt(4, park.getFeatureId());
			insertStmt.setString(5, park.getHours());
			insertStmt.setString(6, park.getFeatureDesc());

			insertStmt.executeUpdate();

			return park;
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

	public List<Park> getParkByName(String Name) throws SQLException {
		List<Park> parks = new ArrayList<>();
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Park WHERE Name = ?")) {
			preparedStatement.setString(1, Name);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedParkId = resultSet.getInt("ParkId");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedName = resultSet.getString("Name");
					String fetchedHours = resultSet.getString("Hours");
					int fetchedFeatureId = resultSet.getInt("FeatureId");
					String fetchedFeatureDesc = resultSet.getString("FeatureDesc");

					Park Park = new Park(fetchedParkId, fetchedNeighborZipId, fetchedName, fetchedFeatureId, fetchedHours, fetchedFeatureDesc);
					parks.add(Park);
				}
			}
		}
		return parks;
	}

	public List<Park> getParkByFeatureId(int featureId) throws SQLException {
		List<Park> parks = new ArrayList<>();
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Park WHERE FeatureId = ?")) {
			preparedStatement.setInt(1, featureId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedParkId = resultSet.getInt("ParkId");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedName = resultSet.getString("Name");
					String fetchedHours = resultSet.getString("Hours");
					int fetchedFeatureId = resultSet.getInt("FeatureId");
					String fetchedFeatureDesc = resultSet.getString("FeatureDesc");

					Park Park = new Park(fetchedParkId, fetchedNeighborZipId, fetchedName, fetchedFeatureId, fetchedHours, fetchedFeatureDesc);
					parks.add(Park);
				}
			}
		}
		return parks;
	}

	/**
	 * It returns Map<String, List<Park>> in case we need the list of parks in the future
	 * @param featureId
	 * @return
	 * @throws SQLException
	 */
	public Map<String, List<Park>> getParksGroupedByCityWithFeatureId(int featureId) throws SQLException {
		Map<String, List<Park>> parksByCity = new HashMap<>();
		String sql = "SELECT Park.*, NeighborhoodbyZip.City " +
				"FROM Park " +
				"INNER JOIN NeighborhoodbyZip ON Park.NeighborZipId = NeighborhoodbyZip.NeighborZipId " +
				"WHERE Park.FeatureId = ?";

		try (Connection connection = connectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, featureId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedParkId = resultSet.getInt("ParkId");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedName = resultSet.getString("Name");
					String fetchedHours = resultSet.getString("Hours");
					int fetchedFeatureId = resultSet.getInt("FeatureId");
					String fetchedFeatureDesc = resultSet.getString("FeatureDesc");
					String city = resultSet.getString("City");

					Park park = new Park(fetchedParkId, fetchedNeighborZipId, fetchedName, fetchedFeatureId, fetchedHours, fetchedFeatureDesc);

					if (!parksByCity.containsKey(city)) {
						parksByCity.put(city, new ArrayList<>());
					}
					parksByCity.get(city).add(park);
				}
			}
		}
		return parksByCity;
	}

	public List<Park> getParksGroupedByZipcode(int neighborZipId) throws SQLException {
		List<Park> parks = new ArrayList<>();;
		String sql = "SELECT * FROM Park WHERE NeighborZipId = ?;";

		try (Connection connection = connectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, neighborZipId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedParkId = resultSet.getInt("ParkId");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedName = resultSet.getString("Name");
					String fetchedHours = resultSet.getString("Hours");
					int fetchedFeatureId = resultSet.getInt("FeatureId");
					String fetchedFeatureDesc = resultSet.getString("FeatureDesc");

					Park park = new Park(fetchedParkId, fetchedNeighborZipId, fetchedName, fetchedFeatureId, fetchedHours, fetchedFeatureDesc);
					parks.add(park);
				}
			}
		}
		return parks;
	}

	public List<Park> getParksGroupedByZipcodeWithFeatureId(int neighborZipId, int featureId) throws SQLException {
		List<Park> parks = new ArrayList<>();;
		String sql = "SELECT * FROM Park WHERE NeighborZipId = ? AND FeatureId = ?;";

		try (Connection connection = connectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, neighborZipId);
			preparedStatement.setInt(2, featureId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedParkId = resultSet.getInt("ParkId");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedName = resultSet.getString("Name");
					String fetchedHours = resultSet.getString("Hours");
					int fetchedFeatureId = resultSet.getInt("FeatureId");
					String fetchedFeatureDesc = resultSet.getString("FeatureDesc");

					Park park = new Park(fetchedParkId, fetchedNeighborZipId, fetchedName, fetchedFeatureId, fetchedHours, fetchedFeatureDesc);
					parks.add(park);
				}
			}
		}
		return parks;
	}

	public Park updateParkName(Park Park, String newParkName) throws SQLException {
		String updateParkName = "UPDATE Park SET Name = ? WHERE ParkId = ?;";
		try (Connection connection = connectionManager.getConnection();
				PreparedStatement updateStmt = connection.prepareStatement(updateParkName)) {

			updateStmt.setString(1, newParkName);
			updateStmt.setInt(2, Park.getParkId());
			updateStmt.executeUpdate();

			Park.setName(newParkName);
			return Park;
		}
	}

	// READ
	public Park getParkById(int parkId, int featureId) throws SQLException {
		String selectPark = "SELECT ParkId, NeighborZipId, Name, FeatureId, Hours, FeatureDesc FROM Park WHERE ParkId=? AND FeatureId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPark);
			selectStmt.setInt(1, parkId);
			selectStmt.setInt(2, featureId);

			results = selectStmt.executeQuery();
			if(results.next()) {
				int neighborZipId = results.getInt("NeighborZipId");
				String name = results.getString("Name");
				int retrievedFeatureId = results.getInt("FeatureId");
				String hours = results.getString("Hours");
				String featureDesc = results.getString("FeatureDesc");

				Park park = new Park(parkId, neighborZipId, name, retrievedFeatureId, hours, featureDesc);
				return park;
			}
		} finally {
			if(results != null) results.close();
			if(selectStmt != null) selectStmt.close();
			if(connection != null) connection.close();
		}
		return null;
	}

	// UPDATE
	public Park update(Park park) throws SQLException {
		String updatePark = "UPDATE Park SET NeighborZipId=?, Name=?, Hours=?, FeatureDesc=? WHERE ParkId=? AND FeatureId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePark);
			// Set parameters for the update
			updateStmt.setInt(1, park.getNeighborZipId());
			updateStmt.setString(2, park.getName());
			updateStmt.setString(3, park.getHours());
			updateStmt.setString(4, park.getFeatureDesc());
			updateStmt.setInt(5, park.getParkId());
			updateStmt.setInt(6, park.getFeatureId());

			updateStmt.executeUpdate();

			// Return the updated park
			return park;
		} finally {
			if(updateStmt != null) updateStmt.close();
			if(connection != null) connection.close();
		}
	}


	/**
	 * Delete the Park instance.
	 * This runs a DELETE statement.
	 */
	public Park delete(Park park) throws SQLException {
		String deletePark =
				"DELETE FROM Park WHERE ParkId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePark);
			deleteStmt.setInt(1, park.getParkId());
			deleteStmt.executeUpdate();
//			super.delete(park);

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
