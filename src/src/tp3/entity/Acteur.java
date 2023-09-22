package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Acteur generated by hbm2java
 */
public class Acteur  implements java.io.Serializable {


     private BigDecimal idacteur;
     private String prenom;
     private String nom;
     private Date datedenaissance;
     private String lieudenaissance;
     private Clob biographie;
     private Set personnages = new HashSet(0);

    public Acteur() {
    }

	
    public Acteur(BigDecimal idacteur, String prenom, String nom) {
        this.idacteur = idacteur;
        this.prenom = prenom;
        this.nom = nom;
    }
    public Acteur(BigDecimal idacteur, String prenom, String nom, Date datedenaissance, String lieudenaissance, Clob biographie, Set personnages) {
       this.idacteur = idacteur;
       this.prenom = prenom;
       this.nom = nom;
       this.datedenaissance = datedenaissance;
       this.lieudenaissance = lieudenaissance;
       this.biographie = biographie;
       this.personnages = personnages;
    }
   
    public BigDecimal getIdacteur() {
        return this.idacteur;
    }
    
    public void setIdacteur(BigDecimal idacteur) {
        this.idacteur = idacteur;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Date getDatedenaissance() {
        return this.datedenaissance;
    }
    
    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }
    public String getLieudenaissance() {
        return this.lieudenaissance;
    }
    
    public void setLieudenaissance(String lieudenaissance) {
        this.lieudenaissance = lieudenaissance;
    }
    public Clob getBiographie() {
        return this.biographie;
    }
    
    public void setBiographie(Clob biographie) {
        this.biographie = biographie;
    }
    public Set getPersonnages() {
        return this.personnages;
    }
    
    public void setPersonnages(Set personnages) {
        this.personnages = personnages;
    }




}

