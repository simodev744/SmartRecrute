<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Candidats List</title>
</head>
<body>
<h2>Candidats</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Utilisateur ID</th>
        <th>CV</th>
        <th>Date Creation</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="candidat" items="${candidats}">
        <tr>
            <td>${candidat.id}</td>
            <td>${candidat.utilisateurId}</td>
            <td>${candidat.cv}</td>
            <td>${candidat.dateCreation}</td>
            <td>
                <a href="candidat?action=view&id=${candidat.id}">View</a>
                <a href="candidat?action=delete&id=${candidat.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
