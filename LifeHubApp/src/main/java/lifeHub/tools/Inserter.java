package lifeHub.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is a inserter class with insert methods, to see or test all the CRUD operation, go to CRUDTester.java
 */
public class Inserter {
    
    // Method to insert user data into the Users table manually, comment out when don't needs
    public static void insertUserData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Users (UserName, FirstName, LastName, Email, PasswordHash) VALUES ('emelinaw', 'Yiwen', 'Wang', 'eyyw@hotmail.com', 'a9f24d8ae410ef9f4be7a97e70191144')",
        };
        
        executeBatchInsert(connection, insertStatements);
    }
    
    // Method to insert crime data into the CrimeActivity table manually, comment out when don't need
    public static void insertCrimeData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO CrimeActivity (CaseId, CrimeName, City, State, NeighborZipId) VALUES ('11112345', 'Theft', 'Seattle', 'Washington', 98105)",
        };
        
        executeBatchInsert(connection, insertStatements);
    }
    
    // Method to insert Neighborhood Data into the Neighborhood table manually, comment out when don't need
    public static void insertNeighborhoodData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO NeighborhoodbyZip (NeighborZipId, City) VALUES (98000, 'NoWhere')",
        };

        executeBatchInsert(connection, insertStatements);
    }
    
    // Method to insert Review Data into the Review table manually, comment out when don't need
    public static void insertReviewData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Review (Created, Content, Rating, UserId, NeighborZipId) VALUES ('2023-12-18 19:50:51', 'Love the vibe!', 5, 1, 98004)",
        };

        executeBatchInsert(connection, insertStatements);
    }
    
    // Method to insert FarmersMarket Data into the FarmersMarket table manually, comment out when don't need
    public static void insertFarmersMarketData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO FarmersMarket (NeighborZipId, Name, Dates, Hours, Website, MarketType) VALUES (98105, 'University District Farmers Market', 'Saturday', '9:00 AM - 2:00 PM', 'http://udistrictmarket.com/', 'SEASONAL')",
            "INSERT INTO FarmersMarket (NeighborZipId, Name, Dates, Hours, Website, MarketType) VALUES (98106, 'West Seattle Farmers Market', 'Sunday', '10:00 AM - 2:00 PM', 'https://seattlefarmersmarkets.org/markets/west-seattle', 'SEASONAL')"
        };

        executeBatchInsert(connection, insertStatements);
    }

    // Method to insert Park Data into the Park table manually, comment out when don't need
    public static void insertParkData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Park (ParkId, NeighborZipId, Name, FeatureId, Hours, FeatureDesc) VALUES (1, 98105, 'Green Lake Park', 1, '6:00 AM - 10:00 PM', 'Large park with a lake for boating and walking trails.')",
        };

        executeBatchInsert(connection, insertStatements);
    }

    // Method to insert Pet Data into the Pet table manually, comment out when don't need
    public static void insertPetData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Pet (LicenseId, Name, Species, PrimaryBreed, NeighborZipId) VALUES ('12345', 'Max', 'Dog', 'Labrador Retriever', 98105)",
        };

        executeBatchInsert(connection, insertStatements);
    }

    // Method to insert Recommendation Data into the Recommendation table manually, comment out when don't need
    public static void insertRecommendationData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Recommendation (UserId, NeighborZipId) VALUES (1, 98105)",
        };

        executeBatchInsert(connection, insertStatements);
    }

    // Method to insert Restaurant Data into the Restaurant table manually, comment out when don't need
    public static void insertRestaurantData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Restaurant (Name, Description, Hours, Website, CuisineType, NeighborZipId) VALUES ('Seattle Grill', 'A cozy grill restaurant offering a variety of American dishes.', '11:00 AM - 9:00 PM', 'http://seattlegrill.com/', 'AMERICAN', 98105)",
        };

        executeBatchInsert(connection, insertStatements);
    }
    
    // Method to insert Bookmark Data into the Bookmark table manually, comment out when don't need
    public static void insertBookmarkData(Connection connection) throws SQLException {
        String[] insertStatements = {
            "INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) VALUES ('2023-11-15 19:52:42', 1, 'Must visit', 98104)",
            "INSERT INTO Bookmark (Created, UserId, Description, NeighborZipId) VALUES ('2023-08-30 19:52:42', 2, 'Must visit', 98105)"
        };

        executeBatchInsert(connection, insertStatements);
    }

    
    // Method to execute batch insert statements
    private static void executeBatchInsert(Connection connection, String[] insertStatements) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("")) {
            for (String statement : insertStatements) {
                preparedStatement.addBatch(statement);
            }
            preparedStatement.executeBatch();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting data: " + e.getMessage());
            throw e;
        }
    }
}
