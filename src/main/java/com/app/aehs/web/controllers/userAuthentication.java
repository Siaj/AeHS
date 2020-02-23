/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.web.commons.AeHSConstants;
import com.app.aehs.web.commons.JSFUtility;
import com.app.aehs.web.commons.UserAccessController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAuthentication")
@SessionScoped
public class userAuthentication implements Serializable {

    private SystemUser systemUser = new SystemUser();
    private UserAccessController userAccessController = new UserAccessController();
    private String username;
    private String password;

    /**
     * Creates a new instance of userAuthentication
     */
    public userAuthentication() {
    }

    public String authenticateUser() {
        try {
            System.out.println(username);
            System.out.println(password);
            System.out.println("Authentication reached successfully");
            return "pages/system_admin/system_admin.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in authenticating user: " + e.getLocalizedMessage());
            return null;
        }
    }

    public void isLoggedIn() {
    }

    public String logOutUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, null);
            JSFUtility.destroySession();
            this.systemUser = null;
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String returnToIndex() {
        return "/index.xhtml?faces-redirect=true";
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public UserAccessController getUserAccessController() {
        return userAccessController;
    }

    public void setUserAccessController(UserAccessController userAccessController) {
        this.userAccessController = userAccessController;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
