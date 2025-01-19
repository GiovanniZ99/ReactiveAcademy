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
public class CountryCommandServiceParam  extends BaseCommand<List<CountryModel>> {


   private final CountryService countryService;

    private final Long id;
    private final String info;

    public CountryCommandServiceParam(CountryService countryService, Long id, String info) {
        this.countryService = countryService;
        this.id = id;
        this.info = info;
    }

    @Override
    protected List<CountryModel> doExecute() {
        return countryService.getCountriesWithParam(this.id, this.info);
    }
}

