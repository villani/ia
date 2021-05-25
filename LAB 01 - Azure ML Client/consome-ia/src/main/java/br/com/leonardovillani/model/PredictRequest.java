package br.com.leonardovillani.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PredictRequest {

    @JsonProperty("Inputs")
    InputPrediction inputs;
    
}