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
@Table(name = "dt_stock_movement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtStockMovement.findAll", query = "SELECT d FROM DtStockMovement d"),
    @NamedQuery(name = "DtStockMovement.findById", query = "SELECT d FROM DtStockMovement d WHERE d.id = :id"),
    @NamedQuery(name = "DtStockMovement.findByProductCode", query = "SELECT d FROM DtStockMovement d WHERE d.productCode = :productCode"),
    @NamedQuery(name = "DtStockMovement.findByInStock", query = "SELECT d FROM DtStockMovement d WHERE d.inStock = :inStock"),
    @NamedQuery(name = "DtStockMovement.findByOutStock", query = "SELECT d FROM DtStockMovement d WHERE d.outStock = :outStock"),
    @NamedQuery(name = "DtStockMovement.findByDate", query = "SELECT d FROM DtStockMovement d WHERE d.date = :date")})
public class DtStockMovement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "product_code", nullable = false, length = 50)
    private String productCode;
    @Basic(optional = false)
    @Column(name = "in_stock", nullable = false)
    private int inStock;
    @Basic(optional = false)
    @Column(name = "out_stock", nullable = false)
    private int outStock;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @OneToOne(mappedBy="stock")
    private DtInventory inventory;
    
    @OneToOne(mappedBy="sale_stock")
    private DtSalestrx sale;

    public DtStockMovement() {
    }

    public DtStockMovement(Integer id) {
        this.id = id;
    }

    public DtStockMovement(String productCode, int inStock, int outStock, Date date) {
//        this.id = id;
        this.productCode = productCode;
        this.inStock = inStock;
        this.outStock = outStock;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DtInventory getInventory() {
        return inventory;
    }

    public void setInventory(DtInventory inventory) {
        this.inventory = inventory;
    }

    public DtSalestrx getSale() {
        return sale;
    }

    public void setSale(DtSalestrx sale) {
        this.sale = sale;
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
        if (!(object instanceof DtStockMovement)) {
            return false;
        }
        DtStockMovement other = (DtStockMovement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DtStockMovement[ id=" + id + " ]";
    }
    
}
