/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import com.app.aehs.server.ejb.SupportBean;
import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.web.commons.AeHSConstants;
import com.app.aehs.web.commons.GenerateIDs;
import com.app.aehs.web.commons.JSFUtility;
import com.app.aehs.web.commons.LoginUser;
import com.app.aehs.web.commons.UserAccessController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAuthentication")
@SessionScoped
public class userAuthentication implements Serializable {

    @Inject
    private SupportBean supportBean;

    private SystemUser systemUser = new SystemUser();
    private UserAccessController userAccessController = new UserAccessController();
    private adminPages pages = new adminPages();
    private LoginUser sysadUserSession;
    private LoginUser extUserSession;
    private LoginUser userSession;

    private String username;
    private String password;

    /**
     * Creates a new instance of userAuthentication
     */
    public userAuthentication() {
    }

    public String authenticateUser() {
        try {
            if (username == null || "".equals(username)) {
                JSFUtility.warnMessage("Login: ", "Username is required");
                return "index.xhtml";
            } else if (password == null || "".equals(password)) {
                JSFUtility.warnMessage("Login: ", "Password is required for login");
                return "index.xhtml";
            } else {
                password = GenerateIDs.generateHash(password);
//                System.out.println("Hashed Password: " + password);

                systemUser = supportBean.authenticateUser(username, password);

                if (systemUser != null) {
                    LoginUser loginUser = new LoginUser();

                    if (systemUser.getAccountStatus().equals("Inactive")) {
                        JSFUtility.errorMessage("Account Status: ", "Your account is currently disabled, contact systems administrator");
                        return "index.xhtml";
                    } else {
//                        System.out.println("Role: " + systemUser.getUserRole().getRoleName());

                        if (null == systemUser.getUserRole().getRoleName()) {
                            JSFUtility.errorMessage("Role: ", "Your role is not defined, contact systems administrator");
                            return "index.xhtml?faces-redirect=true";
                        } else {
                            userSession = (LoginUser) JSFUtility.getSessionValue(AeHSConstants.ADMIN_USER);

                            switch (systemUser.getUserRole().getRoleName()) {
                                case "Regional Extension Officer":

//                                    sysadUserSession = (LoginUser) JSFUtility.getSessionValue(AeHSConstants.ADMIN_USER);
                                    if (userSession != null) {
                                        JSFUtility.destroySession();

                                        loginUser.setAccessFor("Regional Extension Officer");
                                        loginUser.setUserLogin(systemUser);
                                        loginUser.setUserScreenName(username);
                                        loginUser.setIsLogin(true);
                                        loginUser.setIsAdmin(true);

                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_USER, loginUser);
                                        JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, systemUser);
                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER, pages);

                                        return "pages/system_admin/system_admin.xhtml?faces-redirect=true";
                                    } else {

                                        loginUser.setAccessFor("Regional Extension Officer");
                                        loginUser.setUserLogin(systemUser);
                                        loginUser.setUserScreenName(username);
                                        loginUser.setIsLogin(true);
                                        loginUser.setIsAdmin(true);

                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_USER, loginUser);
                                        JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, systemUser);
                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER, pages);

                                        return "pages/system_admin/system_admin.xhtml?faces-redirect=true";
                                    }
                                case "District Extension Officer":

//                                    extUserSession = (LoginUser) JSFUtility.getSessionValue(AeHSConstants.ADMIN_USER);
                                    if (userSession != null) {
                                        JSFUtility.destroySession();

                                        loginUser.setAccessFor("District Extension Officer");
                                        loginUser.setUserLogin(systemUser);
                                        loginUser.setUserScreenName(username);
                                        loginUser.setIsLogin(true);
                                        loginUser.setIsAdmin(false);

                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_USER, loginUser);
                                        JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, systemUser);
                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER, pages);

                                        return "pages/extension_officer/extension_officer.xhtml?faces-redirect=true";
                                    } else {

                                        loginUser.setAccessFor("District Extension Officer");
                                        loginUser.setUserLogin(systemUser);
                                        loginUser.setUserScreenName(username);
                                        loginUser.setIsLogin(true);
                                        loginUser.setIsAdmin(false);

                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_USER, loginUser);
                                        JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, systemUser);
                                        JSFUtility.putSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER, pages);

                                        return "pages/extension_officer/extension_officer.xhtml?faces-redirect=true";
                                    }
                                default:
                                    JSFUtility.errorMessage("Role: ", "Your role is not defined, contact systems administrator");
                                    return "index.xhtml?faces-redirect=true";
                            }
                        }
                    }
                } else {
                    JSFUtility.warnMessage("Login :", "Username or Password Incorrect");
//                    System.out.println("Wrong username or password");
                    return "index.xhtml";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("Error in authenticating user: " + e.getLocalizedMessage());
            return null;
        }
    }

    public void isLoggedIn() {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) JSFUtility.getCurrentContext().getApplication().getNavigationHandler();

        if (userAccessController.isIsLogin() == false) {
//            System.out.println("Login Status: " + userAccessController.isIsLogin());
            nav.performNavigation("/index.xhtml?faces-redirect=true");
            JSFUtility.warnMessage("Authorization Error", "You need to login with correct credential to access this section");
        } else {
            String accessFor = userAccessController.getLoginUser().getAccessFor();
            System.out.println("Access For: " + accessFor);
        }
    }

    public void checkIsAdmin() {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) JSFUtility.getCurrentContext().getApplication().getNavigationHandler();

        if (userAccessController.isIsLogin() == true && userAccessController.isIsAdmin() == false) {
            nav.performNavigation("/pages/extension_officer/extension_officer.xhtml?faces-redirect=true");
        }
    }

    public void checkIsUser() {
        ConfigurableNavigationHandler nav
                = (ConfigurableNavigationHandler) JSFUtility.getCurrentContext().getApplication().getNavigationHandler();

        try {
            if (userAccessController.getLoginUser() != null) {
                if ("District Extension Officer".equals(userAccessController.getLoginUser().getAccessFor())) {
                    System.out.println("User has access to page: " + userAccessController.getLoginUser().getAccessFor());
                } else {
//                    System.out.println("Null Value for user and not System Admin...");
                    nav.performNavigation("/index.xhtml?faces-redirect=true");
                }
            } else {
                System.out.println("Could not get LoginUser...");
            }

        } catch (Exception e) {
            e.printStackTrace();
            nav.performNavigation("/index.xhtml?faces-redirect=true");
        }
    }

    public String logOutUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
//            JSFUtility.putSessionValue(AeHSConstants.ADMIN_USER, null);
//            JSFUtility.putSessionValue(AeHSConstants.LOGIN_USER, null);
//            JSFUtility.putSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER, null);
            JSFUtility.destroySession();
            this.systemUser = null;
            username = "";
            password = "";
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "/index.xhtml?faces-redirect=true";
    }

    public String returnToIndex() {
        return "/index.xhtml?faces-redirect=true";
    }

    //<editor-fold defaultstate="collapsed" desc="Encapsuator: Getter & Setter Methods">
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

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public adminPages getPages() {
        return pages;
    }

    public void setPages(adminPages pages) {
        this.pages = pages;
    }

//</editor-fold>
}
