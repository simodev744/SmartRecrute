package com.example.smartrecrute.models;

import java.sql.Timestamp;
import java.util.List;

public class OffreEmploi {
    private int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecruteurId() {
        return recruteurId;
    }

    public void setRecruteurId(int recruteurId) {
        this.recruteurId = recruteurId;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Timestamp getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    private String titre;
    private String description;
    private Timestamp datePublication;
    private String entreprise;
    private int recruteurId;

    List<Candidature> candidatureList;

    public List<Candidature> getCandidatureList() {
        return candidatureList;
    }

    public void setCandidatureList(List<Candidature> candidatureList) {
        this.candidatureList = candidatureList;
    }

    public OffreEmploi(String titre, String description, String entreprise, int recruteurId) {
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.entreprise = entreprise;
        this.recruteurId = recruteurId;
    }

    public OffreEmploi(int id, String titre, String description, Timestamp datePublication, String entreprise, int recruteurId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.datePublication = datePublication;
        this.entreprise = entreprise;
        this.recruteurId = recruteurId;
    }

    public OffreEmploi(int id, String titre, String description, String entreprise, int recruteurId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.entreprise = entreprise;
        this.recruteurId = recruteurId;
    }
}