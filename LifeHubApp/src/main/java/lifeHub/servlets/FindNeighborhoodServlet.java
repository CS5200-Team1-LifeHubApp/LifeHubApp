package lifeHub.servlets;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lifeHub.dal.FilterDao;
import lifeHub.dal.ParkDao;
import lifeHub.model.Park;
import lifeHub.model.Restaurant.CuisineType;

// FindNeighborhoodServlet should match the form's 'action' attribute in JSP.
@WebServlet("/FindNeighborhoodServlet")
public class FindNeighborhoodServlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get the parameters.
    int featureId = Integer.parseInt(request.getParameter("FeatureId"));
    String cuisineType = request.getParameter("CuisineType");

    FilterDao filterDao = new FilterDao();
    Set<String> cities;
//    Set<Integer> zipcodes;
    try {
      cities = filterDao.getCitiesWithCriteria(featureId, CuisineType.valueOf(cuisineType));
    } catch (SQLException e) {
      System.err.println("Error msg: " + e);
      throw new RuntimeException(e);
    }

     request.setAttribute("neighborhoods", cities);
     RequestDispatcher dispatcher = request.getRequestDispatcher("FindNeighborhood.jsp");
     dispatcher.forward(request, response);
  }

  /**
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String featureIdStr = request.getParameter("FeatureId");
    int featureId = -1; // Default or invalid value
    if (featureIdStr != null && !featureIdStr.isEmpty()) {
      try {
        featureId = Integer.parseInt(featureIdStr);
      } catch (NumberFormatException e) {
        System.err.println("Invalid format for FeatureId: " + featureIdStr);
      }
    }

    String cuisineTypeStr = request.getParameter("CuisineType");
    CuisineType cuisineType = null;
    if (cuisineTypeStr != null && !cuisineTypeStr.isEmpty()) {
      try {
        cuisineType = CuisineType.valueOf(cuisineTypeStr.toUpperCase());
      } catch (IllegalArgumentException e) {
        System.err.println("Invalid CuisineType: " + cuisineTypeStr);
      }
    }

    if (featureId != -1 && cuisineType != null) {
      FilterDao filterDao = new FilterDao();
      Set<String> cities;
      try {
        cities = filterDao.getCitiesWithCriteria(featureId, cuisineType);
        request.setAttribute("neighborhoods", cities);
      } catch (SQLException e) {
        System.err.println("Error fetching cities: " + e);
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("FindNeighborhood.jsp");
    dispatcher.forward(request, response);
  }
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String featureIdStr = request.getParameter("FeatureId");
    int featureId = -1; // Default or invalid value
    if (featureIdStr != null && !featureIdStr.isEmpty()) {
      try {
        featureId = Integer.parseInt(featureIdStr);
      } catch (NumberFormatException e) {
        System.err.println("Invalid format for FeatureId: " + featureIdStr);
      }
    }

    String cuisineTypeStr = request.getParameter("CuisineType");
    CuisineType cuisineType = null;
    if (cuisineTypeStr != null && !cuisineTypeStr.isEmpty()) {
      try {
        cuisineType = CuisineType.valueOf(cuisineTypeStr.toUpperCase());
      } catch (IllegalArgumentException e) {
        System.err.println("Invalid CuisineType: " + cuisineTypeStr);
      }
    }

    if (featureId != -1 && cuisineType != null) {
      FilterDao filterDao = new FilterDao();
      Set<Integer> zipcodes;
      try {
        zipcodes = filterDao.getZipcodeWithCriteria(featureId, cuisineType);
        request.setAttribute("neighborhoods", zipcodes);
      } catch (SQLException e) {
        System.err.println("Error fetching cities: " + e);
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("FindNeighborhood.jsp");
    dispatcher.forward(request, response);
  }
}

