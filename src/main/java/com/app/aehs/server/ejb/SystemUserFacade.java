/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.ejb;

import com.app.aehs.server.entities.SystemUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class SystemUserFacade extends AbstractFacade<SystemUser> {

    @PersistenceContext(unitName = "aehsPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemUserFacade() {
        super(SystemUser.class);
    }
    
     public String systemUserCreate(SystemUser systemUser) {
        try {
            systemUser.setDeleted("NO");
            systemUser.setUpdated("NO");
            super.create(systemUser);
            return systemUser.getId();

        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            e.getConstraintViolations();
            return null;
        }
    }

    public boolean systemUserDelete(SystemUser systemUser, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(systemUser);
            } else if (permanent == false) {
                systemUser.setDeleted("YES");
                systemUser.setUpdated("NO");
                super.edit(systemUser);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean systemUserUpdate(SystemUser systemUser) {
        try {

            systemUser.setDeleted("NO");
            systemUser.setUpdated("NO");
            super.edit(systemUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public SystemUser systemUserFind(String systemUserId) {
        try {
            return super.find(systemUserId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<SystemUser> systemUserFindByAttribute(String systemUserAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        qryString = "SELECT e FROM SystemUser e ";
        qryString += "WHERE e." + systemUserAttribute + " =:systemUserAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SystemUser e ";
                qryString += "WHERE e." + systemUserAttribute + " LIKE '%" + attributeValue + "%'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserGetAll(boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM SystemUser e WHERE NOT (e.userRole.id = '3')";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.deleted = 'NO' AND NOT (e.userRole.id = '3')";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> farmerFindByAttribute(String systemUserAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        qryString = "SELECT e FROM SystemUser e ";
        qryString += "WHERE e." + systemUserAttribute + " =:systemUserAttribute AND e.userRole.id = '3'";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SystemUser e ";
                qryString += "WHERE e." + systemUserAttribute + " LIKE '%" + attributeValue + "%' AND NOT (e.userRole.id = '3')";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserGetAllFarmers(boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM SystemUser e WHERE e.userRole.id = '3'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.deleted = 'NO' AND e.userRole.id = '3'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
}
