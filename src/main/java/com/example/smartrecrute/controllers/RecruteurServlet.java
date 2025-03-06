package com.example.smartrecrute.controllers;

import com.example.smartrecrute.daos.DaoRecruter;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Recruteur;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recruteur")
public class RecruteurServlet extends HttpServlet {
    private DaoRecruter daoRecruter;

    @Override
    public void init() throws ServletException {
        try {
            daoRecruter = new DaoRecruter();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize DaoRecruter", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null || !"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
            return;
        }

        Recruteur recruteur;
        try {
            recruteur = daoRecruter.getRecruteurByUtilisateurId(utilisateur.getId());
            if (recruteur == null) {
                response.sendRedirect(request.getContextPath() + "/auth?action=login");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException("Failed to fetch Recruteur", e);
        }

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            request.setAttribute("recruteur", recruteur); // Pass recruteur to JSP
            request.getRequestDispatcher("/recruteur/createOffre.jsp").forward(request, response);
        } else if ("list".equals(action) || action == null) {
            try {
                List<OffreEmploi> offres = daoRecruter.getOffresByRecruteur(recruteur.getId());
                request.setAttribute("recruteur", recruteur); // Pass recruteur to JSP
                request.setAttribute("offres", offres);
                request.getRequestDispatcher("/recruteur/dashboard.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving offers: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
        if (utilisateur == null || !"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
            return;
        }

        Recruteur recruteur;
        try {
            recruteur = daoRecruter.getRecruteurByUtilisateurId(utilisateur.getId());
            if (recruteur == null) {
                response.sendRedirect(request.getContextPath() + "/auth?action=login");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException("Failed to fetch Recruteur", e);
        }

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            String entreprise = recruteur.getEntreprise();

            OffreEmploi offre = new OffreEmploi();
            offre.setTitre(titre);
            offre.setDescription(description);
            offre.setEntreprise(entreprise);
            offre.setRecruteurId(recruteur.getId());

            try {
                daoRecruter.addOffre(offre);
                response.sendRedirect(request.getContextPath() + "/recruteur?action=list");
            } catch (SQLException e) {
                request.setAttribute("error", "Erreur lors de la création de l’offre: " + e.getMessage());
                request.setAttribute("recruteur", recruteur);
                request.getRequestDispatcher("/recruteur/createOffre.jsp").forward(request, response);
            }
        }
    }
}