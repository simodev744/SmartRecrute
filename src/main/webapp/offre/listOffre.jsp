<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.smartrecrute.models.Utilisateur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Offres - SmartRecrute</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">
<%
    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    if (utilisateur == null || !"candidat".equalsIgnoreCase(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/auth?action=login");
        return;
    }
%>

<!-- Navbar -->
<nav class="bg-blue-600 text-white p-4 flex justify-between items-center shadow-md">
    <div class="text-xl font-bold">SmartRecrute</div>
    <div class="flex items-center space-x-4">
        <span class="text-sm">Welcome, <%= utilisateur.getEmail() %> (Candidat)</span>
        <a href="${pageContext.request.contextPath}/auth?action=logout" class="bg-red-500 hover:bg-red-600 px-3 py-1 rounded">Logout</a>
    </div>
</nav>

<!-- Main Content -->
<main class="p-6">
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h1 class="text-2xl font-bold mb-4">Offres d'Emploi Disponibles</h1>

        <c:choose>
            <c:when test="${not empty offres}">
                <table class="w-full text-left">
                    <thead>
                    <tr class="bg-gray-200">
                        <th class="p-3">ID</th>
                        <th class="p-3">Titre</th>
                        <th class="p-3">Description</th>
                        <th class="p-3">Date de Création</th>
                        <th class="p-3">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="offre" items="${offres}">
                        <tr class="border-b">
                            <td class="p-3"><c:out value="${offre.id}" /></td>
                            <td class="p-3"><c:out value="${offre.titre}" /></td>
                            <td class="p-3"><c:out value="${offre.description}" /></td>
                            <td class="p-3"><c:out value="${offre.dateCreation}" /></td>
                            <td class="p-3 flex space-x-2">
                                <a href="${pageContext.request.contextPath}/offre?action=view&id=${offre.id}" class="text-blue-500 hover:underline">Détails</a>
                                <form action="${pageContext.request.contextPath}/offre" method="post" class="inline">
                                    <input type="hidden" name="action" value="apply">
                                    <input type="hidden" name="offreId" value="${offre.id}">
                                    <input type="hidden" name="candidatId" value="<%= utilisateur.getId() %>">
                                    <button type="submit" class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600">Postuler</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="text-gray-600">Aucune offre d'emploi disponible pour le moment.</p>
            </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/candidat/dashboard.jsp" class="mt-6 inline-block text-blue-600 hover:underline">Retour au tableau de bord</a>
    </div>
</main>
</body>
</html>