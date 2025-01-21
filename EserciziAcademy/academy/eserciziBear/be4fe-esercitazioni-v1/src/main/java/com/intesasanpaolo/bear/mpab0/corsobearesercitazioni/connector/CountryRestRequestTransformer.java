package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorRequest;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestRequestTransformer;
import org.springframework.stereotype.Service;

@Service
public class CountryRestRequestTransformer implements IRestRequestTransformer<String,Long> {

    @Override
    public RestConnectorRequest<Long> transform(String om, Object... args) {
        RestConnectorRequest<Long> restConnectorRequest = new RestConnectorRequest<>();
        restConnectorRequest.setRequest(Long.valueOf(om));
        return restConnectorRequest;
    }
}