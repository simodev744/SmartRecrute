package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Candidature;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecruteurDAO {
    private static final Logger logger = LoggerFactory.getLogger(RecruteurDAO.class);
    private final Connection connection;

    public RecruteurDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to initialize RecruteurDAO", e);
        }
    }

    public void createOffre(OffreEmploi offreEmploi) throws SQLException {
        validateOffre(offreEmploi);
        String sql = "INSERT INTO offres_emploi (titre, description, entreprise, recruteur_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, offreEmploi.getTitre());
            ps.setString(2, offreEmploi.getDescription());
            ps.setString(3, offreEmploi.getEntreprise());
            ps.setInt(4, offreEmploi.getRecruteurId());
            ps.executeUpdate();
            logger.info("Offre created for recruteurId: {}", offreEmploi.getRecruteurId());
        }
    }

    public void updateOffre(OffreEmploi offreEmploi) throws SQLException {
        validateOffre(offreEmploi);
        String sql = "UPDATE offres_emploi SET titre = ?, description = ?, entreprise = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, offreEmploi.getTitre());
            ps.setString(2, offreEmploi.getDescription());
            ps.setString(3, offreEmploi.getEntreprise());
            ps.setInt(4, offreEmploi.getId());
            ps.executeUpdate();
            logger.info("Offre updated with id: {}", offreEmploi.getId());
        }
    }

    public void deleteOffre(int id) throws SQLException {
        String sql = "DELETE FROM offres_emploi WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Offre deleted with id: {}", id);
        }
    }

    public List<OffreEmploi> listOffres() throws SQLException {
        List<OffreEmploi> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres_emploi";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                offres.add(new OffreEmploi(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getTimestamp("date_publication"),
                        rs.getString("entreprise"),
                        rs.getInt("recruteur_id")
                ));
            }
        }
        return offres;
    }

    public OffreEmploi getOffre(int id, int recruteurId) throws SQLException {
        String sql = "SELECT * FROM offres_emploi WHERE id = ? AND recruteur_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setInt(2, recruteurId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OffreEmploi(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            rs.getString("description"),
                            rs.getTimestamp("date_publication"),
                            rs.getString("entreprise"),
                            rs.getInt("recruteur_id")
                    );
                }
                return null;
            }
        }
    }

    public List<OffreEmploi> listOffresByRecruteur(int idRecruteur) throws SQLException {
        List<OffreEmploi> offres = new ArrayList<>();
        String sql = "SELECT * FROM offres_emploi WHERE recruteur_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idRecruteur);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    offres.add(new OffreEmploi(
                            rs.getInt("id"),
                            rs.getString("titre"),
                            rs.getString("description"),
                            rs.getTimestamp("date_publication"),
                            rs.getString("entreprise"),
                            rs.getInt("recruteur_id")
                    ));
                }
            }
        }
        return offres;
    }

    public List<Candidature> getCandidatureByOffre(int idOffre) throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
        String sql = "SELECT c.*, u.* FROM utilisateurs u INNER JOIN candidatures c ON u.id = c.candidat_id " +
                "INNER JOIN offres_emploi_candidatures oec ON c.id = oec.candidature_id WHERE oec.offre_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idOffre);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    candidatures.add(new Candidature(
                            rs.getInt("id"),
                            rs.getInt("candidat_id"),
                            idOffre,
                            rs.getString("statut"),
                            new Utilisateur(
                                    rs.getString("nom_utilisateur"),
                                    rs.getString("email"),
                                    rs.getString("role"),
                                    rs.getString("cv")
                            ),
                            rs.getTimestamp("date_candidature")
                    ));
                }
            }
        }
        return candidatures;
    }

    public void acceptCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Acceptée' WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCandidature);
            ps.executeUpdate();
            logger.info("Candidature accepted with id: {}", idCandidature);
        }
    }

    public void rejectCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Rejetée' WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCandidature);
            ps.executeUpdate();
            logger.info("Candidature rejected with id: {}", idCandidature);
        }
    }

    public void deleteCandidature(int idCandidature) throws SQLException {
        String sql = "DELETE FROM candidatures WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idCandidature);
            ps.executeUpdate();
            logger.info("Candidature deleted with id: {}", idCandidature);
        }
    }

    private void validateOffre(OffreEmploi offre) {
        if (offre == null || isEmpty(offre.getTitre()) || isEmpty(offre.getDescription()) || isEmpty(offre.getEntreprise())) {
            throw new IllegalArgumentException("All offer fields must be non-empty");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}