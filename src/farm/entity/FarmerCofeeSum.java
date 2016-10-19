/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "farmer_cofee_sum")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FarmerCofeeSum.findAll", query = "SELECT f FROM FarmerCofeeSum f"),
    @NamedQuery(name = "FarmerCofeeSum.findById", query = "SELECT f FROM FarmerCofeeSum f WHERE f.id = :id"),
    @NamedQuery(name = "FarmerCofeeSum.findByFarmerid", query = "SELECT f FROM FarmerCofeeSum f WHERE f.farmerid = :farmerid"),
    @NamedQuery(name = "FarmerCofeeSum.findByType", query = "SELECT f FROM FarmerCofeeSum f WHERE f.type = :type"),
    @NamedQuery(name = "FarmerCofeeSum.findByQuantity", query = "SELECT f FROM FarmerCofeeSum f WHERE f.quantity = :quantity"),
    @NamedQuery(name = "FarmerCofeeSum.findByHarvest", query = "SELECT f FROM FarmerCofeeSum f WHERE f.harvest = :harvest"),
    @NamedQuery(name = "FarmerCofeeSum.findByRate", query = "SELECT f FROM FarmerCofeeSum f WHERE f.rate = :rate"),
    @NamedQuery(name = "FarmerCofeeSum.findByDate", query = "SELECT f FROM FarmerCofeeSum f WHERE f.date = :date")})
public class FarmerCofeeSum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "farmerid", nullable = false, length = 11)
    private String farmerid;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity", precision = 19, scale = 2)
    private Double quantity;
    @Basic(optional = false)
    @Column(name = "harvest", nullable = false, length = 3)
    private String harvest;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public FarmerCofeeSum() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(String farmerid) {
        this.farmerid = farmerid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getHarvest() {
        return harvest;
    }

    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
