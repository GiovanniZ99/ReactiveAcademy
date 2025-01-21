package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.CountryRestResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRestConnector countryRestConnector;

    private final CountryRestResponseTransformer countryRestResponseTransformer;

    private final CountryRestRequestTransformer countryRestRequestTransformer;

    public CountryService(CountryRestConnector countryRestConnector,
                          CountryRestResponseTransformer countryRestResponseTransformer,
                          CountryRestRequestTransformer countryRestRequestTransformer) {
        this.countryRestConnector = countryRestConnector;
        this.countryRestResponseTransformer = countryRestResponseTransformer;
        this.countryRestRequestTransformer = countryRestRequestTransformer;
    }
    public CountryResource getCountry(String id) {
        return countryRestConnector.call(id, countryRestRequestTransformer, countryRestResponseTransformer);
    }
}
