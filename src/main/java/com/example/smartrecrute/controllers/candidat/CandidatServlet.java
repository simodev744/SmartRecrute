package com.example.smartrecrute.controllers.candidat;

import com.example.smartrecrute.daos.DaoCandidat;
import com.example.smartrecrute.models.Utilisateur;
import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/candidat")
public class CandidatServlet extends HttpServlet {
    private DaoCandidat daoCandidat;
    private Utilisateur utilisateur;

    @Override
    public void init() throws ServletException {
        try {
          daoCandidat = new DaoCandidat();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Utilisateur utilisateur=req.getSession().getAttribute("utilisateur") != null ? (Utilisateur) req.getSession().getAttribute("utilisateur") : null;

        if (utilisateur == null) {
            resp.sendRedirect("/login");
            return;
        }

        System.out.println(utilisateur.getRole());
        System.out.println("Requested URL: " + req.getRequestURI());

        if (action == null) {
            req.getRequestDispatcher("/candidat/dashboard.jsp").forward(req, resp);
        } else {
            switch (action) {
                case "listCandidatures":
                    try {
                        listCandidatures(req,resp);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "editProfile":
                    editProfile(req,resp);
                    break;

                case "listOffres":
                    listOffres(req,resp);
                    break;

                case "profile":
                    req.getRequestDispatcher("/candidat/profile.jsp").forward(req, resp);
                    break;

                case "dashboard":
                    req.getRequestDispatcher("/candidat/dashboard.jsp").forward(req, resp);
                    break;

                default:
                    req.getRequestDispatcher("/candidat/dashboard.jsp").forward(req, resp);
                    break;
            }
        }
    }

    private void listOffres(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("offres",daoCandidat.getlistOffres());
        req.getRequestDispatcher("/candidat/listoffres.jsp").forward(req, resp);
    }

    private void editProfile(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void listCandidatures(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        req.setAttribute("candidatures",daoCandidat.getlistCandidatures(utilisateur.getId()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if (action != null) {
            switch (action) {
                case "postuler":



                    break;
                case "updateprofile":

                    System.out.println("Update action triggered");
                    break;

                    case "annuler":

                    System.out.println("Update action triggered");
                    break;

                default:

                    break;
            }
        }
    }
}
