package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Utilisateur;
import jakarta.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoUser {

    public Utilisateur getUtilisateur(String email, String password) throws ServletException {
        Utilisateur utilisateur = null;
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT id, nom_utilisateur, email,mot_de_passe,role,date_creation FROM utilisateurs WHERE email = ? AND mot_de_passe = SHA2(?, 256)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        utilisateur = new Utilisateur(
                                rs.getInt("id"),
                                rs.getString("nom_utilisateur"),
                                rs.getString("mot_de_passe"),
                                rs.getString("email"),
                                rs.getString("role"),
                                rs.getTimestamp("date_creation")
                        );


                    }
                    return utilisateur;
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur de connexion à la base de données.", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




    public boolean checkemail(String email) throws SQLException {
        try (Connection conn = DBConnection.getConnection()) {

            boolean exist=false;
            String checkEmailSql = "SELECT id FROM utilisateurs WHERE email = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailSql)) {
                checkStmt.setString(1, email);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                       exist=true;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            
            return exist;


    } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertuser(String username, String email, String password, String role) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO utilisateurs (nom_utilisateur, email, mot_de_passe, role) VALUES (?, ?, SHA2(?, 256), ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}