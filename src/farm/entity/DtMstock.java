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
@Table(name = "dt_mstock")
//@SecondaryTable(name="dt_sales", pkJoinColumns={
//        @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})
//@org.hibernate.annotations.Table(
//   appliesTo="dt_sales",
//   fetch=FetchMode.SELECT,
//   optional=true)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtMstock.findAll", query = "SELECT d FROM DtMstock d"),
    @NamedQuery(name = "DtMstock.findById", query = "SELECT d FROM DtMstock d WHERE d.id = :id"),
    @NamedQuery(name = "DtMstock.findByDate", query = "SELECT d FROM DtMstock d WHERE d.date = :date"),
    @NamedQuery(name = "DtMstock.findByDescriprion", query = "SELECT d FROM DtMstock d WHERE d.descriprion = :descriprion"),
    @NamedQuery(name = "DtMstock.findByCompanyId", query = "SELECT d FROM DtMstock d WHERE d.companyId = :companyId"),
    @NamedQuery(name = "DtMstock.findByDebit", query = "SELECT d FROM DtMstock d WHERE d.debit = :debit"),
    @NamedQuery(name = "DtMstock.findByCredit", query = "SELECT d FROM DtMstock d WHERE d.credit = :credit"),
    @NamedQuery(name = "DtMstock.findByUserid", query = "SELECT d FROM DtMstock d WHERE d.userid = :userid")})
public class DtMstock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "descriprion", nullable = false, length = 100)
    private String descriprion;
    @Basic(optional = false)
    @Column(name = "company_id", nullable = false)
    private int companyId;
    @Basic(optional = false)
    @Column(name = "debit", nullable = false)
    private double debit;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;
    @Basic(optional = false)
    @Column(name = "userid", nullable = false, length = 50)
    private String userid;
    
    @OneToOne(mappedBy="stock")
    private DtDailycollection collection;
    
    @OneToOne(mappedBy="sale_stock")
    private DtSales sale;

    public DtMstock() {
    }

    public DtMstock(Integer id) {
        this.id = id;
    }

    public DtMstock(int id,Date date, String descriprion, int companyId, double debit, double credit, String userid) {
        this.id = id;
        this.date = date;
        this.descriprion = descriprion;
        this.companyId = companyId;
        this.debit = debit;
        this.credit = credit;
        this.userid = userid;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public DtDailycollection getCollection() {
        return collection;
    }

    public void setCollection(DtDailycollection collection) {
        this.collection = collection;
    }

    public DtSales getSale() {
        return sale;
    }

    public void setSale(DtSales sale) {
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
        if (!(object instanceof DtMstock)) {
            return false;
        }
        DtMstock other = (DtMstock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtMstock[ id=" + id + " ]";
    }
    
}
