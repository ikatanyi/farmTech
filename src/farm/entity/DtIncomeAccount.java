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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Kent
 */
@Entity
@Table(name = "dt_income_account")
@SecondaryTable(name="dt_account_statement", pkJoinColumns={
        @PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})
@org.hibernate.annotations.Table(
   appliesTo="dt_account_statement",
   fetch=FetchMode.SELECT,
   optional=true)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtIncomeAccount.findAll", query = "SELECT d FROM DtIncomeAccount d"),
    @NamedQuery(name = "DtIncomeAccount.findById", query = "SELECT d FROM DtIncomeAccount d WHERE d.id = :id"),
    @NamedQuery(name = "DtIncomeAccount.findByDate", query = "SELECT d FROM DtIncomeAccount d WHERE d.date = :date"),
    @NamedQuery(name = "DtIncomeAccount.findByDeptId", query = "SELECT d FROM DtIncomeAccount d WHERE d.deptId = :deptId"),
    @NamedQuery(name = "DtIncomeAccount.findByDescription", query = "SELECT d FROM DtIncomeAccount d WHERE d.description = :description"),
    @NamedQuery(name = "DtIncomeAccount.findByAmount", query = "SELECT d FROM DtIncomeAccount d WHERE d.amount = :amount")})
public class DtIncomeAccount implements Serializable {
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
    @Column(name = "dept_id", nullable = false, length = 20)
    private String deptId;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Column(table="dt_account_statement")
    private String pmtype;
    @Column(name="cheque_no",table="dt_account_statement")
    private String chequeNo;
    @Column(table="dt_account_statement")
    private String bank;
    @Column(table="dt_account_statement")
    private String account;
    @Column(table="dt_account_statement")
    private double debit;
    @Column(table="dt_account_statement")
    private double credit;
    @Column(name = "date", nullable = false,table="dt_account_statement")
    @Temporal(TemporalType.DATE)
    private Date adate;

    public DtIncomeAccount() {
    }

    public DtIncomeAccount(Integer id) {
        this.id = id;
    }

    public DtIncomeAccount(Integer id, Date date, String deptId, String description, double amount,String pmtype, String chequeNo, String bank, String account, double debit, double credit) {
        this.id = id;
        this.date = date;
        this.deptId = deptId;
        this.description = description;
        this.amount = amount;
        this.pmtype = pmtype;
        this.chequeNo = chequeNo;
        this.bank = bank;
        this.account = account;
        this.debit = debit;
        this.credit = credit;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPmtype() {
        return pmtype;
    }

    public void setPmtype(String pmtype) {
        this.pmtype = pmtype;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
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
        if (!(object instanceof DtIncomeAccount)) {
            return false;
        }
        DtIncomeAccount other = (DtIncomeAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtIncomeAccount[ id=" + id + " ]";
    }
    
}
