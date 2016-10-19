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
@Table(name = "v_farmer_payroll")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VFarmerPayroll.findAll", query = "SELECT v FROM VFarmerPayroll v"),
    @NamedQuery(name = "VFarmerPayroll.findById", query = "SELECT v FROM VFarmerPayroll v WHERE v.id = :id"),
    @NamedQuery(name = "VFarmerPayroll.findByFarmerId", query = "SELECT v FROM VFarmerPayroll v WHERE v.farmerId = :farmerId"),
    @NamedQuery(name = "VFarmerPayroll.findByDate", query = "SELECT v FROM VFarmerPayroll v WHERE v.date = :date"),
    @NamedQuery(name = "VFarmerPayroll.findByQuantity", query = "SELECT v FROM VFarmerPayroll v WHERE v.quantity = :quantity"),
    @NamedQuery(name = "VFarmerPayroll.findByRate", query = "SELECT v FROM VFarmerPayroll v WHERE v.rate = :rate"),
    @NamedQuery(name = "VFarmerPayroll.findByCredit", query = "SELECT v FROM VFarmerPayroll v WHERE v.credit = :credit")})
public class VFarmerPayroll implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    @Id
    private int id;
    @Basic(optional = false)
    @Column(name = "farmerId", nullable = false, length = 11)
    private String farmerId;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity", precision = 19, scale = 2)
    private Double quantity;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    @Column(name = "credit", precision = 22, scale=2)
    private Double credit;

    public VFarmerPayroll() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
    
}
