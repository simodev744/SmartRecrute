package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Recruteur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoRecruter {
    private Connection connection;

    public DaoRecruter() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    public void addOffre(OffreEmploi offre) throws SQLException {
        String sql = "INSERT INTO offres_emploi (titre, description, entreprise, recruteur_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, offre.getTitre());
            stmt.setString(2, offre.getDescription());
            stmt.setString(3, offre.getEntreprise());
            stmt.setInt(4, offre.getRecruteurId());
            stmt.executeUpdate();
        }
    }

    public List<OffreEmploi> getOffresByRecruteur(int recruteurId) throws SQLException {
        List<OffreEmploi> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres_emploi WHERE recruteur_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, recruteurId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OffreEmploi offre = new OffreEmploi();
                    offre.setId(rs.getInt("id"));
                    offre.setTitre(rs.getString("titre"));
                    offre.setDescription(rs.getString("description"));
                    offre.setEntreprise(rs.getString("entreprise"));
                    offre.setRecruteurId(rs.getInt("recruteur_id"));
                    offre.setDatePublication(rs.getTimestamp("date_publication"));
                    offres.add(offre);
                }
            }
        }
        return offres;
    }

    public Recruteur getRecruteurByUtilisateurId(int utilisateurId) throws SQLException {
        String sql = "SELECT r.*, u.nom_utilisateur, u.email, u.role, u.date_creation " +
                "FROM recruteurs r JOIN utilisateurs u ON r.utilisateur_id = u.id " +
                "WHERE r.utilisateur_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, utilisateurId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Recruteur recruteur = new Recruteur();
                    recruteur.setId(rs.getInt("id"));
                    recruteur.setUtilisateurId(rs.getInt("utilisateur_id"));
                    recruteur.setEntreprise(rs.getString("entreprise"));
                    recruteur.setDateCreation(rs.getTimestamp("date_creation"));
                    recruteur.setNomUtilisateur(rs.getString("nom_utilisateur"));
                    recruteur.setEmail(rs.getString("email"));
                    recruteur.setRole(rs.getString("role"));
                    return recruteur;
                }
            }
        }
        return null;
    }
}