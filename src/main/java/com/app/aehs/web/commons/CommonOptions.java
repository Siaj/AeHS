/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.commons;

import com.app.aehs.server.ejb.FarmDetailFacade;
import com.app.aehs.server.ejb.SystemUserFacade;
import com.app.aehs.server.ejb.UserRoleFacade;
import com.app.aehs.server.entities.FarmDetail;
import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.server.entities.UserRole;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "commonOptions")
@SessionScoped
public class CommonOptions implements Serializable {

    /**
     * Creates a new instance of CommonOptions
     */
    private SelectItem[] genderOptions;
    private SelectItem[] activeStatusOptions;
    private SelectItem[] userRoleOptions;
    private SelectItem[] systemUsersOptions;
    private SelectItem[] predictParam1Options;
    private SelectItem[] predictParam2Options;
    private SelectItem[] predictParam3Options;
    private SelectItem[] predictParam4Options;
    private SelectItem[] predictParam5Options;
    private SelectItem[] farmDetailsOption;

    @Inject
    private UserRoleFacade userRoleFacade;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private FarmDetailFacade farmDetailFacade;

    public CommonOptions() {
    }

    public SelectItem[] getGenderOptions() {
        genderOptions = new SelectItem[3];
        genderOptions[0] = new SelectItem("", "---Select Gender---", "Select One");
        genderOptions[1] = new SelectItem("Male", "Male", "Select Male");
        genderOptions[2] = new SelectItem("Female", "Female", "Select Female");
        return genderOptions;
    }

    public void setGenderOptions(SelectItem[] genderOptions) {
        this.genderOptions = genderOptions;
    }

    public SelectItem[] getActiveStatusOptions() {
        activeStatusOptions = new SelectItem[3];
        activeStatusOptions[0] = new SelectItem("", "---Select One---");
        activeStatusOptions[1] = new SelectItem("Active", "Active");
        activeStatusOptions[2] = new SelectItem("Inactive", "Inactive");
        return activeStatusOptions;
    }

    public void setActiveStatusOptions(SelectItem[] activeStatusOptions) {
        this.activeStatusOptions = activeStatusOptions;
    }

    public SelectItem[] getUserRoleOptions() {
         userRoleOptions = new SelectItem[userRoleFacade.findAll().size() + 1];
        userRoleOptions[0] = new SelectItem("", "---Select One---");
        int c = 1;

        try {
            for (UserRole d : userRoleFacade.findAll()) {
                userRoleOptions[c] = new SelectItem(d.getId(), d.getRoleName());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRoleOptions;
    }

    public void setUserRoleOptions(SelectItem[] userRoleOptions) {
        this.userRoleOptions = userRoleOptions;
    }

    public SelectItem[] getFarmDetailsOption() {
        farmDetailsOption = new SelectItem[farmDetailFacade.findAll().size() + 1];
//        System.out.println("My Size: " + systemUserFacade.systemUserGetAllFarmers(true));
        farmDetailsOption[0] = new SelectItem("", "---Select Farm---");
        int c = 1;

        try {
            for (FarmDetail u : farmDetailFacade.findAll()) {
                farmDetailsOption[c] = new SelectItem(u.getId(), u.getName() + " - "
                        + u.getName() + "(" + u.getId() + ")");
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return farmDetailsOption;
    }

    public void setFarmDetailsOption(SelectItem[] farmDetailsOption) {
        this.farmDetailsOption = farmDetailsOption;
    }

    public SelectItem[] getSystemUsersOptions() {
//        Shall change query to fetch only user who have role defined as FARMER = DONE
        systemUsersOptions = new SelectItem[systemUserFacade.systemUserGetAllFarmers(true).size() + 1];
        System.out.println("My Size: " + systemUserFacade.systemUserGetAllFarmers(true));
        systemUsersOptions[0] = new SelectItem("", "---Select Owner---");
        int c = 1;

        try {
            for (SystemUser u : systemUserFacade.systemUserGetAllFarmers(true)) {
                systemUsersOptions[c] = new SelectItem(u.getId(), u.getFirstname() + " - "
                        + u.getUsername() + "(" + u.getId() + ")");
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemUsersOptions;
    }

    public void setSystemUsersOptions(SelectItem[] systemUsersOptions) {
        this.systemUsersOptions = systemUsersOptions;
    }

    public UserRoleFacade getUserRoleFacade() {
        return userRoleFacade;
    }

    public void setUserRoleFacade(UserRoleFacade userRoleFacade) {
        this.userRoleFacade = userRoleFacade;
    }

    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public SelectItem[] getPredictParam1Options() {
        predictParam1Options = new SelectItem[8];
        predictParam1Options[0] = new SelectItem("", "---Select One---");
        predictParam1Options[1] = new SelectItem(0, "April");
        predictParam1Options[2] = new SelectItem(1, "May");
        predictParam1Options[3] = new SelectItem(2, "June");
        predictParam1Options[4] = new SelectItem(3, "July");
        predictParam1Options[5] = new SelectItem(4, "August");
        predictParam1Options[6] = new SelectItem(5, "September");
        predictParam1Options[7] = new SelectItem(6, "October");

        return predictParam1Options;
    }

    public void setPredictParam1Options(SelectItem[] predictParam1Options) {
        this.predictParam1Options = predictParam1Options;
    }

    public SelectItem[] getPredictParam2Options() {
        predictParam2Options = new SelectItem[3];
        predictParam2Options[0] = new SelectItem("", "---Select One---");
        predictParam2Options[1] = new SelectItem(0, "Normal");
        predictParam2Options[2] = new SelectItem(1, "Less-Than Normal");

        return predictParam2Options;
    }

    public void setPredictParam2Options(SelectItem[] predictParam2Options) {
        this.predictParam2Options = predictParam2Options;
    }

    public SelectItem[] getPredictParam3Options() {
        predictParam3Options = new SelectItem[4];
        predictParam3Options[0] = new SelectItem("", "---Select One---");
        predictParam3Options[1] = new SelectItem(0, "Less-Than Normal");
        predictParam3Options[2] = new SelectItem(1, "Normal");
        predictParam3Options[3] = new SelectItem(2, "Greater-Than Normal");

        return predictParam3Options;
    }

    public void setPredictParam3Options(SelectItem[] predictParam3Options) {
        this.predictParam3Options = predictParam3Options;
    }

    public SelectItem[] getPredictParam4Options() {
        predictParam4Options = new SelectItem[4];
        predictParam4Options[0] = new SelectItem("", "---Select One---");
        predictParam4Options[1] = new SelectItem(0, "Less-Than Normal");
        predictParam4Options[2] = new SelectItem(1, "Normal");
        predictParam4Options[3] = new SelectItem(2, "Greater-Than Normal");

        return predictParam4Options;
    }

    public void setPredictParam4Options(SelectItem[] predictParam4Options) {
        this.predictParam4Options = predictParam4Options;
    }

    public SelectItem[] getPredictParam5Options() {
        predictParam5Options = new SelectItem[3];
        predictParam5Options[0] = new SelectItem("", "---Select One---");
        predictParam5Options[1] = new SelectItem("1", "System Administrator");
        predictParam5Options[2] = new SelectItem("2", "Extension Officer");

        return predictParam5Options;
    }

    public void setPredictParam5Options(SelectItem[] predictParam5Options) {
        this.predictParam5Options = predictParam5Options;
    }

}
