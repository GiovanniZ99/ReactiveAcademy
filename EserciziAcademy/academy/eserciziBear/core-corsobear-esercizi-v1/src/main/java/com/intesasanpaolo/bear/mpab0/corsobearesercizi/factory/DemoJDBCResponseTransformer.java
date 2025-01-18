package com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory;

import com.intesasanpaolo.bear.connector.jdbc.response.JDBCResponse;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCResponseTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoJDBCResponseTransformer implements IJDBCResponseTransformer<CountryResource, List<CountryResource>>{

    @Override
    public List<CountryResource> transform(JDBCResponse<CountryResource> response) {
        return response.getResult();
    }
}
