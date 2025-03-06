<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription - SmartRecrute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .fade-in {
            animation: fadeIn 0.5s ease-in-out;
        }
        @keyframes fadeIn {
            0% { opacity: 0; transform: translateY(-10px); }
            100% { opacity: 1; transform: translateY(0); }
        }
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-md fade-in">
    <h2 class="text-2xl font-bold text-center text-blue-600 mb-6">Inscription à SmartRecrute</h2>

    <% if (request.getAttribute("error") != null) { %>
    <div class="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 mb-6 rounded" role="alert">
        <p><%= request.getAttribute("error") %></p>
    </div>
    <% } %>

    <form action="${pageContext.request.contextPath}/auth" method="post" class="space-y-6">
        <input type="hidden" name="action" value="register">

        <div>
            <label for="username" class="block text-sm font-medium text-gray-700">Nom d'utilisateur</label>
            <input type="text" id="username" name="username" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
        </div>

        <div>
            <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
            <input type="email" id="email" name="email" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
        </div>

        <div>
            <label for="password" class="block text-sm font-medium text-gray-700">Mot de passe</label>
            <input type="password" id="password" name="password" required
                   class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
        </div>

        <div>
            <label for="role" class="block text-sm font-medium text-gray-700">Rôle</label>
            <select id="role" name="role" required
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-blue-500 focus:border-blue-500 sm:text-sm">
                <option value="candidat">Candidat</option>
                <option value="recruteur">Recruteur</option>
            </select>
        </div>

        <button type="submit"
                class="w-full bg-blue-600 text-white py-2 px-4 rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition duration-200">
            S'inscrire
        </button>
    </form>

    <p class="mt-4 text-center text-sm text-gray-600">
        Déjà inscrit ?
        <a href="${pageContext.request.contextPath}/auth?action=login" class="text-blue-600 hover:underline">Se connecter</a>
    </p>
</div>
</body>
</html>