package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Candidat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatDAO {
    private Connection connection;

    public CandidatDAO() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    public Candidat getById(int id) throws SQLException {
        String sql = "SELECT * FROM candidat WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Candidat candidat = new Candidat();
                    candidat.setId(resultSet.getInt("id"));
                    candidat.setNom(resultSet.getString("nom"));
                    candidat.setEmail(resultSet.getString("email"));
                    candidat.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                    candidat.setCv(resultSet.getString("cv"));
                    candidat.setDateCreation(resultSet.getTimestamp("date_creation"));
                    return candidat;
                }
            }
        }
        return null;
    }

    public List<Candidat> getAll() throws SQLException {
        String sql = "SELECT * FROM candidat";
        List<Candidat> candidats = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Candidat candidat = new Candidat();
                candidat.setId(resultSet.getInt("id"));
                candidat.setNom(resultSet.getString("nom"));
                candidat.setEmail(resultSet.getString("email"));
                candidat.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                candidat.setCv(resultSet.getString("cv"));
                candidat.setDateCreation(resultSet.getTimestamp("date_creation"));
                candidats.add(candidat);
            }
        }
        return candidats;
    }

    public void create(Candidat candidat) throws SQLException {
        String sql = "INSERT INTO candidat (nom, email, utilisateur_id, cv, date_creation) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getEmail());
            preparedStatement.setInt(3, candidat.getUtilisateurId());
            preparedStatement.setString(4, candidat.getCv());
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis())); // Auto-set creation date
            preparedStatement.executeUpdate();
        }
    }

    public void update(Candidat candidat) throws SQLException {
        String sql = "UPDATE candidat SET nom = ?, email = ?, utilisateur_id = ?, cv = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, candidat.getNom());
            preparedStatement.setString(2, candidat.getEmail());
            preparedStatement.setInt(3, candidat.getUtilisateurId());
            preparedStatement.setString(4, candidat.getCv());
            preparedStatement.setInt(5, candidat.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM candidat WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}