<script src="https://cdn.tailwindcss.com"></script>
<nav class="bg-blue-600 text-white p-4 shadow-md">
    <div class="container mx-auto flex justify-between items-center">
        <a href="candidat?action=dashboard" class="text-lg font-bold">SmartRecrute</a>
        <ul class="flex space-x-4">
            <li><a href="candidat?action=dashboard" class="hover:underline">Dashboard</a></li>
            <li><a href="candidat?action=listOffres" class="hover:underline">les offres</a></li>
            <li><a href="candidat?action=listCandidatures" class="hover:underline">Mes candidatures</a></li>
            <li><a href="candidat?action=profile" class="hover:underline">Profile</a></li>
            <li>     <form action="auth?action=logout" method="post">
                <button type="submit">Déconnecter</button>
            </form></li>

        </ul>
    </div>
</nav>
