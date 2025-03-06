<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer une Offre - SmartRecrute</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md">
    <h2 class="text-2xl font-bold text-center text-blue-600 mb-6">Créer une Nouvelle Offre</h2>

    <!-- Error Message -->
    <c:if test="${not empty error}">
        <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded" role="alert">
            <p><c:out value="${error}" /></p>
        </div>
    </c:if>

    <!-- Create Offer Form -->
    <form action="${pageContext.request.contextPath}/recruteur" method="post" class="space-y-6">
        <input type="hidden" name="action" value="create">

        <div>
            <label for="titre" class="block text-sm font-medium text-gray-700">Titre de l’offre</label>
            <input type="text" id="titre" name="titre" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
        </div>

        <div>
            <label for="description" class="block text-sm font-medium text-gray-700">Description</label>
            <textarea id="description" name="description" required rows="4"
                      class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm"></textarea>
        </div>

        <div>
            <label class="block text-sm font-medium text-gray-700">Entreprise</label>
            <input type="text" value="${recruteur.entreprise}" disabled
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md bg-gray-100 sm:text-sm">
        </div>

        <button type="submit"
                class="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition duration-200">
            Publier l’offre
        </button>
    </form>

    <a href="${pageContext.request.contextPath}/recruteur?action=list" class="mt-4 inline-block text-blue-600 hover:underline text-center w-full">Retour au tableau de bord</a>
</div>
</body>
</html>