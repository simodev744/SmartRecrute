<%@ page import="com.example.smartrecrute.models.OffreEmploi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container mx-auto mt-8">
    <div>
        <h1>Create offre</h1>
        <a href="recruteur?action=create">create offre</a>
    </div>

    <h2 class="text-xl font-bold mb-4">Liste des Offres</h2>

    <table class="w-full border-collapse border border-gray-300">
        <thead>
        <tr class="bg-blue-500 text-white">
            <th class="p-3 border border-gray-300">Titre</th>
            <th class="p-3 border border-gray-300">Description</th>
            <th class="p-3 border border-gray-300">Action</th>
            <th class="p-3 border border-gray-300">Candidatures</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<OffreEmploi> offres = (List<OffreEmploi>) request.getAttribute("offres");
            for (OffreEmploi offre : offres) {
        %>
        <tr class="bg-gray-100">
            <td class="p-3 border border-gray-300"><%= offre.getTitre() %></td>
            <td class="p-3 border border-gray-300"><%= offre.getDescription() %></td>
            <td class="p-3 border border-gray-300">
                <a href="recruteur?action=editOffre&id=<%= offre.getId() %>" class="text-blue-600 hover:underline">Modifier</a> |
                <a href="recruteur?action=deleteOffre&id=<%= offre.getId() %>" class="text-red-600 hover:underline">Supprimer</a>
            </td>
            <td class="p-3 border border-gray-300">
                <a href="recruteur?action=candidatures&id=<%= offre.getId() %>" class="text-blue-600 hover:underline">
                    Afficher les candidatures
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>