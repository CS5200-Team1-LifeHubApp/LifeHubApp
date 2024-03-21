package lifeHub.tools;

import lifeHub.dal.*;
import lifeHub.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;

public class CRUDTester {

	public static void main(String[] args) throws SQLException {
		testPet();
		testBookmark();
		testCrimeActivity();
		testFarmersMarket();
		testNeighborhood();
		testPark();
		testRecommendation();
		testRestaurant();
		testReview();
		testUser();
	}

	private static void testPet() {
		// DAO instance
		PetDao petDao = PetDao.getInstance();
		System.out.println("=======Start of Pet CRUD testing=======");
		// CREATE
		Pet newPet = new Pet("123ABC", "Fido", "Dog", "Labrador", 98004);
		try {
			newPet = petDao.create(newPet);

			System.out.println("Pet created: " + newPet.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// READ
		try {
			Pet pet = petDao.getPetByLicenseId("123ABC");
			if (pet != null) {
				System.out.println("Pet retrieved: " + pet.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// UPDATE
		String newName = "Rex";
		newPet.setName(newName);
		try {
			Pet updatedPet = petDao.update(newPet);
			if (updatedPet != null) {
				System.out.println("Pet name updated: " + updatedPet.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DELETE
		try {
			Pet deletedPet = petDao.delete(newPet);
			if (deletedPet == null) {
				System.out.println("Pet deleted: " + newPet.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=======End of Pet CRUD testing=======");
	}

	private static void testBookmark() {
		// DAO instance
		BookmarkDao bookmarkDao = BookmarkDao.getInstance();

		System.out.println("=======Start of Bookmark CRUD testing=======");

		// CREATE
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Bookmark newBookmark = new Bookmark(0, now, 1, "This is a test bookmark description.", 98004);
		try {
			newBookmark = bookmarkDao.create(newBookmark);
			System.out.println("Bookmark created with ID: " + newBookmark.getBookmarkId());
		} catch (SQLException e) {
			System.out.println("Error creating bookmark");
			e.printStackTrace();
			return; // Exit if create fails to prevent further errors
		}

		// READ
		try {
			Bookmark bookmark = bookmarkDao.getBookmarkById(newBookmark.getBookmarkId());
			if (bookmark != null) {
				System.out.println("Bookmark retrieved: " + bookmark.getDescription());
			} else {
				System.out.println("Bookmark not found with ID: " + newBookmark.getBookmarkId());
			}
		} catch (SQLException e) {
			System.out.println("Error retrieving bookmark");
			e.printStackTrace();
		}

		// UPDATE
		String newDescription = "Updated bookmark description.";
		newBookmark.setDescription(newDescription);
		try {
			Bookmark updatedBookmark = bookmarkDao.update(newBookmark); // Now explicitly implemented
			System.out.println("Bookmark description updated: " + updatedBookmark.getDescription());
		} catch (SQLException e) {
			System.out.println("Error updating bookmark");
			e.printStackTrace();
		}

		// DELETE
		try {
			Bookmark deletedBookmark = bookmarkDao.delete(newBookmark);
			if (deletedBookmark == null) {
				System.out.println("Bookmark deleted with ID: " + newBookmark.getBookmarkId());
			}
		} catch (SQLException e) {
			System.out.println("Error deleting bookmark");
			e.printStackTrace();
		}

		System.out.println("=======End of Bookmark CRUD testing=======");
	}

	private static void testCrimeActivity() {
		// DAO instance
		CrimeActivityDao crimeActivityDao = CrimeActivityDao.getInstance();
		System.out.println("=======Start of Crime Activity CRUD testing=======");

		// CREATE
		CrimeActivity newCrimeActivity = new CrimeActivity("CASE123", "Springfield", "StateX", 98004,
				"Example Crime Name");
		try {
			newCrimeActivity = crimeActivityDao.create(newCrimeActivity);
			System.out.println("Crime Activity created: " + newCrimeActivity.getCaseId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			CrimeActivity crimeActivity = crimeActivityDao.getCrimeActivityByCaseId("CASE123");
			if (crimeActivity != null) {
				System.out.println("Crime Activity retrieved: " + crimeActivity.getCrimeName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// updating the crime name and city
		String newCrimeName = "Updated Crime Name";
		String newCity = "New Springfield";
		newCrimeActivity.setCrimeName(newCrimeName);
		newCrimeActivity.setCity(newCity);
		try {
			CrimeActivity updatedCrimeActivity = crimeActivityDao.update(newCrimeActivity);
			if (updatedCrimeActivity != null) {
				System.out.println("Crime Activity updated: " + updatedCrimeActivity.getCrimeName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			CrimeActivity deletedCrimeActivity = crimeActivityDao.delete(newCrimeActivity);
			if (deletedCrimeActivity == null) {
				System.out.println("Crime Activity deleted: " + newCrimeActivity.getCaseId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Crime Activity CRUD testing=======");
	}

	private static void testFarmersMarket() {
		// DAO instance
		FarmersMarketDao marketDao = FarmersMarketDao.getInstance();
		System.out.println("=======Start of FarmersMarket CRUD testing=======");

		// CREATE
		FarmersMarket newMarket = new FarmersMarket(0, 98004, "Local Farmers Market", "Every Saturday", "8AM - 2PM",
				"www.localfarmersmarket.com", FarmersMarket.MarketType.SEASONAL);
		try {
			newMarket = marketDao.create(newMarket);
			System.out.println("Farmers Market created: " + newMarket.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			FarmersMarket market = marketDao.getMarketById(newMarket.getMarketId());
			if (market != null) {
				System.out.println("Farmers Market retrieved: " + market.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// updating the market's dates and hours
		String newDates = "Every Sunday";
		String newHours = "9AM - 3PM";
		newMarket.setDates(newDates);
		newMarket.setHours(newHours);
		try {
			FarmersMarket updatedMarket = marketDao.update(newMarket);
			if (updatedMarket != null) {
				System.out.println(
						"Farmers Market updated: " + updatedMarket.getDates() + ", " + updatedMarket.getHours());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			FarmersMarket deletedMarket = marketDao.delete(newMarket);
			if (deletedMarket == null) {
				System.out.println("Farmers Market deleted: " + newMarket.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of FarmersMarket CRUD testing=======");
	}

	private static void testNeighborhood() {
		// DAO instance
		NeighborhoodDao neighborhoodDao = NeighborhoodDao.getInstance();
		System.out.println("=======Start of Neighborhood CRUD testing=======");

		// CREATE
		Neighborhood newNeighborhood = new Neighborhood(91000, "ANNNNN");
		try {
			newNeighborhood = neighborhoodDao.create(newNeighborhood);
			System.out.println("Neighborhood created: " + newNeighborhood.getCity());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			Neighborhood neighborhood = neighborhoodDao.getNeighborhoodByZipId(91000);
			if (neighborhood != null) {
				System.out.println("Neighborhood retrieved: " + neighborhood.getCity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// updating the city of the neighborhood
		String newCity = "New Springfield";
		newNeighborhood.setCity(newCity);
		try {
			Neighborhood updatedNeighborhood = neighborhoodDao.update(newNeighborhood);
			if (updatedNeighborhood != null) {
				System.out.println("Neighborhood city updated: " + updatedNeighborhood.getCity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			Neighborhood deletedNeighborhood = neighborhoodDao.delete(newNeighborhood);
			if (deletedNeighborhood == null) {
				System.out.println("Neighborhood deleted: " + newNeighborhood.getCity());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Neighborhood CRUD testing=======");
	}

	private static void testPark() {
		// DAO instance
		ParkDao parkDao = ParkDao.getInstance();
		System.out.println("=======Start of Park CRUD testing=======");

		// CREATE
		Park newPark = new Park(1, 98004, "Greenwood Park", 101, "9AM - 8PM", "Large playground and picnic areas");
		try {
			newPark = parkDao.create(newPark);
			System.out.println("Park created: " + newPark.getName() + " with feature: " + newPark.getFeatureDesc());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			Park park = parkDao.getParkById(newPark.getParkId(), newPark.getFeatureId());
			if (park != null) {
				System.out.println("Park retrieved: " + park.getName() + ", Feature: " + park.getFeatureDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// Assuming you're updating the park's hours and feature description
		String newHours = "10AM - 9PM";
		String newFeatureDesc = "Updated playground area";
		newPark.setHours(newHours);
		newPark.setFeatureDesc(newFeatureDesc);
		try {
			Park updatedPark = parkDao.update(newPark);
			if (updatedPark != null) {
				System.out.println("Park hours updated to: " + updatedPark.getHours() + ", Feature updated to: "
						+ updatedPark.getFeatureDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			Park deletedPark = parkDao.delete(newPark);
			if (deletedPark == null) {
				System.out.println("Park deleted: " + newPark.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Park CRUD testing=======");
	}

	private static void testRecommendation() {
		// DAO instance
		RecommendationDao recommendationDao = RecommendationDao.getInstance();
		System.out.println("=======Start of Recommendation CRUD testing=======");

		// CREATE
		Recommendation newRecommendation = new Recommendation(0, 1, 98004); // Assuming a user ID of 1 for simplicity
		try {
			newRecommendation = recommendationDao.create(newRecommendation);
			System.out.println("Recommendation created with ID: " + newRecommendation.getRecommendationId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			Recommendation recommendation = recommendationDao
					.getRecommendationById(newRecommendation.getRecommendationId());
			if (recommendation != null) {
				System.out.println("Recommendation retrieved with User ID: " + recommendation.getUserId() + " and Zip: "
						+ recommendation.getNeighborZipId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// Updating the UserId for the recommendation
		int newUserId = 2; // Change to a different user ID
		newRecommendation.setUserId(newUserId);
		try {
			Recommendation updatedRecommendation = recommendationDao.update(newRecommendation);
			if (updatedRecommendation != null) {
				System.out.println("Recommendation updated with new User ID: " + updatedRecommendation.getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			boolean deleted = recommendationDao.delete(newRecommendation);
			if (deleted) {
				System.out.println("Recommendation deleted with ID: " + newRecommendation.getRecommendationId());
			} else {
				System.out.println("Recommendation deletion failed for ID: " + newRecommendation.getRecommendationId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Recommendation CRUD testing=======");
	}

	private static void testRestaurant() {
		// DAO instance
		RestaurantDao restaurantDao = RestaurantDao.getInstance();
		System.out.println("=======Start of Restaurant CRUD testing=======");

		// CREATE
		Restaurant newRestaurant = new Restaurant(0, "Giovanni's",
				"Authentic Italian cuisine in the heart of the city.", "9AM - 11PM", "www.giovannis.com",
				Restaurant.CuisineType.EUROPEAN, 98004);
		try {
			newRestaurant = restaurantDao.create(newRestaurant);
			System.out.println("Restaurant created: " + newRestaurant.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			Restaurant restaurant = restaurantDao.getRestaurantById(newRestaurant.getRestaurantId());
			if (restaurant != null) {
				System.out.println(
						"Restaurant retrieved: " + restaurant.getName() + ", Cuisine: " + restaurant.getCuisineType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// Assuming you're updating the restaurant's hours and website
		String newHours = "8AM - 10PM";
		String newWebsite = "www.giovannisnew.com";
		newRestaurant.setHours(newHours);
		newRestaurant.setWebsite(newWebsite);
		try {
			Restaurant updatedRestaurant = restaurantDao.update(newRestaurant);
			if (updatedRestaurant != null) {
				System.out.println("Restaurant hours updated to: " + updatedRestaurant.getHours()
						+ ", Website updated to: " + updatedRestaurant.getWebsite());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			Restaurant deletedRestaurant = restaurantDao.delete(newRestaurant);
			if (deletedRestaurant == null) {
				System.out.println("Restaurant deleted: " + newRestaurant.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Restaurant CRUD testing=======");
	}

	private static void testReview() {
		// DAO instance
		ReviewDao reviewDao = ReviewDao.getInstance();
		System.out.println("=======Start of Review CRUD testing=======");

		// CREATE
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Review newReview = new Review(0, now, "Great place to relax and enjoy nature.", 4.5, 1, 98004);
		try {
			newReview = reviewDao.create(newReview);
			System.out.println("Review created with ID: " + newReview.getReviewId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// READ
		try {
			Review review = reviewDao.getReviewById(newReview.getReviewId());
			if (review != null) {
				System.out.println("Review retrieved: " + review.getContent() + " with rating: " + review.getRating());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// UPDATE
		// Updating the rating and content of the review
		double newRating = 5.0;
		String newContent = "Absolutely love this place! Best experience ever.";
		newReview.setRating(newRating);
		newReview.setContent(newContent);
		try {
			Review updatedReview = reviewDao.update(newReview);
			if (updatedReview != null) {
				System.out.println("Review updated with new rating: " + updatedReview.getRating() + " and new content: "
						+ updatedReview.getContent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DELETE
		try {
			Review deletedReview = reviewDao.delete(newReview);
			if (deletedReview == null) {
				System.out.println("Review deleted with ID: " + newReview.getReviewId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=======End of Review CRUD testing=======");
	}
	
	private static void testUser() {
	    // DAO instance
	    UserDao userDao = UserDao.getInstance();
	    System.out.println("=======Start of User CRUD testing=======");
	    // CREATE
	    User newUser = new User(0, "john_doe", "John", "Doe", "john@example.com", "password123");
	    try {
	        newUser = userDao.createUser(newUser);

	        System.out.println("User created: " + newUser.getUserName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    // READ
	    try {
	        User user = userDao.getUserById(newUser.getUserId());
	        if (user != null) {
	            System.out.println("User retrieved: " + user.getUserName());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    // UPDATE
	    String newFirstName = "Johnny";
	    newUser.setFirstName(newFirstName);
	    try {
	        User updatedUser = userDao.updateUser(newUser);
	        if (updatedUser != null) {
	            System.out.println("User first name updated: " + updatedUser.getFirstName());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    // DELETE
	    try {
	        userDao.deleteUser(newUser.getUserId());
	        System.out.println("User deleted: " + newUser.getUserName());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println("=======End of User CRUD testing=======");
	}
}