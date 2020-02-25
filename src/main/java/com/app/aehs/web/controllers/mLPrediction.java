/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.controllers;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void predict() throws IOException {
        try {
            // Logic to make request and get prediction
            URL url = new URL("");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException ex) {
            Logger.getLogger(mLPrediction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
