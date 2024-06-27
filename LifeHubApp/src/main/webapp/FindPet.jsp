<%@ page import="lifeHub.model.Pet" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pet Search Results</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Pet Search Results</h1>
    <form method="get" action="FindPet">
        <div class="form-row">
            <div class="col">
                <input type="text" class="form-control" placeholder="Zipcode" name="searchTerm">
            </div>
            <div class="col">
                <select class="form-control" name="species">
                    <option value="">Select Species</option>
                    <option value="Dog">Dog</option>
                    <option value="Cat">Cat</option>
                </select>
            </div>
            <div class="col">
                <select class="form-control" name="breed">
                    <option value="">Select Breed</option>
                    <option value="Labrador">Labrador</option>
                    <option value="Persian">Persian</option>
                    <option value="Terrier">Terrier</option>
                    <!-- Add more breeds as needed -->
                </select>
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </div>
    </form>
    <table class="table table-bordered mt-3">
        <thead>
        <tr>
            <th>License ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Primary Breed</th>
            <th>Neighbor Zip ID</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Pet> pets = (List<Pet>) request.getAttribute("pets");
            if (pets != null && !pets.isEmpty()) {
                for (Pet pet : pets) {
        %>
        <tr>
            <td><%= pet.getLicenseId() %></td>
            <td><%= pet.getName() %></td>
            <td><%= pet.getSpecies() %></td>
            <td><%= pet.getPrimaryBreed() %></td>
            <td><%= pet.getNeighborZipId() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5">No pets found</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
