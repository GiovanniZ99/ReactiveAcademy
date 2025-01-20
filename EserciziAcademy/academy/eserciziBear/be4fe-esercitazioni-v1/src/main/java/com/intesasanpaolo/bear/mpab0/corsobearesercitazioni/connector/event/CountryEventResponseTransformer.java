package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event;

import com.intesasanpaolo.bear.event.response.EventResponse;
import com.intesasanpaolo.bear.event.transformer.IEventResponseTransformer;
import org.springframework.stereotype.Service;

@Service
public class CountryEventResponseTransformer implements IEventResponseTransformer<Void, Boolean> {
    @Override
    public Boolean transform(EventResponse<Void> eventResponse) {
        return eventResponse.getResult();
    }
}
