package com.intesasanpaolo.bear.mpab0.corsobearesercizi.service;

import com.intesasanpaolo.bear.mpab0.corsobearesercizi.connector.JpaRepository;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryMapper;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceJpa {
    private final JpaRepository jpaRepository;

    public CountryServiceJpa(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Optional<CountryResource> getJpa(Long key) {
        Optional<CountryModel> optionalCountryModel = jpaRepository.findById(key);

        return Optional.of(CountryMapper.modelToResource(optionalCountryModel.orElse(new CountryModel())));

    }
}
