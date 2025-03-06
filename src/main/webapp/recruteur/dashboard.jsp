<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recruteur Dashboard - SmartRecrute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .sidebar { transition: transform 0.3s ease-in-out; }
        .sidebar-hidden { transform: translateX(-100%); }
    </style>
</head>
<body class="bg-gray-100 font-sans">
<!-- Navbar -->
<nav class="bg-blue-600 text-white p-4 flex justify-between items-center shadow-md">
    <div class="text-xl font-bold">SmartRecrute</div>
    <div class="flex items-center space-x-4">
        <span class="text-sm">Welcome, <c:out value="${recruteur.email}" /> (Recruteur)</span>
        <a href="${pageContext.request.contextPath}/auth?action=logout" class="bg-red-500 hover:bg-red-600 px-3 py-1 rounded">Logout</a>
    </div>
</nav>

<!-- Main Layout: Sidebar + Content -->
<div class="flex">
    <!-- Sidebar -->
    <aside id="sidebar" class="bg-gray-800 text-white w-64 h-screen p-4 sidebar">
        <h2 class="text-lg font-semibold mb-4">Menu</h2>
        <ul class="space-y-2">
            <li><a href="${pageContext.request.contextPath}/recruteur?action=list" class="block p-2 rounded hover:bg-gray-700">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/recruteur?action=create" class="block p-2 rounded hover:bg-gray-700">Add Job Offer</a></li>
        </ul>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <button id="toggleSidebar" class="bg-blue-500 text-white p-2 rounded mb-4 hover:bg-blue-600">Toggle Menu</button>
        <h1 class="text-2xl font-bold mb-6">Recruiter Dashboard</h1>

        <!-- Welcome Section -->
        <div class="bg-white p-6 rounded-lg shadow-md mb-6">
            <h2 class="text-xl font-semibold mb-2">Hello, <c:out value="${recruteur.nomUtilisateur}" />!</h2>
            <p class="text-gray-600">Role: <span class="font-medium">Recruteur</span></p>
            <p class="text-gray-600">Email: <span class="font-medium"><c:out value="${recruteur.email}" /></span></p>
            <p class="text-gray-600">Entreprise: <span class="font-medium"><c:out value="${recruteur.entreprise}" /></span></p>
        </div>

        <!-- Job Offers Section -->
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-4">Your Job Offers</h2>
            <c:choose>
                <c:when test="${not empty offres}">
                    <table class="w-full text-left">
                        <thead>
                        <tr class="bg-gray-200">
                            <th class="p-3">ID</th>
                            <th class="p-3">Titre</th>
                            <th class="p-3">Description</th>
                            <th class="p-3">Entreprise</th>
                            <th class="p-3">Date de Publication</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="offre" items="${offres}">
                            <tr class="border-b">
                                <td class="p-3"><c:out value="${offre.id}" /></td>
                                <td class="p-3"><c:out value="${offre.titre}" /></td>
                                <td class="p-3"><c:out value="${offre.description}" /></td>
                                <td class="p-3"><c:out value="${offre.entreprise}" /></td>
                                <td class="p-3"><c:out value="${offre.datePublication}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-gray-600">Vous n’avez pas encore ajouté d’offres. <a href="${pageContext.request.contextPath}/recruteur?action=create" class="text-blue-600 hover:underline">Créer une offre</a>.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>

<!-- Sidebar Toggle Script -->
<script>
    const sidebar = document.getElementById('sidebar');
    const toggleButton = document.getElementById('toggleSidebar');
    toggleButton.addEventListener('click', () => {
        sidebar.classList.toggle('sidebar-hidden');
    });
</script>
</body>
</html>