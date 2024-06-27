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
        String searchTerm = request.getParameter("searchTerm");
        String speciesFilter = request.getParameter("species");
        String breedFilter = request.getParameter("breed");

        try {
            List<Pet> pets;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                pets = petDao.getPetsByZipcode(Integer.parseInt(searchTerm));
                if (speciesFilter != null && !speciesFilter.isEmpty()) {
                    pets.removeIf(p -> !p.getSpecies().equalsIgnoreCase(speciesFilter));
                }
                if (breedFilter != null && !breedFilter.isEmpty()) {
                    pets.removeIf(p -> !p.getPrimaryBreed().equalsIgnoreCase(breedFilter));
                }
            } else {
                pets = null;
            }

            request.setAttribute("pets", pets);
            request.getRequestDispatcher("/FindPet.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
