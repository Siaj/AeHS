/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import com.app.aehs.server.ejb.FarmDetailFacade;
import com.app.aehs.server.ejb.SystemUserFacade;
import com.app.aehs.server.entities.FarmDetail;
import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.web.commons.GenerateIDs;
import com.app.aehs.web.commons.JSFUtility;
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
@Named(value = "farmDetailsController")
@SessionScoped
public class farmDetailsController implements Serializable {

    /**
     * Creates a new instance of farmDetailsController
     */
    @Inject
    private FarmDetailFacade farmDetailFacade;
    @Inject
    private SystemUserFacade systemUserFacade;

    private SystemUser systemUser = new SystemUser();
    private FarmDetail farmDetail = new FarmDetail();

    private List<FarmDetail> listOfFarmDetails = new ArrayList<>();
    private transient DataModel<FarmDetail> farmDetailsDataModel;

    private String farmOwner;
    private String searchCriteria, searchText;
    private boolean renderSaveBtn = true;

    public farmDetailsController() {
    }

    public void saveFarmDetails() {
        String farmId = GenerateIDs.generateUsername("farm", farmDetail.getName()) + "-" + GenerateIDs.generateID();
        System.out.println("FId: " + farmId);

        farmDetail.setId(farmId);

        systemUser = systemUserFacade.find(farmOwner);
        System.out.println("Farm Owner: " + systemUser.getFirstname() + " " + systemUser.getLastname());
        if (systemUser == null) {
            JSFUtility.warnMessage("Requiured: ", "You need to select a farmer to associate with farm");
        } else {
            farmDetail.setOwner(systemUser);
            String farmDetailsSaved = farmDetailFacade.createFarmDetails(farmDetail);

            if (farmDetailsSaved != null) {
                resetFarmDetails();
                JSFUtility.infoMessage("Success: ", "Farm details have been successfully saved");
            } else {
                JSFUtility.infoMessage("Error: ", "An error occured while trying to save farm details. Pls contact Admin");
            }
        }
    }

    public void searchFarmDetails() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfFarmDetails = getListOfFarmDetails();
        } else {
            listOfFarmDetails = getListOfFarmDetails();
        }
    }

    public void resetFarmDetails() {
        systemUser = new SystemUser();
        farmOwner = "";
        farmDetail = new FarmDetail();

        renderSaveBtn = true;
    }

    public void cancelUpdate() {
        farmDetail = new FarmDetail();
        farmOwner = "";

        renderSaveBtn = true;
    }

    public void resetFarmDetailSearch() {
        searchCriteria = null;
        searchText = "";
        listOfFarmDetails = getListOfFarmDetails();
    }

    public void rowDataFarmDetails() {
//        farmDetail = new FarmDetail();
        farmDetail = farmDetailsDataModel.getRowData();
        systemUser = farmDetail.getOwner();

        farmOwner = String.valueOf(systemUser.getId());
        renderSaveBtn = false;
    }

    public void updateFarmDetails() {
        try {
//            systemUser = systemUserFacade.find(farmOwner);
//            if (systemUserFacade.find(farmOwner) == null) {
//                JSFUtility.warnMessage("Required: ", "A farmer needs to be selected before update can proceed");
//            } else if (farmDetail.getName().equals("") || farmDetail.getMainPlantation().equals("")
//                    || farmDetail.getLocation().equals("")) {
//                JSFUtility.warnMessage("Required: ", "All fields need to be defined before update can proceed");
//            } else {
            systemUser = systemUserFacade.find(farmOwner);
            farmDetail.setOwner(systemUser);
            boolean farmDetailsUpdated = farmDetailFacade.updateFarmDetails(farmDetail);

            if (farmDetailsUpdated) {
                resetFarmDetails();
                JSFUtility.infoMessage("Success: ", "Farm details was successfully updated");
            } else {
                JSFUtility.errorMessage("Error: ", "Update of farm details details failed, please try again");
            }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<FarmDetail> getListOfFarmDetails() {
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            listOfFarmDetails = farmDetailFacade.findAll();
        } else {
            listOfFarmDetails = farmDetailFacade.farmerDetailsFindByAttribute(getSearchCriteria(), getSearchText(), "STRING", true);
        }
        return listOfFarmDetails;
    }

    public void setListOfFarmDetails(List<FarmDetail> listOfFarmDetails) {
        this.listOfFarmDetails = listOfFarmDetails;
    }

    public DataModel<FarmDetail> getFarmDetailsDataModel() {
        farmDetailsDataModel = new ListDataModel<>(getListOfFarmDetails());
        return farmDetailsDataModel;
    }

    public void setFarmDetailsDataModel(DataModel<FarmDetail> farmDetailsDataModel) {
        this.farmDetailsDataModel = farmDetailsDataModel;
    }

    public FarmDetailFacade getFarmDetailFacade() {
        return farmDetailFacade;
    }

    public void setFarmDetailFacade(FarmDetailFacade farmDetailFacade) {
        this.farmDetailFacade = farmDetailFacade;
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

    public FarmDetail getFarmDetail() {
        return farmDetail;
    }

    public void setFarmDetail(FarmDetail farmDetail) {
        this.farmDetail = farmDetail;
    }

    public String getFarmOwner() {
        return farmOwner;
    }

    public void setFarmOwner(String farmOwner) {
        this.farmOwner = farmOwner;
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

    public boolean isRenderSaveBtn() {
        return renderSaveBtn;
    }

    public void setRenderSaveBtn(boolean renderSaveBtn) {
        this.renderSaveBtn = renderSaveBtn;
    }

}
