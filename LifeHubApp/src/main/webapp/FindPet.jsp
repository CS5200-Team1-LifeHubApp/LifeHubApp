<%@ page import="lifeHub.model.Pet" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pet Search Results</title>
</head>
<body>
    <h1>Pet Search Results</h1>
    <table border="1">
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
            // Assuming the search results are passed as a request attribute named "pets"
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
</body>
</html>
