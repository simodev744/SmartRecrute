<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    if (session.getAttribute("userId") != null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Connexion</h2>
<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
<form action="auth" method="post">
    <input type="hidden" name="action" value="login">
    <label>Email:</label>
    <input type="email" name="email" required><br>
    <label>Mot de passe:</label>
    <input type="password" name="password" required><br>
    <button type="submit">Se connecter</button>
</form>
<p>Pas encore inscrit ? <a href="auth?action=register">Créer un compte</a></p>
</body>
</html>
