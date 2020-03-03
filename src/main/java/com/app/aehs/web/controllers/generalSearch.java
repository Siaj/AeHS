/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import com.app.aehs.server.ejb.DiseasesFacade;
import com.app.aehs.server.ejb.FarmDetailFacade;
import com.app.aehs.server.ejb.MlPredictionFacade;
import com.app.aehs.server.ejb.PlantStandFacade;
import com.app.aehs.server.ejb.PrecipFacade;
import com.app.aehs.server.ejb.PredDateFacade;
import com.app.aehs.server.ejb.SystemUserFacade;
import com.app.aehs.server.ejb.TemperatureFacade;
import com.app.aehs.server.entities.Diseases;
import com.app.aehs.server.entities.FarmDetail;
import com.app.aehs.server.entities.MlPrediction;
import com.app.aehs.server.entities.PlantStand;
import com.app.aehs.server.entities.Precip;
import com.app.aehs.server.entities.PredDate;
import com.app.aehs.server.entities.SystemUser;
import com.app.aehs.server.entities.Temperature;
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
@Named(value = "generalSearch")
@SessionScoped //Change scope to request
public class generalSearch implements Serializable {

    /**
     * Creates a new instance of generalSearch
     */
    @Inject
    private MlPredictionFacade mlPredictionFacade;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private PrecipFacade precipFacade;
    @Inject
    private TemperatureFacade temperatureFacade;
    @Inject
    private PlantStandFacade plantStandFacade;
    @Inject
    private PredDateFacade dateFacade;
    @Inject
    private FarmDetailFacade farmDetailFacade;
    @Inject
    private DiseasesFacade diseasesFacade;

    private MlPrediction mlPrediction = new MlPrediction();
    private SystemUser systemUser = new SystemUser();
    private Diseases diseases = new Diseases();
    private FarmDetail farmDetail = new FarmDetail();
    private PredDate predDate = new PredDate();
    private Temperature predTemp = new Temperature();
    private Precip predPrecip = new Precip();
    private PlantStand predPlantStand = new PlantStand();

    private List<MlPrediction> listOfMlPredictions = new ArrayList<>();
    private transient DataModel<MlPrediction> mlPredictionsDataTable;
    private List<SystemUser> listOfFarmers = new ArrayList<>();
    private transient DataModel<SystemUser> farmersDataTable;
    private List<FarmDetail> listOfFarmDetails = new ArrayList<>();
    private transient DataModel<FarmDetail> farmOwnerFarmDataTable;

    private String searchCriteria, searchText;
    private boolean renderSearchedView = false;
    private String userId;

    public generalSearch() {
    }

    public void searchPredictions() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfMlPredictions = getListOfMlPredictions();
        } else {
            listOfMlPredictions = getListOfMlPredictions();
        }
    }

    public void searchFarmer() {
        if (searchCriteria == null || searchText.equals("") || searchCriteria.equals("null")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfFarmers = getListOfFarmers();
        } else {
            System.out.println("Here for search");
            listOfFarmers = getListOfFarmers();
        }

    }

    public void resetSearch() {
        searchCriteria = null;
        searchText = "";
        renderSearchedView = false;
        listOfMlPredictions = getListOfMlPredictions();
        listOfFarmers = getListOfFarmers();
    }

    public void rowSelectPrediction() {
        mlPrediction = (MlPrediction) mlPredictionsDataTable.getRowData();

        predPrecip = precipFacade.find(mlPrediction.getInputParam1());
        predTemp = temperatureFacade.find(mlPrediction.getInputParam2());
        predPlantStand = plantStandFacade.find(mlPrediction.getInputParam3());
        predDate = dateFacade.find(mlPrediction.getInputParam4());
        diseases = diseasesFacade.find(mlPrediction.getDiseases());

        farmDetail = mlPrediction.getFarmDetail();

        renderSearchedView = true;
    }

    public void rowSelectFarmer() {
        systemUser = (SystemUser) farmersDataTable.getRowData();
        userId = String.valueOf(systemUser.getId());
        System.out.println("User Id: " + userId);

        listOfFarmDetails = getListOfFarmDetails();

        renderSearchedView = true;

//        listOfFarmDetails = systemUser.getFarmDetailList();
    }

    public void resetSelection() {
        predPrecip = null;
        predTemp = null;
        predPlantStand = null;
        predDate = null;

        mlPrediction = new MlPrediction();
        systemUser = new SystemUser();
        farmDetail = null;
        diseases = null;

        renderSearchedView = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Encapsulation: getter and setter methods">
    public MlPredictionFacade getMlPredictionFacade() {
        return mlPredictionFacade;
    }

    public void setMlPredictionFacade(MlPredictionFacade mlPredictionFacade) {
        this.mlPredictionFacade = mlPredictionFacade;
    }

    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public MlPrediction getMlPrediction() {
        return mlPrediction;
    }

    public void setMlPrediction(MlPrediction mlPrediction) {
        this.mlPrediction = mlPrediction;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public Diseases getDiseases() {
        return diseases;
    }

    public void setDiseases(Diseases diseases) {
        this.diseases = diseases;
    }

    public FarmDetail getFarmDetail() {
        return farmDetail;
    }

    public List<FarmDetail> getListOfFarmDetails() {
        // Find farm details based on owner
        if (!userId.equals("")) {
            listOfFarmDetails = farmDetailFacade.farmerDetailsFindByOwner("owner.id", getUserId(), "STRING", true);
        }
        return listOfFarmDetails;
    }

    public void setListOfFarmDetails(List<FarmDetail> listOfFarmDetails) {
        this.listOfFarmDetails = listOfFarmDetails;
    }

    public DataModel<FarmDetail> getFarmOwnerFarmDataTable() {
        farmOwnerFarmDataTable = new ListDataModel<>(getListOfFarmDetails());
        return farmOwnerFarmDataTable;
    }

    public void setFarmOwnerFarmDataTable(DataModel<FarmDetail> farmOwnerFarmDataTable) {
        this.farmOwnerFarmDataTable = farmOwnerFarmDataTable;
    }

    public void setFarmDetail(FarmDetail farmDetail) {
        this.farmDetail = farmDetail;
    }

    public List<MlPrediction> getListOfMlPredictions() {
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            listOfMlPredictions = mlPredictionFacade.findAll();
        } else {
            listOfMlPredictions = mlPredictionFacade.mlPredictionFindByAttribute(getSearchCriteria(), getSearchText(), "STRING", true);
        }
        return listOfMlPredictions;
    }

    public void setListOfMlPredictions(List<MlPrediction> listOfMlPredictions) {
        this.listOfMlPredictions = listOfMlPredictions;
    }

    public DataModel<MlPrediction> getMlPredictionsDataTable() {
        mlPredictionsDataTable = new ListDataModel<>(getListOfMlPredictions());
        return mlPredictionsDataTable;
    }

    public void setMlPredictionsDataTable(DataModel<MlPrediction> mlPredictionsDataTable) {
        this.mlPredictionsDataTable = mlPredictionsDataTable;
    }

    public List<SystemUser> getListOfFarmers() {
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            listOfFarmers = systemUserFacade.systemUserGetAllFarmers(true);
        } else {
            listOfFarmers = systemUserFacade.farmerFindByAttribute(getSearchCriteria(), getSearchText(), "STRING", true);
        }
        return listOfFarmers;
    }

    public void setListOfFarmers(List<SystemUser> listOfFarmers) {
        this.listOfFarmers = listOfFarmers;
    }

    public DataModel<SystemUser> getFarmersDataTable() {
        farmersDataTable = new ListDataModel<>(getListOfFarmers());
        return farmersDataTable;
    }

    public void setFarmersDataTable(DataModel<SystemUser> farmersDataTable) {
        this.farmersDataTable = farmersDataTable;
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

    public boolean isRenderSearchedView() {
        return renderSearchedView;
    }

    public void setRenderSearchedView(boolean renderSearchedView) {
        this.renderSearchedView = renderSearchedView;
    }

    public PredDate getPredDate() {
        return predDate;
    }

    public void setPredDate(PredDate predDate) {
        this.predDate = predDate;
    }

    public Temperature getPredTemp() {
        return predTemp;
    }

    public void setPredTemp(Temperature predTemp) {
        this.predTemp = predTemp;
    }

    public Precip getPredPrecip() {
        return predPrecip;
    }

    public void setPredPrecip(Precip predPrecip) {
        this.predPrecip = predPrecip;
    }

    public PlantStand getPredPlantStand() {
        return predPlantStand;
    }

    public void setPredPlantStand(PlantStand predPlantStand) {
        this.predPlantStand = predPlantStand;
    }

    public PrecipFacade getPrecipFacade() {
        return precipFacade;
    }

    public void setPrecipFacade(PrecipFacade precipFacade) {
        this.precipFacade = precipFacade;
    }

    public TemperatureFacade getTemperatureFacade() {
        return temperatureFacade;
    }

    public void setTemperatureFacade(TemperatureFacade temperatureFacade) {
        this.temperatureFacade = temperatureFacade;
    }

    public PlantStandFacade getPlantStandFacade() {
        return plantStandFacade;
    }

    public void setPlantStandFacade(PlantStandFacade plantStandFacade) {
        this.plantStandFacade = plantStandFacade;
    }

    public FarmDetailFacade getFarmDetailFacade() {
        return farmDetailFacade;
    }

    public void setFarmDetailFacade(FarmDetailFacade farmDetailFacade) {
        this.farmDetailFacade = farmDetailFacade;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PredDateFacade getDateFacade() {
        return dateFacade;
    }

    public void setDateFacade(PredDateFacade dateFacade) {
        this.dateFacade = dateFacade;
    }
//</editor-fold>

}
