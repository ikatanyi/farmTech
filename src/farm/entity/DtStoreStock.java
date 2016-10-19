/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package farm.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_store_stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtStoreStock.findAll", query = "SELECT d FROM DtStoreStock d"),
    @NamedQuery(name = "DtStoreStock.findByEntryCode", query = "SELECT d FROM DtStoreStock d WHERE d.entryCode = :entryCode"),
    @NamedQuery(name = "DtStoreStock.findByProductName", query = "SELECT d FROM DtStoreStock d WHERE d.productName = :productName"),
    @NamedQuery(name = "DtStoreStock.findByBatchNo", query = "SELECT d FROM DtStoreStock d WHERE d.batchNo = :batchNo"),
    @NamedQuery(name = "DtStoreStock.findByInStock", query = "SELECT d FROM DtStoreStock d WHERE d.inStock = :inStock"),
    @NamedQuery(name = "DtStoreStock.findByOutStock", query = "SELECT d FROM DtStoreStock d WHERE d.outStock = :outStock"),
    @NamedQuery(name = "DtStoreStock.findByTransDate", query = "SELECT d FROM DtStoreStock d WHERE d.transDate = :transDate")})
public class DtStoreStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Entry_Code", nullable = false)
    private Integer entryCode;
    @Basic(optional = false)
    @Column(name = "ProductName", nullable = false, length = 100)
    private String productName;
    @Basic(optional = false)
    @Column(name = "Batch_No", nullable = false, length = 20)
    private String batchNo;
    @Basic(optional = false)
    @Column(name = "In_Stock", nullable = false)
    private int inStock;
    @Basic(optional = false)
    @Column(name = "Out_Stock", nullable = false)
    private int outStock;
    @Basic(optional = false)
    @Column(name = "TransDate", nullable = false, length = 20)
    private String transDate;

    public DtStoreStock() {
    }

    public DtStoreStock(Integer entryCode) {
        this.entryCode = entryCode;
    }

    public DtStoreStock(Integer entryCode, String productName, String batchNo, int inStock, int outStock, String transDate) {
        this.entryCode = entryCode;
        this.productName = productName;
        this.batchNo = batchNo;
        this.inStock = inStock;
        this.outStock = outStock;
        this.transDate = transDate;
    }

    public Integer getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(Integer entryCode) {
        this.entryCode = entryCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getOutStock() {
        return outStock;
    }

    public void setOutStock(int outStock) {
        this.outStock = outStock;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (entryCode != null ? entryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtStoreStock)) {
            return false;
        }
        DtStoreStock other = (DtStoreStock) object;
        if ((this.entryCode == null && other.entryCode != null) || (this.entryCode != null && !this.entryCode.equals(other.entryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtStoreStock[ entryCode=" + entryCode + " ]";
    }
    
}
