package com.intesasanpaolo.bear.mpab0.corsobearesercizi.command;

import com.intesasanpaolo.bear.core.command.BaseCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.dto.CountryLangDTO;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.service.CountryService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CountryKafkaCommand  extends BaseCommand<List<CountryResource>> {
    private final CountryService countryService;
    private CountryLangDTO countryLangDTO;

    public CountryKafkaCommand(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    protected List<CountryResource> doExecute() throws Exception {
        return countryService.getCountriesByLangWithJDBC(countryLangDTO);
    }
}
