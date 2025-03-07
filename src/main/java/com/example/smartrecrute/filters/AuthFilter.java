package com.example.smartrecrute.filters;

import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        Utilisateur utilisateur = (session != null) ? (Utilisateur) session.getAttribute("utilisateur") : null;
        String path = req.getRequestURI();


        System.out.println(utilisateur);


        if (path.endsWith("/admin") && (utilisateur == null || !utilisateur.getRole().equals("admin"))) {
            res.sendRedirect("auth?action=login");
            return;
        }

        if (path.endsWith("/recruteur") && (utilisateur == null || !utilisateur.getRole().equals("recruteur"))) {
            res.sendRedirect("auth?action=login");
            return;
        }

        if (path.endsWith("/candidat") && ((utilisateur == null) || !utilisateur.getRole().equals("candidat"))) {
            res.sendRedirect("auth?action=login");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
