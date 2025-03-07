package com.example.smartrecrute.models;

import java.sql.Timestamp;
import java.util.List;

public class Candidature  {
    private int id;
    private int candidatId;
    private int offreId;
    private String statut;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
    private Utilisateur utilisateur;
    List<OffreEmploi> offreEmplois;


    public List<OffreEmploi> getOffreEmplois() {
        return offreEmplois;
    }

    public void setOffreEmplois(List<OffreEmploi> offreEmplois) {
        this.offreEmplois = offreEmplois;
    }

    private Timestamp dateCandidature;


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Candidature(int id, int candidatId, int offreId, String statut, Timestamp dateCandidature) {
        this.id = id;
        this.candidatId = candidatId;
        this.offreId = offreId;
        this.statut = statut;
        this.dateCandidature = dateCandidature;
    }

    public Candidature(int id, int candidatId, int offreId, String statut, Utilisateur utilisateur, Timestamp dateCandidature) {
        this.id = id;
        this.candidatId = candidatId;
        this.offreId = offreId;
        this.statut = statut;
        this.utilisateur = utilisateur;
        this.dateCandidature = dateCandidature;
    }

    public Candidature(int candidatId, int offreId, String statut) {
        this.candidatId = candidatId;
        this.offreId = offreId;
        this.statut = statut;
    }

    public Candidature(int candidatId, int offreId, String statut, Timestamp dateCandidature) {
        this.candidatId = candidatId;
        this.offreId = offreId;
        this.statut = statut;
        this.dateCandidature = dateCandidature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(int candidatId) {
        this.candidatId = candidatId;
    }

    public int getOffreId() {
        return offreId;
    }

    public void setOffreId(int offreId) {
        this.offreId = offreId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Timestamp getDateCandidature() {
        return dateCandidature;
    }

    public void setDateCandidature(Timestamp dateCandidature) {
        this.dateCandidature = dateCandidature;
    }
}