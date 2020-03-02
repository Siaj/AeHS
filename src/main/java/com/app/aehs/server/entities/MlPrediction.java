/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "ml_prediction", catalog = "aehs_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MlPrediction.findAll", query = "SELECT m FROM MlPrediction m")
    , @NamedQuery(name = "MlPrediction.findById", query = "SELECT m FROM MlPrediction m WHERE m.id = :id")
    , @NamedQuery(name = "MlPrediction.findByDate", query = "SELECT m FROM MlPrediction m WHERE m.date = :date")
    , @NamedQuery(name = "MlPrediction.findByInputParam1", query = "SELECT m FROM MlPrediction m WHERE m.inputParam1 = :inputParam1")
    , @NamedQuery(name = "MlPrediction.findByInputParam2", query = "SELECT m FROM MlPrediction m WHERE m.inputParam2 = :inputParam2")
    , @NamedQuery(name = "MlPrediction.findByInputParam3", query = "SELECT m FROM MlPrediction m WHERE m.inputParam3 = :inputParam3")
    , @NamedQuery(name = "MlPrediction.findByInputParam4", query = "SELECT m FROM MlPrediction m WHERE m.inputParam4 = :inputParam4")
    , @NamedQuery(name = "MlPrediction.findByInputParam5", query = "SELECT m FROM MlPrediction m WHERE m.inputParam5 = :inputParam5")
    , @NamedQuery(name = "MlPrediction.findByUpdated", query = "SELECT m FROM MlPrediction m WHERE m.updated = :updated")
    , @NamedQuery(name = "MlPrediction.findByDeleted", query = "SELECT m FROM MlPrediction m WHERE m.deleted = :deleted")})
public class MlPrediction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "_id")
    private String id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 50)
    @Column(name = "input_param1")
    private String inputParam1;
    @Size(max = 50)
    @Column(name = "input_param2")
    private String inputParam2;
    @Size(max = 50)
    @Column(name = "input_param3")
    private String inputParam3;
    @Size(max = 50)
    @Column(name = "input_param4")
    private String inputParam4;
    @Size(max = 50)
    @Column(name = "input_param5")
    private String inputParam5;
    @Size(max = 10)
    private String updated;
    @Size(max = 10)
    private String deleted;
    @JoinColumn(name = "farm_detail", referencedColumnName = "_id")
    @ManyToOne
    private FarmDetail farmDetail;
    @JoinColumn(name = "prediction", referencedColumnName = "_id")
    @ManyToOne
    private Diseases prediction;

    public MlPrediction() {
    }

    public MlPrediction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInputParam1() {
        return inputParam1;
    }

    public void setInputParam1(String inputParam1) {
        this.inputParam1 = inputParam1;
    }

    public String getInputParam2() {
        return inputParam2;
    }

    public void setInputParam2(String inputParam2) {
        this.inputParam2 = inputParam2;
    }

    public String getInputParam3() {
        return inputParam3;
    }

    public void setInputParam3(String inputParam3) {
        this.inputParam3 = inputParam3;
    }

    public String getInputParam4() {
        return inputParam4;
    }

    public void setInputParam4(String inputParam4) {
        this.inputParam4 = inputParam4;
    }

    public String getInputParam5() {
        return inputParam5;
    }

    public void setInputParam5(String inputParam5) {
        this.inputParam5 = inputParam5;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public FarmDetail getFarmDetail() {
        return farmDetail;
    }

    public void setFarmDetail(FarmDetail farmDetail) {
        this.farmDetail = farmDetail;
    }

    public Diseases getPrediction() {
        return prediction;
    }

    public void setPrediction(Diseases prediction) {
        this.prediction = prediction;
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
        if (!(object instanceof MlPrediction)) {
            return false;
        }
        MlPrediction other = (MlPrediction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.MlPrediction[ id=" + id + " ]";
    }
    
}
