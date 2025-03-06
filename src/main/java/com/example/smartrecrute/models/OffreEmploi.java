package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class OffreEmploi {
    private int id;
    private String titre;
    private String description;
    private String entreprise;
    private int recruteurId;
    private Timestamp datePublication;

    // Constructors
    public OffreEmploi() {}
    public OffreEmploi(int id, String titre, String description, String entreprise, int recruteurId, Timestamp datePublication) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.entreprise = entreprise;
        this.recruteurId = recruteurId;
        this.datePublication = datePublication;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }
    public int getRecruteurId() { return recruteurId; }
    public void setRecruteurId(int recruteurId) { this.recruteurId = recruteurId; }
    public Timestamp getDatePublication() { return datePublication; }
    public void setDatePublication(Timestamp datePublication) { this.datePublication = datePublication; }
}