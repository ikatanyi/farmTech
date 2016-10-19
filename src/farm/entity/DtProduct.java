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
@Table(name = "dt_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtProduct.findAll", query = "SELECT d FROM DtProduct d"),
    @NamedQuery(name = "DtProduct.findById", query = "SELECT d FROM DtProduct d WHERE d.id = :id"),
    @NamedQuery(name = "DtProduct.findByItemCode", query = "SELECT d FROM DtProduct d WHERE d.itemCode = :itemCode"),
    @NamedQuery(name = "DtProduct.findByItemName", query = "SELECT d FROM DtProduct d WHERE d.itemName = :itemName"),
    @NamedQuery(name = "DtProduct.findBySuppliername", query = "SELECT d FROM DtProduct d WHERE d.suppliername = :suppliername"),
    @NamedQuery(name = "DtProduct.findByCategory", query = "SELECT d FROM DtProduct d WHERE d.category = :category"),
    @NamedQuery(name = "DtProduct.findByDescription", query = "SELECT d FROM DtProduct d WHERE d.description = :description"),
    @NamedQuery(name = "DtProduct.findByPrice", query = "SELECT d FROM DtProduct d WHERE d.price = :price"),
    @NamedQuery(name = "DtProduct.findByVat", query = "SELECT d FROM DtProduct d WHERE d.vat = :vat"),
    @NamedQuery(name = "DtProduct.findByQty", query = "SELECT d FROM DtProduct d WHERE d.qty = :qty"),
    @NamedQuery(name = "DtProduct.findByDate", query = "SELECT d FROM DtProduct d WHERE d.date = :date")})
public class DtProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "item_code", nullable = false, length = 10)
    private String itemCode;
    @Basic(optional = false)
    @Column(name = "item_name", nullable = false, length = 20)
    private String itemName;
    @Basic(optional = false)
    @Column(name = "suppliername", nullable = false, length = 30)
    private String suppliername;
    @Basic(optional = false)
    @Column(name = "category", nullable = false, length = 20)
    private String category;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 200)
    private String description;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "vat", nullable = false)
    private int vat;
    @Basic(optional = false)
    @Column(name = "qty", nullable = false)
    private int qty;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtProduct() {
    }

    public DtProduct(Integer id) {
        this.id = id;
    }

    public DtProduct(String itemCode, String itemName, String suppliername, String category, String description, double price, int vat, int qty, Date date) {
//        this.id = id;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.suppliername = suppliername;
        this.category = category;
        this.description = description;
        this.price = price;
        this.vat = vat;
        this.qty = qty;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
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
        if (!(object instanceof DtProduct)) {
            return false;
        }
        DtProduct other = (DtProduct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtProduct[ id=" + id + " ]";
    }
    
}
