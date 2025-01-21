package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommand extends BaseCommand<CountryResource> {
    private final CountryService countryService;

    private final String id;

    public CountryCommand(CountryService countryService, String id) {
        this.countryService = countryService;
        this.id = id;
    }
    @Override
    protected CountryResource doExecute() throws Exception {
        return countryService.getCountry(this.id);
    }
}
