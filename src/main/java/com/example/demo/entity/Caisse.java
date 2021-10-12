/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Casimir
 */
@Entity
@Table(name = "caisse")
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String mouvement;
    
    private String cc;
    
    private int piece;
    
    private int encaissement;
    
    private int decaissement;
    
    private int solde;
    
    private boolean provisoir;
    
    private LocalDate date;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_modification;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date_creation;

    public Caisse() {
    }

    public Caisse(String mouvement, String cc, int piece, int encaissement, int decaissement, int solde, boolean provisoir, LocalDate date, Date date_creation, Date date_modification) {
        this.mouvement = mouvement;
        this.cc = cc;
        this.piece = piece;
        this.encaissement = encaissement;
        this.decaissement = decaissement;
        this.solde = solde;
        this.provisoir = provisoir;
        this.date = date;
        this.date_creation = date_creation;
        this.date_modification = date_modification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMouvement() {
        return mouvement;
    }

    public void setMouvement(String mouvement) {
        this.mouvement = mouvement;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public int getEncaissement() {
        return encaissement;
    }

    public void setEncaissement(int encaissement) {
        this.encaissement = encaissement;
    }

    public int getDecaissement() {
        return decaissement;
    }

    public void setDecaissement(int decaissement) {
        this.decaissement = decaissement;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isProvisoir() {
        return provisoir;
    }

    public void setProvisoir(boolean provisoir) {
        this.provisoir = provisoir;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Date getDate_modification() {
        return date_modification;
    }

    public void setDate_modification(Date date_modification) {
        this.date_modification = date_modification;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }
    
    
    
}
