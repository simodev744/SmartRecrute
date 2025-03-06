package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class Candidat {
    private int id;
    private String nom;
    private String email;
    private int utilisateurId;
    private String cv;
    private Timestamp dateCreation;

    public Candidat() {
    }

    public Candidat(int id, String nom, String email, int utilisateurId, String cv, Timestamp dateCreation) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.utilisateurId = utilisateurId;
        this.cv = cv;
        this.dateCreation = dateCreation;
    }

    public Candidat(String nom, String email, int utilisateurId, String cv) {
        this.nom = nom;
        this.email = email;
        this.utilisateurId = utilisateurId;
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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