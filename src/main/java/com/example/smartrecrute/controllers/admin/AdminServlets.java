package com.example.smartrecrute.controllers.admin;

import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url=req.getRequestURI();
        HttpSession session = req.getSession(false);
        Utilisateur utilisateur = (session != null) ? (Utilisateur) session.getAttribute("utilisateur") : null;
        String action=req.getParameter("action");
        System.out.println(utilisateur.getRole());
        System.out.println(url);
        if (action.equals("dashboard")) {
            req.getRequestDispatcher("/candidat/dashboard.jsp").forward(req,resp);
        }
    }


}
