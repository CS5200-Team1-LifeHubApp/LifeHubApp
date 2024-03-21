package lifeHub.tools;

import lifeHub.dal.*;
import lifeHub.model.*;
import lifeHub.model.FarmersMarket.MarketType;
import lifeHub.model.Restaurant.CuisineType;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * main() runner, used for the app demo.
 *
 * Instructions:
 * 1. Create a new MySQL schema and then run the CREATE TABLE statements from lecture:
 * http://goo.gl/86a11H.
 * 2. Update ConnectionManager with the correct user, password, and schema.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		UsersDao usersDao = UsersDao.getInstance();
		ReviewDao reviewDao = ReviewDao.getInstance();
		RestaurantDao restaurantDao = RestaurantDao.getInstance();
		RecommendationDao recommendationDao = RecommendationDao.getInstance();
		PetDao petDao = PetDao.getInstance();
		ParkDao parkDao = ParkDao.getInstance();
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		FarmersMarketDao farmersMarketDao = FarmersMarketDao.getInstance();
		CrimeActivityDao crimeActivityDao = CrimeActivityDao.getInstance();
		BookmarkDao bookmarkDao = BookmarkDao.getInstance();

		// INSERT objects from our model.		
		// TODO -- UPDATE DATA TO MATCH NEW CLASS MODELS, ADD ADDITIONAL DATA AS NEEDED
		// Users
		Users user = new Users(1,"user1","Derek","Laister","dLaister@email.com","password1");
		user = usersDao.create(user);
		Users user1 = new Users(2,"user2","Alexander","Dickey","aDickey@email.com","password2");
		user = usersDao.create(user1);
		Users user2 = new Users(3,"user3","Jiyu","He","jHe@email.com","password3");
		user = usersDao.create(user2);
		Users user3 = new Users(4,"user4","Yueh-Chen","Tsai","yTsai@email.com","password4");
		user = usersDao.create(user3);
		Users user4 = new Users(5,"user5","Yiwen ","Wang","yWang@email.com","password5");
		user = usersDao.create(user4);
		Users user5 = new Users(6,"user6","Fan ","Zhou","fZhou@email.com","password6");
		user = usersDao.create(user5);
		Users user6 = new Users(7,"user7","Taiji ","Tai","tTai@email.com","password7");
		user = usersDao.create(user6);
		Users user7 = new Users(8,"user8","Alexander","Doe","aDoe","password8");
		user = usersDao.create(user7);

		// Timestamp
		Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		// Neighborhood
		Neighborhood neighborhood = new Neighborhood(1, "Shangri-La");
		neighborhood = neighborhoodDao.create(neighborhood);

		// Restaurant
		Restaurant restaurant = new Restaurant(1, "Restaurant1", "Description here", "Hours here", "restaurant1@website.com", CuisineType.ASIAN, 1);
		restaurant = restaurantDao.create(restaurant);

//		for (int xyz = 0; xyz < 200; xyz++) {
//			System.out.println("waiting");
//		}
		// Recommendation
		Recommendation recommendation = new Recommendation(1, 1, 1);
		recommendation = recommendationDao.create(recommendation);

		// Review
		Review review = new Review(1, currentTimestamp,"Content here", 5.0, 1, 1);
		review = reviewDao.create(review);

		// Pet
		Pet pet = new Pet(1, "Betty Boop", "Cat", "Mix", 1);
		pet = petDao.create(pet);

		// Park
		Park park = new Park(1, 1, "Park1", 1, "noon-midnight", "Feature description here");
		park = parkDao.create(park);

		// FarmersMarket
		FarmersMarket farmersMarket = new FarmersMarket(1, 1, "Name1", "Dates1", "Hours1", "name1.com", MarketType.ALLYEAR);
		farmersMarket = farmersMarketDao.create(farmersMarket);

		// CrimeActivity
		CrimeActivity crimeActivity = new CrimeActivity(1, "City1", "State1", 1, "Burglary");
		crimeActivity = crimeActivityDao.create(crimeActivity);

		// Bookmark
		Bookmark bookmark = new Bookmark(1, currentTimestamp, 1, "Description1", 1);
		bookmark = bookmarkDao.create(bookmark);


		// ---------------------------------  READ INFORMATION --------------------------------- //
		List <Neighborhood> neighborhoodList = NeighborhoodDao.getNeighborhoodByZip(1);
		System.out.println("----PRINTING HERE-----");
		for (Neighborhood n : neighborhoodList) {
			System.out.format("Looping Neighborhoods: NZipID:%d, City: %s",
					n.getNeighborZipId(),
					n.getCity());
		}

		List<Bookmark> bookmarkList = bookmarkDao.getBookmarkById(1);
		System.out.println("---get bookmarks by id---");
		for (Bookmark b : bookmarkList) {
			System.out.format("Looping Bookmark: id:%d created:%s userId:%d description:%s neighborZipId:%d\n",
					b.getBookmarkId(), b.getCreated().toString(), b.getUserId(),
					b.getDescription(), b.getNeighborZipId());
		}

		List<CrimeActivity> crimeActivityList = CrimeActivity.getCrimeActivityById(1);
		System.out.println("---get crime activities by id---");
		for (CrimeActivity c : crimeActivityList) {
			System.out.format("Looping CrimeActivity: CaseId:%d City:%s State:%s NeighborZipId:%d CrimeName:%s\n",
					c.getCaseId(), c.getCity(), c.getState(),
					c.getNeighborZipId(), c.getCrimeName());
		}


		// ---------------------------------  UPDATE INFORMATION --------------------------------- //

		bookmark.update(bookmark, "New Crime Name");
		System.out.format("Updating Bookmark: Created:%s UserId:%d Description:%s NeighborZipId:%d BookmarkId:%d\n",
				bookmark.getCreated(), bookmark.getUserId(), bookmark.getDescription(),
				bookmark.getNeighborZipId(), bookmark.getBookmarkId());

		String newCrimeName = "New Crime Name";
		try {
			CrimeActivityDao c = CrimeActivityDao.getInstance();
			c.updateCrimeName(crimeActivity, newCrimeName);
			System.out.format("Updating CrimeActivity: CaseId:%d City:%s State:%s NeighborZipId:%d CrimeName:%s\n",
					crimeActivity.getCaseId(), crimeActivity.getCity(), crimeActivity.getState(),
					crimeActivity.getNeighborZipId(), newCrimeName);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ---------------------------------  DELETE INFORMATION --------------------------------- //

		System.out.println("\n---Delete Bookmark---");
		try {
			bookmarkDao.delete(bookmark);
			System.out.println("Successful deletion of bookmark");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Delete CrimeActivity
		System.out.println("\n---Delete CrimeActivity---");
		try {
			boolean deletionSuccessful = crimeActivity.delete(); // Assuming delete() returns a boolean indicating success
			if (deletionSuccessful) {
				System.out.println("Successful deletion of crime activity");
			} else {
				System.out.println("Failed to delete crime activity");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
