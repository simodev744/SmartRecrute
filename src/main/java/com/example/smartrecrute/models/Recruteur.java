package com.example.smartrecrute.models;
import java.sql.Timestamp;

public class Recruteur extends Utilisateur {
    private int id;
    private int utilisateurId;
    private String entreprise;
    private Timestamp dateCreation;


    public Recruteur(int id, String nomUtilisateur, String motDePasse, String email, String role, Timestamp dateCreation) {
        super(id, nomUtilisateur, motDePasse, email, role, dateCreation);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}