<%--
  Created by IntelliJ IDEA.
  User: franniezhou
  Date: 4/17/24
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="lifeHub.model.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Search Restaurants</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Search Restaurants</h1>
    <form action="FindRestaurant" method="get">
        <div class="form-group">
            <label for="neighborZipId">Neighborhood ID:</label>
            <input type="text" class="form-control" id="neighborZipId" placeholder="Zipcode" name="neighborZipId">
        </div>
        <div class="form-group">
            <label for="cuisineType">Cuisine Type:</label>
            <select class="form-control" id="cuisineType" name="cuisineType">
                <option value="">Any</option>
                <option value="AMERICAN">American</option>
                <option value="ASIAN">Asian</option>
                <option value="EUROPEAN">European</option>
                <option value="HISPANIC">Hispanic</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Hours</th>
            <th>Website</th>
            <th>Cuisine Type</th>
            <th>Zip ID</th>
        </tr>
        </thead>
        <tbody>
        <% List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant restaurant : restaurants) {
        %>
        <tr>
            <td><%= restaurant.getRestaurantId() %></td>
            <td><%= restaurant.getName() %></td>
            <td><%= restaurant.getDescription() %></td>
            <td><%= restaurant.getHours() %></td>
            <td><%= restaurant.getWebSite() %></td>
            <td><%= restaurant.getCuisineType().toString() %></td>
            <td><%= restaurant.getNeighborZipId() %></td>
        </tr>
        <%      }
        } else if ("search".equals(request.getParameter("action"))) {
        %>
        <tr><td colspan="7">No restaurants found</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
