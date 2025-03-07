<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Create Candidat</title>
</head>
<body>
<h2>Create New Candidat</h2>
<form action="candidat" method="post">
    <input type="hidden" name="action" value="create">
    <label for="utilisateur_id">Utilisateur ID:</label><br>
    <input type="number" id="utilisateur_id" name="utilisateur_id" required><br><br>
    <label for="cv">CV:</label><br>
    <textarea id="cv" name="cv" rows="4" cols="50" required></textarea><br><br>
    <input type="submit" value="Create">
</form>
<br>
<a href="candidat?action=list">Back to List</a>
</body>
</html>
