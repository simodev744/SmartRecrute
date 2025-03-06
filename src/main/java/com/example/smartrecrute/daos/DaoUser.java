package com.example.smartrecrute.daos;

import java.sql.*;
import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Utilisateur;

public class DaoUser {

    // Retrieve a user by ID
    public Utilisateur getUserById(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT id, nom_utilisateur, email, mot_de_passe, role, date_creation FROM utilisateurs WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_utilisateur"),
                        resultSet.getString("mot_de_passe"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("date_creation")
                );
            }
            return null;
        }
    }

    // Add a new user
    public boolean addUser(Utilisateur utilisateur) throws SQLException {
        String query = "INSERT INTO utilisateurs (nom_utilisateur, email, mot_de_passe, role) VALUES (?, ?, SHA2(?, 256), ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, utilisateur.getNomUtilisateur());
            statement.setString(2, utilisateur.getEmail());
            statement.setString(3, utilisateur.getMotDePasse());
            statement.setString(4, utilisateur.getRole());

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Check if the email is already registered
    public boolean isEmailExists(String email) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM utilisateurs WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        }
    }

    public Utilisateur getUserByEmailAndPassword(String email, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT id, nom_utilisateur, email, mot_de_passe, role, date_creation FROM utilisateurs WHERE email = ? AND mot_de_passe = SHA2(?, 256)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password); // Assuming the password is already hashed before passing it

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Utilisateur(
                        resultSet.getInt("id"),
                        resultSet.getString("nom_utilisateur"),
                        resultSet.getString("mot_de_passe"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("date_creation")
                );
            }
            return null; // No matching user found
        }
    }

}
