package tp3.entity;
// Generated 2021-11-22 12:55:05 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cartedecredit generated by hbm2java
 */
public class Cartedecredit  implements java.io.Serializable {


     private BigDecimal idcartedecredit;
     private Typecartedecredit typecartedecredit;
     private String numero;
     private Date dateexpiration;
     private String cvc;
     private Set clients = new HashSet(0);

    public Cartedecredit() {
    }

	
    public Cartedecredit(BigDecimal idcartedecredit, Typecartedecredit typecartedecredit, String numero, Date dateexpiration, String cvc) {
        this.idcartedecredit = idcartedecredit;
        this.typecartedecredit = typecartedecredit;
        this.numero = numero;
        this.dateexpiration = dateexpiration;
        this.cvc = cvc;
    }
    public Cartedecredit(BigDecimal idcartedecredit, Typecartedecredit typecartedecredit, String numero, Date dateexpiration, String cvc, Set clients) {
       this.idcartedecredit = idcartedecredit;
       this.typecartedecredit = typecartedecredit;
       this.numero = numero;
       this.dateexpiration = dateexpiration;
       this.cvc = cvc;
       this.clients = clients;
    }
   
    public BigDecimal getIdcartedecredit() {
        return this.idcartedecredit;
    }
    
    public void setIdcartedecredit(BigDecimal idcartedecredit) {
        this.idcartedecredit = idcartedecredit;
    }
    public Typecartedecredit getTypecartedecredit() {
        return this.typecartedecredit;
    }
    
    public void setTypecartedecredit(Typecartedecredit typecartedecredit) {
        this.typecartedecredit = typecartedecredit;
    }
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public Date getDateexpiration() {
        return this.dateexpiration;
    }
    
    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }
    public String getCvc() {
        return this.cvc;
    }
    
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public Set getClients() {
        return this.clients;
    }
    
    public void setClients(Set clients) {
        this.clients = clients;
    }




}


