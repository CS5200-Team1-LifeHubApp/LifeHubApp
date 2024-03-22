package lifeHub.dal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import lifeHub.model.Pet;

public class PetDao {
    protected ConnectionManager connectionManager;

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

    //CREATE
    public Pet create(Pet pet) throws SQLException {
        String insertPet = "INSERT INTO Pet(LicenseId, Name, Species, PrimaryBreed, NeighborZipId) VALUES(?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPet);
            // Set parameters
            insertStmt.setString(1, pet.getLicenseId());
            insertStmt.setString(2, pet.getName());
            insertStmt.setString(3, pet.getSpecies());
            insertStmt.setString(4, pet.getPrimaryBreed());
            insertStmt.setInt(5, pet.getNeighborZipId());
            insertStmt.executeUpdate();
            
            return pet;
        } finally {
            if(insertStmt != null) insertStmt.close();
            if(connection != null) connection.close();
        }
    }
    
    //READ
    public Pet getPetByLicenseId(String licenseId) throws SQLException {
        String selectPet = "SELECT LicenseId, Name, Species, PrimaryBreed, NeighborZipId FROM Pet WHERE LicenseId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPet);
            selectStmt.setString(1, licenseId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                String name = results.getString("Name");
                String species = results.getString("Species");
                String primaryBreed = results.getString("PrimaryBreed");
                int neighborZipId = results.getInt("NeighborZipId");

                Pet pet = new Pet(licenseId, name, species, primaryBreed, neighborZipId);
                return pet;
            }
        } finally {
            if(results != null) results.close();
            if(selectStmt != null) selectStmt.close();
            if(connection != null) connection.close();
        }
        return null;
    }
    
    /**
     * Retrieves pets belonging to a specific zipcode.
     *
     * @param zipcode The zipcode to retrieve pets for.
     * @return A list of pets belonging to the specified zipcode.
     * @throws SQLException if there's an error accessing the database.
     */
    public List<Pet> getPetsByZipcode(int zipcode) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String selectPetsByZipcode = "SELECT LicenseId, Name, Species, PrimaryBreed FROM Pet WHERE NeighborZipId = ?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPetsByZipcode);
            selectStmt.setInt(1, zipcode);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String licenseId = results.getString("LicenseId");
                String name = results.getString("Name");
                String species = results.getString("Species");
                String primaryBreed = results.getString("PrimaryBreed");

                Pet pet = new Pet(licenseId, name, species, primaryBreed, zipcode);
                pets.add(pet);
            }
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return pets;
    }

    public List<Pet> getPetsByName(String name) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String selectPetsByName = "SELECT LicenseId, Name, Species, PrimaryBreed, NeighborZipId FROM Pet WHERE Name = ?";
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectPetsByName);) {
            selectStmt.setString(1, name);
            try (ResultSet results = selectStmt.executeQuery();) {
                while (results.next()) {
                    pets.add(mapPetFromResultSet(results));
                }
            }
        }
        return pets;
    }

    // Fetch pets by breed
    public List<Pet> getPetsByBreed(String breed) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String selectPetsByBreed = "SELECT LicenseId, Name, Species, PrimaryBreed, NeighborZipId FROM Pet WHERE PrimaryBreed = ?";
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectPetsByBreed);) {
            selectStmt.setString(1, breed);
            try (ResultSet results = selectStmt.executeQuery();) {
                while (results.next()) {
                    pets.add(mapPetFromResultSet(results));
                }
            }
        }
        return pets;
    }

    // Fetch pets by species
    public List<Pet> getPetsBySpecies(String species) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String selectPetsBySpecies = "SELECT LicenseId, Name, Species, PrimaryBreed, NeighborZipId FROM Pet WHERE Species = ?";
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectPetsBySpecies);) {
            selectStmt.setString(1, species);
            try (ResultSet results = selectStmt.executeQuery();) {
                while (results.next()) {
                    pets.add(mapPetFromResultSet(results));
                }
            }
        }
        return pets;
    }

    // Utility method to map a ResultSet to a Pet object
    private Pet mapPetFromResultSet(ResultSet results) throws SQLException {
        String licenseId = results.getString("LicenseId");
        String name = results.getString("Name");
        String species = results.getString("Species");
        String primaryBreed = results.getString("PrimaryBreed");
        int neighborZipId = results.getInt("NeighborZipId");
        return new Pet(licenseId, name, species, primaryBreed, neighborZipId);
    }


    //UPDATE
    public Pet update(Pet pet) throws SQLException {
        String updatePet = "UPDATE Pet SET Name=?, Species=?, PrimaryBreed=?, NeighborZipId=? WHERE LicenseId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePet);
            // Set parameters for the update based on the Pet object's current state
            updateStmt.setString(1, pet.getName());
            updateStmt.setString(2, pet.getSpecies());
            updateStmt.setString(3, pet.getPrimaryBreed());
            updateStmt.setInt(4, pet.getNeighborZipId());
            updateStmt.setString(5, pet.getLicenseId());
            
            updateStmt.executeUpdate();
            
            // Return the updated pet
            return pet;
        } finally {
            if(updateStmt != null) updateStmt.close();
            if(connection != null) connection.close();
        }
    }

    //DELETE
    public Pet delete(Pet pet) throws SQLException {
        String deletePet = "DELETE FROM Pet WHERE LicenseId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deletePet);
            deleteStmt.setString(1, pet.getLicenseId());
            deleteStmt.executeUpdate();

            // Return null on successful delete
            return null;
        } finally {
            if(deleteStmt != null) deleteStmt.close();
            if(connection != null) connection.close();
        }
    }
}
