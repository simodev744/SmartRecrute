//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.smartrecrute.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Utilisateur implements Serializable {
    private int id;
    private String nomUtilisateur;
    private String motDePasse;
    private String email;
    private String cv;
    private Candidature candidature;
    private String role;
    private Timestamp dateCreation;

    public Candidature getCandidature() {
        return this.candidature;
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public Timestamp getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCv() {
        return this.cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public Utilisateur(int id, String email, String motDePasse, String nomUtilisateur, String role) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nomUtilisateur = nomUtilisateur;
        this.role = role;
    }

    public Utilisateur(String email, String nomUtilisateur, String role, String cv) {
        this.nomUtilisateur = nomUtilisateur;
        this.email = email;
        this.role = role;
        this.cv = cv;
    }

    public Utilisateur(int id, String nomUtilisateur, String motDePasse, String email, String cv, String role, Timestamp dateCreation) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
        this.cv = cv;
        this.role = role;
        this.dateCreation = dateCreation;
    }

    public Utilisateur(int id, String nomUtilisateur, String motDePasse, String email, String role, Timestamp dateCreation) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return this.nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return this.motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
