package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.connector.jdbc.request.JDBCRequest;
import com.intesasanpaolo.bear.connector.jdbc.response.JDBCResponse;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.GetCountriesJdbcConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.DemoJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.DemoJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService extends BaseService {
    @Autowired
    GetCountriesJdbcConnector getCountriesJdbcConnector;
    @Autowired
    private DemoJDBCRequestTransformer demoJDBCRequestTransformer;
    @Autowired
    private DemoJDBCResponseTransformer demoJDBCResponseTransformer;

    public List<CountryModel> getCountries() {
        List<CountryModel> listaCountry = new ArrayList<>();
        listaCountry.add(new CountryModel(1L, "Italia-italiano-Europa"));
        listaCountry.add(new CountryModel(2L, "Giappone-giapponese-Asia"));
        listaCountry.add(new CountryModel(3L, "StatiUniti-inglese-America"));
        return listaCountry;
    }

    public List<CountryModel> getCountriesWithParam(Long id, String info) {
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Giappone-giapponese-Asia"));
        countryModels.add(new CountryModel(3, "Cina-cinese-Asia"));
        countryModels.add(new CountryModel(id, info));
        return countryModels;
    }

    public List<CountryModel> getJdbc(){
        List<CountryResource> countryResources = getCountriesJdbcConnector.call(
                "select * from countries",
                demoJDBCRequestTransformer,
                demoJDBCResponseTransformer);
        );

        // Now, map the List<CountryResource> to List<CountryModel>
        List<CountryModel> countryModels = new ArrayList<>();
        for (CountryResource countryResource : countryResources) {
            CountryModel countryModel = new CountryModel();
            countryModel.setId(countryResource.getId());  // Map fields
            countryModel.setInfo(countryResource.getInfo());
            countryModels.add(countryModel);
        }
        return countryModels;
    }
}
