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
@Table(name = "dt_suppliers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtSuppliers.findAll", query = "SELECT d FROM DtSuppliers d"),
    @NamedQuery(name = "DtSuppliers.findById", query = "SELECT d FROM DtSuppliers d WHERE d.id = :id"),
    @NamedQuery(name = "DtSuppliers.findBySuppliername", query = "SELECT d FROM DtSuppliers d WHERE d.suppliername = :suppliername"),
    @NamedQuery(name = "DtSuppliers.findBySupplierId", query = "SELECT d FROM DtSuppliers d WHERE d.supplierId = :supplierId"),
    @NamedQuery(name = "DtSuppliers.findByPhone", query = "SELECT d FROM DtSuppliers d WHERE d.phone = :phone"),
    @NamedQuery(name = "DtSuppliers.findByAddress", query = "SELECT d FROM DtSuppliers d WHERE d.address = :address"),
    @NamedQuery(name = "DtSuppliers.findByEmail", query = "SELECT d FROM DtSuppliers d WHERE d.email = :email"),
    @NamedQuery(name = "DtSuppliers.findByWebsite", query = "SELECT d FROM DtSuppliers d WHERE d.website = :website"),
    @NamedQuery(name = "DtSuppliers.findByPoSp", query = "SELECT d FROM DtSuppliers d WHERE d.poSp = :poSp"),
    @NamedQuery(name = "DtSuppliers.findByPoAttention", query = "SELECT d FROM DtSuppliers d WHERE d.poAttention = :poAttention"),
    @NamedQuery(name = "DtSuppliers.findByContact1", query = "SELECT d FROM DtSuppliers d WHERE d.contact1 = :contact1"),
    @NamedQuery(name = "DtSuppliers.findByContact1Title", query = "SELECT d FROM DtSuppliers d WHERE d.contact1Title = :contact1Title"),
    @NamedQuery(name = "DtSuppliers.findByContact2", query = "SELECT d FROM DtSuppliers d WHERE d.contact2 = :contact2"),
    @NamedQuery(name = "DtSuppliers.findByContact2Title", query = "SELECT d FROM DtSuppliers d WHERE d.contact2Title = :contact2Title"),
    @NamedQuery(name = "DtSuppliers.findByNotes", query = "SELECT d FROM DtSuppliers d WHERE d.notes = :notes"),
    @NamedQuery(name = "DtSuppliers.findByStatus", query = "SELECT d FROM DtSuppliers d WHERE d.status = :status"),
    @NamedQuery(name = "DtSuppliers.findByDiscount", query = "SELECT d FROM DtSuppliers d WHERE d.discount = :discount"),
    @NamedQuery(name = "DtSuppliers.findByPaymentTerms", query = "SELECT d FROM DtSuppliers d WHERE d.paymentTerms = :paymentTerms"),
    @NamedQuery(name = "DtSuppliers.findByShipVia", query = "SELECT d FROM DtSuppliers d WHERE d.shipVia = :shipVia"),
    @NamedQuery(name = "DtSuppliers.findByPoTrailer", query = "SELECT d FROM DtSuppliers d WHERE d.poTrailer = :poTrailer"),
    @NamedQuery(name = "DtSuppliers.findByLoadHrs", query = "SELECT d FROM DtSuppliers d WHERE d.loadHrs = :loadHrs"),
    @NamedQuery(name = "DtSuppliers.findByLogo", query = "SELECT d FROM DtSuppliers d WHERE d.logo = :logo"),
    @NamedQuery(name = "DtSuppliers.findByTaxId", query = "SELECT d FROM DtSuppliers d WHERE d.taxId = :taxId"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Contact", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Contact = :rfq1Contact"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Phone", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Phone = :rfq1Phone"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Fax", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Fax = :rfq1Fax"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Email", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Email = :rfq1Email"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Address", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Address = :rfq1Address"),
    @NamedQuery(name = "DtSuppliers.findByRfq1Status", query = "SELECT d FROM DtSuppliers d WHERE d.rfq1Status = :rfq1Status"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Contact", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Contact = :rfq2Contact"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Phone", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Phone = :rfq2Phone"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Fax", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Fax = :rfq2Fax"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Email", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Email = :rfq2Email"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Address", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Address = :rfq2Address"),
    @NamedQuery(name = "DtSuppliers.findByRfq2Status", query = "SELECT d FROM DtSuppliers d WHERE d.rfq2Status = :rfq2Status"),
    @NamedQuery(name = "DtSuppliers.findByDate", query = "SELECT d FROM DtSuppliers d WHERE d.date = :date")})
public class DtSuppliers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "suppliername", nullable = false, length = 100)
    private String suppliername;
    @Basic(optional = false)
    @Column(name = "supplier_id", nullable = false, length = 50)
    private String supplierId;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    @Basic(optional = false)
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic(optional = false)
    @Column(name = "website", nullable = false, length = 20)
    private String website;
    @Basic(optional = false)
    @Column(name = "po_sp", nullable = false, length = 20)
    private String poSp;
    @Basic(optional = false)
    @Column(name = "po_attention", nullable = false, length = 20)
    private String poAttention;
    @Basic(optional = false)
    @Column(name = "contact1", nullable = false, length = 20)
    private String contact1;
    @Basic(optional = false)
    @Column(name = "contact1_title", nullable = false, length = 10)
    private String contact1Title;
    @Basic(optional = false)
    @Column(name = "contact2", nullable = false, length = 20)
    private String contact2;
    @Basic(optional = false)
    @Column(name = "contact2_title", nullable = false, length = 10)
    private String contact2Title;
    @Basic(optional = false)
    @Column(name = "notes", nullable = false, length = 100)
    private String notes;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @Basic(optional = false)
    @Column(name = "discount", nullable = false)
    private int discount;
    @Basic(optional = false)
    @Column(name = "payment_terms", nullable = false, length = 30)
    private String paymentTerms;
    @Basic(optional = false)
    @Column(name = "ship_via", nullable = false, length = 20)
    private String shipVia;
    @Basic(optional = false)
    @Column(name = "po_trailer", nullable = false, length = 20)
    private String poTrailer;
    @Basic(optional = false)
    @Column(name = "load_hrs", nullable = false, length = 20)
    private String loadHrs;
    @Basic(optional = false)
    @Column(name = "logo", nullable = false, length = 20)
    private String logo;
    @Basic(optional = false)
    @Column(name = "tax_id", nullable = false, length = 20)
    private String taxId;
    @Basic(optional = false)
    @Column(name = "rfq1_contact", nullable = false, length = 20)
    private String rfq1Contact;
    @Basic(optional = false)
    @Column(name = "rfq1_phone", nullable = false, length = 20)
    private String rfq1Phone;
    @Basic(optional = false)
    @Column(name = "rfq1_fax", nullable = false, length = 20)
    private String rfq1Fax;
    @Basic(optional = false)
    @Column(name = "rfq1_email", nullable = false, length = 20)
    private String rfq1Email;
    @Basic(optional = false)
    @Column(name = "rfq1_address", nullable = false, length = 20)
    private String rfq1Address;
    @Basic(optional = false)
    @Column(name = "rfq1_status", nullable = false)
    private boolean rfq1Status;
    @Basic(optional = false)
    @Column(name = "rfq2_contact", nullable = false, length = 20)
    private String rfq2Contact;
    @Basic(optional = false)
    @Column(name = "rfq2_phone", nullable = false, length = 20)
    private String rfq2Phone;
    @Basic(optional = false)
    @Column(name = "rfq2_fax", nullable = false, length = 20)
    private String rfq2Fax;
    @Basic(optional = false)
    @Column(name = "rfq2_email", nullable = false, length = 20)
    private String rfq2Email;
    @Basic(optional = false)
    @Column(name = "rfq2_address", nullable = false, length = 20)
    private String rfq2Address;
    @Basic(optional = false)
    @Column(name = "rfq2_status", nullable = false)
    private boolean rfq2Status;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public DtSuppliers() {
    }

    public DtSuppliers(Integer id) {
        this.id = id;
    }

    public DtSuppliers(String suppliername, String supplierId, String phone, String address, String email, String website, String poSp, String poAttention, String contact1, String contact1Title, String contact2, String contact2Title, String notes, String status, int discount, String paymentTerms, String shipVia, String poTrailer, String loadHrs, String logo, String taxId, String rfq1Contact, String rfq1Phone, String rfq1Fax, String rfq1Email, String rfq1Address, boolean rfq1Status, String rfq2Contact, String rfq2Phone, String rfq2Fax, String rfq2Email, String rfq2Address, boolean rfq2Status, Date date) {
//        this.id = id;
        this.suppliername = suppliername;
        this.supplierId = supplierId;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.website = website;
        this.poSp = poSp;
        this.poAttention = poAttention;
        this.contact1 = contact1;
        this.contact1Title = contact1Title;
        this.contact2 = contact2;
        this.contact2Title = contact2Title;
        this.notes = notes;
        this.status = status;
        this.discount = discount;
        this.paymentTerms = paymentTerms;
        this.shipVia = shipVia;
        this.poTrailer = poTrailer;
        this.loadHrs = loadHrs;
        this.logo = logo;
        this.taxId = taxId;
        this.rfq1Contact = rfq1Contact;
        this.rfq1Phone = rfq1Phone;
        this.rfq1Fax = rfq1Fax;
        this.rfq1Email = rfq1Email;
        this.rfq1Address = rfq1Address;
        this.rfq1Status = rfq1Status;
        this.rfq2Contact = rfq2Contact;
        this.rfq2Phone = rfq2Phone;
        this.rfq2Fax = rfq2Fax;
        this.rfq2Email = rfq2Email;
        this.rfq2Address = rfq2Address;
        this.rfq2Status = rfq2Status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPoSp() {
        return poSp;
    }

    public void setPoSp(String poSp) {
        this.poSp = poSp;
    }

    public String getPoAttention() {
        return poAttention;
    }

    public void setPoAttention(String poAttention) {
        this.poAttention = poAttention;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1;
    }

    public String getContact1Title() {
        return contact1Title;
    }

    public void setContact1Title(String contact1Title) {
        this.contact1Title = contact1Title;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }

    public String getPoTrailer() {
        return poTrailer;
    }

    public void setPoTrailer(String poTrailer) {
        this.poTrailer = poTrailer;
    }

    public String getLoadHrs() {
        return loadHrs;
    }

    public void setLoadHrs(String loadHrs) {
        this.loadHrs = loadHrs;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getRfq1Contact() {
        return rfq1Contact;
    }

    public void setRfq1Contact(String rfq1Contact) {
        this.rfq1Contact = rfq1Contact;
    }

    public String getRfq1Phone() {
        return rfq1Phone;
    }

    public void setRfq1Phone(String rfq1Phone) {
        this.rfq1Phone = rfq1Phone;
    }

    public String getRfq1Fax() {
        return rfq1Fax;
    }

    public void setRfq1Fax(String rfq1Fax) {
        this.rfq1Fax = rfq1Fax;
    }

    public String getRfq1Email() {
        return rfq1Email;
    }

    public void setRfq1Email(String rfq1Email) {
        this.rfq1Email = rfq1Email;
    }

    public String getRfq1Address() {
        return rfq1Address;
    }

    public void setRfq1Address(String rfq1Address) {
        this.rfq1Address = rfq1Address;
    }

    public boolean getRfq1Status() {
        return rfq1Status;
    }

    public void setRfq1Status(boolean rfq1Status) {
        this.rfq1Status = rfq1Status;
    }

    public String getRfq2Contact() {
        return rfq2Contact;
    }

    public void setRfq2Contact(String rfq2Contact) {
        this.rfq2Contact = rfq2Contact;
    }

    public String getRfq2Phone() {
        return rfq2Phone;
    }

    public void setRfq2Phone(String rfq2Phone) {
        this.rfq2Phone = rfq2Phone;
    }

    public String getRfq2Fax() {
        return rfq2Fax;
    }

    public void setRfq2Fax(String rfq2Fax) {
        this.rfq2Fax = rfq2Fax;
    }

    public String getRfq2Email() {
        return rfq2Email;
    }

    public void setRfq2Email(String rfq2Email) {
        this.rfq2Email = rfq2Email;
    }

    public String getRfq2Address() {
        return rfq2Address;
    }

    public void setRfq2Address(String rfq2Address) {
        this.rfq2Address = rfq2Address;
    }

    public boolean getRfq2Status() {
        return rfq2Status;
    }

    public void setRfq2Status(boolean rfq2Status) {
        this.rfq2Status = rfq2Status;
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
        if (!(object instanceof DtSuppliers)) {
            return false;
        }
        DtSuppliers other = (DtSuppliers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtSuppliers[ id=" + id + " ]";
    }
    
}
