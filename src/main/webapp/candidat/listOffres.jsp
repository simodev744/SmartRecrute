<%@ page import="com.example.smartrecrute.models.OffreEmploi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Job Offers</title>
    <%@ include file="includes/navbar.jsp" %></head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">listes des offres disponibles</h1>
        <div class="space-y-6">
            <%
                List<OffreEmploi> offres = (List<OffreEmploi>) request.getAttribute("offres");
                if (offres != null) {
                    for (OffreEmploi offre : offres) {
            %>
            <div class="bg-gray-50 p-4 rounded-lg shadow-sm hover:shadow-lg transition">
                <h2 class="text-2xl font-semibold"><%= offre.getTitre() %></h2>
                <p class="text-gray-600"><%= offre.getDescription() %></p>
                <p class="mt-2 text-sm text-gray-500">Published on: <%= offre.getDatePublication() %></p>
                <div class="mt-4">
                    <form action="candidat?action=postuler&offre_id=<%= offre.getId() %>" method="post">
                        <input type="hidden" name="action" value="postuler">
                        <input type="hidden" name="offre_id" value="<%= offre.getId() %>">
                        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">postuler</button>
                    </form>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
