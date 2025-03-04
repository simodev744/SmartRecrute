package com.example.smartrecrute.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String role = (session != null) ? (String) session.getAttribute("role") : null;
        String path = req.getRequestURI();

        if (path.startsWith("/admin") && (role == null || !role.equals("admin"))) {
            res.sendRedirect("/login.jsp");
            return;
        }
        if (path.startsWith("/recruteur") && (role == null || !role.equals("recruteur"))) {
            res.sendRedirect("/login.jsp");
            return;
        }
        if (path.startsWith("/candidat") && (role == null || !role.equals("candidat"))) {
            res.sendRedirect("/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
