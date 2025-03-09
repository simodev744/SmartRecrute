package com.example.smartrecrute.controllers.recruteur;

import com.example.smartrecrute.daos.RecruteurDAO;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet({"/recruteur"})
public class RecruteurServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RecruteurServlet.class);
    private RecruteurDAO recruteurDAO;
    private Utilisateur utilisateur;

    @Override
    public void init() throws ServletException {
        try {
            this.recruteurDAO = new RecruteurDAO();
        } catch (RuntimeException e) {
            throw new ServletException("Failed to initialize RecruteurDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || (utilisateur = (Utilisateur) session.getAttribute("utilisateur")) == null) {
            resp.sendRedirect("/login");
            return;
        }
        if (!"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès réservé aux recruteurs");
            return;
        }

        String action = req.getParameter("action");
        try {
            if (action == null) {
                showDashboard(req, resp);
            } else {
                switch (action) {
                    case "candidatures": showCandidatures(req, resp); break;
                    case "acceptCandidature": accCandidature(req, resp); break;
                    case "rejectCandidature": rejCandidature(req, resp); break;
                    case "profile": showProfile(req, resp); break;
                    case "create": showCreateOffre(req, resp); break;
                    case "editOffre": showEditOffre(req, resp); break;
                    case "deleteOffre": deleteOffre(req, resp); break;
                    case "listOffres": showListOffres(req, resp); break;
                    default: showDashboard(req, resp);
                }
            }
        } catch (SQLException e) {
            logger.error("Database error in doGet", e);
            req.setAttribute("error", "Une erreur est survenue : " + e.getMessage());
            showDashboard(req, resp);
        } catch (NumberFormatException e) {
            logger.error("Invalid parameter format", e);
            req.setAttribute("error", "Paramètre invalide");
            showDashboard(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || (utilisateur = (Utilisateur) session.getAttribute("utilisateur")) == null) {
            resp.sendRedirect("/login");
            return;
        }
        if (!"recruteur".equalsIgnoreCase(utilisateur.getRole())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès réservé aux recruteurs");
            return;
        }

        String action = req.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "create": createOffre(req, resp); break;
                    case "update": updateOffre(req, resp); break;
                    default: resp.sendRedirect("recruteur");
                }
            }
        } catch (SQLException e) {
            logger.error("Database error in doPost", e);
            req.setAttribute("error", "Une erreur est survenue : " + e.getMessage());
            req.getRequestDispatcher("/recruteur/dashboard.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            logger.error("Invalid parameter format in doPost", e);
            req.setAttribute("error", "Paramètre invalide");
            req.getRequestDispatcher("/recruteur/dashboard.jsp").forward(req, resp);
        }
    }

    private void createOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String entreprise = req.getParameter("entreprise");

        if (isEmpty(title) || isEmpty(description) || isEmpty(entreprise)) {
            req.setAttribute("error", "Tous les champs sont requis.");
            req.getRequestDispatcher("/recruteur/createOffre.jsp").forward(req, resp);
            return;
        }

        int recruteurId = utilisateur.getId();
        OffreEmploi newOffre = new OffreEmploi(title, description, entreprise, recruteurId);
        recruteurDAO.createOffre(newOffre);
        req.getSession().setAttribute("success", "Offre créée avec succès");
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void updateOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        String updatedTitle = req.getParameter("title");
        String updatedDescription = req.getParameter("description");
        String updatedEntreprise = req.getParameter("entreprise");

        if (isEmpty(updatedTitle) || isEmpty(updatedDescription) || isEmpty(updatedEntreprise)) {
            req.setAttribute("error", "Tous les champs sont requis.");
            req.setAttribute("offre", recruteurDAO.getOffre(idOffre, utilisateur.getId()));
            req.getRequestDispatcher("/recruteur/editOffre.jsp").forward(req, resp);
            return;
        }

        OffreEmploi updatedOffre = new OffreEmploi(idOffre, updatedTitle, updatedDescription, updatedEntreprise, utilisateur.getId());
        recruteurDAO.updateOffre(updatedOffre);
        req.getSession().setAttribute("success", "Offre mise à jour avec succès");
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void rejCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int candidatureId = Integer.parseInt(req.getParameter("id"));
        String offreId = req.getParameter("offreId");
        recruteurDAO.rejectCandidature(candidatureId);
        resp.sendRedirect("recruteur?action=candidatures&id=" + (offreId != null ? offreId : ""));
    }

    private void accCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int candidatureId = Integer.parseInt(req.getParameter("id"));
        String offreId = req.getParameter("offreId");
        recruteurDAO.acceptCandidature(candidatureId);
        resp.sendRedirect("recruteur?action=candidatures&id=" + (offreId != null ? offreId : ""));
    }

    private void showCandidatures(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("candidatures", recruteurDAO.getCandidatureByOffre(idOffre));
        req.setAttribute("offreId", idOffre);
        req.getRequestDispatcher("/recruteur/listCandidatures.jsp").forward(req, resp);
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/profile.jsp").forward(req, resp);
    }

    private void showCreateOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/createOffre.jsp").forward(req, resp);
    }

    private void showEditOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("offre", recruteurDAO.getOffre(idOffre, utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/editOffre.jsp").forward(req, resp);
    }

    private void deleteOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        recruteurDAO.deleteOffre(id);
        req.getSession().setAttribute("success", "Offre supprimée avec succès");
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void showListOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("offres", recruteurDAO.listOffresByRecruteur(utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/listOffres.jsp").forward(req, resp);
    }

    private void showDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/dashboard.jsp").forward(req, resp);
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}