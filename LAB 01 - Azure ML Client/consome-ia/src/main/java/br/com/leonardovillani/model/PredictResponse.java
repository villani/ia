package br.com.leonardovillani.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PredictResponse {

    @JsonProperty("Results")
    OutputPrediction results;
    
}