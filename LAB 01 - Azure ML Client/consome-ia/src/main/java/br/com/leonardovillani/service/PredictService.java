package br.com.leonardovillani.service;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.com.leonardovillani.model.PredictRequest;

@Path("/score")
@RegisterRestClient(configKey = "predict-diabetes")
public interface PredictService {

    @POST
    Response getPrediction(@HeaderParam(HttpHeaders.AUTHORIZATION) String bearerToken, @HeaderParam(HttpHeaders.CONTENT_TYPE) String contentType, @RequestBody PredictRequest patient);

}
