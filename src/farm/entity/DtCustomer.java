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
@Table(name = "dt_customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtCustomer.findAll", query = "SELECT d FROM DtCustomer d"),
    @NamedQuery(name = "DtCustomer.findById", query = "SELECT d FROM DtCustomer d WHERE d.id = :id"),
    @NamedQuery(name = "DtCustomer.findByCustomerId", query = "SELECT d FROM DtCustomer d WHERE d.customerId = :customerId"),
    @NamedQuery(name = "DtCustomer.findByCustomerName", query = "SELECT d FROM DtCustomer d WHERE d.customerName = :customerName"),
    @NamedQuery(name = "DtCustomer.findByCompanyname", query = "SELECT d FROM DtCustomer d WHERE d.companyname = :companyname"),
    @NamedQuery(name = "DtCustomer.findByAddress", query = "SELECT d FROM DtCustomer d WHERE d.address = :address"),
    @NamedQuery(name = "DtCustomer.findByPhone", query = "SELECT d FROM DtCustomer d WHERE d.phone = :phone"),
    @NamedQuery(name = "DtCustomer.findByFax", query = "SELECT d FROM DtCustomer d WHERE d.fax = :fax"),
    @NamedQuery(name = "DtCustomer.findByEmail", query = "SELECT d FROM DtCustomer d WHERE d.email = :email"),
    @NamedQuery(name = "DtCustomer.findByWebsite", query = "SELECT d FROM DtCustomer d WHERE d.website = :website"),
    @NamedQuery(name = "DtCustomer.findByAccount", query = "SELECT d FROM DtCustomer d WHERE d.account = :account"),
    @NamedQuery(name = "DtCustomer.findByContact1", query = "SELECT d FROM DtCustomer d WHERE d.contact1 = :contact1"),
    @NamedQuery(name = "DtCustomer.findByConatct1Title", query = "SELECT d FROM DtCustomer d WHERE d.conatct1Title = :conatct1Title"),
    @NamedQuery(name = "DtCustomer.findByContact2", query = "SELECT d FROM DtCustomer d WHERE d.contact2 = :contact2"),
    @NamedQuery(name = "DtCustomer.findByContact2Title", query = "SELECT d FROM DtCustomer d WHERE d.contact2Title = :contact2Title"),
    @NamedQuery(name = "DtCustomer.findByShiptoAddress", query = "SELECT d FROM DtCustomer d WHERE d.shiptoAddress = :shiptoAddress"),
    @NamedQuery(name = "DtCustomer.findByBusinessType", query = "SELECT d FROM DtCustomer d WHERE d.businessType = :businessType"),
    @NamedQuery(name = "DtCustomer.findByDiscount", query = "SELECT d FROM DtCustomer d WHERE d.discount = :discount"),
    @NamedQuery(name = "DtCustomer.findByPaymentTerms", query = "SELECT d FROM DtCustomer d WHERE d.paymentTerms = :paymentTerms"),
    @NamedQuery(name = "DtCustomer.findByInvoiceMemo", query = "SELECT d FROM DtCustomer d WHERE d.invoiceMemo = :invoiceMemo"),
    @NamedQuery(name = "DtCustomer.findBySalesRep1", query = "SELECT d FROM DtCustomer d WHERE d.salesRep1 = :salesRep1"),
    @NamedQuery(name = "DtCustomer.findBySalesRep2", query = "SELECT d FROM DtCustomer d WHERE d.salesRep2 = :salesRep2"),
    @NamedQuery(name = "DtCustomer.findBySalesRep3", query = "SELECT d FROM DtCustomer d WHERE d.salesRep3 = :salesRep3"),
    @NamedQuery(name = "DtCustomer.findByTaxId", query = "SELECT d FROM DtCustomer d WHERE d.taxId = :taxId"),
    @NamedQuery(name = "DtCustomer.findByDate", query = "SELECT d FROM DtCustomer d WHERE d.date = :date")})
public class DtCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "customer_id", nullable = false, length = 20)
    private String customerId;
    @Basic(optional = false)
    @Column(name = "customer_name", nullable = false, length = 30)
    private String customerName;
    @Basic(optional = false)
    @Column(name = "companyname", nullable = false, length = 30)
    private String companyname;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
    @Basic(optional = false)
    @Column(name = "fax", nullable = false, length = 20)
    private String fax;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 30)
    private String email;
    @Basic(optional = false)
    @Column(name = "website", nullable = false, length = 30)
    private String website;
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 30)
    private String account;
    @Basic(optional = false)
    @Column(name = "contact1", nullable = false, length = 50)
    private String contact1;
    @Basic(optional = false)
    @Column(name = "conatct1_title", nullable = false, length = 10)
    private String conatct1Title;
    @Basic(optional = false)
    @Column(name = "contact2", nullable = false, length = 30)
    private String contact2;
    @Basic(optional = false)
    @Column(name = "contact2_title", nullable = false, length = 10)
    private String contact2Title;
    @Basic(optional = false)
    @Column(name = "shipto_address", nullable = false, length = 150)
    private String shiptoAddress;
    @Basic(optional = false)
    @Column(name = "business_type", nullable = false, length = 30)
    private String businessType;
    @Basic(optional = false)
    @Column(name = "discount", nullable = false)
    private int discount;
    @Basic(optional = false)
    @Column(name = "payment_terms", nullable = false, length = 30)
    private String paymentTerms;
    @Basic(optional = false)
    @Column(name = "invoice_memo", nullable = false, length = 30)
    private String invoiceMemo;
    @Basic(optional = false)
    @Column(name = "sales_rep1", nullable = false, length = 50)
    private String salesRep1;
    @Basic(optional = false)
    @Column(name = "sales_rep2", nullable = false, length = 50)
    private String salesRep2;
    @Basic(optional = false)
    @Column(name = "sales_rep3", nullable = false, length = 50)
    private String salesRep3;
    @Basic(optional = false)
    @Column(name = "tax_id", nullable = false, length = 20)
    private String taxId;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtCustomer() {
    }

    public DtCustomer(Integer id) {
        this.id = id;
    }

    public DtCustomer(String customerId, String customerName, String companyname, String address, String phone, String fax, String email, String website, String account, String contact1, String conatct1Title, String contact2, String contact2Title, String shiptoAddress, String businessType, int discount, String paymentTerms, String invoiceMemo, String salesRep1, String salesRep2, String salesRep3, String taxId, Date date) {
//        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.companyname = companyname;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.account = account;
        this.contact1 = contact1;
        this.conatct1Title = conatct1Title;
        this.contact2 = contact2;
        this.contact2Title = contact2Title;
        this.shiptoAddress = shiptoAddress;
        this.businessType = businessType;
        this.discount = discount;
        this.paymentTerms = paymentTerms;
        this.invoiceMemo = invoiceMemo;
        this.salesRep1 = salesRep1;
        this.salesRep2 = salesRep2;
        this.salesRep3 = salesRep3;
        this.taxId = taxId;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getConatct1Title() {
        return conatct1Title;
    }

    public void setConatct1Title(String conatct1Title) {
        this.conatct1Title = conatct1Title;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getContact2Title() {
        return contact2Title;
    }

    public void setContact2Title(String contact2Title) {
        this.contact2Title = contact2Title;
    }

    public String getShiptoAddress() {
        return shiptoAddress;
    }

    public void setShiptoAddress(String shiptoAddress) {
        this.shiptoAddress = shiptoAddress;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getInvoiceMemo() {
        return invoiceMemo;
    }

    public void setInvoiceMemo(String invoiceMemo) {
        this.invoiceMemo = invoiceMemo;
    }

    public String getSalesRep1() {
        return salesRep1;
    }

    public void setSalesRep1(String salesRep1) {
        this.salesRep1 = salesRep1;
    }

    public String getSalesRep2() {
        return salesRep2;
    }

    public void setSalesRep2(String salesRep2) {
        this.salesRep2 = salesRep2;
    }

    public String getSalesRep3() {
        return salesRep3;
    }

    public void setSalesRep3(String salesRep3) {
        this.salesRep3 = salesRep3;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
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
        if (!(object instanceof DtCustomer)) {
            return false;
        }
        DtCustomer other = (DtCustomer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtCustomer[ id=" + id + " ]";
    }
    
}
