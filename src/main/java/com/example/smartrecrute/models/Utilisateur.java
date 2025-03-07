package com.example.smartrecrute.models;

import java.io.Serializable;
import java.sql.Timestamp;

public class Utilisateur implements Serializable {
    private int id;
    private String nomUtilisateur;
    private String motDePasse;
    private String email;
    private String role;
    private Timestamp dateCreation;

    public Utilisateur(int id, String nomUtilisateur, String motDePasse, String email, String role, Timestamp dateCreation) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
        this.dateCreation = dateCreation;
    }


    public Utilisateur(String nomUtilisateur, String motDePasse, String email, String role) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}