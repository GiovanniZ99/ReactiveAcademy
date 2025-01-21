package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.service.CountryAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryAsyncCommand extends BaseCommand<Boolean> {
    @Autowired
    private CountryAsyncService countryAsyncService;

    private final CountryLangDTO countryLangDTO;

    public CountryAsyncCommand(CountryLangDTO countryLangDTO) {
        this.countryLangDTO = countryLangDTO;
    }

    @Override
    protected Boolean doExecute() throws Exception {
        return countryAsyncService.getCountry(this.countryLangDTO);
    }
}
