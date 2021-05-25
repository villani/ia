package br.com.leonardovillani.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Patient {

    @JsonProperty("PatientID")
    Long patientID;

    @JsonProperty("Pregnancies")
    Integer pregnancies;

    @JsonProperty("PlasmaGlucose")
    Integer plasmaGlucose;

    @JsonProperty("DiastolicBloodPressure")
    Integer diastolicBloodPressure;

    @JsonProperty("TricepsThickness")
    Integer tricepsThickness;

    @JsonProperty("SerumInsulin")
    Integer serumInsulin;

    @JsonProperty("BMI")
    Double bMI;

    @JsonProperty("DiabetesPedigree")
    Double diabetesPedigree;

    @JsonProperty("Age")
    Integer age;

}
