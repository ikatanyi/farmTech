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
@Table(name = "dt_orders")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtOrders.findAll", query = "SELECT d FROM DtOrders d"),
    @NamedQuery(name = "DtOrders.findById", query = "SELECT d FROM DtOrders d WHERE d.id = :id"),
    @NamedQuery(name = "DtOrders.findBySupplierid", query = "SELECT d FROM DtOrders d WHERE d.supplierid = :supplierid"),
    @NamedQuery(name = "DtOrders.findByOrderNo", query = "SELECT d FROM DtOrders d WHERE d.orderNo = :orderNo"),
    @NamedQuery(name = "DtOrders.findByState", query = "SELECT d FROM DtOrders d WHERE d.state = :state"),
    @NamedQuery(name = "DtOrders.findByAmount", query = "SELECT d FROM DtOrders d WHERE d.amount = :amount"),
    @NamedQuery(name = "DtOrders.findByDate", query = "SELECT d FROM DtOrders d WHERE d.date = :date"),
    @NamedQuery(name = "DtOrders.findByStateDate", query = "SELECT d FROM DtOrders d WHERE d.stateDate = :stateDate")})
public class DtOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "supplierid", nullable = false, length = 20)
    private String supplierid;
    @Basic(optional = false)
    @Column(name = "order_no", nullable = false, length = 20)
    private String orderNo;
    @Basic(optional = false)
    @Column(name = "state", nullable = false, length = 12)
    private String state;
    @Basic(optional = false)
    @Column(name = "Amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "state_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stateDate;

    public DtOrders() {
    }

    public DtOrders(Integer id) {
        this.id = id;
    }

    public DtOrders(String supplierid, String orderNo, String state, double amount, Date date, Date stateDate) {
//        this.id = id;
        this.supplierid = supplierid;
        this.orderNo = orderNo;
        this.state = state;
        this.amount = amount;
        this.date = date;
        this.stateDate = stateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(String supplierid) {
        this.supplierid = supplierid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
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
        if (!(object instanceof DtOrders)) {
            return false;
        }
        DtOrders other = (DtOrders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ui.DtOrders[ id=" + id + " ]";
    }
    
}
