package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.rest.model.RestConnectorResponse;
import com.intesasanpaolo.bear.connector.rest.transformer.IRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CountryRestResponseTransformer implements IRestResponseTransformer<CountryDTO, CountryResource> {

    @Override
    public CountryResource transform(RestConnectorResponse<CountryDTO> response) {
        CountryDTO countryDTO = response.getResponse().getBody();
        CountryResource resource = new CountryResource();
        if (countryDTO != null){
            resource.setKey(countryDTO.getId());
            resource.setOraAggiornamento(Instant.now());
            resource.setLingua(countryDTO.getLanguage());
        }
        return resource;
    }
}
