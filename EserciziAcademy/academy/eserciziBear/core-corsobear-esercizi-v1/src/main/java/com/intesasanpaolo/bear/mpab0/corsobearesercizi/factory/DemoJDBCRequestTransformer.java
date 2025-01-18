package com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory;

import com.intesasanpaolo.bear.connector.jdbc.JDBCQueryType;
import com.intesasanpaolo.bear.connector.jdbc.request.JDBCRequest;
import com.intesasanpaolo.bear.connector.jdbc.transformer.IJDBCRequestTransformer;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DemoJDBCRequestTransformer implements IJDBCRequestTransformer<String, Void> {

    @Override
    public JDBCRequest<Void> transform(String query, Object... args) {
        JDBCRequest<Void> jdbcConnectorRequest = new JDBCRequest<>();
        jdbcConnectorRequest.setQuery(query);
        jdbcConnectorRequest.setRowMapper(
                new BeanPropertyRowMapper<>(CountryModel.class));
        jdbcConnectorRequest.setType((JDBCQueryType) args[0]);
        if (args.length > 1)
            jdbcConnectorRequest.setParams(
                    Arrays.copyOfRange(args, 1, args.length));
        return jdbcConnectorRequest;
    }
}
