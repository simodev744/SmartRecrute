package com.example.smartrecrute.models;
import java.sql.Timestamp;

public class Candidat {
    private int id;
    private int utilisateurId;
    private String cv;
    private Timestamp dateCreation;

    public Candidat(int id, int utilisateurId, String cv, Timestamp dateCreation) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.cv = cv;
        this.dateCreation = dateCreation;
    }

    public Candidat() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
}
