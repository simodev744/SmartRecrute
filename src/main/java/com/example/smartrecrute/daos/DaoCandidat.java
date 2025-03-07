package com.example.smartrecrute.daos;

import com.example.smartrecrute.connection.DBConnection;
import com.example.smartrecrute.models.Candidature;
import com.example.smartrecrute.models.OffreEmploi;
import com.example.smartrecrute.models.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public  class DaoCandidat   {


    private Connection connection;

    public DaoCandidat() throws SQLException, ClassNotFoundException {
        this.connection = DBConnection.getConnection();
    }

    public List<Candidature> getlistCandidatures(int candidat_id) throws SQLException {
        List<Candidature> candidatures = new ArrayList<>();
         String sql="Select * FROM candidatures where candidat_id= ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,candidat_id);
        preparedStatement.executeQuery();
        while (preparedStatement.getResultSet().next()) {
            candidatures.add(new Candidature(
                    preparedStatement.getResultSet().getInt("id"),
                    preparedStatement.getResultSet().getInt("candidat_id"),
                    preparedStatement.getResultSet().getString("statut"),
                    preparedStatement.getResultSet().getTimestamp("date_candidature")
            ));
        }
        return candidatures;


    }


    public List<OffreEmploi> getlistOffres(){
        String sql="Select * FROM offre_emploi ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeQuery();
            List<OffreEmploi> offres = new ArrayList<>();
            while (preparedStatement.getResultSet().next()) {
                offres.add(new OffreEmploi(
                        preparedStatement.getResultSet().getInt("id"),
                        preparedStatement.getResultSet().getString("titre"),
                        preparedStatement.getResultSet().getString("description"),
                        preparedStatement.getResultSet().getTimestamp("date_publication"),
                        preparedStatement.getResultSet().getString("entreprise"),
                        preparedStatement.getResultSet().getInt("recruteur_id")
                ));
            }
            return offres;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

     public void updateProfile(Utilisateur utilisateur){

       String sql="UPDATE utilisateurs SET nom_utilisateur=?,email=?,cv=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,utilisateur.getNomUtilisateur());
            preparedStatement.setString(2,utilisateur.getEmail());
            preparedStatement.setString(3,utilisateur.getCv());
            preparedStatement.setInt(4,utilisateur.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void postuler(int offre_id,int candidat_id){
        String sql="INSERT INTO candidatures (candidat_id,offre_id) VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,candidat_id);
            preparedStatement.setInt(2,offre_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

    }

    }

    public void annuler(int offre_id,int candidat_id) {
        String sql = "DELETE FROM candidatures WHERE candidat_id=? AND offre_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, candidat_id);
            preparedStatement.setInt(2, offre_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
