package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event;

import com.intesasanpaolo.bear.event.BaseEventConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import org.springframework.stereotype.Service;

@Service
public class CountryEventConnector extends BaseEventConnector<CountryLangDTO, Boolean, String, Void> {
}
