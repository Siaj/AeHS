/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.ejb;

import com.app.aehs.server.entities.MlPrediction;
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
public class MlPredictionFacade extends AbstractFacade<MlPrediction> {

    @PersistenceContext(unitName = "aehsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MlPredictionFacade() {
        super(MlPrediction.class);
    }

    public String createPrediction(MlPrediction mlPrediction) {
        try {
            mlPrediction.setDeleted("NO");
            mlPrediction.setUpdated("NO");
            super.create(mlPrediction);
            return mlPrediction.getId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deletePrediction(MlPrediction mlPrediction) {
        try {
            super.remove(mlPrediction);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<MlPrediction> mlPredictionsFindAll() {
        List<MlPrediction> listOfMlPredictions = null;
        String qryString;

        qryString = "SELECT e FROM MlPrediction e ";

        try {
            listOfMlPredictions = (List<MlPrediction>) getEntityManager().createQuery(qryString).getResultList();
            return listOfMlPredictions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<MlPrediction> mlPredictionFindByAttribute(String mlPredictionAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<MlPrediction> listOfMlPredictions = null;

        String qryString;

        qryString = "SELECT e FROM MlPrediction e ";
        qryString += "WHERE e." + mlPredictionAttribute + " =:mlPredictionAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfMlPredictions = (List<MlPrediction>) getEntityManager().createQuery(qryString).setParameter("mlPredictionAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM MlPrediction e ";
                qryString += "WHERE e." + mlPredictionAttribute + " LIKE '%" + attributeValue + "%'";
                listOfMlPredictions = (List<MlPrediction>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfMlPredictions = (List<MlPrediction>) getEntityManager().createQuery(qryString).setParameter("mlPredictionAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfMlPredictions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
