<%@ page import="com.example.smartrecrute.models.Utilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="includes/navbar.jsp" %>

<% Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); %>

<a href="editProfile.jsp">modifier profile</a>
<div class="container mx-auto mt-8">
    <h2 class="text-xl font-bold mb-4">Mon Profile</h2>
    <p class="text-lg"><strong>Nom:</strong> <%= utilisateur.getNomUtilisateur() %></p>
    <p class="text-lg"><strong>Email:</strong> <%= utilisateur.getEmail() %></p>
    <a href=" <%= utilisateur.getCv() %>" class="text-lg"><strong>Email:</strong> <%= utilisateur.getCv() %></a>

</div>



