
package com.example.smartrecrute.controllers.candidat;

import com.example.smartrecrute.daos.DaoCandidat;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/candidat"})
public class CandidatServlet extends HttpServlet {
    private DaoCandidat daoCandidat;

    public CandidatServlet() {
    }

    public void init() throws ServletException {
        try {
            this.daoCandidat = new DaoCandidat();
        } catch (ClassNotFoundException | SQLException var2) {
            Exception e = var2;
            throw new RuntimeException(e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        String action = req.getParameter("action");
        Utilisateur utilisateur = (Utilisateur)req.getSession().getAttribute("utilisateur");
        if (utilisateur == null) {
            resp.sendRedirect("/login");
        } else {
            if (action == null) {
                req.getRequestDispatcher("candidat/dashboard.jsp").forward(req, resp);
            } else {
                switch (action) {
                    case "listCandidatures":
                        try {
                            this.listCandidatures(req, resp, utilisateur);
                            break;
                        } catch (SQLException var8) {
                            SQLException e = var8;
                            throw new RuntimeException(e);
                        }
                    case "editProfile":
                        this.editProfile(req, resp);
                        break;
                    case "listOffres":
                        this.listOffres(req, resp);
                        break;
                    case "profile":
                        req.getRequestDispatcher("candidat/profile.jsp").forward(req, resp);
                        break;
                    default:
                        req.getRequestDispatcher("candidat/dashboard.jsp").forward(req, resp);
                }
            }

        }
    }

    private void listOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("offres", this.daoCandidat.getlistOffres());
        req.getRequestDispatcher("candidat/listOffres.jsp").forward(req, resp);
    }

    private void listCandidatures(HttpServletRequest req, HttpServletResponse resp, Utilisateur utilisateur) throws SQLException, ServletException, IOException {
        req.setAttribute("candidatures", this.daoCandidat.getlistCandidatures(utilisateur.getId()));
        req.getRequestDispatcher("candidat/listCandidatures.jsp").forward(req, resp);
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("candidat/editProfile.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post work");
        String action = req.getParameter("action");
        int candidat_id = ((Utilisateur)req.getSession().getAttribute("utilisateur")).getId();
        if (action != null) {
            switch (action) {
                case "postuler":
                    int offre_id = Integer.parseInt(req.getParameter("offre_id"));
                    System.out.println("postuler servlet ");
                    this.daoCandidat.postuler(offre_id, candidat_id);
                    resp.sendRedirect("candidat?action=listOffres");
                    break;
                case "annuler":
                    int candidature_id = Integer.parseInt(req.getParameter("candidature_id"));
                    this.daoCandidat.annuler(candidature_id, candidat_id);
                    resp.sendRedirect("candidat?action=listCandidatures");
            }
        }

    }
}
