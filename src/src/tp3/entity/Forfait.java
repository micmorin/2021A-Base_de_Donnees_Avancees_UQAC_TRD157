package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Forfait generated by hbm2java
 */
public class Forfait  implements java.io.Serializable {


     private BigDecimal idforfait;
     private String nom;
     private BigDecimal cout;
     private BigDecimal maxlocation;
     private BigDecimal maxduree;
     private Set clients = new HashSet(0);

    public Forfait() {
    }

	
    public Forfait(BigDecimal idforfait, String nom, BigDecimal cout, BigDecimal maxlocation, BigDecimal maxduree) {
        this.idforfait = idforfait;
        this.nom = nom;
        this.cout = cout;
        this.maxlocation = maxlocation;
        this.maxduree = maxduree;
    }
    public Forfait(BigDecimal idforfait, String nom, BigDecimal cout, BigDecimal maxlocation, BigDecimal maxduree, Set clients) {
       this.idforfait = idforfait;
       this.nom = nom;
       this.cout = cout;
       this.maxlocation = maxlocation;
       this.maxduree = maxduree;
       this.clients = clients;
    }
   
    public BigDecimal getIdforfait() {
        return this.idforfait;
    }
    
    public void setIdforfait(BigDecimal idforfait) {
        this.idforfait = idforfait;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public BigDecimal getCout() {
        return this.cout;
    }
    
    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }
    public BigDecimal getMaxlocation() {
        return this.maxlocation;
    }
    
    public void setMaxlocation(BigDecimal maxlocation) {
        this.maxlocation = maxlocation;
    }
    public BigDecimal getMaxduree() {
        return this.maxduree;
    }
    
    public void setMaxduree(BigDecimal maxduree) {
        this.maxduree = maxduree;
    }
    public Set getClients() {
        return this.clients;
    }
    
    public void setClients(Set clients) {
        this.clients = clients;
    }




}


