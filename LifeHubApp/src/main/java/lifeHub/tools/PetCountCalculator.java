package lifeHub.tools;

import java.sql.*;

import lifeHub.dal.ConnectionManager;

/**
 * calculate the total  pet count in each zipcode and return in order, from biggest to smallest
 */
public class PetCountCalculator {

    public static void main(String[] args) {
        PetCountCalculator calculator = new PetCountCalculator();
        calculator.calculateAndPrintPetCounts();
    }

    public void calculateAndPrintPetCounts() {
        try {
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                connection = connectionManager.getConnection();
                stmt = connection.createStatement();

                rs = retrievePetCounts(stmt);

                printResults(rs);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
