<%@ page import="com.example.smartrecrute.models.Candidature" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>mes candidatures</title>
    <%@ include file="includes/navbar.jsp" %></head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">mes candidatures</h1>
        <div class="space-y-6">
            <%
                List<Candidature> candidatures = (List<Candidature>) request.getAttribute("candidatures");
                if (candidatures != null) {
                    for (Candidature candidature : candidatures) {
            %>
            <div class="bg-gray-50 p-4 rounded-lg shadow-sm hover:shadow-lg transition">
                <p class="text-xl">offreid: <%=candidature.getId()%></p>
                <p class="text-gray-600">Status: <%=candidature.getStatut()%></p>
                <p class="text-sm text-gray-500">date de candidature: <%=candidature.getDateCandidature()%></p>
                <form action="candidat" method="post">

                    <input type="hidden" name="candidature_id" value="<%=candidature.getId()%>">
                    <input type="hidden" name="action" value="annuler">
                    <button type="submit">annuler</button>
                </form>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
