/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.commons;

import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.web.controllers.adminPages;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAccessController")
@RequestScoped
public class UserAccessController implements Serializable {

    /**
     * Creates a new instance of UserAccessController
     */
    private boolean isLogin = false;
    private boolean isAdmin = false;
    private LoginUser loginUser = null;
    private String loginUsername = "";
    private SystemUser systemUser = null;
    private adminPages pages = null;

    public UserAccessController() {
    }

    public boolean isIsLogin() {
        LoginUser user = getLoginUser();
        if (user == null) {
            isLogin = false;
        } else {
            isLogin = user.isIsLogin();
        }
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin() {
        LoginUser user = getLoginUser();
        if (user == null) {
            isAdmin = false;
        } else {
            isAdmin = user.isIsAdmin();
        }
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LoginUser getLoginUser() {
        try {
            loginUser = (LoginUser) JSFUtility.getSessionValue(AeHSConstants.ADMIN_USER);
        } catch (Exception e) {
            loginUser = null;
        }
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginUsername() {
        try {
            LoginUser user = getLoginUser();
            if (user == null) {
                loginUsername = "";
            } else {
                loginUsername = user.getUserScreenName();
            }
        } catch (Exception e) {
            loginUsername = null;
        }
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public SystemUser getSystemUser() {
        try {
            systemUser = (SystemUser) JSFUtility.getSessionValue(AeHSConstants.LOGIN_USER);
        } catch (Exception e) {
            systemUser = null;
        }
        return systemUser;
    }

    public adminPages getPages() {
        try {
            pages = (adminPages) JSFUtility.getSessionValue(AeHSConstants.ADMIN_PAGE_MANAGER);
        } catch (Exception e) {
            pages = null;
        }
        return pages;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

}
