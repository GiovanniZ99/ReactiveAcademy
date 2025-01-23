package com.intesasanpaolo.bear.mpab0.corsobearesercizi.listener;



import com.intesasanpaolo.bear.eventlistener.BaseEventListener;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryKafkaCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.dto.CountryLangDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryListener extends BaseEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CountryListener.class);

    @Value("${KAFKA_TOPIC_DEMO}")
    private String TOPIC;

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public void onReceived(byte[] payload, Headers headers) {
        if(payload != null) {
            JsonDeserializer<CountryLangDTO> js = new JsonDeserializer<>(CountryLangDTO.class);
            CountryLangDTO message =js.deserialize(TOPIC, headers, payload);
            js.close();

            List<CountryResource> countryResourceList;
            try {
                countryResourceList = beanFactory.getBean(CountryKafkaCommand.class, message).execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (countryResourceList != null) {
                countryResourceList.forEach(elem -> logger.info("Country con lingua ricercata trovate: {}", elem.getName()));
            } else {
                logger.warn("Nessuna Country trovata con la lingua richiesta.");
            }
        }
    }
}