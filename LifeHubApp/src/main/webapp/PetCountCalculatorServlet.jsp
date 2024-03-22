<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Count Results</title>
</head>
<body>
    <h1>Pet Count Results</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Ranking</th>
                <th>Zipcode</th>
                <th>Pet Count</th>
            </tr>
        </thead>
        <tbody>
            <% 
            // Assuming the pet counts are passed as a request attribute named "petCounts"
            Map<Integer, Integer> petCounts = (Map<Integer, Integer>) request.getAttribute("petCounts");
            if (petCounts != null) {
                int ranking = 1;
                for (Map.Entry<Integer, Integer> entry : petCounts.entrySet()) {
                    Integer zipcode = entry.getKey();
                    Integer count = entry.getValue();
            %>
            <tr>
                <td><%= ranking++ %></td>
                <td><%= zipcode %></td>
                <td><%= count %></td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="3">No data available</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
