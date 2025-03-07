<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.smartrecrute.models.Candidature" %>
<%@ include file="includes/navbar.jsp" %>

<%
    List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Candidatures</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<div class="container mx-auto mt-10 p-6 bg-white shadow-lg rounded-lg">
    <h2 class="text-2xl font-bold mb-4">📋 Liste des Candidatures</h2>

    <table class="min-w-full bg-white border border-gray-200">
        <thead>
        <tr class="bg-gray-200 text-gray-700">
            <th class="py-2 px-4 border">ID</th>
            <th class="py-2 px-4 border">Nom du Candidat</th>
            <th class="py-2 px-4 border">Email</th>
            <th class="py-2 px-4 border">CV</th>
            <th class="py-2 px-4 border">Date de Candidature</th>
            <th class="py-2 px-4 border">Actions</th>
            <th class="py-2 px-4 border">Status</th>
        </tr>
        </thead>
        <tbody>
        <% if (candidatures != null && !candidatures.isEmpty()) {
            for (Candidature candidature : candidatures) { %>
        <tr class="border">
            <td class="py-2 px-4 border"><%= candidature.getId() %></td>
            <td class="py-2 px-4 border"><%= candidature.getUtilisateur().getNomUtilisateur() %></td>
            <td class="py-2 px-4 border"><%= candidature.getUtilisateur().getEmail() %></td>
            <td class="py-2 px-4 border">
                <a href="<%= candidature.getUtilisateur().getCv() %>" class="text-blue-500 underline" target="_blank">Voir CV</a>
            </td>
            <td class="py-2 px-4 border"><%= candidature.getDateCandidature() %></td>
            <td class="py-2 px-4 border">
                <a href="recruteur?action=acceptCandidature&&id=<%= candidature.getId() %>" class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600">Accepter</a>
                <a href="recruteur?action=rejectCandidature&&id=<%= candidature.getId() %>" class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600">Refuser</a>
            </td>
            <td class="py-2 px-4 border"><%= candidature.getStatut() %></td>
        </tr>
        <% } } else { %>
        <tr>
            <td colspan="7" class="text-center py-4 text-gray-500">Aucune candidature trouvée.</td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>