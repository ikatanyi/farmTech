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
@Table(name = "dt_delivery_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DtDeliveryStatus.findAll", query = "SELECT d FROM DtDeliveryStatus d"),
    @NamedQuery(name = "DtDeliveryStatus.findById", query = "SELECT d FROM DtDeliveryStatus d WHERE d.id = :id"),
    @NamedQuery(name = "DtDeliveryStatus.findByFarmNo", query = "SELECT d FROM DtDeliveryStatus d WHERE d.farmNo = :farmNo"),
    @NamedQuery(name = "DtDeliveryStatus.findByDepartureDate", query = "SELECT d FROM DtDeliveryStatus d WHERE d.departureDate = :departureDate"),
    @NamedQuery(name = "DtDeliveryStatus.findByArrivalDate", query = "SELECT d FROM DtDeliveryStatus d WHERE d.arrivalDate = :arrivalDate"),
    @NamedQuery(name = "DtDeliveryStatus.findByDno", query = "SELECT d FROM DtDeliveryStatus d WHERE d.dno = :dno"),
    @NamedQuery(name = "DtDeliveryStatus.findByBatchno", query = "SELECT d FROM DtDeliveryStatus d WHERE d.batchno = :batchno"),
    @NamedQuery(name = "DtDeliveryStatus.findByStatus", query = "SELECT d FROM DtDeliveryStatus d WHERE d.status = :status"),
    @NamedQuery(name = "DtDeliveryStatus.findByMethod", query = "SELECT d FROM DtDeliveryStatus d WHERE d.method = :method"),
    @NamedQuery(name = "DtDeliveryStatus.findByVesselNo", query = "SELECT d FROM DtDeliveryStatus d WHERE d.vesselNo = :vesselNo"),
    @NamedQuery(name = "DtDeliveryStatus.findByReceived", query = "SELECT d FROM DtDeliveryStatus d WHERE d.received = :received"),
    @NamedQuery(name = "DtDeliveryStatus.findByComments", query = "SELECT d FROM DtDeliveryStatus d WHERE d.comments = :comments")})
public class DtDeliveryStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "farm_no", nullable = false, length = 50)
    private String farmNo;
    @Basic(optional = false)
    @Column(name = "departure_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date departureDate;
    @Basic(optional = false)
    @Column(name = "arrival_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;
    @Basic(optional = false)
    @Column(name = "dno", nullable = false, length = 20)
    private String dno;
    @Basic(optional = false)
    @Column(name = "batchno", nullable = false, length = 20)
    private String batchno;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    @Basic(optional = false)
    @Column(name = "method", nullable = false, length = 10)
    private String method;
    @Basic(optional = false)
    @Column(name = "vessel_no", nullable = false, length = 20)
    private String vesselNo;
    @Basic(optional = false)
    @Column(name = "received", nullable = false)
    private boolean received;
    @Basic(optional = false)
    @Column(name = "comments", nullable = false, length = 200)
    private String comments;

    public DtDeliveryStatus() {
    }

    public DtDeliveryStatus(Integer id) {
        this.id = id;
    }

    public DtDeliveryStatus(String farmNo, Date departureDate, Date arrivalDate, String dno, String batchno, String status, String method, String vesselNo, boolean received, String comments) {
//        this.id = id;
        this.farmNo = farmNo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.dno = dno;
        this.batchno = batchno;
        this.status = status;
        this.method = method;
        this.vesselNo = vesselNo;
        this.received = received;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFarmNo() {
        return farmNo;
    }

    public void setFarmNo(String farmNo) {
        this.farmNo = farmNo;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVesselNo() {
        return vesselNo;
    }

    public void setVesselNo(String vesselNo) {
        this.vesselNo = vesselNo;
    }

    public boolean getReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        if (!(object instanceof DtDeliveryStatus)) {
            return false;
        }
        DtDeliveryStatus other = (DtDeliveryStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "erim.entity.DtDeliveryStatus[ id=" + id + " ]";
    }
    
}
