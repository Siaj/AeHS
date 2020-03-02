/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(catalog = "aehs_db", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diseases.findAll", query = "SELECT d FROM Diseases d")
    , @NamedQuery(name = "Diseases.findById", query = "SELECT d FROM Diseases d WHERE d.id = :id")
    , @NamedQuery(name = "Diseases.findByCode", query = "SELECT d FROM Diseases d WHERE d.code = :code")
    , @NamedQuery(name = "Diseases.findByName", query = "SELECT d FROM Diseases d WHERE d.name = :name")
    , @NamedQuery(name = "Diseases.findByUpdated", query = "SELECT d FROM Diseases d WHERE d.updated = :updated")
    , @NamedQuery(name = "Diseases.findByDeleted", query = "SELECT d FROM Diseases d WHERE d.deleted = :deleted")})
public class Diseases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "_id")
    private String id;
    @Size(max = 10)
    private String code;
    @Size(max = 70)
    private String name;
    @Lob
    @Size(max = 65535)
    private String description;
    @Lob
    @Size(max = 65535)
    private String prescription;
    @Size(max = 10)
    private String updated;
    @Size(max = 10)
    private String deleted;
    @OneToMany(mappedBy = "prediction")
    private List<MlPrediction> mlPredictionList;

    public Diseases() {
    }

    public Diseases(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
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

    @XmlTransient
    public List<MlPrediction> getMlPredictionList() {
        return mlPredictionList;
    }

    public void setMlPredictionList(List<MlPrediction> mlPredictionList) {
        this.mlPredictionList = mlPredictionList;
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
        if (!(object instanceof Diseases)) {
            return false;
        }
        Diseases other = (Diseases) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.Diseases[ id=" + id + " ]";
    }
    
}
