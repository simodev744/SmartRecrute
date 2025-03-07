<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex items-center justify-center min-h-screen bg-gray-100">
<div class="bg-white p-8 rounded-lg shadow-lg w-96">
    <h2 class="text-2xl font-bold text-center mb-4">Inscription</h2>
    <form action="auth" method="post" class="space-y-4">
        <input type="hidden" name="action" value="register">
        <div>
            <label class="block font-medium">Nom d'utilisateur:</label>
            <input type="text" name="username" required class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div>
            <label class="block font-medium">Email:</label>
            <input type="email" name="email" required class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div>
            <label class="block font-medium">Mot de passe:</label>
            <input type="password" name="password" required class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div>
            <label class="block font-medium">Rôle:</label>
            <select name="role" class="w-full p-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <option value="candidat">Candidat</option>
                <option value="recruteur">Recruteur</option>
            </select>
        </div>
        <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition">S'inscrire</button>
    </form>
    <p class="text-center mt-4">Déjà inscrit ? <a href="auth?action=login" class="text-blue-500 hover:underline">Se connecter</a></p>
</div>
</body>
</html>