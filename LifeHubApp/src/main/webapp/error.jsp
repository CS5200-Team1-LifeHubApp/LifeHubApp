<%--
  Created by IntelliJ IDEA.
  User: franniezhou
  Date: 4/17/24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
<h1>Error</h1>
<p><%= request.getAttribute("errorMessage") %></p>
</body>
</html>
