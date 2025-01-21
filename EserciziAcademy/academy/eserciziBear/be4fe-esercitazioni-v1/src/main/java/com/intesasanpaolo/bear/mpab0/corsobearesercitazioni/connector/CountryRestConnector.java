package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector;

import com.intesasanpaolo.bear.connector.bear.BearInternalConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.stereotype.Service;

@Service
public class CountryRestConnector extends BearInternalConnector<String, CountryResource, Long, CountryDTO> {
}
