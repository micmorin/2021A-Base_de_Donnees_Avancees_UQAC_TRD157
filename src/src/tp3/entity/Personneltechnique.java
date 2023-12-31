package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Personneltechnique generated by hbm2java
 */
public class Personneltechnique  implements java.io.Serializable {


     private BigDecimal idpersonnel;
     private Roletechnique roletechnique;
     private String prenom;
     private String nom;
     private Date datedenaissance;
     private Set films = new HashSet(0);

    public Personneltechnique() {
    }

	
    public Personneltechnique(BigDecimal idpersonnel, Roletechnique roletechnique, String prenom) {
        this.idpersonnel = idpersonnel;
        this.roletechnique = roletechnique;
        this.prenom = prenom;
    }
    public Personneltechnique(BigDecimal idpersonnel, Roletechnique roletechnique, String prenom, String nom, Date datedenaissance, Set films) {
       this.idpersonnel = idpersonnel;
       this.roletechnique = roletechnique;
       this.prenom = prenom;
       this.nom = nom;
       this.datedenaissance = datedenaissance;
       this.films = films;
    }
   
    public BigDecimal getIdpersonnel() {
        return this.idpersonnel;
    }
    
    public void setIdpersonnel(BigDecimal idpersonnel) {
        this.idpersonnel = idpersonnel;
    }
    public Roletechnique getRoletechnique() {
        return this.roletechnique;
    }
    
    public void setRoletechnique(Roletechnique roletechnique) {
        this.roletechnique = roletechnique;
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


