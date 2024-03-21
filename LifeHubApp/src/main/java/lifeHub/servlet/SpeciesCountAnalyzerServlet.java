package lifeHub.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lifeHub.tools.SpeciesCountAnalyzer;

@WebServlet("/SpeciesCountAnalyzerServlet")
public class SpeciesCountAnalyzerServlet extends HttpServlet {
    private SpeciesCountAnalyzer speciesCountAnalyzer;

    @Override
    public void init() throws ServletException {
        speciesCountAnalyzer = new SpeciesCountAnalyzer();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<Integer, String> speciesSummary = speciesCountAnalyzer.generateSpeciesSummary();
            request.setAttribute("speciesSummary", speciesSummary);
            request.getRequestDispatcher("/species_count.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
