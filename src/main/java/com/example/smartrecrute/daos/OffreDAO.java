package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Offre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreDAO {
    private Connection connection;

    public OffreDAO() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    public List<Offre> getAll() throws SQLException {
        List<Offre> offres = new ArrayList<>();
        String sql = "SELECT * FROM offre";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Offre offre = new Offre();
                offre.setId(rs.getInt("id"));
                offre.setTitre(rs.getString("titre"));
                offre.setDescription(rs.getString("description"));
                offre.setRecruteurId(rs.getInt("recruteur_id"));
                offre.setDateCreation(rs.getTimestamp("date_creation"));
                offres.add(offre);
            }
        }
        return offres;
    }
    public Offre getById(int id) throws SQLException {
        String sql = "SELECT * FROM offre WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Offre offre = new Offre();
                    offre.setId(rs.getInt("id"));
                    offre.setTitre(rs.getString("titre"));
                    offre.setDescription(rs.getString("description"));
                    offre.setRecruteurId(rs.getInt("recruteur_id"));
                    offre.setDateCreation(rs.getTimestamp("date_creation"));
                    return offre;
                }
            }
        }
        return null;
    }

    public void apply(int offreId, int candidatId) throws SQLException {
        String sql = "INSERT INTO candidature (offre_id, candidat_id, date_candidature) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, offreId);
            stmt.setInt(2, candidatId);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }
}