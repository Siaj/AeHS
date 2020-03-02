/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global axios */


const predBtn = document.getElementById('predict');

const predDate = document.getElementById('mlPredDate');
const predPlantStand = document.getElementById('mlPredPlantStand');
const predTemp = document.getElementById('mlTemp');
const predPrecip = document.getElementById('mlPrecip');

const predictedDisease = document.getElementById('mlPredictedDisease');
const predictedDiseaseDesc = document.getElementById('predDisDesc');
const predictedDiseasePres = document.getElementById('predDisPres');

let valPred;
let predValues = [];

predBtn.addEventListener('click', (event) => {
    event.preventDefault();
    predValues.splice(0, predValues.length);
    predValues.push(predDate.value);
    predValues.push(predPlantStand.value);
    predValues.push(predTemp.value);
    predValues.push(predPrecip.value);

    axReq();
    axReq2(0, 2, 2, 7);

//    predValues = [...predValues];
    console.log('Final Values: ', predValues);
    console.log('Result', valPred);
});

//this call has to be in a function in order to make a return on it
const axReq = () => {
    console.log(predPrecip.value);
    return axios.post('https://flask-ml-siaj.herokuapp.com', {
        Pclass: predPrecip.value,
        Age: predTemp.value,
        SibSp: predPlantStand.value,
        Fare: predDate.value
    })
            .then(response => {
                console.log('Main: ', response);
                console.log('Data: ', response.data);
                console.log('Value: ', response.data.results.results);
            })
            .catch(error => console.log(error));
};

const axReq2 = (precip, temp, plStand, date) => {
    console.log(predPrecip.value);
    return axios.post('https://flask-ml-siaj.herokuapp.com', {
        Pclass: precip,
        Age: temp,
        SibSp: plStand,
        Fare: date
    })
            .then(response => {
                console.log('Main2: ', response);
                console.log('Data2: ', response.data);
                console.log('Value2: ', response.data.results.results);
            })
            .catch(error => console.log(error));
};

const ax = function () {
    return axios.get('https://flask-ml-siaj.herokuapp.com/predict')
            .then(response => console.log('Axios Get: ', response))
            .catch(error => console.log(error));
};

const getPrediction = () => {
    console.log('ES6 Wroking fine here - writting to HTML page');
    console.log('Values: ', predValues);
    predictedDisease.value = 'From HTTP JS - Disease';
    predictedDiseaseDesc.value = 'From HTTP JS - Description';
    predictedDiseasePres.value = 'From HTTP JS - Prescription';
};

ax();
getPrediction();

