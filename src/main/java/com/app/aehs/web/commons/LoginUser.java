/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.commons;

import java.io.Serializable;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class LoginUser implements Serializable {

    private String userType;
    private String userScreenName = "";
    private String accessFor;
    private Object userLogin;
    private boolean isLogin = false;
    private boolean isAdmin = false;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserScreenName() {
//        System.out.println(userScreenName);
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getAccessFor() {
        return accessFor;
    }

    public void setAccessFor(String accessFor) {
        this.accessFor = accessFor;
    }

    public Object getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(Object userLogin) {
        this.userLogin = userLogin;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
