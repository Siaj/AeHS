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
import com.app.aehs.server.ejb.TemperatureFacade;
import com.app.aehs.server.entities.Diseases;
import com.app.aehs.server.entities.FarmDetail;
import com.app.aehs.server.entities.MlPrediction;
import com.app.aehs.server.entities.PlantStand;
import com.app.aehs.server.entities.Precip;
import com.app.aehs.server.entities.PredDate;
import com.app.aehs.server.entities.Temperature;
import com.app.aehs.web.commons.GenerateIDs;
import com.app.aehs.web.commons.JSFUtility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "mLPrediction")
@SessionScoped
public class mLPrediction implements Serializable {

    @Inject
    private MlPredictionFacade predictionFacade;
    @Inject
    private DiseasesFacade diseasesFacade;
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

    private MlPrediction mlPrediction = new MlPrediction();
    private Diseases diseases = new Diseases();
    private FarmDetail farmDetail = new FarmDetail();
    private PredDate predDate = new PredDate();
    private Temperature predTemp = new Temperature();
    private Precip predPrecip = new Precip();
    private PlantStand predPlantStand = new PlantStand();

    private List<MlPrediction> MLPredictionList = new ArrayList<>();
    private transient DataModel<MlPrediction> MLPredDataTable;

    private String date = "";
    private String plantStand = "";
    private String precip = "";
    private String temp = "";

    private String farmDetailSelected;

    private boolean renderPrediction = false;
    private boolean renderManagePrediction = false;

    /**
     * Creates a new instance of mLPrediction
     */
    public mLPrediction() {
    }

    public void predict() throws IOException {
        URL urlForGet = new URL("https://aehs-ml-predict.herokuapp.com/predict");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGet.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            String myRes = response.toString();
            final JSONObject jSONObject = new JSONObject(myRes);
            System.out.println(jSONObject.getJSONObject("results"));
            System.out.println(jSONObject.getJSONObject("results").getJSONObject("data"));
            System.out.println(jSONObject.getJSONObject("results").getJSONObject("data").getInt("Fare"));
            System.out.println(jSONObject.getJSONObject("results").getInt("results"));
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
            JSFUtility.errorMessage("Error: ", "An error occurred while trying to get data from server. Please contact IT");
        }

//        System.out.println("I have been clicked to predict...");
//        System.out.println(date + " " + plantStand);
//        System.out.println(precip + " " + temp);
        renderPrediction = true;
    }

    public void predictPost() throws IOException {
        URL urlForGet = new URL("https://aehs-ml-predict.herokuapp.com/");
        HttpURLConnection conection = (HttpURLConnection) urlForGet.openConnection();
        conection.setRequestMethod("POST");
        conection.setRequestProperty("Content-Type", "application/json; utf-8");
        conection.setRequestProperty("Accept", "application/json");
        conection.setDoOutput(true);

//        String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
        String myData = "{\"precip\":" + precip + ",\"temp\":" + temp + ",\"plant stand\":" + plantStand + ",\"date\":" + date + "}";
        System.out.println("MyData: " + myData);

        try (OutputStream os = conection.getOutputStream()) {
            byte[] input = myData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int resCode = conection.getResponseCode();
        System.out.println("Response Code: " + resCode);

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("JSON Result String: " + response.toString());
            String myRes = response.toString();
            final JSONObject jSONObject = new JSONObject(myRes);
            System.out.println("Result Object: " + jSONObject.getJSONObject("results"));
            System.out.println("Result Item: " + jSONObject.getJSONObject("results").getString("results"));

            String diseaseCode = jSONObject.getJSONObject("results").getString("results");
            String[] mc = diseaseCode.split("");
            System.out.println("MC: " + mc[1]);
            String diseaseId = mc[1];

            diseases = diseasesFacade.find(diseaseId);
        }

        if (diseases != null) {
            renderPrediction = true;
        } else {
            JSFUtility.errorMessage("Error: ", "Could not get the disease from storage. Please contact IT for resolution");
        }
    }

    public void savePrediction() {
        System.out.println(precip + " " + temp);
        System.out.println(date + " " + plantStand);

//        LocalDate dateTime = LocalDate.now();
//        DateTimeFormatter formateEntry = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.sql.Date dateObj = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//        System.out.println("Date Now: " + dateObj);

        String predId = "pred-" + GenerateIDs.generateID();
//        System.out.println("ID: " + predId);

        predPrecip = precipFacade.find(precip);
        predTemp = temperatureFacade.find(temp);
        predPlantStand = plantStandFacade.find(plantStand);
        predDate = dateFacade.find(date);

        // Set the Farm details to {null} for now!
        mlPrediction.setId(predId);
        mlPrediction.setDate(dateObj);
        mlPrediction.setInputParam1(predPrecip.getId());
        mlPrediction.setInputParam2(predTemp.getId());
        mlPrediction.setInputParam3(predPlantStand.getId());
        mlPrediction.setInputParam4(predDate.getId());

        mlPrediction.setDiseases(diseases.getDiseaseId());
        mlPrediction.setDiseaseName(diseases.getDescription());

        if (farmDetailSelected == null) {
            JSFUtility.warnMessage("Note: ", "Associated Farm Details need to be selected before save could proceed");
            return;
        } else {
            farmDetail = farmDetailFacade.find(farmDetailSelected);
        }

        if (farmDetail != null) {
            mlPrediction.setFarmDetail(farmDetail);
        } else {
            System.out.println("Could not fetch farm details from database");
            return;
        }

        String createdPred = predictionFacade.createPrediction(mlPrediction);
        if (createdPred != null) {
            JSFUtility.infoMessage("Success: ", "Prediction details have been saved successfully");
            resetPrediction();
        } else {
            JSFUtility.errorMessage("Error: ", "An error occurred while trying to save prediction. Contact the IT team");
        }
    }

    public void resetPrediction() {
        date = "";
        plantStand = "";
        precip = "";
        temp = "";
        farmDetailSelected = "";

        predPrecip = null;
        predTemp = null;
        predPlantStand = null;
        predDate = null;

        farmDetail = null;
        diseases = null;
        renderPrediction = false;
        renderManagePrediction = false;
    }

    public void selectMLpredictionRow() {
        mlPrediction = (MlPrediction) MLPredDataTable.getRowData();
        predPrecip = precipFacade.find(mlPrediction.getInputParam1());
        predTemp = temperatureFacade.find(mlPrediction.getInputParam2());
        predPlantStand = plantStandFacade.find(mlPrediction.getInputParam3());
        predDate = dateFacade.find(mlPrediction.getInputParam4());
        diseases = diseasesFacade.find(mlPrediction.getDiseases());

        farmDetail = mlPrediction.getFarmDetail();

        renderManagePrediction = true;
    }

//    public void cancelPredView() {
//        mlPrediction = null;
//        farmDetail = null;
//        diseases = null;
//
//        predPrecip = null;
//        predTemp = null;
//        predPlantStand = null;
//        predDate = null;
//
//        renderManagePrediction = false;
//    }
    public void deletePrediction() {
        boolean deleted = predictionFacade.deletePrediction(mlPrediction);

        if (deleted) {
            // delete success
            JSFUtility.infoMessage("Success: ", "Prediction data was successfully deleted.");
            resetPrediction();
        } else {
            JSFUtility.errorMessage("Error: ", "Was unable to delete prediction data. Please contact IT");
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlantStand() {
        return plantStand;
    }

    public void setPlantStand(String plantStand) {
        this.plantStand = plantStand;
    }

    public String getPrecip() {
        return precip;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public MlPrediction getMlPrediction() {
        return mlPrediction;
    }

    public void setMlPrediction(MlPrediction mlPrediction) {
        this.mlPrediction = mlPrediction;
    }

    public boolean isRenderPrediction() {
        return renderPrediction;
    }

    public void setRenderPrediction(boolean renderPrediction) {
        this.renderPrediction = renderPrediction;
    }

    public List<MlPrediction> getMLPredictionList() {
        MLPredictionList = predictionFacade.mlPredictionsFindAll();
        return MLPredictionList;
    }

    public void setMLPredictionList(List<MlPrediction> MLPredictionList) {
        this.MLPredictionList = MLPredictionList;
    }

    public DataModel<MlPrediction> getMLPredDataTable() {
        MLPredDataTable = new ListDataModel<>(getMLPredictionList());
        return MLPredDataTable;
    }

    public void setMLPredDataTable(DataModel<MlPrediction> MLPredDataTable) {
        this.MLPredDataTable = MLPredDataTable;
    }

    public MlPredictionFacade getPredictionFacade() {
        return predictionFacade;
    }

    public void setPredictionFacade(MlPredictionFacade predictionFacade) {
        this.predictionFacade = predictionFacade;
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

    public void setFarmDetail(FarmDetail farmDetail) {
        this.farmDetail = farmDetail;
    }

    public boolean isRenderManagePrediction() {
        return renderManagePrediction;
    }

    public void setRenderManagePrediction(boolean renderManagePrediction) {
        this.renderManagePrediction = renderManagePrediction;
    }

    public DiseasesFacade getDiseasesFacade() {
        return diseasesFacade;
    }

    public void setDiseasesFacade(DiseasesFacade diseasesFacade) {
        this.diseasesFacade = diseasesFacade;
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

    public PredDateFacade getDateFacade() {
        return dateFacade;
    }

    public void setDateFacade(PredDateFacade dateFacade) {
        this.dateFacade = dateFacade;
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

    public FarmDetailFacade getFarmDetailFacade() {
        return farmDetailFacade;
    }

    public void setFarmDetailFacade(FarmDetailFacade farmDetailFacade) {
        this.farmDetailFacade = farmDetailFacade;
    }

    public String getFarmDetailSelected() {
        return farmDetailSelected;
    }

    public void setFarmDetailSelected(String farmDetailSelected) {
        this.farmDetailSelected = farmDetailSelected;
    }

}
