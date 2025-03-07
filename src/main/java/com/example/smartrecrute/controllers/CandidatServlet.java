package com.example.smartrecrute.controllers;

import com.example.smartrecrute.daos.CandidatDAO;
import com.example.smartrecrute.models.Candidat;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.util.List;


@WebServlet("/candidat")
public class CandidatServlet extends HttpServlet {
    private CandidatDAO candidatDAO;

    @Override
    public void init() throws ServletException {
        try {
            candidatDAO = new CandidatDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("create")) {
            request.getRequestDispatcher("/candidat/createCandidat.jsp").forward(request, response);

        }


        if ("view".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Candidat candidat = candidatDAO.getById(id);
            request.setAttribute("candidat", candidat);
            request.getRequestDispatcher("candiat/viewCandidat.jsp").forward(request, response);
        } else if ("list".equals(action)) {
            List<Candidat> candidats = candidatDAO.getAll();
            request.setAttribute("candidats", candidats);
             request.getRequestDispatcher("candidat/listCandidats.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            int utilisateurId = Integer.parseInt(request.getParameter("utilisateur_id"));
            String cv = request.getParameter("cv");

            Candidat candidat = new Candidat();
            candidat.setUtilisateurId(utilisateurId);
            candidat.setCv(cv);

            boolean result = candidatDAO.create(candidat);
            if (result) {
                response.sendRedirect("candidat?action=list");
            } else {
                response.getWriter().write("Error creating candidat.");
            }
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int utilisateurId = Integer.parseInt(request.getParameter("utilisateur_id"));
            String cv = request.getParameter("cv");

            Candidat candidat = new Candidat();
            candidat.setId(id);
            candidat.setUtilisateurId(utilisateurId);
            candidat.setCv(cv);

            boolean result = candidatDAO.update(candidat);
            if (result) {
                response.sendRedirect("candidat?action=view&id=" + id);
            } else {
                response.getWriter().write("Error updating candidat.");
            }
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            boolean result = candidatDAO.delete(id);
            if (result) {
                response.sendRedirect("candidat?action=list");
            } else {
                response.getWriter().write("Error deleting candidat.");
            }
        }
    }
}
