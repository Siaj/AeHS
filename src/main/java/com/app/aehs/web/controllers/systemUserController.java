/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import com.app.aehs.server.ejb.SystemUserFacade;
import com.app.aehs.server.ejb.UserRoleFacade;
import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.server.entities.UserRole;
import com.app.aehs.web.commons.GenerateIDs;
import com.app.aehs.web.commons.JSFUtility;
import com.app.aehs.web.commons.UserAccessController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "systemUserController")
@SessionScoped
public class systemUserController implements Serializable {

    /**
     * Creates a new instance of systemUserController
     */
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private UserRoleFacade userRoleFacade;

    private SystemUser systemUser = new SystemUser();
    private UserRole userRole = new UserRole();

    private List<SystemUser> listOfFarmers = new ArrayList<>();
    private DataModel<SystemUser> farmerDataTable;
    private List<SystemUser> listOfSystemUsers = new ArrayList<>();
    private transient DataModel<SystemUser> systemUserDataTable;

    private UserAccessController accessController = new UserAccessController();

    private String selectedRole;
    private String password, hashedPassword;
    private String lastname, firstname;
    private String userGroup, username;
    private boolean renderAfterSave = false;
    private String searchCriteria, searchText;
    private boolean renderSave = true;
    private boolean disableGroup = false;

    public systemUserController() {
    }

    public void saveUser() {
        lastname = systemUser.getLastname();
        firstname = systemUser.getFirstname();
        System.out.println(lastname + " " + firstname);

        password = GenerateIDs.generateUsername(lastname, firstname);
        hashedPassword = GenerateIDs.generateHash(password);
        String userId = GenerateIDs.generateID();
        System.out.println("Pass: " + password);
        System.out.println("HashedPass: " + hashedPassword);
        System.out.println("GenId: " + userId);

        systemUser.setId(userId);
        systemUser.setPassword(hashedPassword);
        systemUser.setAccountStatus("Active");

        userRole = userRoleFacade.find(selectedRole);
        System.out.println("Role: " + userRole.getId() + userRole.getRoleName());
        if (userRole == null) {
            JSFUtility.warnMessage("Required: ", "User role needs to be selected for save to be successful");
        } else {
            systemUser.setUserRole(userRole);
            // Find User by email & if exist - error (user already exist)

            String userSaveSuccess = systemUserFacade.systemUserCreate(systemUser);

            if (userSaveSuccess != null) {
                username = systemUser.getUsername();
                userGroup = userRole.getRoleName();
                // reset
                resetButton();
                JSFUtility.infoMessage("Success: ", "User Details have been successfully created");
                renderAfterSave = true;
            } else {
                JSFUtility.errorMessage("Error: ", "User details was not saved successfully. Try Again");
            }
        }

    }

    public void saveFarmer() {
        lastname = systemUser.getLastname();
        firstname = systemUser.getFirstname();
        System.out.println(lastname + " " + firstname);

        password = GenerateIDs.generateUsername(lastname, firstname);
        hashedPassword = GenerateIDs.generateHash(password);
        String userId = GenerateIDs.generateID();
        System.out.println("Pass: " + password);
        System.out.println("HashedPass: " + hashedPassword);
        System.out.println("GenId: " + userId);

        systemUser.setId(userId);
        systemUser.setUsername(password);
        systemUser.setPassword(password);
        systemUser.setAccountStatus("Active");

        userRole = userRoleFacade.find("3");
        System.out.println("Role: " + userRole.getId() + userRole.getRoleName());
        if (userRole == null) {
            JSFUtility.warnMessage("Required: ", "Setting of user role failed. Contact system admin");
        } else {
            systemUser.setUserRole(userRole);

            String userSaveSuccess = systemUserFacade.systemUserCreate(systemUser);

            if (userSaveSuccess != null) {
                // reset
                systemUser = new SystemUser();
                JSFUtility.infoMessage("Success: ", "Farmer Details have been successfully created");
            } else {
                JSFUtility.errorMessage("Error: ", "Farmer details was not saved successfully. Try Again");
            }
        }
    }

    public void updateFarmer() {
        try {
//            userRole = userRoleFacade.find("3");
//            systemUser.setUserRole(userRole);

            // Put constraints on the page to ensure user enters all the required data
            boolean updateFarmer = systemUserFacade.systemUserUpdate(getSystemUser());

            if (updateFarmer) {
                systemUser = new SystemUser();
                renderSave = true;
                JSFUtility.infoMessage("Success: ", "Details of farmer was successfully updated");
            } else {
                JSFUtility.errorMessage("Error: ", "Saving of farmer details failed, please try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSysUser() {
        try {
            userRole = userRoleFacade.find(selectedRole);

            if (userRole == null) {
                JSFUtility.warnMessage("Required: ", "User role needs to be defined before update can proceed");
                return;
            } else {
                systemUser.setUserRole(userRole);
            }

            if (systemUser.getFirstname() == null || systemUser.getLastname() == null || systemUser.getGender() == null
                    || systemUser.getPhoneNumber() == null || systemUser.getUsername() == null) {
                JSFUtility.warnMessage("Required: ", "All fields need to be defined before update can proceed");
                return;
            }

            boolean updatesystemUser = systemUserFacade.systemUserUpdate(getSystemUser());

            if (updatesystemUser) {
                resetButton();
                JSFUtility.infoMessage("Success: ", "Details of system user was successfully updated");
            } else {
                JSFUtility.errorMessage("Error: ", "Saving of system user's details failed, please try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetButton() {
        systemUser = new SystemUser();
        selectedRole = "";
        disableGroup = false;
        renderSave = true;
        renderAfterSave = false;
    }

    public void searchSystemUser() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfSystemUsers = getListOfSystemUsers();
        } else {
            listOfSystemUsers = getListOfSystemUsers();
        }
    }

    public void searchFarmer() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfFarmers = getListOfFarmers();
        } else {
            listOfFarmers = getListOfFarmers();
        }
    }

    public void resetSearch() {
        searchCriteria = null;
        searchText = "";
        listOfFarmers = getListOfFarmers();
        listOfSystemUsers = getListOfSystemUsers();
    }

    public void rowSelectDataFarmer() {
        systemUser = (SystemUser) farmerDataTable.getRowData();
        userRole = systemUser.getUserRole();
        renderSave = false;
    }

    public void rowSelectDataSysUser() {
        systemUser = (SystemUser) systemUserDataTable.getRowData();
        userRole = systemUser.getUserRole();
        selectedRole = String.valueOf(userRole.getId());

        disableGroup = accessController.getLoginUser().getUserLogin().equals(systemUser);

        renderSave = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Encapsulation: Getter and setter methods">
    public List<SystemUser> getListOfFarmers() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            listOfFarmers = systemUserFacade.systemUserGetAllFarmers(true);
        } else {
            listOfFarmers = systemUserFacade.farmerFindByAttribute(getSearchCriteria(), getSearchText(), "STRING", true);
        }

        return listOfFarmers;
    }

    public void setListOfFarmers(List<SystemUser> listOfFarmers) {
        this.listOfFarmers = listOfFarmers;
    }

    public DataModel<SystemUser> getFarmerDataTable() {
        farmerDataTable = new ListDataModel<>(getListOfFarmers());
        return farmerDataTable;
    }

    public void setFarmerDataTable(DataModel<SystemUser> farmerDataTable) {
        this.farmerDataTable = farmerDataTable;
    }

    public List<SystemUser> getListOfSystemUsers() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            listOfSystemUsers = systemUserFacade.systemUserGetAll(true);
        } else {
            listOfSystemUsers = systemUserFacade.systemUserFindByAttribute(getSearchCriteria(), getSearchText(), "STRING", true);
        }
        return listOfSystemUsers;
    }

    public void setListOfSystemUsers(List<SystemUser> listOfSystemUsers) {
        this.listOfSystemUsers = listOfSystemUsers;
    }

    public DataModel<SystemUser> getSystemUserDataTable() {
        systemUserDataTable = new ListDataModel<>(getListOfSystemUsers());
        return systemUserDataTable;
    }

    public void setSystemUserDataTable(DataModel<SystemUser> systemUserDataTable) {
        this.systemUserDataTable = systemUserDataTable;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public boolean isRenderAfterSave() {
        return renderAfterSave;
    }

    public void setRenderAfterSave(boolean renderAfterSave) {
        this.renderAfterSave = renderAfterSave;
    }

    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public UserRoleFacade getUserRoleFacade() {
        return userRoleFacade;
    }

    public void setUserRoleFacade(UserRoleFacade userRoleFacade) {
        this.userRoleFacade = userRoleFacade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRenderSave() {
        return renderSave;
    }

    public void setRenderSave(boolean renderSave) {
        this.renderSave = renderSave;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public boolean isDisableGroup() {
        return disableGroup;
    }

    public void setDisableGroup(boolean disableGroup) {
        this.disableGroup = disableGroup;
    }

//</editor-fold>
}
