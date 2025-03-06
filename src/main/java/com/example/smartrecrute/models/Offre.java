package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class OffreEmploi {
    private int id;
    private String titre;
    private String description;
    private Timestamp datePublication;
    private String entreprise;
    private int recruteurId;

    public OffreEmploi(int id, String titre, String description, Timestamp datePublication, String entreprise, int recruteurId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.entreprise = entreprise;
        this.recruteurId = recruteurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        this.datePublication = datePublication;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public int getRecruteurId() {
        return recruteurId;
    }

    public void setRecruteurId(int recruteurId) {
        this.recruteurId = recruteurId;
    }
}