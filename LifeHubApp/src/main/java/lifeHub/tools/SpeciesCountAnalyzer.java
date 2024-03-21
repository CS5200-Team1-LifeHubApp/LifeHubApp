package lifeHub.tools;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lifeHub.dal.PetDao;
import lifeHub.model.Pet;

/**
 * Utility class to generate a summary table of species counts by zipcode.
 * Find out each Species found (dog count and cat count) in each zipcode, 
 * and create a new table one column is the zipcode, 
 * one column is the result: with the higher count species would be in this column, 
 * and if it is the same, just say "miracle paradise for both cat and dog lovers"
 */
public class SpeciesCountAnalyzer {

    /**
     * Generates a summary table of species counts by zipcode.
     *
     * @return A map containing zipcode as key and the dominant species count or "miracle paradise" as value.
     * @throws SQLException if there's an error accessing the database.
     */
    public Map<Integer, String> generateSpeciesSummary() throws SQLException {
        PetDao petDao = PetDao.getInstance();
        Map<Integer, Integer> dogCounts = new HashMap<>();
        Map<Integer, Integer> catCounts = new HashMap<>();

        // Retrieve pets and count species by zipcode
        for (int zipcode = 10000; zipcode <= 99999; zipcode++) {
            // Get all pets in the zipcode
            // Assuming PetDao has a method to get pets by zipcode
            // Adjust this according to your PetDao implementation
            List<Pet> petsInZipcode = petDao.getPetsByZipcode(zipcode);

            int dogCount = 0;
            int catCount = 0;

            // Count dog and cat species
            for (Pet pet : petsInZipcode) {
                if ("dog".equalsIgnoreCase(pet.getSpecies())) {
                    dogCount++;
                } else if ("cat".equalsIgnoreCase(pet.getSpecies())) {
                    catCount++;
                }
            }

            // Store counts for the zipcode
            dogCounts.put(zipcode, dogCount);
            catCounts.put(zipcode, catCount);
        }

        // Generate summary table
        Map<Integer, String> summaryTable = new HashMap<>();
        for (int zipcode : dogCounts.keySet()) {
            int dogCount = dogCounts.get(zipcode);
            int catCount = catCounts.get(zipcode);

            if (dogCount > catCount) {
                summaryTable.put(zipcode, "Dog's base: " + dogCount);
            } else if (dogCount < catCount) {
                summaryTable.put(zipcode, "Cat's base: " + catCount);
            } else {
                summaryTable.put(zipcode, "Miracle paradise for both cat and dog lovers");
            }
        }

        return summaryTable;
    }
}
