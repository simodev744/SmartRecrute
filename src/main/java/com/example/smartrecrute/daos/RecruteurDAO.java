
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

public class RecruteurDAO {
    private Connection connection = DBConnection.getConnection();

    public RecruteurDAO() throws SQLException, ClassNotFoundException {
    }

    public void createOffre(OffreEmploi offreEmploi) throws SQLException {
        String sql = "INSERT INTO offres_emploi (titre, description, entreprise, recruteur_id) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setString(3, offreEmploi.getEntreprise());
            preparedStatement.setInt(4, offreEmploi.getRecruteurId());
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public void updateOffre(OffreEmploi offreEmploi) throws SQLException {
        String sql = "UPDATE offres_emploi SET titre = ?, description = ? WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setString(1, offreEmploi.getTitre());
            preparedStatement.setString(2, offreEmploi.getDescription());
            preparedStatement.setInt(3, offreEmploi.getId());
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public void deleteOffre(int id) throws SQLException {
        String sql = "DELETE FROM offres_emploi WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public List<OffreEmploi> listOffres() throws SQLException {
        List<OffreEmploi> offres = new ArrayList();
        String sql = "SELECT * FROM offres_emploi";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            ResultSet resultSet = preparedStatement.executeQuery();

            try {
                while(resultSet.next()) {
                    offres.add(new OffreEmploi(resultSet.getInt("id"), resultSet.getString("titre"), resultSet.getString("description"), resultSet.getTimestamp("date_publication"), resultSet.getString("entreprise"), resultSet.getInt("recruteur_id")));
                }
            } catch (Throwable var9) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Throwable var10) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }
            }

            throw var10;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return offres;
    }

    public OffreEmploi getOffre(int id, int recruteurId) throws SQLException {
        String sql = "SELECT * FROM offres_emploi WHERE id = ? AND recruteur_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        OffreEmploi var6;
        label69: {
            try {
                label70: {
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, recruteurId);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    label71: {
                        try {
                            if (resultSet.next()) {
                                var6 = new OffreEmploi(resultSet.getInt("id"), resultSet.getString("titre"), resultSet.getString("description"), resultSet.getTimestamp("date_publication"), resultSet.getString("entreprise"), resultSet.getInt("recruteur_id"));
                                break label71;
                            }
                        } catch (Throwable var10) {
                            if (resultSet != null) {
                                try {
                                    resultSet.close();
                                } catch (Throwable var9) {
                                    var10.addSuppressed(var9);
                                }
                            }

                            throw var10;
                        }

                        if (resultSet != null) {
                            resultSet.close();
                        }
                        break label70;
                    }

                    if (resultSet != null) {
                        resultSet.close();
                    }
                    break label69;
                }
            } catch (Throwable var11) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var8) {
                        var11.addSuppressed(var8);
                    }
                }

                throw var11;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            return null;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return var6;
    }

    public List<OffreEmploi> listOffresByRecruteur(int idRecruteur) throws SQLException {
        List<OffreEmploi> offres = new ArrayList();
        String sql = "SELECT * FROM offres_emploi WHERE recruteur_id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, idRecruteur);
            ResultSet resultSet = preparedStatement.executeQuery();

            try {
                while(resultSet.next()) {
                    offres.add(new OffreEmploi(resultSet.getInt("id"), resultSet.getString("titre"), resultSet.getString("description"), resultSet.getTimestamp("date_publication"), resultSet.getString("entreprise"), resultSet.getInt("recruteur_id")));
                }
            } catch (Throwable var10) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Throwable var11) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var8) {
                    var11.addSuppressed(var8);
                }
            }

            throw var11;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return offres;
    }

    public List<Candidature> getCandidatureByOffre(int idOffre) throws SQLException {
        List<Candidature> candidatures = new ArrayList();
        String sql = "SELECT c.*,u.* FROM utilisateurs u INNER JOIN  candidatures c ON u.id=c.candidat_id INNER JOIN offres_emploi_candidatures oec ON c.id = oec.candidature_id WHERE oec.offre_id = ? ";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, idOffre);
            ResultSet resultSet = preparedStatement.executeQuery();

            try {
                while(resultSet.next()) {
                    candidatures.add(new Candidature(resultSet.getInt("id"), resultSet.getInt("candidat_id"), idOffre, resultSet.getString("statut"), new Utilisateur(resultSet.getString("nom_utilisateur"), resultSet.getString("email"), resultSet.getString("role"), resultSet.getString("cv")), resultSet.getTimestamp("date_candidature")));
                }
            } catch (Throwable var10) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Throwable var11) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var8) {
                    var11.addSuppressed(var8);
                }
            }

            throw var11;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        return candidatures;
    }

    public void acceptCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Acceptée' WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public void rejectCandidature(int idCandidature) throws SQLException {
        String sql = "UPDATE candidatures SET statut = 'Rejetée' WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    public void deleteCandidature(int idCandidature) throws SQLException {
        String sql = "DELETE FROM candidatures WHERE id = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

        try {
            preparedStatement.setInt(1, idCandidature);
            preparedStatement.executeUpdate();
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }
}
