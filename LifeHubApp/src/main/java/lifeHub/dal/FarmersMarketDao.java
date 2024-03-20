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

	// TODO -- CRUD statements below (create, read, update, delete)
	
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
	
	
	
	
	// TODO -- READ and UPDATE methods here!!!
	
	
	
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
//			super.delete(farmersMarket); // TODO -- this may or many NOT be needed based on instance

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
