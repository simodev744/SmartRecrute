<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Candidat dashboard</title>
    <%@ include file="includes/navbar.jsp" %>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold text-center mb-6">Welcome, ${utilisateur.nomUtilisateur}</h1>
        <ul class="space-y-4 text-xl">
            <li><a href="candidat?action=listOffres" class="text-blue-500 hover:underline">liste des offers</a></li>
            <li><a href="candidat?action=listCandidatures" class="text-blue-500 hover:underline">Mes candidatures</a></li>
            <li><a href="candidat?action=profile" class="text-blue-500 hover:underline">Profile</a></li>
        </ul>
    </div>
</div>
</body>
</html>
