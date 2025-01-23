package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.kafka;

import com.intesasanpaolo.bear.connector.jdbc.response.JDBCResponse;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetCountriesJdbcResponseKafkaTransformer implements IJDBCResponseTransformer<CountryModel, List<CountryResource>> {
    @Override
    public List<CountryResource> transform(JDBCResponse <CountryModel> jdbcResponse) {
        List<CountryModel> countryModelList = jdbcResponse.getResult();

        List<CountryResource> countryResourceList = new ArrayList<>();
        countryModelList.forEach(elem -> {
            CountryResource countryResource = new CountryResource();
            String[] splittedInfo = elem.getInfo().split("-");
            if (splittedInfo.length >= 3) {

                String name = splittedInfo[0];
                String lang = splittedInfo[1];
                String cont = splittedInfo[2];

                countryResource.setId(elem.getId());
                countryResource.setName(name);
                countryResource.setLanguage(lang);
                countryResource.setContinent(cont);
                countryResource.setEntityId(countryResource.getName());

                countryResourceList.add(countryResource);
            }
        });
        return countryResourceList;
    }
}

