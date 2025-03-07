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

@WebServlet("/recruteur")
public class RecruteurServlet extends HttpServlet {

    private RecruteurDAO recruteurDAO;
    private Utilisateur utilisateur;

    @Override
    public void init() throws ServletException {
        try {
            recruteurDAO = new RecruteurDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);
         utilisateur = (session != null) ? (Utilisateur) session.getAttribute("utilisateur") : null;

        if (utilisateur == null) {
            resp.sendRedirect("/login");
            return;
        }

        try {
            if (action == null) {
                showDashboard(req, resp);
            } else {
                switch (action) {
                    case "candidatures":
                        showCandidatures(req,resp);
                        break;
                        case "acceptCandidature":
                            accCandidature(req,resp);
                        break;

                        case "rejectCandidature":
                            rejCandidature(req,resp);
                        break;
                    case "profile":
                        showProfile(req,resp);
                        break;
                    case "create":
                        showCreateOffre(req,resp);
                        break;
                    case "editOffre":
                        showEditOffre(req,resp);
                        break;
                    case "deleteOffre":
                        deleteOffre(req,resp);
                        break;
                    case "listOffres":
                        showListOffres(req,resp);
                        break;
                    default:
                        showDashboard(req,resp);
                        break;
                }
            }
            }
            catch (SQLException e) {
                throw new ServletException(e);
                }
    }

    private void rejCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException {

        int candidatureId= Integer.parseInt(req.getParameter("id"));
        recruteurDAO.rejectCandidature(candidatureId);
    }

    private void accCandidature(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int candidatureId= Integer.parseInt(req.getParameter("id"));
        recruteurDAO.acceptCandidature(candidatureId);
    }

    private void showCandidatures(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int idoffre = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("candidatures", recruteurDAO.getCandidatureByOffre(idoffre));
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
        req.setAttribute("offre", recruteurDAO.getOffre(idOffre,utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/editOffre.jsp").forward(req, resp);
    }

    private void deleteOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        recruteurDAO.deleteOffre(id);
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void showListOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.setAttribute("offres", recruteurDAO.listOffresByRecruteur(utilisateur.getId()));
        req.getRequestDispatcher("/recruteur/listOffres.jsp").forward(req, resp);
    }

    private void showDashboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/recruteur/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "create":
                        createOffre(req, resp);
                        break;
                    case "update":
                        updateOffre(req, resp);
                        break;
                    default:
                        resp.sendRedirect("recruteur");
                        break;
                }
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void createOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String entreprise = req.getParameter("entreprise");
        int recruteurId =utilisateur.getId();
                //Integer.parseInt(req.getParameter("recruteurId"));
        OffreEmploi newOffre = new OffreEmploi(title, description,entreprise,recruteurId);
        recruteurDAO.createOffre(newOffre);
        resp.sendRedirect("recruteur?action=listOffres");
    }

    private void updateOffre(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int idOffre = Integer.parseInt(req.getParameter("id"));
        String updatedTitle = req.getParameter("title");
        String updatedDescription = req.getParameter("description");
        String updatedEntreprise = req.getParameter("entreprise");
        OffreEmploi updatedOffre = new OffreEmploi(idOffre, updatedTitle, updatedDescription,updatedEntreprise,utilisateur.getId());
        recruteurDAO.updateOffre(updatedOffre);
        resp.sendRedirect("recruteur?action=listOffres");
    }
}