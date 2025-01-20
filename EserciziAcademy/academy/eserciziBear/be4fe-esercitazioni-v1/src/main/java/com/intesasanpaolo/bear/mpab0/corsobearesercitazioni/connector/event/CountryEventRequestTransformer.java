package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.connector.event;


import com.intesasanpaolo.bear.event.request.EventRequest;
import com.intesasanpaolo.bear.event.transformer.IEventRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

@Service
public class CountryEventRequestTransformer implements IEventRequestTransformer<CountryLangDTO, Void> {
    @Value("${KAFKA_TOPIC}")
    private String topic;

        @Override
        public EventRequest transform(CountryLangDTO om, Object... args) {
            EventRequest<String> eventRequest = new EventRequest<>();

            try (JsonSerializer<CountryLangDTO> jsonSerializer = new JsonSerializer<>()) {
                eventRequest.setTopic(topic);
                eventRequest.setPayload(jsonSerializer.serialize(topic, om));
            }
            return eventRequest;
        }
}
