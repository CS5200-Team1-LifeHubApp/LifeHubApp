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
 * Data access object (DAO) class to interact with the underlying Pet table in your MySQL
 * instance. This is used to store {@link Pet} into your MySQL instance and retrieve 
 * {@link Pet} from MySQL instance.
 */
public class PetDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PetDao instance = null;
	protected PetDao() {
		connectionManager = new ConnectionManager();
	}
	public static PetDao getInstance() {
		if(instance == null) {
			instance = new PetDao();
		}
		return instance;
	}

	// TODO -- CRUD statements below (create, read, update, delete)
	
	/**
	 * Save the Pet instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Pet create(Pet pet) throws SQLException {
		String insertUsers = 
				"INSERT INTO Pet(LicenseId,Name,Species,PrimayBreed,NeighborZipId) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUsers);

			insertStmt.setInt(1, pet.getLicenseId());
			insertStmt.setString(2, pet.getName());
			insertStmt.setString(3, pet.getSpecies());
			insertStmt.setString(4, pet.getPrimaryBreed());
			insertStmt.setInt(5, pet.getNeighborZipId());
		
			insertStmt.executeUpdate();
			
			return pet;
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
	 * Delete the Pet instance.
	 * This runs a DELETE statement.
	 */
	public Pet delete(Pet pet) throws SQLException {
		String deletePet = 
				"DELETE FROM Pet WHERE LicenseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePet);
			deleteStmt.setInt(1, pet.getLicenseId());
			deleteStmt.executeUpdate();
//			super.delete(pet); // TODO -- this may or many NOT be needed based on instance

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
