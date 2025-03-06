<%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 3/4/2025
  Time: 4:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%@ page import="com.example.smartrecrute.models.Utilisateur" %><%--
  Created by IntelliJ IDEA.
  User: Med
  Date: 3/4/2025
  Time: 3:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>




<%
    Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
%>

hello
<%= utilisateur.getEmail() %> <br>
<%= utilisateur.getRole() %>
<%= utilisateur.getRole() %>
</body>
</html>




</body>
</html>
