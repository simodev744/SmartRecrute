package com.example.smartrecrute.controllers;

import com.example.smartrecrute.daos.CandidatDAO;
import com.example.smartrecrute.models.Candidat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/candidat")
public class CandidatServlet extends HttpServlet {
    private CandidatDAO candidatDAO;

    @Override
    public void init() throws ServletException {
        try {
            candidatDAO = new CandidatDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize CandidatDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            request.getRequestDispatcher("/candidat/createCandidat.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Candidat candidat = candidatDAO.getById(id);
                request.setAttribute("candidat", candidat);
                request.getRequestDispatcher("/candidat/viewCandidat.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving candidat");
            }
        } else if ("list".equals(action)) {
            try {
                List<Candidat> candidats = candidatDAO.getAll();
                request.setAttribute("candidats", candidats);
                request.getRequestDispatcher("/candidat/listCandidats.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving candidats list");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            Candidat candidat = new Candidat();
            candidat.setNom(request.getParameter("nom"));
            candidat.setEmail(request.getParameter("email"));
            candidat.setUtilisateurId(Integer.parseInt(request.getParameter("utilisateur_id")));
            candidat.setCv(request.getParameter("cv"));

            try {
                candidatDAO.create(candidat);
                response.sendRedirect("candidat?action=list");
            } catch (SQLException e) {
                response.getWriter().write("Error creating candidat: " + e.getMessage());
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Candidat candidat = new Candidat();
            candidat.setId(id);
            candidat.setNom(request.getParameter("nom"));
            candidat.setEmail(request.getParameter("email"));
            candidat.setUtilisateurId(Integer.parseInt(request.getParameter("utilisateur_id")));
            candidat.setCv(request.getParameter("cv"));

            try {
                candidatDAO.update(candidat);
                response.sendRedirect("candidat?action=view&id=" + id);
            } catch (SQLException e) {
                response.getWriter().write("Error updating candidat: " + e.getMessage());
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                candidatDAO.delete(id);
                response.sendRedirect("candidat?action=list");
            } catch (SQLException e) {
                response.getWriter().write("Error deleting candidat: " + e.getMessage());
            }
        }
    }
}