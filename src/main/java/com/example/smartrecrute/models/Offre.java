package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class Offre {
    private int id;
    private String titre;
    private String description;
    private int recruteurId;
    private Timestamp dateCreation;

    // Constructors
    public Offre() {}
    public Offre(int id, String titre, String description, int recruteurId, Timestamp dateCreation) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.recruteurId = recruteurId;
        this.dateCreation = dateCreation;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getRecruteurId() { return recruteurId; }
    public void setRecruteurId(int recruteurId) { this.recruteurId = recruteurId; }
    public Timestamp getDateCreation() { return dateCreation; }
    public void setDateCreation(Timestamp dateCreation) { this.dateCreation = dateCreation; }
}