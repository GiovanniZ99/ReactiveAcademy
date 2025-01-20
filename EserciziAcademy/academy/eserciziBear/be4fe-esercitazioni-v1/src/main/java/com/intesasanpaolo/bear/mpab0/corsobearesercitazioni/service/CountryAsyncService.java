package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event.CountryEventResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import org.springframework.stereotype.Service;

@Service
public class CountryAsyncService {
    private final CountryEventConnector connector;

    private final CountryEventRequestTransformer eventRequestTransformer;

    private final CountryEventResponseTransformer eventResponseTransformer;

    public CountryAsyncService(CountryEventConnector connector, CountryEventRequestTransformer eventRequestTransformer, CountryEventResponseTransformer eventResponseTransformer) {
        this.connector = connector;
        this.eventRequestTransformer = eventRequestTransformer;
        this.eventResponseTransformer = eventResponseTransformer;
    }
    public Boolean getCountry(String language) {
        return connector.call(new CountryLangDTO(language), eventRequestTransformer, eventResponseTransformer);
    }
}
