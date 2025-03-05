
package com.example.smartrecrute.models;

import java.sql.Timestamp;
import java.util.List;

public class OffreEmploi {
    private int id;
    private String titre;
    private String description;
    private Timestamp datePublication;
    private String entreprise;
    private int recruteurId;
    List<Candidature> candidatureList;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecruteurId() {
        return this.recruteurId;
    }

    public void setRecruteurId(int recruteurId) {
        this.recruteurId = recruteurId;
    }

    public String getEntreprise() {
        return this.entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public Timestamp getDatePublication() {
        return this.datePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        this.datePublication = datePublication;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Candidature> getCandidatureList() {
        return this.candidatureList;
    }

    public void setCandidatureList(List<Candidature> candidatureList) {
        this.candidatureList = candidatureList;
    }

    public OffreEmploi(String titre, String description, String entreprise, int recruteurId) {
        this.titre = titre;
        this.description = description;
        this.datePublication = this.datePublication;
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
