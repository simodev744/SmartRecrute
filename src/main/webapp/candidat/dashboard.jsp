<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.smartrecrute.models.Utilisateur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidat Dashboard - SmartRecrute</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .sidebar { transition: transform 0.3s ease-in-out; }
        .sidebar-hidden { transform: translateX(-100%); }
    </style>
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

<!-- Main Layout: Sidebar + Content -->
<div class="flex">
    <!-- Sidebar -->
    <aside id="sidebar" class="bg-gray-800 text-white w-64 h-screen p-4 sidebar">
        <h2 class="text-lg font-semibold mb-4">Menu</h2>
        <ul class="space-y-2">
            <li><a href="${pageContext.request.contextPath}/candidat/dashboard.jsp" class="block p-2 rounded hover:bg-gray-700">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/offre?action=list" class="block p-2 rounded hover:bg-gray-700">View Job Offers</a></li>
            <li><a href="${pageContext.request.contextPath}/candidat?action=create" class="block p-2 rounded hover:bg-gray-700">Update Profile</a></li>
        </ul>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <button id="toggleSidebar" class="bg-blue-500 text-white p-2 rounded mb-4 hover:bg-blue-600">Toggle Menu</button>
        <h1 class="text-2xl font-bold mb-6">Candidate Dashboard</h1>

        <!-- Welcome Section -->
        <div class="bg-white p-6 rounded-lg shadow-md mb-6">
            <h2 class="text-xl font-semibold mb-2">Hello, <c:out value="${utilisateur.nom != null ? utilisateur.nom : utilisateur.email.split('@')[0]}" />!</h2>
            <p class="text-gray-600">Role: <span class="font-medium">Candidat</span></p>
            <p class="text-gray-600">Email: <span class="font-medium"><c:out value="${utilisateur.email}" /></span></p>
        </div>

        <!-- Job Offers Section -->
        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-4">Available Job Offers</h2>
            <c:choose>
                <c:when test="${not empty offres}">
                    <table class="w-full text-left">
                        <thead>
                        <tr class="bg-gray-200">
                            <th class="p-3">ID</th>
                            <th class="p-3">Title</th>
                            <th class="p-3">Description</th>
                            <th class="p-3">Posted On</th>
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
                                <td class="p-3">
                                    <form action="${pageContext.request.contextPath}/offre" method="post" class="inline">
                                        <input type="hidden" name="action" value="apply">
                                        <input type="hidden" name="offreId" value="${offre.id}">
                                        <input type="hidden" name="candidatId" value="<%= utilisateur.getId() %>">
                                        <button type="submit" class="bg-green-500 text-white px-3 py-1 rounded hover:bg-green-600">Apply</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-gray-600">No job offers available at the moment.</p>
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