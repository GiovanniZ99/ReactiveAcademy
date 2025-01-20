package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorRequest;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestRequestTransformer;
import org.springframework.stereotype.Service;

@Service
public class CountryRestRequestTransformer<String> implements IRestRequestTransformer<String,Integer> {

    @Override
    public RestConnectorRequest<Integer> transform(String om, Object... args) {
        RestConnectorRequest<Integer> restConnectorRequest = new RestConnectorRequest<>();
        restConnectorRequest.setRequest(Integer.parseInt((java.lang.String) om));
        return restConnectorRequest;
    }
}