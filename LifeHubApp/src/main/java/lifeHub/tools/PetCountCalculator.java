package lifeHub.tools;

import java.sql.*;

import java.util.HashMap;
import java.util.Map;
import lifeHub.dal.ConnectionManager;

/**
 * calculate the total  pet count in each zipcode and return in order, from biggest to smallest
 */
public class PetCountCalculator {

    public static void main(String[] args) {
        PetCountCalculator calculator = new PetCountCalculator();
        calculator.calculateAndPrintPetCounts();
    }

    public Map<Integer, Integer> calculateAndPrintPetCounts() {
        Map<Integer, Integer> petCounts = new HashMap<>();
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            try (Connection connection = connectionManager.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = retrievePetCounts(stmt)) {

                while (rs.next()) {
                    int zipcode = rs.getInt("NeighborZipId");
                    int petCount = rs.getInt("PetCount");
                    petCounts.put(zipcode, petCount);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return petCounts;
    }


    private ResultSet retrievePetCounts(Statement stmt) throws SQLException {
        String query = "SELECT NeighborZipId, COUNT(*) AS PetCount " +
                       "FROM Pet GROUP BY NeighborZipId ORDER BY PetCount DESC;";
        return stmt.executeQuery(query);
    }

    private void printResults(ResultSet rs) throws SQLException {
        System.out.println("Ranking\tZipcode\tPet Count");
        System.out.println("===========================");
        int ranking = 1;
        while (rs.next()) {
            int zipcode = rs.getInt("NeighborZipId");
            int petCount = rs.getInt("PetCount");
            System.out.println(ranking++ + "\t" + zipcode + "\t" + petCount);
        }
    }
}
