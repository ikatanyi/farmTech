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
@Table(name = "v_coffee_farmer_payroll")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VCoffeeFarmerPayroll.findAll", query = "SELECT v FROM VCoffeeFarmerPayroll v"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findById", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.id = :id"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByFarmerId", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.farmerId = :farmerId"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByDate", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.date = :date"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByQuantity", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.quantity = :quantity"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findBySession", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.session = :session"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByRate", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.rate = :rate"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByGrossPay", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.grossPay = :grossPay"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByCredit", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.credit = :credit"),
    @NamedQuery(name = "VCoffeeFarmerPayroll.findByNetTotal", query = "SELECT v FROM VCoffeeFarmerPayroll v WHERE v.netTotal = :netTotal")})
public class VCoffeeFarmerPayroll implements Serializable {
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
    @Column(name = "session", nullable = false, length = 3)
    private String session;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    @Column(name = "gross_pay", precision = 19, scale = 2)
    private Double grossPay;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;
    @Column(name = "net_total", precision = 19, scale = 2)
    private Double netTotal;

    public VCoffeeFarmerPayroll() {
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

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(Double netTotal) {
        this.netTotal = netTotal;
    }
    
}
