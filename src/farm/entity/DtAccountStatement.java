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
@Table(name = "dt_account_statement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtAccountStatement.findAll", query = "SELECT d FROM DtAccountStatement d"),
    @NamedQuery(name = "DtAccountStatement.findById", query = "SELECT d FROM DtAccountStatement d WHERE d.id = :id"),
    @NamedQuery(name = "DtAccountStatement.findByDate", query = "SELECT d FROM DtAccountStatement d WHERE d.date = :date"),
    @NamedQuery(name = "DtAccountStatement.findByPmtype", query = "SELECT d FROM DtAccountStatement d WHERE d.pmtype = :pmtype"),
    @NamedQuery(name = "DtAccountStatement.findByChequeNo", query = "SELECT d FROM DtAccountStatement d WHERE d.chequeNo = :chequeNo"),
    @NamedQuery(name = "DtAccountStatement.findByBank", query = "SELECT d FROM DtAccountStatement d WHERE d.bank = :bank"),
    @NamedQuery(name = "DtAccountStatement.findByAccount", query = "SELECT d FROM DtAccountStatement d WHERE d.account = :account"),
    @NamedQuery(name = "DtAccountStatement.findByDebit", query = "SELECT d FROM DtAccountStatement d WHERE d.debit = :debit"),
    @NamedQuery(name = "DtAccountStatement.findByCredit", query = "SELECT d FROM DtAccountStatement d WHERE d.credit = :credit")})
public class DtAccountStatement implements Serializable {
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
    @Column(name = "pmtype", nullable = false, length = 50)
    private String pmtype;
    @Basic(optional = false)
    @Column(name = "cheque_no", nullable = false, length = 50)
    private String chequeNo;
    @Basic(optional = false)
    @Column(name = "bank", nullable = false, length = 50)
    private String bank;
    @Basic(optional = false)
    @Column(name = "account", nullable = false, length = 50)
    private String account;
    @Basic(optional = false)
    @Column(name = "debit", nullable = false)
    private double debit;
    @Basic(optional = false)
    @Column(name = "credit", nullable = false)
    private double credit;

    public DtAccountStatement() {
    }

    public DtAccountStatement(Integer id) {
        this.id = id;
    }

    public DtAccountStatement(Integer id, Date date, String pmtype, String chequeNo, String bank, String account, double debit, double credit) {
        this.id = id;
        this.date = date;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DtAccountStatement)) {
            return false;
        }
        DtAccountStatement other = (DtAccountStatement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "farm.entity.DtAccountStatement[ id=" + id + " ]";
    }
    
}
