<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.smartrecrute.models.Utilisateur" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidat Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .sidebar { transition: transform 0.3s ease-in-out; }
        .sidebar-hidden { transform: translateX(-100%); }
    </style>
</head>
<body class="bg-gray-100 font-sans">
<%
    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
    if (utilisateur == null) {
        response.sendRedirect(request.getContextPath() + "/auth?action=login");
        return;
    }
%>

<!-- Navbar -->
<nav class="bg-blue-600 text-white p-4 flex justify-between items-center shadow-md">
    <div class="text-xl font-bold">SmartRecrute</div>
    <div class="flex items-center space-x-4">
        <span class="text-sm">Welcome, <%= utilisateur.getEmail() %> (<%= utilisateur.getRole() %>)</span>
        <a href="${pageContext.request.contextPath}/auth?action=logout" class="bg-red-500 hover:bg-red-600 px-3 py-1 rounded">Logout</a>
    </div>
</nav>

<!-- Main Layout: Sidebar + Content -->
<div class="flex">
    <!-- Sidebar -->
    <aside id="sidebar" class="bg-gray-800 text-white w-64 h-screen p-4 sidebar">
        <h2 class="text-lg font-semibold mb-4">Menu</h2>
        <ul class="space-y-2">
            <li><a href="${pageContext.request.contextPath}/dashboard.jsp" class="block p-2 rounded hover:bg-gray-700">Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/candidat?action=list" class="block p-2 rounded hover:bg-gray-700">View Candidates</a></li>
            <li><a href="${pageContext.request.contextPath}/candidat?action=create" class="block p-2 rounded hover:bg-gray-700">Add Candidate</a></li>
            <% if ("ADMIN".equalsIgnoreCase(utilisateur.getRole())) { %>
            <li><a href="${pageContext.request.contextPath}/admin.jsp" class="block p-2 rounded hover:bg-gray-700">Admin Panel</a></li>
            <% } %>
        </ul>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-6">
        <button id="toggleSidebar" class="bg-blue-500 text-white p-2 rounded mb-4 hover:bg-blue-600">Toggle Menu</button>
        <h1 class="text-2xl font-bold mb-6">Dashboard</h1>

        <div class="bg-white p-6 rounded-lg shadow-md mb-6">
            <h2 class="text-xl font-semibold mb-2">Hello, <c:out value="${utilisateur.nom != null ? utilisateur.nom : utilisateur.email.split('@')[0]}" />!</h2>
            <p class="text-gray-600">Role: <span class="font-medium"><c:out value="${utilisateur.role}" /></span></p>
            <p class="text-gray-600">Email: <span class="font-medium"><c:out value="${utilisateur.email}" /></span></p>
        </div>

        <div class="bg-white p-6 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-4">Your Candidates</h2>
            <c:choose>
                <c:when test="${not empty candidats}">
                    <table class="w-full text-left">
                        <thead>
                        <tr class="bg-gray-200">
                            <th class="p-3">ID</th>
                            <th class="p-3">Name</th>
                            <th class="p-3">Email</th>
                            <th class="p-3">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="candidat" items="${candidats}">
                            <tr class="border-b">
                                <td class="p-3"><c:out value="${candidat.id}" /></td>
                                <td class="p-3"><c:out value="${candidat.nom}" /></td>
                                <td class="p-3"><c:out value="${candidat.email}" /></td>
                                <td class="p-3">
                                    <a href="${pageContext.request.contextPath}/candidat?action=view&id=${candidat.id}" class="text-blue-500 hover:underline">View</a>
                                    <a href="${pageContext.request.contextPath}/candidat?action=delete&id=${candidat.id}" class="text-red-500 hover:underline ml-2">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-gray-600">No candidates found. <a href="${pageContext.request.contextPath}/candidat?action=create" class="text-blue-500 hover:underline">Add one now</a>.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </main>
</div>

<script>
    const sidebar = document.getElementById('sidebar');
    const toggleButton = document.getElementById('toggleSidebar');
    toggleButton.addEventListener('click', () => {
        sidebar.classList.toggle('sidebar-hidden');
    });
</script>
</body>
</html>