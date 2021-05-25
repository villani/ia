package br.com.leonardovillani.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OutputPrediction {

    @JsonProperty("WebServiceOutput0")
    List<Prediction> webServiceOutput0;
    
}
