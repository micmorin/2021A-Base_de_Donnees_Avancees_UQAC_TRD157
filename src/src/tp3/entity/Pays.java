package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Pays generated by hbm2java
 */
public class Pays  implements java.io.Serializable {


     private BigDecimal idpays;
     private String nompays;
     private Set films = new HashSet(0);

    public Pays() {
    }

	
    public Pays(BigDecimal idpays, String nompays) {
        this.idpays = idpays;
        this.nompays = nompays;
    }
    public Pays(BigDecimal idpays, String nompays, Set films) {
       this.idpays = idpays;
       this.nompays = nompays;
       this.films = films;
    }
   
    public BigDecimal getIdpays() {
        return this.idpays;
    }
    
    public void setIdpays(BigDecimal idpays) {
        this.idpays = idpays;
    }
    public String getNompays() {
        return this.nompays;
    }
    
    public void setNompays(String nompays) {
        this.nompays = nompays;
    }
    public Set getFilms() {
        return this.films;
    }
    
    public void setFilms(Set films) {
        this.films = films;
    }




}


