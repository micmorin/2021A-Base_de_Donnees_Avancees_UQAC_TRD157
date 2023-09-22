package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Personnage generated by hbm2java
 */
public class Personnage  implements java.io.Serializable {


     private BigDecimal idpersonnage;
     private Acteur acteur;
     private String prenom;
     private String nom;
     private Date datedenaissance;
     private Set films = new HashSet(0);

    public Personnage() {
    }

	
    public Personnage(BigDecimal idpersonnage, Acteur acteur, String prenom) {
        this.idpersonnage = idpersonnage;
        this.acteur = acteur;
        this.prenom = prenom;
    }
    public Personnage(BigDecimal idpersonnage, Acteur acteur, String prenom, String nom, Date datedenaissance, Set films) {
       this.idpersonnage = idpersonnage;
       this.acteur = acteur;
       this.prenom = prenom;
       this.nom = nom;
       this.datedenaissance = datedenaissance;
       this.films = films;
    }
   
    public BigDecimal getIdpersonnage() {
        return this.idpersonnage;
    }
    
    public void setIdpersonnage(BigDecimal idpersonnage) {
        this.idpersonnage = idpersonnage;
    }
    public Acteur getActeur() {
        return this.acteur;
    }
    
    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
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
    public Set getFilms() {
        return this.films;
    }
    
    public void setFilms(Set films) {
        this.films = films;
    }




}

