package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryMapper;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandJpa extends BaseCommand<CountryResource> {
    private final Long id;

    private final CountryService countryService;

    public CountryCommandJpa(Long id,CountryService countryService) {
        this.id = id;
        this.countryService = countryService;
    }

    @Override
    public CountryResource doExecute() throws Exception{
        return CountryMapper.modelToResource(countryService.getJpa(id));
    }
}
