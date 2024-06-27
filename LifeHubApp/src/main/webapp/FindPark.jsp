<%--
  Created by IntelliJ IDEA.
  User: franniezhou
  Date: 4/17/24
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="lifeHub.model.Park" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Find Parks</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Find Parks</h1>
    <form action="FindPark" method="post">
        <div class="form-group">
            <label for="neighborZipId">Neighborhood ID:</label>
            <input type="text" class="form-control" id="neighborZipId" name="neighborZipId" placeholder="Enter Neighborhood ID" required>
        </div>
        <div class="form-group">
            <label for="featureId">Feature ID:</label>
            <input type="text" class="form-control" id="featureId" name="featureId" placeholder="Enter Feature ID (optional)">
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
    <%
        List<Park> parks = (List<Park>) request.getAttribute("parks");
        if (parks != null && !parks.isEmpty()) {
            // Display parks if found
    %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Hours</th>
            <th>Feature ID</th>
        </tr>
        </thead>
        <tbody>
        <% for (Park park : parks) { %>
        <tr>
            <td><%= park.getParkId() %></td>
            <td><%= park.getName() %></td>
            <td><%= park.getFeatureDesc() %></td>
            <td><%= park.getHours() %></td>
            <td><%= park.getFeatureId() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <div>No parks found</div>
    <% } %>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
