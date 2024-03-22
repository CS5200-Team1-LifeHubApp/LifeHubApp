package lifeHub.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.tools.PetCountCalculator;

@WebServlet("/PetCountCalculatorServlet")
public class PetCountCalculatorServlet extends HttpServlet {
    private PetCountCalculator petCountCalculator;
    private ResultSet rs;

    @Override
    public void init() throws ServletException {
        petCountCalculator = new PetCountCalculator();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> petCounts = petCountCalculator.calculateAndPrintPetCounts();
        request.setAttribute("petCounts", petCounts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/PetCountCalculatorServlet.jsp");
        dispatcher.forward(request, response);
    }

}
