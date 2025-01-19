package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryServiceJpa;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandJpa extends BaseCommand<Optional<CountryResource>> {
    private final Long id;

    private final CountryServiceJpa countryServiceJPA;

    public CountryCommandJpa(Long id, CountryServiceJpa countryServiceJPA) {
        this.id = id;
        this.countryServiceJPA = countryServiceJPA;
    }


    @Override
    public Optional<CountryResource> doExecute() throws Exception{
        return countryServiceJPA.getJpa(id);
    }
}
