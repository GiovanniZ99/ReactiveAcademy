package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class CountryAsyncService extends BaseService {
    private final CountryEventConnector connector;

    private final CountryEventRequestTransformer eventRequestTransformer;

    private final CountryEventResponseTransformer eventResponseTransformer;

    public CountryAsyncService(CountryEventConnector connector, CountryEventRequestTransformer eventRequestTransformer, CountryEventResponseTransformer eventResponseTransformer) {
        this.connector = connector;
        this.eventRequestTransformer = eventRequestTransformer;
        this.eventResponseTransformer = eventResponseTransformer;
    }

    public Boolean getCountry(CountryLangDTO countryLangDTO) {
        return connector.call(countryLangDTO, eventRequestTransformer, eventResponseTransformer);
    }
}
