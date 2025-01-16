package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService extends BaseService {

    public List<CountryModel> getCountries(){
        List<CountryModel> listaCountry = new ArrayList<>();
        listaCountry.add(new CountryModel(1L, "Italia-italiano-Europa"));
        listaCountry.add(new CountryModel(2L, "Giappone-giapponese-Asia"));
        listaCountry.add(new CountryModel(3L, "StatiUniti-inglese-America"));
        return listaCountry;
    }

    public List<CountryModel> getCountriesWithParam(Long id, String info){
        List<CountryModel> countryModels = new ArrayList<>();

        countryModels.add(new CountryModel(1, "Italia-Italiano-Europa"));
        countryModels.add(new CountryModel(2, "Giappone-giapponese-Asia"));
        countryModels.add(new CountryModel(3, "Cina-cinese-Asia"));
        countryModels.add(new CountryModel(id, info));
        return countryModels;
    }
}
