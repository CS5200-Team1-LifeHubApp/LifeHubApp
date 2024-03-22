<%--
  Created by IntelliJ IDEA.
  User: franniezhou
  Date: 3/21/24
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Find Your Dream Neighborhood</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Filter Your Neighborhood Out!</h2>
    <form action="FindNeighborhoodServlet" method="post" class="mb-3">
        <div class="form-group">
            <label for="ParkFeature">Park Feature:</label>
            <select id="ParkFeature" name="FeatureId" class="form-control">
                <option value="" selected disabled>Select a park feature</option>
                <option value="7">Boat Launch</option>
                <option value="27">Restrooms</option>
                <option value="34">View</option>
            </select>
        </div>
        <div class="form-group">
            <label for="CuisineType">Restaurant Cuisine Type:</label>
            <select id="CuisineType" name="CuisineType" class="form-control">
                <option value="" selected disabled>Select a cuisine type</option>
                <option value="ASIAN">Asian</option>
                <option value="HISPANIC">Hispanic</option>
                <option value="AMERICAN">American</option>
                <option value="AFRICAN">African</option>
            </select>
        </div>
        <input type="submit" value="Search" class="btn btn-primary">
    </form>

    <h3>Search Results: Neighborhoods</h3>
    <%
        Set<Integer> neighborhoods = (Set<Integer>) request.getAttribute("neighborhoods");
        if (neighborhoods != null && !neighborhoods.isEmpty()) {
    %>
    <p>Found neighborhoods with the selected criteria:</p>
    <ul class="list-group">
        <% for (Integer zipcode : neighborhoods) { %>
        <li class="list-group-item"><%= zipcode %></li>
        <% } %>
    </ul>
    <%  } else { %>
    <p>No neighborhoods found matching the selected criteria or the criteria has not yet been searched.</p>
    <%  } %>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
