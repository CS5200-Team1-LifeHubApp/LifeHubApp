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
		
		// Review
		Review review = new Review(1, currentTimestamp,"Content here", 5.0, 1, 1);
		review = reviewDao.create(review);
		
		// Restaurant
		Restaurant restaurant = new Restaurant(1, "Restaurant1", "Description here", "Hours here", "restaurant1@website.com", CuisineType.ASIAN, 1);
		restaurant = restaurantDao.create(restaurant);
		
		// Recommendation
		Recommendation recommendation = new Recommendation(1, 1, 1);
		recommendation = recommendationDao.create(recommendation);
		
		// Pet
		Pet pet = new Pet(1, "Betty Boop", "Cat", "Mix", 1);
		pet = petDao.create(pet);
		
		// Park
		Park park = new Park(1, 1, "Park1", 1, "noon-midnight", "Feature description here");
		park = parkDao.create(park);
		
		// Neighborhood
		Neighborhood neighborhood = new Neighborhood(1, "Shangri-La");
		neighborhood = neighborhoodDao.create(neighborhood);
		
		// FarmersMarket
		FarmersMarket farmersMarket = new FarmersMarket(1, 1, "Name1", "Dates1", "Hours1", "name1.com", MarketType.ALLYEAR);
		farmersMarket = farmersMarketDao.create(farmersMarket);
		
		// CrimeActivity
		CrimeActivity crimeActivity = new CrimeActivity(1, "City1", "State1", 1, "Burglary");
		crimeActivity = crimeActivityDao.create(crimeActivity);
		
		// Bookmark
		Bookmark bookmark = new Bookmark(1, currentTimestamp, 1, "Description1", 1);
		bookmark = bookmarkDao.create(bookmark);
		

		// READ.
		// TODO -- UPDATE DATA TO MATCH NEW CLASS LISTS
		BlogUsers bu1 = blogUsersDao.getBlogUserFromUserName("bu");
		List<BlogUsers> buList1 = blogUsersDao.getBlogUsersFromFirstName("bruce");
		System.out.format("Reading blog user: u:%s f:%s l:%s d:%s s:%s \n",
			bu1.getUserName(), bu1.getFirstName(), bu1.getLastName(), bu1.getDob(), bu1.getStatusLevel().name());
		for(BlogUsers bu : buList1) {
			System.out.format("Looping blog users: u:%s f:%s l:%s d:%s s:%s \n",
				bu.getUserName(), bu.getFirstName(), bu.getLastName(), bu.getDob(), bu.getStatusLevel().name());
		}
		List<BlogPosts> bpList1 = blogPostsDao.getBlogPostsForUser(bu1);
		for(BlogPosts bp : bpList1) {
			System.out.format("Looping blog posts: t:%s c:%s u:%s \n",
				bp.getTitle(), bp.getContent(), bu1.getUserName());
		}

	
	}
}
