<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container mx-auto mt-8 bg-white p-6 rounded-lg shadow-md max-w-md">
    <h2 class="text-xl font-bold mb-4">Créer une Offre</h2>
    <form action="recruteur" method="post">
        <input type="hidden" name="action" value="create">
        <div class="mb-4">
            <label class="block font-bold mb-1">Titre</label>
            <input type="text" name="title" class="w-full p-2 border border-gray-300 rounded">
        </div>
        <div class="mb-4">
            <label class="block font-bold mb-1">Description</label>
            <textarea name="description" class="w-full p-2 border border-gray-300 rounded"></textarea>
        </div>
        <div class="mb-4">
            <label class="block font-bold mb-1">Entreprise</label>
            <input type="text" name="entreprise" class="w-full p-2 border border-gray-300 rounded">
        </div>
        <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded">Créer</button>
    </form>
</div>
