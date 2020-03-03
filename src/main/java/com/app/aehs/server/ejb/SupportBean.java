/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.server.ejb;

import com.app.aehs.server.entities.SystemUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class SupportBean {

    @PersistenceContext(unitName = "aehsPU")
    private EntityManager em;

    public SystemUser authenticateUser(String username, String password) {
        try {
            return (SystemUser) em.createNamedQuery(SystemUser.FIND_BY_USERNAME_PASSWORD)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Did not get any Results for entry");
            e.printStackTrace();
            return null;
        }
    }
}
