package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryMapper;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandJdbc extends BaseCommand<List<CountryResource>> {
    private final CountryService countryService;

    public CountryCommandJdbc(CountryService countryService) {
        this.countryService = countryService;
    }
    @Override
    protected List<CountryResource> doExecute() {
        return countryService.getCountries().stream().map(CountryMapper::modelToResource).collect(Collectors.toList());
    }
}
