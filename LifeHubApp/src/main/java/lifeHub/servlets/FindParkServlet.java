package lifeHub.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.dal.ParkDao;
import lifeHub.model.Park;

@WebServlet("/FindPark")
public class FindParkServlet extends HttpServlet {

  private ParkDao parkDao;

  @Override
  public void init() throws ServletException {
    parkDao = ParkDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // Forward the request to the JSP page without processing parameters
    request.getRequestDispatcher("/FindPark.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      int neighborZipId = Integer.parseInt(request.getParameter("neighborZipId"));
      String featureIdParam = request.getParameter("featureId");
      int featureId = (featureIdParam != null && !featureIdParam.isEmpty()) ? Integer.parseInt(featureIdParam) : -1;

      List<Park> parks;
      if (featureId != -1) {
        parks = parkDao.getParksGroupedByZipcodeWithFeatureId(neighborZipId, featureId);
      } else {
        parks = parkDao.getParksGroupedByZipcode(neighborZipId);
      }

      request.setAttribute("parks", parks);
      request.getRequestDispatcher("/FindPark.jsp").forward(request, response);
    } catch (NumberFormatException e) {
      // Handle invalid input format
      request.setAttribute("errorMessage", "Invalid input format.");
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    } catch (SQLException e) {
      // Handle SQL exception
      e.printStackTrace();
      request.setAttribute("errorMessage", "Database access error: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    } catch (Exception e) {
      // Handle other exceptions
      e.printStackTrace();
      request.setAttribute("errorMessage", "Error: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
