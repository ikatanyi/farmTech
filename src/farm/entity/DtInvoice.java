/*
 * Copyright (c) 2015, Kent
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
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
@Table(name = "dt_invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtInvoice.findAll", query = "SELECT d FROM DtInvoice d"),
    @NamedQuery(name = "DtInvoice.findById", query = "SELECT d FROM DtInvoice d WHERE d.id = :id"),
    @NamedQuery(name = "DtInvoice.findByInvoiceNo", query = "SELECT d FROM DtInvoice d WHERE d.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "DtInvoice.findByCustomerid", query = "SELECT d FROM DtInvoice d WHERE d.customerid = :customerid"),
    @NamedQuery(name = "DtInvoice.findByOrderNo", query = "SELECT d FROM DtInvoice d WHERE d.orderNo = :orderNo"),
    @NamedQuery(name = "DtInvoice.findByOrderDate", query = "SELECT d FROM DtInvoice d WHERE d.orderDate = :orderDate"),
    @NamedQuery(name = "DtInvoice.findByServiceDate", query = "SELECT d FROM DtInvoice d WHERE d.serviceDate = :serviceDate"),
    @NamedQuery(name = "DtInvoice.findByVat", query = "SELECT d FROM DtInvoice d WHERE d.vat = :vat"),
    @NamedQuery(name = "DtInvoice.findByRemarks", query = "SELECT d FROM DtInvoice d WHERE d.remarks = :remarks"),
    @NamedQuery(name = "DtInvoice.findByTotal", query = "SELECT d FROM DtInvoice d WHERE d.total = :total"),
    @NamedQuery(name = "DtInvoice.findByDiscount", query = "SELECT d FROM DtInvoice d WHERE d.discount = :discount"),
    @NamedQuery(name = "DtInvoice.findByShippingCost", query = "SELECT d FROM DtInvoice d WHERE d.shippingCost = :shippingCost"),
    @NamedQuery(name = "DtInvoice.findByPaidStatus", query = "SELECT d FROM DtInvoice d WHERE d.paidStatus = :paidStatus"),
    @NamedQuery(name = "DtInvoice.findByDueDate", query = "SELECT d FROM DtInvoice d WHERE d.dueDate = :dueDate"),
    @NamedQuery(name = "DtInvoice.findByDate", query = "SELECT d FROM DtInvoice d WHERE d.date = :date")})
public class DtInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "invoice_no", nullable = false, length = 15)
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "customerid", nullable = false, length = 20)
    private String customerid;
    @Basic(optional = false)
    @Column(name = "order_no", nullable = false, length = 20)
    private String orderNo;
    @Basic(optional = false)
    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "service_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date serviceDate;
    @Basic(optional = false)
    @Column(name = "vat", nullable = false)
    private double vat;
    @Basic(optional = false)
    @Column(name = "remarks", nullable = false, length = 100)
    private String remarks;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    @Basic(optional = false)
    @Column(name = "discount", nullable = false)
    private double discount;
    @Basic(optional = false)
    @Column(name = "shipping_cost", nullable = false)
    private double shippingCost;
    @Basic(optional = false)
    @Column(name = "paid_status", nullable = false, length = 10)
    private String paidStatus;
    @Basic(optional = false)
    @Column(name = "due_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtInvoice() {
    }

    public DtInvoice(Integer id) {
        this.id = id;
    }

    public DtInvoice(String invoiceNo, String customerid, String orderNo, Date orderDate, Date serviceDate, double vat, String remarks, double total, double discount, double shippingCost, String paidStatus, Date dueDate, Date date) {
//        this.id = id;
        this.invoiceNo = invoiceNo;
        this.customerid = customerid;
        this.orderNo = orderNo;
        this.orderDate = orderDate;
        this.serviceDate = serviceDate;
        this.vat = vat;
        this.remarks = remarks;
        this.total = total;
        this.discount = discount;
        this.shippingCost = shippingCost;
        this.paidStatus = paidStatus;
        this.dueDate = dueDate;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
        if (!(object instanceof DtInvoice)) {
            return false;
        }
        DtInvoice other = (DtInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtInvoice[ id=" + id + " ]";
    }
    
}
