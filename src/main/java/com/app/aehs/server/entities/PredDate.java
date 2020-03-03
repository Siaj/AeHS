/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "pred_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredDate.findAll", query = "SELECT p FROM PredDate p")
    , @NamedQuery(name = "PredDate.findById", query = "SELECT p FROM PredDate p WHERE p.id = :id")
    , @NamedQuery(name = "PredDate.findByValue", query = "SELECT p FROM PredDate p WHERE p.value = :value")
    , @NamedQuery(name = "PredDate.findByUpdated", query = "SELECT p FROM PredDate p WHERE p.updated = :updated")
    , @NamedQuery(name = "PredDate.findByDeleted", query = "SELECT p FROM PredDate p WHERE p.deleted = :deleted")})
public class PredDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "_id")
    private String id;
    @Size(max = 70)
    @Column(name = "value")
    private String value;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;

    public PredDate() {
    }

    public PredDate(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PredDate)) {
            return false;
        }
        PredDate other = (PredDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.aehs.server.entities.PredDate[ id=" + id + " ]";
    }
    
}
