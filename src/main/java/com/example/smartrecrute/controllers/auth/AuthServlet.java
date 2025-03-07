package com.example.smartrecrute.controllers.auth;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.daos.DaoUser;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    private DaoUser daoUser;

    @Override
    public void init() throws ServletException {
        daoUser=new DaoUser();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        } else if ("register".equals(action)) {
            request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            login(request, response);
        } else if ("register".equals(action)) {
            register(request, response);
        } else if ("logout".equals(action)) {
            logout(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

                if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                    request.setAttribute("error", "Tous les champs sont obligatoires !");
                    System.out.println("logim post");
                    request.getRequestDispatcher("auth/login.jsp").forward(request, response);
                    return;
                }

                        Utilisateur utilisateur = daoUser.getUtilisateur(email,password);

                        HttpSession session = request.getSession();
                        session.setAttribute("utilisateur", utilisateur);
                        session.setMaxInactiveInterval(30 * 60);

                        if (utilisateur==null){
                            return;
                        }

                        switch (utilisateur.getRole().toLowerCase()) {
                            case "admin":
                                response.sendRedirect("admin");
                                break;
                            case "recruteur":
                                response.sendRedirect("recruteur");
                                break;
                            case "candidat":
                                response.sendRedirect("candidat");
                                break;
                            default:
                                response.sendRedirect("dashboard"); // Par défaut
                                break;
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

        try (Connection conn = DBConnection.getConnection()) {


                    if (daoUser.checkemail(email)) {
                        request.setAttribute("error", "Cet email est déjà utilisé.");
                        request.getRequestDispatcher("/auth/register.jsp").forward(request, response);
                        return;
                    }

            daoUser.insertuser(username,email,password,role);
            response.sendRedirect("auth?action=login");
        } catch (SQLException e) {
            throw new ServletException("Erreur d'inscription.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("auth?action=login");
    }
}


