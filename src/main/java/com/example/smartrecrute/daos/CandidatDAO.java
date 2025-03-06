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

    public boolean create(Candidat candidat) {
        String query = "INSERT INTO candidats (utilisateur_id, cv) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, candidat.getUtilisateurId());
            statement.setString(2, candidat.getCv());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Candidat getById(int id) {
        String query = "SELECT * FROM candidats WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Candidat candidat = new Candidat();
                candidat.setId(resultSet.getInt("id"));
                candidat.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                candidat.setCv(resultSet.getString("cv"));
                candidat.setDateCreation(resultSet.getTimestamp("date_creation"));
                return candidat;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Candidat> getAll() {
        List<Candidat> candidats = new ArrayList<>();
        String query = "SELECT * FROM candidats";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Candidat candidat = new Candidat();
                candidat.setId(resultSet.getInt("id"));
                candidat.setUtilisateurId(resultSet.getInt("utilisateur_id"));
                candidat.setCv(resultSet.getString("cv"));
                candidat.setDateCreation(resultSet.getTimestamp("date_creation"));
                candidats.add(candidat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidats;
    }


    public boolean update(Candidat candidat) {
        String query = "UPDATE candidats SET utilisateur_id = ?, cv = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, candidat.getUtilisateurId());
            statement.setString(2, candidat.getCv());
            statement.setInt(3, candidat.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(int id) {
        String query = "DELETE FROM candidats WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
