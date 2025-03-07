package com.example.smartrecrute.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import com.example.smartrecrute.models.Utilisateur;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code (if needed)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Retrieve the user from session (if available)
        Utilisateur utilisateur = (session != null) ? (Utilisateur) session.getAttribute("utilisateur") : null;

        // Get the requested path
        String path = req.getRequestURI();

        System.out.println("Requested path: " + path);


        if (path.endsWith("/admin") && (utilisateur == null || !"admin".equals(utilisateur.getRole()))) {
            res.sendRedirect("auth?action=login");
            return;
        }
        if (path.endsWith("/recruteur") && (utilisateur == null || !"recruteur".equals(utilisateur.getRole()))) {
            res.sendRedirect("auth?action=login");
            return;
        }
        if (path.endsWith("/candidat") && (utilisateur == null || !"candidat".equals(utilisateur.getRole()))) {
            res.sendRedirect("auth?action=login");
            return;
        }

        // Proceed with the filter chain if no redirect occurred
        System.out.println("it s work");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code (if needed)
    }
}
