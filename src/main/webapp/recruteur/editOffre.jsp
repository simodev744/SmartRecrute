<%@ page import="com.example.smartrecrute.models.OffreEmploi" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes/navbar.jsp" %>

<%
    OffreEmploi offre = (OffreEmploi) request.getAttribute("offre");
%>

<div class="container mx-auto mt-12 max-w-lg bg-white p-6 rounded-lg shadow-lg">
    <h2 class="text-2xl font-semibold text-gray-800 mb-6">Modifier l'Offre</h2>
    <form action="recruteur" method="post" class="space-y-4">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= offre.getId() %>">

        <div>
            <label class="block text-gray-700 font-medium mb-2">Titre</label>
            <input type="text" name="title" value="<%= offre.getTitre() %>"
                   class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none">
        </div>

        <div>
            <label class="block text-gray-700 font-medium mb-2">Description</label>
            <textarea name="description"
                      class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:outline-none h-32 resize-none"><%= offre.getDescription() %></textarea>
        </div>

        <button type="submit" class="w-full bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded-lg transition-all">Mettre à jour</button>
    </form>
</div>
