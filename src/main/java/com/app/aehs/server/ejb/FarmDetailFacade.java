/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.ejb;

import com.app.aehs.server.entities.FarmDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class FarmDetailFacade extends AbstractFacade<FarmDetail> {

    @PersistenceContext(unitName = "com.app.webservices_AeHS_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FarmDetailFacade() {
        super(FarmDetail.class);
    }
    
     public String createFarmDetails(FarmDetail farmDetail) {
        try {
            farmDetail.setUpdated("NO");
            farmDetail.setDeleted("NO");
            super.create(farmDetail);
            return farmDetail.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateFarmDetails(FarmDetail farmDetail) {
        try {
            farmDetail.setUpdated("NO");
            farmDetail.setDeleted("NO");
            super.edit(farmDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<FarmDetail> farmerDetailsFindByAttribute(String farmDetailsAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<FarmDetail> listOfFarmDetails = null;

        String qryString;

        qryString = "SELECT e FROM FarmDetail e ";
        qryString += "WHERE e." + farmDetailsAttribute + " =:farmDetailsAttribute";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).setParameter("farmDetailsAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM FarmDetail e ";
                qryString += "WHERE e." + farmDetailsAttribute + " LIKE '%" + attributeValue + "%'";
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).setParameter("farmDetailsAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfFarmDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public List<FarmDetail> farmerDetailsFindByOwner(String farmDetailsAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<FarmDetail> listOfFarmDetails = null;

        String qryString;

        qryString = "SELECT e FROM FarmDetail e ";
        qryString += "WHERE e." + farmDetailsAttribute + " =:farmDetailsAttribute";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).setParameter("farmDetailsAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM FarmDetail e ";
                qryString += "WHERE e." + farmDetailsAttribute + " LIKE '%" + attributeValue + "%'";
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfFarmDetails = (List<FarmDetail>) getEntityManager().createQuery(qryString).setParameter("farmDetailsAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfFarmDetails;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
