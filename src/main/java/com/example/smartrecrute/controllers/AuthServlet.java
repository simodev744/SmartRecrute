package com.example.smartrecrute.controllers;

import com.example.smartrecrute.daos.DaoUser;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private DaoUser daoUser;

    @Override
    public void init() throws ServletException {
        try {
            daoUser = new DaoUser();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DaoUser", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        } else if ("register".equals(action)) {
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
        } else if ("logout".equals(action)) { // Handle logout via GET
            logout(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth?action=login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            try {
                login(request, response);
            } catch (SQLException | ClassNotFoundException e) {
                throw new ServletException(e);
            }
        } else if ("register".equals(action)) {
            register(request, response);
        } else if ("logout".equals(action)) {
            logout(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException, ClassNotFoundException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires !");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
            return;
        }

        Utilisateur utilisateur = daoUser.getUserByEmailAndPassword(email, password);

        if (utilisateur != null) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateur);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            switch (utilisateur.getRole().toLowerCase()) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/admin/dashboard.jsp");
                    break;
                case "recruteur":
                    response.sendRedirect(request.getContextPath() + "/recruteur/dashboard.jsp");
                    break;
                case "candidat":
                    response.sendRedirect(request.getContextPath() + "/candidat/dashboard.jsp");
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
                    break;
            }
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (username == null || email == null || password == null || role == null ||
                username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires !");
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            return;
        }

        try {
            if (daoUser.isEmailExists(email)) {
                request.setAttribute("error", "Cet email est déjà utilisé.");
                request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
                return;
            }

            Utilisateur utilisateur = new Utilisateur(username, password, email, role);
            if (daoUser.addUser(utilisateur)) {
                response.sendRedirect(request.getContextPath() + "/auth?action=login");
            } else {
                request.setAttribute("error", "Erreur d'inscription.");
                request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Erreur d'inscription.", e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/auth?action=login"); // Use absolute path
    }
}