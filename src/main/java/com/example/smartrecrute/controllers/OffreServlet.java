package com.example.smartrecrute.controllers;

import com.example.smartrecrute.daos.OffreDAO;
import com.example.smartrecrute.models.Offre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/offre")
public class OffreServlet extends HttpServlet {
    private OffreDAO offreDAO;

    @Override
    public void init() throws ServletException {
        try {
            offreDAO = new OffreDAO();
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize OffreDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            try {
                List<Offre> offres = offreDAO.getAll();
                request.setAttribute("offres", offres);
                request.getRequestDispatcher("/offre/listOffre.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving offers");
            }
        } else if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                Offre offre = offreDAO.getById(id); // Add this method to OffreDAO
                request.setAttribute("offre", offre);
                request.getRequestDispatcher("/offre/offre.jsp").forward(request, response);
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving offer");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("apply".equals(action)) {
            int offreId = Integer.parseInt(request.getParameter("offreId"));
            int candidatId = Integer.parseInt(request.getParameter("candidatId"));
            try {
                offreDAO.apply(offreId, candidatId);
                response.sendRedirect(request.getContextPath() + "/offre?action=list");
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error applying to offer");
            }
        }
    }
}