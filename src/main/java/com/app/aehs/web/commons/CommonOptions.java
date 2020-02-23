/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.commons;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.SelectItem;

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
    
    
}
