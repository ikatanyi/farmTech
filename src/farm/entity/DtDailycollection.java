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
@Table(name = "dt_dailycollection")
//@SecondaryTable(name="dt_mstock", pkJoinColumns={
//        @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})
//@org.hibernate.annotations.Table(
//   appliesTo="dt_mstock",
//   fetch=FetchMode.SELECT,
//   optional=true)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtDailycollection.findAll", query = "SELECT d FROM DtDailycollection d"),
    @NamedQuery(name = "DtDailycollection.findById", query = "SELECT d FROM DtDailycollection d WHERE d.id = :id"),
    @NamedQuery(name = "DtDailycollection.findByDate", query = "SELECT d FROM DtDailycollection d WHERE d.date = :date"),
    @NamedQuery(name = "DtDailycollection.findByFarmerId", query = "SELECT d FROM DtDailycollection d WHERE d.farmerId = :farmerId"),
    @NamedQuery(name = "DtDailycollection.findByQuantity", query = "SELECT d FROM DtDailycollection d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "DtDailycollection.findByWater", query = "SELECT d FROM DtDailycollection d WHERE d.water = :water"),
    @NamedQuery(name = "DtDailycollection.findBySnf", query = "SELECT d FROM DtDailycollection d WHERE d.snf = :snf"),
    @NamedQuery(name = "DtDailycollection.findByProteins", query = "SELECT d FROM DtDailycollection d WHERE d.proteins = :proteins"),
    @NamedQuery(name = "DtDailycollection.findByFat", query = "SELECT d FROM DtDailycollection d WHERE d.fat = :fat"),
    @NamedQuery(name = "DtDailycollection.findByFp", query = "SELECT d FROM DtDailycollection d WHERE d.fp = :fp"),
    @NamedQuery(name = "DtDailycollection.findBySession", query = "SELECT d FROM DtDailycollection d WHERE d.session = :session"),
    @NamedQuery(name = "DtDailycollection.findByAttendant", query = "SELECT d FROM DtDailycollection d WHERE d.attendant = :attendant"),
    @NamedQuery(name = "DtDailycollection.findByRate", query = "SELECT d FROM DtDailycollection d WHERE d.rate = :rate")})
public class DtDailycollection implements Serializable {
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
    @Column(name = "water", nullable = false)
    private double water;
    @Basic(optional = false)
    @Column(name = "snf", nullable = false)
    private double snf;
    @Basic(optional = false)
    @Column(name = "proteins", nullable = false)
    private double proteins;
    @Basic(optional = false)
    @Column(name = "fat", nullable = false)
    private double fat;
    @Basic(optional = false)
    @Column(name = "fp", nullable = false)
    private double fp;
    @Basic(optional = false)
    @Column(name = "session", nullable = false, length = 4)
    private String session;
    @Basic(optional = false)
    @Column(name = "attendant", nullable = false, length = 100)
    private String attendant;
    @Basic(optional = false)
    @Column(name = "rate", nullable = false)
    private double rate;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="in_stock_code", referencedColumnName="id")
    private DtMstock stock;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="purchase_code", referencedColumnName="id")
    private DtPurchasesAccount purchases;
    
//    @OneToOne(mappedBy="collection",cascade=CascadeType.ALL)
//    private DtPurchasesAccount p_account;

    public DtDailycollection() {
    }

    public DtDailycollection(Integer id) {
        this.id = id;
    }

    public DtDailycollection(int id, Date date, String farmerId, double quantity, double water, double snf, double proteins, double fat, double fp, String session, String attendant, double rate) {
        this.id = id;
        this.date = date;
        this.farmerId = farmerId;
        this.quantity = quantity;
        this.water = water;
        this.snf = snf;
        this.proteins = proteins;
        this.fat = fat;
        this.fp = fp;
        this.session = session;
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

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getSnf() {
        return snf;
    }

    public void setSnf(double snf) {
        this.snf = snf;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFp() {
        return fp;
    }

    public void setFp(double fp) {
        this.fp = fp;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
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

    public DtMstock getStock() {
        return stock;
    }

    public void setStock(DtMstock stock) {
        this.stock = stock;
    }

    public DtPurchasesAccount getPurchases() {
        return purchases;
    }

    public void setPurchases(DtPurchasesAccount purchases) {
        this.purchases = purchases;
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
        if (!(object instanceof DtDailycollection)) {
            return false;
        }
        DtDailycollection other = (DtDailycollection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtDailycollection[ id=" + id + " ]";
    }
    
}
