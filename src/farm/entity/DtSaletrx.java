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
@Table(name = "dt_saletrx")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtSaletrx.findAll", query = "SELECT d FROM DtSaletrx d"),
    @NamedQuery(name = "DtSaletrx.findById", query = "SELECT d FROM DtSaletrx d WHERE d.id = :id"),
    @NamedQuery(name = "DtSaletrx.findByFarmNo", query = "SELECT d FROM DtSaletrx d WHERE d.farmNo = :farmNo"),
    @NamedQuery(name = "DtSaletrx.findByDno", query = "SELECT d FROM DtSaletrx d WHERE d.dno = :dno"),
    @NamedQuery(name = "DtSaletrx.findByBatchno", query = "SELECT d FROM DtSaletrx d WHERE d.batchno = :batchno"),
    @NamedQuery(name = "DtSaletrx.findByProductName", query = "SELECT d FROM DtSaletrx d WHERE d.productName = :productName"),
    @NamedQuery(name = "DtSaletrx.findByUnits", query = "SELECT d FROM DtSaletrx d WHERE d.units = :units"),
    @NamedQuery(name = "DtSaletrx.findByAttendant", query = "SELECT d FROM DtSaletrx d WHERE d.attendant = :attendant"),
    @NamedQuery(name = "DtSaletrx.findByDate", query = "SELECT d FROM DtSaletrx d WHERE d.date = :date")})
public class DtSaletrx implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "farm_no", nullable = false, length = 50)
    private String farmNo;
    @Basic(optional = false)
    @Column(name = "dno", nullable = false, length = 100)
    private String dno;
    @Basic(optional = false)
    @Column(name = "batchno", nullable = false, length = 50)
    private String batchno;
    @Basic(optional = false)
    @Column(name = "ProductName", nullable = false, length = 50)
    private String productName;
    @Basic(optional = false)
    @Column(name = "Units", nullable = false)
    private int units;
    @Basic(optional = false)
    @Column(name = "Attendant", nullable = false, length = 20)
    private String attendant;
    @Basic(optional = false)
    @Column(name = "Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtSaletrx() {
    }

    public DtSaletrx(Integer id) {
        this.id = id;
    }

    public DtSaletrx(String farmNo, String dno, String batchno, String productName, int units, String attendant, Date date) {
//        this.id = id;
        this.farmNo = farmNo;
        this.dno = dno;
        this.batchno = batchno;
        this.productName = productName;
        this.units = units;
        this.attendant = attendant;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFarmNo() {
        return farmNo;
    }

    public void setFarmNo(String farmNo) {
        this.farmNo = farmNo;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof DtSaletrx)) {
            return false;
        }
        DtSaletrx other = (DtSaletrx) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtSaletrx[ id=" + id + " ]";
    }
    
}
