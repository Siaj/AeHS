/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import javax.inject.Named;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "adminPages")
@RequestScoped
public class adminPages implements Serializable {

    private boolean renderWelcome = true;
    private boolean renderRegisterUser = false;
    private boolean renderRegisterFarmer = false;
    private boolean renderRegisterFarmDetails = false;
    private boolean renderMLPrediction = false;
    private boolean renderManagePrediction = false;
    private boolean renderAnalyticsSearch = false;
    private boolean renderFarmerInfoSearch = false;
    private boolean renderChangePassword = false;

    /**
     * Creates a new instance of adminPages
     */
    public adminPages() {
    }

    public void viewDashboard() {
        renderWelcome = true;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void registerSystemUser() {
        renderWelcome = false;
        renderRegisterUser = true;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void registerFarmer() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = true;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void registerFarmDetails() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = true;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void MLPredict() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = true;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void managePredictions() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = true;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void analyticsSearch() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = true;
        renderFarmerInfoSearch = false;
        renderChangePassword = false;
    }

    public void farmerSearch() {
        renderWelcome = false;
        renderRegisterUser = false;
        renderRegisterFarmer = false;
        renderRegisterFarmDetails = false;
        renderMLPrediction = false;
        renderManagePrediction = false;
        renderAnalyticsSearch = false;
        renderFarmerInfoSearch = true;
        renderChangePassword = false;
    }

//<editor-fold defaultstate="collapsed" desc="Encapsulation ~ getter & setter methods">
    public boolean isRenderWelcome() {
        return renderWelcome;
    }

    public void setRenderWelcome(boolean renderWelcome) {
        this.renderWelcome = renderWelcome;
    }

    public boolean isRenderRegisterUser() {
        return renderRegisterUser;
    }

    public void setRenderRegisterUser(boolean renderRegisterUser) {
        this.renderRegisterUser = renderRegisterUser;
    }

    public boolean isRenderRegisterFarmer() {
        return renderRegisterFarmer;
    }

    public void setRenderRegisterFarmer(boolean renderRegisterFarmer) {
        this.renderRegisterFarmer = renderRegisterFarmer;
    }

    public boolean isRenderRegisterFarmDetails() {
        return renderRegisterFarmDetails;
    }

    public void setRenderRegisterFarmDetails(boolean renderRegisterFarmDetails) {
        this.renderRegisterFarmDetails = renderRegisterFarmDetails;
    }

    public boolean isRenderMLPrediction() {
        return renderMLPrediction;
    }

    public void setRenderMLPrediction(boolean renderMLPrediction) {
        this.renderMLPrediction = renderMLPrediction;
    }

    public boolean isRenderManagePrediction() {
        return renderManagePrediction;
    }

    public void setRenderManagePrediction(boolean renderManagePrediction) {
        this.renderManagePrediction = renderManagePrediction;
    }

    public boolean isRenderAnalyticsSearch() {
        return renderAnalyticsSearch;
    }

    public void setRenderAnalyticsSearch(boolean renderAnalyticsSearch) {
        this.renderAnalyticsSearch = renderAnalyticsSearch;
    }

    public boolean isRenderFarmerInfoSearch() {
        return renderFarmerInfoSearch;
    }

    public void setRenderFarmerInfoSearch(boolean renderFarmerInfoSearch) {
        this.renderFarmerInfoSearch = renderFarmerInfoSearch;
    }

    public boolean isRenderChangePassword() {
        return renderChangePassword;
    }

    public void setRenderChangePassword(boolean renderChangePassword) {
        this.renderChangePassword = renderChangePassword;
    }
//</editor-fold>

}
