<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Species Count Summary</title>
</head>
<body>
    <h1>Species Count Summary</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Zipcode</th>
                <th>Summary</th>
            </tr>
        </thead>
        <tbody>
            <% 
            // Assuming the species summary is passed as a request attribute named "speciesSummary"
            Map<Integer, String> speciesSummary = (Map<Integer, String>) request.getAttribute("speciesSummary");
            if (speciesSummary != null) {
                for (Map.Entry<Integer, String> entry : speciesSummary.entrySet()) {
                    Integer zipcode = entry.getKey();
                    String summary = entry.getValue();
            %>
            <tr>
                <td><%= zipcode %></td>
                <td><%= summary %></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="2">No data available</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
