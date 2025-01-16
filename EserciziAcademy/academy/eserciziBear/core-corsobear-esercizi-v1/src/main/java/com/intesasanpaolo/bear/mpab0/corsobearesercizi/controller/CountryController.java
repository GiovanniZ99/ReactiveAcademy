package com.intesasanpaolo.bear.mpab0.corsobearesercizi.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommandService;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.CountryCommandServiceParam;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/country")
@RestController
public class CountryController extends CoreController {


    private final BeanFactory beanFactory;

    public CountryController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ResponseEntity<List<CountryResource>> getCountries() {
        List<CountryResource> listaCountry = new ArrayList<>();
        listaCountry.add(new CountryResource(1L, "Italia", "italiano", "Europa"));
        listaCountry.add(new CountryResource(2L, "Giappone", "Giapponese", "Asia"));
        listaCountry.add(new CountryResource(3L, "Stati Uniti", "Inglese", "America"));
        return ResponseEntity.ok(listaCountry);
    }


    public ResponseEntity<List<CountryResource>> countriesCommand() throws Exception {
        List<CountryModel> countryModels = beanFactory.getBean(CountryCommand.class).execute();

        List<CountryResource> countryResources = new ArrayList<>();
        for (CountryModel countryModel : countryModels) {
            CountryResource countryResource = new CountryResource();
            countryResource.setId(countryModel.getId());
            String[] info = countryModel.getInfo().split("-");
            countryResource.setName(info[0]);
            countryResource.setLanguage(info[1]);
            countryResource.setContinent(info[2]);
            countryResources.add(countryResource);
        }

        return ResponseEntity.ok(countryResources);
    }

    // @GetMapping(value = "/service")
    public ResponseEntity<List<CountryResource>> countriesCommandService() throws Exception {
        List<CountryModel> countryModels = beanFactory.getBean(CountryCommandService.class).execute();

        List<CountryResource> countryResources = new ArrayList<>();
        for (CountryModel countryModel : countryModels) {
            CountryResource countryResource = new CountryResource();
            countryResource.setId(countryModel.getId());
            String[] info = countryModel.getInfo().split("-");
            countryResource.setName(info[0]);
            countryResource.setLanguage(info[1]);
            countryResource.setContinent(info[2]);
            countryResources.add(countryResource);
        }

        return ResponseEntity.ok(countryResources);
    }

    @GetMapping(value = "/service")
    public ResponseEntity<List<CountryResource>> countriesCommandServiceWithParam() throws Exception {
        List<CountryModel> countryModels = beanFactory.getBean(CountryCommandServiceParam.class, 4L, "StatiUniti-inglese-America").execute();

        List<CountryResource> countryResources = new ArrayList<>();
        for (CountryModel countryModel : countryModels) {
            CountryResource countryResource = new CountryResource();
            countryResource.setId(countryModel.getId());
            String[] info = countryModel.getInfo().split("-");
            countryResource.setName(info[0]);
            countryResource.setLanguage(info[1]);
            countryResource.setContinent(info[2]);
            countryResources.add(countryResource);
        }

        return ResponseEntity.ok(countryResources);
    }
}
