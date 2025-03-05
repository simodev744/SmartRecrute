//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

@WebServlet({"/recruteur"})
public class RecruteurServlet extends HttpServlet {
    private RecruteurDAO recruteurDAO;
    private Utilisateur utilisateur;

    public RecruteurServlet() {
    }

    public void init() throws ServletException {
        try {
            this.recruteurDAO = new RecruteurDAO();
        } catch (ClassNotFoundException | SQLException var2) {
            Exception e = var2;
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);
        this.utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        if (this.utilisateur == null) {
            resp.sendRedirect("/login");
        } else {
            try {
                if (action == null) {
                    this.showDashboard(req, resp);
                } else {
                    switch (action) {
                        case "candidatures":
                            this.showCandidatures(req, resp);
                            break;
                        case "acceptCandidature":
                            this.accCandidature(req, resp);
                            break;
                        case "rejectCandidature":
                            this.rejCandidature(req, resp);
                            break;
                        case "profile":
                            this.showProfile(req, resp);
                            break;
                        case "create":
                            this.showCreateOffre(req, resp);
                            break;
                        case "editOffre":
                            this.showEditOffre(req, resp);
                            break;
                        case "deleteOffre":
                            this.deleteOffre(req, resp);
                            break;
                        case "listOffres":
                            this.showListOffres(req, resp);
                            break;
                        default:
                            this.showDashboard(req, resp);
                    }
                }

            } catch (SQLException var7) {
                SQLException e = var7;
                throw new ServletException(e);
            }
        }
    }

    private void rejCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int candidatureId = Integer.parseInt(req.getParameter("id"));
        this.recruteurDAO.rejectCandidature(candidatureId);
    }

    private void accCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int candidatureId = Integer.parseInt(req.getParameter("id"));
        this.recruteurDAO.acceptCandidature(candidatureId);
    }

    private void showCandidatures(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int idoffre = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("candidatures", this.recruteurDAO.getCandidatureByOffre(idoffre));
        req.getRequestDispatcher("/recruteur/listCandidatures.jsp").forward(req, resp);
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/profile.jsp").forward(req, resp);
    }

    private void showCreateOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(5555);
        req.getRequestDispatcher("/recruteur/createOffre.jsp").forward(req, resp);
    }

    private void showEditOffre(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("offre", this.recruteurDAO.getOffre(idOffre, this.utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/editOffre.jsp").forward(req, resp);
    }

    private void deleteOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        this.recruteurDAO.deleteOffre(id);
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void showListOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("offres", this.recruteurDAO.listOffresByRecruteur(this.utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/listOffres.jsp").forward(req, resp);
    }

    private void showDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/dashboard.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "create":
                        this.createOffre(req, resp);
                        break;
                    case "update":
                        this.updateOffre(req, resp);
                        break;
                    default:
                        resp.sendRedirect("recruteur");
                }
            }

        } catch (SQLException var6) {
            SQLException e = var6;
            throw new ServletException(e);
        }
    }

    private void createOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String entreprise = req.getParameter("entreprise");
        int recruteurId = this.utilisateur.getId();
        OffreEmploi newOffre = new OffreEmploi(title, description, entreprise, recruteurId);
        this.recruteurDAO.createOffre(newOffre);
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void updateOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        String updatedTitle = req.getParameter("title");
        String updatedDescription = req.getParameter("description");
        String updatedEntreprise = req.getParameter("entreprise");
        OffreEmploi updatedOffre = new OffreEmploi(idOffre, updatedTitle, updatedDescription, updatedEntreprise, this.utilisateur.getId());
        this.recruteurDAO.updateOffre(updatedOffre);
        resp.sendRedirect("recruteur?action=listOffres");
    }
}
