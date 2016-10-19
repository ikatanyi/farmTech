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
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "dt_delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtDelivery.findAll", query = "SELECT d FROM DtDelivery d"),
    @NamedQuery(name = "DtDelivery.findById", query = "SELECT d FROM DtDelivery d WHERE d.id = :id"),
    @NamedQuery(name = "DtDelivery.findByDeliveryNo", query = "SELECT d FROM DtDelivery d WHERE d.deliveryNo = :deliveryNo"),
    @NamedQuery(name = "DtDelivery.findByInvoiceNo", query = "SELECT d FROM DtDelivery d WHERE d.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "DtDelivery.findByCustomerId", query = "SELECT d FROM DtDelivery d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtDelivery.findByServiceDate", query = "SELECT d FROM DtDelivery d WHERE d.serviceDate = :serviceDate"),
    @NamedQuery(name = "DtDelivery.findByOrderDate", query = "SELECT d FROM DtDelivery d WHERE d.orderDate = :orderDate"),
    @NamedQuery(name = "DtDelivery.findByDate", query = "SELECT d FROM DtDelivery d WHERE d.date = :date"),
    @NamedQuery(name = "DtDelivery.findByRemarks", query = "SELECT d FROM DtDelivery d WHERE d.remarks = :remarks")})
public class DtDelivery implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "delivery_no", nullable = false, length = 20)
    private String deliveryNo;
    @Basic(optional = false)
    @Column(name = "invoice_no", nullable = false, length = 20)
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "customer_id", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @Column(name = "service_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date serviceDate;
    @Basic(optional = false)
    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "remarks", nullable = false, length = 200)
    private String remarks;

    public DtDelivery() {
    }

    public DtDelivery(Integer id) {
        this.id = id;
    }

    public DtDelivery(String deliveryNo, String invoiceNo, String customerId, Date serviceDate, Date orderDate, Date date, String remarks) {
//        this.id = id;
        this.deliveryNo = deliveryNo;
        this.invoiceNo = invoiceNo;
        this.customerId = customerId;
        this.serviceDate = serviceDate;
        this.orderDate = orderDate;
        this.date = date;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeliveryNo() {
        return deliveryNo;
    }

    public void setDeliveryNo(String deliveryNo) {
        this.deliveryNo = deliveryNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        if (!(object instanceof DtDelivery)) {
            return false;
        }
        DtDelivery other = (DtDelivery) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtDelivery[ id=" + id + " ]";
    }
    
}
