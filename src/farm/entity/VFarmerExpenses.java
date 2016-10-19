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
@Table(name = "v_farmer_expenses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VFarmerExpenses.findAll", query = "SELECT v FROM VFarmerExpenses v"),
    @NamedQuery(name = "VFarmerExpenses.findByFarmerId", query = "SELECT v FROM VFarmerExpenses v WHERE v.farmerId = :farmerId"),
    @NamedQuery(name = "VFarmerExpenses.findByDate", query = "SELECT v FROM VFarmerExpenses v WHERE v.date = :date"),
    @NamedQuery(name = "VFarmerExpenses.findByType", query = "SELECT v FROM VFarmerExpenses v WHERE v.type = :type"),
    @NamedQuery(name = "VFarmerExpenses.findByDescription", query = "SELECT v FROM VFarmerExpenses v WHERE v.description = :description"),
    @NamedQuery(name = "VFarmerExpenses.findByBalance", query = "SELECT v FROM VFarmerExpenses v WHERE v.balance = :balance")})
public class VFarmerExpenses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "farmerId")
    @Id
    private String farmerId;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "Type")
    private String type;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "balance")
    private Double balance;
//    @Id
//    private Long id;

    public VFarmerExpenses() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
