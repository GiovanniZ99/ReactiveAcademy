package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryCommandService  extends BaseCommand<List<CountryModel>> {
    private final CountryService countryService;

    public CountryCommandService(CountryService countryService) {
        this.countryService = countryService;
    }

    public List<CountryModel> getListaCountry() {
        return countryService.getCountries();
    }
}
