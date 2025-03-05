<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <%@ include file="includes/navbar.jsp" %></head>
<body class="bg-gray-100">
<div class="container mx-auto p-6">
    <div class="bg-white p-6 rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">Edit Profile</h1>
        <form action="uploadPdf" method="post" enctype="multipart/form-data"  class="space-y-6">
            <div class="flex flex-col space-y-4">
                <label for="nomUtilisateur" class="font-semibold">nom</label>
                <input type="text" name="nomUtilisateur" id="nomUtilisateur" class="p-2 border border-gray-300 rounded" value="${utilisateur.nomUtilisateur}" required>

                <label for="email" class="font-semibold">Email</label>
                <input type="email" name="email" id="email" class="p-2 border border-gray-300 rounded" value="${utilisateur.email}" required>

                <label for="pdfFile">cv en pdf</label>
                <input type="file" name="pdfFile" id="pdfFile" accept="application/pdf" required />
            </div>
            <div class="mt-4">
                <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">Update Profile</button>
            </div>
        </form>
    </div>
</div>




</body>
</html>
