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
@Table(name = "dt_ordersale")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtOrdersale.findAll", query = "SELECT d FROM DtOrdersale d"),
    @NamedQuery(name = "DtOrdersale.findById", query = "SELECT d FROM DtOrdersale d WHERE d.id = :id"),
    @NamedQuery(name = "DtOrdersale.findBySaleNo", query = "SELECT d FROM DtOrdersale d WHERE d.saleNo = :saleNo"),
    @NamedQuery(name = "DtOrdersale.findByCustomerId", query = "SELECT d FROM DtOrdersale d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtOrdersale.findByCustomerAcc", query = "SELECT d FROM DtOrdersale d WHERE d.customerAcc = :customerAcc"),
    @NamedQuery(name = "DtOrdersale.findByOrderno", query = "SELECT d FROM DtOrdersale d WHERE d.orderno = :orderno"),
    @NamedQuery(name = "DtOrdersale.findByOrderDue", query = "SELECT d FROM DtOrdersale d WHERE d.orderDue = :orderDue"),
    @NamedQuery(name = "DtOrdersale.findByProcessNo", query = "SELECT d FROM DtOrdersale d WHERE d.processNo = :processNo"),
    @NamedQuery(name = "DtOrdersale.findByShipBy", query = "SELECT d FROM DtOrdersale d WHERE d.shipBy = :shipBy"),
    @NamedQuery(name = "DtOrdersale.findByShipType", query = "SELECT d FROM DtOrdersale d WHERE d.shipType = :shipType"),
    @NamedQuery(name = "DtOrdersale.findByShipDate", query = "SELECT d FROM DtOrdersale d WHERE d.shipDate = :shipDate"),
    @NamedQuery(name = "DtOrdersale.findByCarrier", query = "SELECT d FROM DtOrdersale d WHERE d.carrier = :carrier"),
    @NamedQuery(name = "DtOrdersale.findByCarrierNo", query = "SELECT d FROM DtOrdersale d WHERE d.carrierNo = :carrierNo"),
    @NamedQuery(name = "DtOrdersale.findByAmount", query = "SELECT d FROM DtOrdersale d WHERE d.amount = :amount"),
    @NamedQuery(name = "DtOrdersale.findByDate", query = "SELECT d FROM DtOrdersale d WHERE d.date = :date")})
public class DtOrdersale implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "saleNo", nullable = false, length = 20)
    private String saleNo;
    @Basic(optional = false)
    @Column(name = "customerId", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @Column(name = "customer_acc", nullable = false, length = 30)
    private String customerAcc;
    @Basic(optional = false)
    @Column(name = "Order_no", nullable = false, length = 20)
    private String orderno;
    @Basic(optional = false)
    @Column(name = "order_due", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date orderDue;
    @Basic(optional = false)
    @Column(name = "process_no", nullable = false, length = 20)
    private String processNo;
    @Basic(optional = false)
    @Column(name = "ship_by", nullable = false, length = 30)
    private String shipBy;
    @Basic(optional = false)
    @Column(name = "ship_type", nullable = false, length = 20)
    private String shipType;
    @Basic(optional = false)
    @Column(name = "ship_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date shipDate;
    @Basic(optional = false)
    @Column(name = "carrier", nullable = false, length = 50)
    private String carrier;
    @Basic(optional = false)
    @Column(name = "carrier_no", nullable = false, length = 20)
    private String carrierNo;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtOrdersale() {
    }

    public DtOrdersale(Integer id) {
        this.id = id;
    }

    public DtOrdersale(String saleNo, String customerId, String customerAcc, String orderno, Date orderDue, String processNo, String shipBy, String shipType, Date shipDate, String carrier, String carrierNo, double amount, Date date) {
//        this.id = id;
        this.saleNo = saleNo;
        this.customerId = customerId;
        this.customerAcc = customerAcc;
        this.orderno = orderno;
        this.orderDue = orderDue;
        this.processNo = processNo;
        this.shipBy = shipBy;
        this.shipType = shipType;
        this.shipDate = shipDate;
        this.carrier = carrier;
        this.carrierNo = carrierNo;
        this.amount = amount;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaleNo() {
        return saleNo;
    }

    public void setSaleNo(String saleNo) {
        this.saleNo = saleNo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAcc() {
        return customerAcc;
    }

    public void setCustomerAcc(String customerAcc) {
        this.customerAcc = customerAcc;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public Date getOrderDue() {
        return orderDue;
    }

    public void setOrderDue(Date orderDue) {
        this.orderDue = orderDue;
    }

    public String getProcessNo() {
        return processNo;
    }

    public void setProcessNo(String processNo) {
        this.processNo = processNo;
    }

    public String getShipBy() {
        return shipBy;
    }

    public void setShipBy(String shipBy) {
        this.shipBy = shipBy;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierNo() {
        return carrierNo;
    }

    public void setCarrierNo(String carrierNo) {
        this.carrierNo = carrierNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
        if (!(object instanceof DtOrdersale)) {
            return false;
        }
        DtOrdersale other = (DtOrdersale) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtOrdersale[ id=" + id + " ]";
    }
    
}
