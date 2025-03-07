<%@ page contentType="text/html; charset=UTF-8" %>


<nav class="bg-blue-600 text-white p-4 shadow-md">
    <div class="container mx-auto flex justify-between items-center">
        <a href="recruteur?action=dashboard" class="text-lg font-bold">SmartRecrute</a>
        <ul class="flex space-x-4">
            <li><a href="candidat?action=dashboard" class="hover:underline">Dashboard</a></li>
            <li><a href="candidat?action=listOffres" class="hover:underline">Mes candidatures</a></li>
            <li><a href="candidat?action=profile" class="hover:underline">Profile</a></li>
            <li>     <form action="auth?action=logout" method="post">
                <button type="submit">Deconnecter</button>
            </form></li>

        </ul>
    </div>
</nav>
