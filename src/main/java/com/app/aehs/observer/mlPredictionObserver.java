/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.observer;

import com.app.aehs.server.entities.MlPrediction;
import com.app.aehs.web.controllers.quantifiers.Create;
import com.app.aehs.web.controllers.quantifiers.Delete;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "mlPredictionObserver")
@ApplicationScoped
public class mlPredictionObserver {

    String GENERAL_MLPRED_SEARCH_CHANNEL = "/mlDataGeneralSearh";
    String ML_PREDICTION_CHANNEL = "/adminMlPrediction";

    public mlPredictionObserver() {
    }

    public void onMlPredictionDataSave(@Observes @Create MlPrediction mlPrediction) {
        String summary = "New ML Prediction";
        String details = "A new prediction details has been registered";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_MLPRED_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

        eventBus.publish(ML_PREDICTION_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
    
    public void onMlPredictionDataDelete(@Observes @Delete MlPrediction mlPrediction) {
        String summary = "Deleted ML Prediction";
        String details = "A new prediction details has been deleted";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_MLPRED_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

        eventBus.publish(ML_PREDICTION_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
