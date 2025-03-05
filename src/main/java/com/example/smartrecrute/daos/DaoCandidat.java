
package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Candidature;
import com.example.smartrecrute.models.OffreEmploi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCandidat {
    private Connection connection = DBConnection.getConnection();

    public DaoCandidat() throws SQLException, ClassNotFoundException {
    }

    public List<Candidature> getlistCandidatures(int candidat_id) throws SQLException {
        List<Candidature> candidatures = new ArrayList();
        String sql = "SELECT c.*,oec.* FROM candidatures c INNER JOIN offres_emploi_candidatures oec ON c.id = oec.candidature_id WHERE c.candidat_id = ?;";
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        preparedStatement.setInt(1, candidat_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Candidature candidature = null;

        while(resultSet.next()) {
            candidature = new Candidature(resultSet.getInt("id"), resultSet.getInt("candidat_id"), resultSet.getTimestamp("date_candidature"), resultSet.getString("statut"));
            candidatures.add(candidature);
            candidature.setOffreId(resultSet.getInt("offre_id"));
        }

        return candidatures;
    }

    public List<OffreEmploi> getlistOffres() {
        List<OffreEmploi> offres = new ArrayList();
        String sql = "SELECT * FROM offres_emploi";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            try {
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    offres.add(new OffreEmploi(resultSet.getInt("id"), resultSet.getString("titre"), resultSet.getString("description"), resultSet.getTimestamp("date_publication"), resultSet.getString("entreprise"), resultSet.getInt("recruteur_id")));
                }
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
        } catch (SQLException var8) {
            SQLException e = var8;
            e.printStackTrace();
        }

        return offres;
    }

    public void updateProfile(String cv, int candidat_id) {
        String sql = "UPDATE utilisateurs SET  cv = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            try {
                preparedStatement.setString(1, cv);
                preparedStatement.setInt(2, candidat_id);
                preparedStatement.executeUpdate();
            } catch (Throwable var8) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException var9) {
            SQLException e = var9;
            e.printStackTrace();
        }

    }

    public void postuler(int offre_id, int candidat_id) {
        System.out.println("postuLer");
        String createcondidature = "INSERT INTO candidatures (candidat_id) VALUES (?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(createcondidature);

            try {
                preparedStatement.setInt(1, candidat_id);
                preparedStatement.executeUpdate();
            } catch (Throwable var17) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var12) {
                        var17.addSuppressed(var12);
                    }
                }

                throw var17;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException var18) {
            SQLException e = var18;
            e.printStackTrace();
        }

        int candidature_id = 0;
        String maxId = "SELECT MAX(id) as id FROM candidatures WHERE candidat_id=?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(maxId);

            try {
                preparedStatement.setInt(1, candidat_id);

                for(ResultSet resultSet = preparedStatement.executeQuery(); resultSet.next(); candidature_id = resultSet.getInt("id")) {
                }
            } catch (Throwable var15) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var11) {
                        var15.addSuppressed(var11);
                    }
                }

                throw var15;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException var16) {
            SQLException e = var16;
            e.printStackTrace();
        }

        String sql = "INSERT INTO offres_emploi_candidatures (offre_id, candidature_id) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            try {
                preparedStatement.setInt(1, offre_id);
                preparedStatement.setInt(2, candidature_id);
                preparedStatement.executeUpdate();
            } catch (Throwable var13) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var10) {
                        var13.addSuppressed(var10);
                    }
                }

                throw var13;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            e.printStackTrace();
        }

    }

    public void annuler(int candidature_id, int candidat_id) {
        String sql = "DELETE FROM candidatures WHERE  id =? and candidat_id=?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            try {
                preparedStatement.setInt(1, candidature_id);
                preparedStatement.setInt(2, candidat_id);
                preparedStatement.executeUpdate();
                System.out.println("candidature deleted");
            } catch (Throwable var8) {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException var9) {
            SQLException e = var9;
            e.printStackTrace();
        }

    }
}
