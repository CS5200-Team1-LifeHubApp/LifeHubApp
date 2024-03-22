package lifeHub.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.dal.PetDao;
import lifeHub.model.Pet;

@WebServlet("/FindPet")
public class FindPet extends HttpServlet {
    private PetDao petDao;

    @Override
    public void init() throws ServletException {
        petDao = PetDao.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String searchTerm = request.getParameter("searchTerm");

        try {
            List<Pet> pets = null;

            if (searchType != null && searchTerm != null) {
                switch (searchType) {
                    case "name":
                        pets = petDao.getPetsByName(searchTerm);
                        break;
                    case "zipcode":
                        pets = petDao.getPetsByZipcode(Integer.parseInt(searchTerm));
                        break;
                    case "breed":
                        pets = petDao.getPetsByBreed(searchTerm);
                        break;
                    case "species":
                        pets = petDao.getPetsBySpecies(searchTerm);
                        break;
                }
            }

            request.setAttribute("pets", pets);
            request.getRequestDispatcher("/FindPet.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
