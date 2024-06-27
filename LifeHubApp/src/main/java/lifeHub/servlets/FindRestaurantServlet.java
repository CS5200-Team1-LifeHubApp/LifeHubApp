package lifeHub.servlets;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.dal.RestaurantDao;
import lifeHub.model.Restaurant;
import java.io.IOException;
import java.util.List;
import lifeHub.model.Restaurant.CuisineType;

@WebServlet("/FindRestaurant")
public class FindRestaurantServlet extends HttpServlet {

  private RestaurantDao restaurantDao;

  @Override
  public void init() throws ServletException {
    restaurantDao = RestaurantDao.getInstance();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String neighborhoodIdParam = request.getParameter("neighborZipId");
      if (neighborhoodIdParam != null && !neighborhoodIdParam.isEmpty()) {
        int neighborhoodId = Integer.parseInt(neighborhoodIdParam);
        String cuisineTypeParam = request.getParameter("cuisineType");
        Restaurant.CuisineType cuisineType;
        List<Restaurant> restaurants;

        // Check if cuisineTypeParam is not empty
        if (cuisineTypeParam != null && !cuisineTypeParam.isEmpty()) {
          cuisineType = CuisineType.valueOf(cuisineTypeParam);
          restaurants = restaurantDao.getRestaurantsByNeighborZipIdAndCuisineType(neighborhoodId, cuisineType);
        } else {
          restaurants = restaurantDao.getRestaurantsByNeighborZipId(neighborhoodId);
        }

        request.setAttribute("restaurants", restaurants);
      }
      request.getRequestDispatcher("/FindRestaurant.jsp").forward(request, response);
    } catch (NumberFormatException e) {
      // Handle invalid neighborhoodId format
      request.setAttribute("errorMessage", "Invalid neighborhood ID format.");
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    } catch (Exception e) {
      // Handle other exceptions
      e.printStackTrace();
      request.setAttribute("errorMessage", "Error: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }


}

