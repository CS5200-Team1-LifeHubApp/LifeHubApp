package lifeHub.dal;

import lifeHub.dal.ConnectionManager;
import lifeHub.model.CrimeActivity;
import lifeHub.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrimeActivityDao{
	protected ConnectionManager connectionManager;

	protected CrimeActivityDao() {
		connectionManager = new ConnectionManager();
	}
	private static CrimeActivityDao instance = null;

	public static CrimeActivityDao getInstance() {
		if (instance == null) {
			instance = new CrimeActivityDao();
		}
		return instance;
	}
	public CrimeActivity create(CrimeActivity crimeActivity) throws SQLException {
		String insertCrimeActivity =
				"INSERT INTO CrimeActivity(CaseId, City, State, NeighborZipId, CrimeName) VALUES (?, ?, ?, ?, ?);";
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement insertStmt = connection.prepareStatement(insertCrimeActivity)) {

			insertStmt.setInt(1, crimeActivity.getCaseId());
			insertStmt.setString(2, crimeActivity.getCity());
			insertStmt.setString(3, crimeActivity.getState());
			insertStmt.setInt(4, crimeActivity.getNeighborZipId());
			insertStmt.setString(5, crimeActivity.getCrimeName());

			insertStmt.executeUpdate();

			return crimeActivity;
		}
	}

	public List<CrimeActivity> getCrimeActivityById(int caseId) throws SQLException {
		List<CrimeActivity> crimeActivities = new ArrayList<>();
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CrimeActivity WHERE CaseId = ?")) {
			preparedStatement.setInt(1, caseId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					int fetchedCaseId = resultSet.getInt("CaseId");
					String fetchedCity = resultSet.getString("City");
					String fetchedState = resultSet.getString("State");
					int fetchedNeighborZipId = resultSet.getInt("NeighborZipId");
					String fetchedCrimeName = resultSet.getString("CrimeName");

					CrimeActivity crimeActivity = new CrimeActivity(fetchedCaseId, fetchedCity, fetchedState,
							fetchedNeighborZipId, fetchedCrimeName);
					crimeActivities.add(crimeActivity);
				}
			}
		}
		return crimeActivities;
	}

	public CrimeActivity updateCrimeName(CrimeActivity crimeActivity, String newCrimeName) throws SQLException {
		String updateCrimeName = "UPDATE CrimeActivity SET CrimeName = ? WHERE CaseId = ?;";
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement updateStmt = connection.prepareStatement(updateCrimeName)) {

			updateStmt.setString(1, newCrimeName);
			updateStmt.setInt(2, crimeActivity.getCaseId());
			updateStmt.executeUpdate();

			crimeActivity.setCrimeName(newCrimeName);
			return crimeActivity;
		}
	}

	public void delete(CrimeActivity crimeActivity) throws SQLException {
		String deleteCrimeActivity = "DELETE FROM CrimeActivity WHERE CaseId = ?;";
		try (Connection connection = connectionManager.getConnection();
			 PreparedStatement deleteStmt = connection.prepareStatement(deleteCrimeActivity)) {

			deleteStmt.setInt(1, crimeActivity.getCaseId());
			deleteStmt.executeUpdate();
		}
	}
}