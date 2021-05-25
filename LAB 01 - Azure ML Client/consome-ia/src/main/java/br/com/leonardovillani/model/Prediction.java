package br.com.leonardovillani.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Prediction {

    @JsonProperty("PatientID")
    Double patientID;

    @JsonProperty("DiabetesPrediction")
    Double diabetesPrediction;

    @JsonProperty("Probability")
    Double probability;
    
}
