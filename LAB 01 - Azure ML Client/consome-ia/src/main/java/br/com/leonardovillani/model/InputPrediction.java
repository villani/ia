package br.com.leonardovillani.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InputPrediction {

    @JsonProperty("WebServiceInput0")
    List<Patient> webServiceInput0;
    
}
