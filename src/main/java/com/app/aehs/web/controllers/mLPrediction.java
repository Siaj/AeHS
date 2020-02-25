/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "mLPrediction")
@SessionScoped
public class mLPrediction implements Serializable {

    /**
     * Creates a new instance of mLPrediction
     */
    public mLPrediction() {
    }
    
    public void predict() {
    // Logic to make request and get prediction 
    }
    
}
