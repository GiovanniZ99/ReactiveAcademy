package com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector;

import com.intesasanpaolo.bear.connector.jdbc.JdbcConnector;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCountriesJdbcConnector extends JdbcConnector<String, List<CountryModel>, Void, List<CountryResource>> {
}
