package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event;

import com.intesasanpaolo.bear.event.request.EventRequest;
import com.intesasanpaolo.bear.event.transformer.IEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

@Service
public class CountryEventRequestTransformer implements IEventRequestTransformer<CountryLangDTO, String> {
    @Value("${KAFKA_TOPIC_DEMO}")
    private String topic;

    @Override
        public EventRequest<String> transform(CountryLangDTO om, Object... args) {
            EventRequest<String> event = new EventRequest<>();
            JsonSerializer<CountryLangDTO> js =
                    new JsonSerializer<>();
            event.setTopic(topic);
            event.setPayload(js.serialize(topic, om));
            js.close();
            return event;
        }
}
