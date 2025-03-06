package com.example.smartrecrute.models;

import java.sql.Timestamp;

public class Candidature extends Utilisateur {
    private int id;
    private int candidatId;
    private String statut;
    private Timestamp dateCandidature;

    public Candidature(int id, String nomUtilisateur, String motDePasse, String email, String role, Timestamp dateCreation) {
        super(id, nomUtilisateur, motDePasse, email, role, dateCreation);
    }

    // Getters et Setters
}