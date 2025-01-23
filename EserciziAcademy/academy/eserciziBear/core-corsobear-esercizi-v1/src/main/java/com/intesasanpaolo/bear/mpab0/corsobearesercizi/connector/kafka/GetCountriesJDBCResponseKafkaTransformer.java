package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.kafka;

import com.intesasanpaolo.bear.connector.jdbc.response.JDBCResponse;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCountriesJDBCResponseKafkaTransformer implements IJDBCResponseTransformer<List<CountryModel>, List<CountryResource>> {
    @Override
    public List<CountryResource> transform(JDBCResponse<List<CountryModel>> jdbcResponse) {
        return jdbcResponse.getResult().stream()
                .map(countryModel -> {
                    List<String[]> info = countryModel.stream()
                            .map(elem -> elem.getInfo().split(" - "))
                            .collect(Collectors.toList());

                    long id = Long.parseLong(info.get(0)[0]);
                    String field1 = info.get(1)[0];
                    String field2 = info.get(2)[0];
                    String field3 = info.get(3)[0];

                    return new CountryResource(id, field1, field2, field3);
                })
                .collect(Collectors.toList());
    }
}

