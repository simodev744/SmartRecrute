<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.smartrecrute.models.Utilisateur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Offre - SmartRecrute</title>
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
    <div class="bg-white p-6 rounded-lg shadow-md max-w-3xl mx-auto">
        <h1 class="text-2xl font-bold mb-4">Détails de l'Offre</h1>

        <c:choose>
            <c:when test="${not empty offre}">
                <div class="space-y-4">
                    <p><strong>Titre:</strong> <c:out value="${offre.titre}" /></p>
                    <p><strong>Description:</strong> <c:out value="${offre.description}" /></p>
                    <p><strong>Posté le:</strong> <c:out value="${offre.dateCreation}" /></p>
                    <p><strong>Recruteur ID:</strong> <c:out value="${offre.recruteurId}" /></p>

                    <!-- Apply Form -->
                    <form action="${pageContext.request.contextPath}/offre" method="post" class="mt-4">
                        <input type="hidden" name="action" value="apply">
                        <input type="hidden" name="offreId" value="${offre.id}">
                        <input type="hidden" name="candidatId" value="<%= utilisateur.getId() %>">
                        <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600 transition duration-200">
                            Postuler à cette offre
                        </button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p class="text-gray-600">Aucune offre trouvée.</p>
            </c:otherwise>
        </c:choose>

        <a href="${pageContext.request.contextPath}/offre?action=list" class="mt-6 inline-block text-blue-600 hover:underline">Retour à la liste des offres</a>
    </div>
</main>
</body>
</html>