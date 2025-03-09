<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container mx-auto mt-8 px-4 sm:px-6 lg:px-8">
    <!-- Dashboard Header -->
    <div class="bg-gradient-to-r from-blue-500 to-indigo-600 text-white p-6 rounded-lg shadow-lg mb-6">
        <h1 class="text-3xl font-extrabold tracking-tight">Tableau de Bord</h1>
        <p class="mt-2 text-lg text-blue-100">Bienvenue, gérez vos offres et candidatures en toute simplicité.</p>
    </div>

    <!-- Create Offer Section -->
    <div class="bg-white p-6 rounded-lg shadow-md hover:shadow-xl transition-shadow duration-300">
        <h2 class="text-xl font-semibold text-gray-800 mb-4">Créer une Nouvelle Offre</h2>
        <p class="text-gray-600 mb-4">Ajoutez une nouvelle opportunité d'emploi pour attirer les meilleurs talents.</p>
        <a href="recruteur?action=create"
           class="inline-block bg-blue-600 text-white font-medium px-6 py-3 rounded-full hover:bg-blue-700 transition-colors duration-200 transform hover:scale-105 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2">
            Créer une Offre
        </a>
    </div>

    <!-- Optional: Success/Error Messages -->
    <c:if test="${not empty error}">
        <div class="mt-4 p-4 bg-red-100 border-l-4 border-red-500 text-red-700 rounded">
                ${error}
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.success}">
        <div class="mt-4 p-4 bg-green-100 border-l-4 border-green-500 text-green-700 rounded">
                ${sessionScope.success}
            <% session.removeAttribute("success"); %>
        </div>
    </c:if>
</div>