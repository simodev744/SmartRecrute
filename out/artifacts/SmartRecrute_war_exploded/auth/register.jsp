<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Inscription</h2>
<form action="auth" method="post">
    <input type="hidden" name="action" value="register">
    <label>Nom d'utilisateur:</label>
    <input type="text" name="username" required><br>
    <label>Email:</label>
    <input type="email" name="email" required><br>
    <label>Mot de passe:</label>
    <input type="password" name="password" required><br>
    <label>Rôle:</label>
    <select name="role">
        <option value="candidat">Candidat</option>
        <option value="recruteur">Recruteur</option>
    </select><br>
    <button type="submit">S'inscrire</button>
</form>
<p>Déjà inscrit ? <a href="auth?action=login">Se connecter</a></p>
</body>
</html>
