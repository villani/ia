package br.com.leonardovillani.resource;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.leonardovillani.model.PredictRequest;
import br.com.leonardovillani.service.PredictService;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
@Path("/predict")
public class PredictResource {

    @Inject
    @RestClient
    PredictService predictService;
    
    @ConfigProperty(name = "predict-diabetes.token")
    String bearerToken;

    @POST
    @Produces()
    public Response getPrediction(@RequestBody PredictRequest patient) {
        Response prediction = predictService.getPrediction("Bearer " + bearerToken, MediaType.APPLICATION_JSON, patient);

        log.info(prediction);

        return prediction;
    
    }
    
}
