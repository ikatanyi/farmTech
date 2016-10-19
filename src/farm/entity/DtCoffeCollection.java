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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "dt_coffe_collection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtCoffeCollection.findAll", query = "SELECT d FROM DtCoffeCollection d"),
    @NamedQuery(name = "DtCoffeCollection.findById", query = "SELECT d FROM DtCoffeCollection d WHERE d.id = :id"),
    @NamedQuery(name = "DtCoffeCollection.findByDate", query = "SELECT d FROM DtCoffeCollection d WHERE d.date = :date"),
    @NamedQuery(name = "DtCoffeCollection.findByFarmerId", query = "SELECT d FROM DtCoffeCollection d WHERE d.farmerId = :farmerId"),
    @NamedQuery(name = "DtCoffeCollection.findByQuantity", query = "SELECT d FROM DtCoffeCollection d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "DtCoffeCollection.findByType", query = "SELECT d FROM DtCoffeCollection d WHERE d.type = :type"),
    @NamedQuery(name = "DtCoffeCollection.findByHarvest", query = "SELECT d FROM DtCoffeCollection d WHERE d.harvest = :harvest"),
    @NamedQuery(name = "DtCoffeCollection.findByAttendant", query = "SELECT d FROM DtCoffeCollection d WHERE d.attendant = :attendant"),
    @NamedQuery(name = "DtCoffeCollection.findByRate", query = "SELECT d FROM DtCoffeCollection d WHERE d.rate = :rate")})
public class DtCoffeCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "farmerId", nullable = false, length = 11)
    private String farmerId;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private double quantity;
    @Basic(optional = false)
    @Column(name = "type", nullable = false, length = 50)
    private String type;
    @Basic(optional = false)
    @Column(name = "harvest", nullable = false, length = 3)
    private String harvest;
    @Basic(optional = false)
    @Column(name = "attendant", nullable = false, length = 100)
    private String attendant;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;

    public DtCoffeCollection() {
    }

    public DtCoffeCollection(Integer id) {
        this.id = id;
    }

    public DtCoffeCollection(Date date, String farmerId, double quantity, String type, String harvest, String attendant, double rate) {
//        this.id = id;
        this.date = date;
        this.farmerId = farmerId;
        this.quantity = quantity;
        this.type = type;
        this.harvest = harvest;
        this.attendant = attendant;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHarvest() {
        return harvest;
    }

    public void setHarvest(String harvest) {
        this.harvest = harvest;
    }

    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtCoffeCollection)) {
            return false;
        }
        DtCoffeCollection other = (DtCoffeCollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtCoffeCollection[ id=" + id + " ]";
    }
    
}
