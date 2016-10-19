/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_sales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtSales.findAll", query = "SELECT d FROM DtSales d"),
    @NamedQuery(name = "DtSales.findById", query = "SELECT d FROM DtSales d WHERE d.id = :id"),
    @NamedQuery(name = "DtSales.findByCustomerId", query = "SELECT d FROM DtSales d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtSales.findByDeptId", query = "SELECT d FROM DtSales d WHERE d.deptId = :deptId"),
    @NamedQuery(name = "DtSales.findByQty", query = "SELECT d FROM DtSales d WHERE d.qty = :qty"),
    @NamedQuery(name = "DtSales.findByRate", query = "SELECT d FROM DtSales d WHERE d.rate = :rate"),
    @NamedQuery(name = "DtSales.findByDebit", query = "SELECT d FROM DtSales d WHERE d.debit = :debit"),
    @NamedQuery(name = "DtSales.findByCredit", query = "SELECT d FROM DtSales d WHERE d.credit = :credit"),
    @NamedQuery(name = "DtSales.findByDate", query = "SELECT d FROM DtSales d WHERE d.date = :date"),
    @NamedQuery(name = "DtSales.findByUserid", query = "SELECT d FROM DtSales d WHERE d.userid = :userid")})
public class DtSales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "customerId", nullable = false)
    private int customerId;
    @Basic(optional = false)
    @Column(name = "dept_id", nullable = false, length = 50)
    private String deptId;
    @Basic(optional = false)
    @Column(name = "qty", nullable = false)
    private double qty;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    @Basic(optional = false)
    @Column(name = "debit", nullable = false)
    private double debit;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "userid", nullable = false, length = 50)
    private String userid;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="out_stock_code", referencedColumnName="id")
    private DtMstock sale_stock;

    public DtSales() {
    }

    public DtSales(Integer id) {
        this.id = id;
    }

    public DtSales(int customerId, String deptId, double qty, double rate, double debit, double credit, Date date, String userid) {
//        this.id = id;
        this.customerId = customerId;
        this.deptId = deptId;
        this.qty = qty;
        this.rate = rate;
        this.debit = debit;
        this.credit = credit;
        this.date = date;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public DtMstock getSale_stock() {
        return sale_stock;
    }

    public void setSale_stock(DtMstock sale_stock) {
        this.sale_stock = sale_stock;
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
        if (!(object instanceof DtSales)) {
            return false;
        }
        DtSales other = (DtSales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtSales[ id=" + id + " ]";
    }
    
}
