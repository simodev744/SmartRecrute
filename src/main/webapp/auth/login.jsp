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
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
<div class="bg-white p-8 rounded-lg shadow-lg w-96">
    <h2 class="text-2xl font-bold text-center mb-4">Connexion</h2>
    <% if (request.getAttribute("error") != null) { %>
    <p class="text-red-500 text-center"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="auth" method="post" class="space-y-4">
        <input type="hidden" name="action" value="login">
        <div>
            <label class="block font-medium">Email:</label>
            <input type="email" name="email" required class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div>
            <label class="block font-medium">Mot de passe:</label>
            <input type="password" name="password" required class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition">Se connecter</button>
    </form>
    <p class="text-center mt-4">Pas encore inscrit ? <a href="auth?action=register" class="text-blue-500 hover:underline">Créer un compte</a></p>
</div>
</body>
</html>
