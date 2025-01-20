package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryAsyncService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryAsyncCommand extends BaseCommand<Boolean> {
    private final CountryAsyncService countryAsyncService;

    private final String language;

    public CountryAsyncCommand(CountryAsyncService countryAsyncService, String language) {
        this.countryAsyncService = countryAsyncService;
        this.language = language;
    }

    @Override
    protected Boolean doExecute() throws Exception {
        return countryAsyncService.getCountry(language);
    }
}
