package com.example.smartrecrute.daos;
import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Candidature;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecruteurDAO {

    private Connection connection;

    public RecruteurDAO() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    public void createOffre(OffreEmploi offreEmploi) throws SQLException {
        String sql = "INSERT INTO offres_emploi (titre, description, entreprise, recruteur_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setString(3, offreEmploi.getEntreprise());
            preparedStatement.setInt(4, offreEmploi.getRecruteurId());
            preparedStatement.executeUpdate();
        }
    }

    public void updateOffre(OffreEmploi offreEmploi) throws SQLException {
        String sql = "UPDATE offres_emploi SET titre = ?, description = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setInt(3, offreEmploi.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteOffre(int id) throws SQLException {
        String sql = "DELETE FROM offres_emploi WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<OffreEmploi> listOffres() throws SQLException {
        List<OffreEmploi> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres_emploi";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                offres.add(new OffreEmploi(
                        resultSet.getInt("id"),
                        resultSet.getString("titre"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("date_publication"),
                        resultSet.getString("entreprise"),
                        resultSet.getInt("recruteur_id")
                ));
            }
        }
        return offres;
    }

    public OffreEmploi getOffre(int id, int recruteurId) throws SQLException {
        String sql = "SELECT * FROM offres_emploi WHERE id = ? AND recruteur_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, recruteurId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new OffreEmploi(
                            resultSet.getInt("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("date_publication"),
                            resultSet.getString("entreprise"),
                            resultSet.getInt("recruteur_id")
                    );
                }
            }
        }
        return null;
    }

    public List<OffreEmploi> listOffresByRecruteur(int idRecruteur) throws SQLException {
        List<OffreEmploi> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres_emploi WHERE recruteur_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idRecruteur);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    offres.add(new OffreEmploi(
                            resultSet.getInt("id"),
                            resultSet.getString("titre"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("date_publication"),
                            resultSet.getString("entreprise"),
                            resultSet.getInt("recruteur_id")
                    ));
                }
            }
        }
        return offres;
    }

    public List<Candidature> getCandidatureByOffre(int idOffre) throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT c.*,u.* FROM utilisateurs u INNER JOIN  candidatures c " +
                "ON u.id=c.candidat_id INNER JOIN offres_emploi_candidatures oec ON c.id = oec.candidature_id " +
                "WHERE oec.offre_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idOffre);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    candidatures.add(new Candidature(
                            resultSet.getInt("id"),
                            resultSet.getInt("candidat_id"),
                            idOffre,
                            resultSet.getString("statut"),
                            new Utilisateur(
                                    resultSet.getString("nom_utilisateur"),
                                    resultSet.getString("email"),
                                    resultSet.getString("role"),
                                    resultSet.getString("cv")
                            ),

                            resultSet.getTimestamp("date_candidature")
                            ));

                }
            }
        }
        return candidatures;
    }


    public void acceptCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Acceptée' WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        }
    }

    public void rejectCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Rejetée' WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteCandidature(int idCandidature) throws SQLException {
        String sql = "DELETE FROM candidatures WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        }
    }
}
