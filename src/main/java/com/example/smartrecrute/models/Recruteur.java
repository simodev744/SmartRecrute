package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class Recruteur extends Utilisateur {
    private int id;           // recruteurs.id
    private int utilisateurId; // recruteurs.utilisateur_id
    private String entreprise;
    private Timestamp dateCreation;

    public Recruteur() {
        super();
    }

    public Recruteur(int utilisateurId, String nomUtilisateur, String motDePasse, String email, String role, String entreprise) {
        super(0, nomUtilisateur, motDePasse, email, role, null);
        this.utilisateurId = utilisateurId;
        this.entreprise = entreprise;
    }

    @Override
    public int getId() { return id; }
    @Override
    public void setId(int id) { this.id = id; }
    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }
    public Timestamp getDateCreation() { return dateCreation; }
    public void setDateCreation(Timestamp dateCreation) { this.dateCreation = dateCreation; }
}