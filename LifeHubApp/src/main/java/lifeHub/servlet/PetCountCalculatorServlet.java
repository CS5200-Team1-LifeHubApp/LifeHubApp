package lifeHub.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.tools.PetCountCalculator;

@WebServlet("/PetCountCalculatorServlet")
public class PetCountCalculatorServlet extends HttpServlet {
    private PetCountCalculator petCountCalculator;

    @Override
    public void init() throws ServletException {
        petCountCalculator = new PetCountCalculator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        petCountCalculator.calculateAndPrintPetCounts();
        response.sendRedirect(request.getContextPath() + "/pet_count.jsp");
    }
}
