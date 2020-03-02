/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.ejb;

import com.app.aehs.server.entities.UserRole;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "com.app.webservices_AeHS_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }
    
   public List<UserRole> roleGetAll(boolean includeLogicallyDeleted) {
        List<UserRole> listOfRole = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfRole = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM UserRole e WHERE e.deleted = 'NO'";
                listOfRole = (List<UserRole>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfRole;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    } 
    
}
