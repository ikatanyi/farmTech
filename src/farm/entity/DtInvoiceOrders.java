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
@Table(name = "dt_invoice_orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtInvoiceOrders.findAll", query = "SELECT d FROM DtInvoiceOrders d"),
    @NamedQuery(name = "DtInvoiceOrders.findById", query = "SELECT d FROM DtInvoiceOrders d WHERE d.id = :id"),
    @NamedQuery(name = "DtInvoiceOrders.findBySupplierId", query = "SELECT d FROM DtInvoiceOrders d WHERE d.supplierId = :supplierId"),
    @NamedQuery(name = "DtInvoiceOrders.findByOrderNo", query = "SELECT d FROM DtInvoiceOrders d WHERE d.orderNo = :orderNo"),
    @NamedQuery(name = "DtInvoiceOrders.findByItemName", query = "SELECT d FROM DtInvoiceOrders d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "DtInvoiceOrders.findByQty", query = "SELECT d FROM DtInvoiceOrders d WHERE d.qty = :qty"),
    @NamedQuery(name = "DtInvoiceOrders.findByDate", query = "SELECT d FROM DtInvoiceOrders d WHERE d.date = :date"),
    @NamedQuery(name = "DtInvoiceOrders.findByItemName_orderNo_id", query = "SELECT d FROM DtInvoiceOrders d WHERE d.id = :id OR d.itemName = :itemName AND d.orderNo = :orderNo")
})
public class DtInvoiceOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "supplier_id", nullable = false, length = 20)
    private String supplierId;
    @Basic(optional = false)
    @Column(name = "order_no", nullable = false, length = 20)
    private String orderNo;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 50)
    private String itemName;
    @Basic(optional = false)
    @Column(name = "qty", nullable = false)
    private int qty;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtInvoiceOrders() {
    }

    public DtInvoiceOrders(Integer id) {
        this.id = id;
    }

    public DtInvoiceOrders(String supplierId, String orderNo, String itemName, int qty, Date date) {
//        this.id = id;
        this.supplierId = supplierId;
        this.orderNo = orderNo;
        this.itemName = itemName;
        this.qty = qty;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        if (!(object instanceof DtInvoiceOrders)) {
            return false;
        }
        DtInvoiceOrders other = (DtInvoiceOrders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtInvoiceOrders[ id=" + id + " ]";
    }
    
}
