package com.intesasanpaolo.bear.mpab0.corsobearesercizi.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.command.*;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.factory.CountryMapper;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.model.CountryModel;
import com.intesasanpaolo.bear.mpab0.corsobearesercizi.resource.CountryResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/country")
@RestController
public class CountryController extends CoreController {

    private final BeanFactory beanFactory;

    public CountryController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    //    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryResource>> getCountries() {
        List<CountryResource> listaCountry = new ArrayList<>();
        listaCountry.add(new CountryResource(1L, "Italia", "italiano", "Europa"));
        listaCountry.add(new CountryResource(2L, "Giappone", "Giapponese", "Asia"));
        listaCountry.add(new CountryResource(3L, "Stati Uniti", "Inglese", "America"));
        return ResponseEntity.ok(listaCountry);
    }


   // @GetMapping(value = "command")
    public ResponseEntity<List<CountryResource>> getCountriesCommand() throws Exception {
        List<CountryModel> countries = beanFactory.getBean(CountryCommand.class).execute();
        List<CountryResource> countriesResources = new ArrayList<>();
        for (CountryModel countryModel : countries) {
            countriesResources.add(CountryMapper.modelToResource(countryModel));
        }
        return ResponseEntity.ok(countriesResources);
    }

    //    @GetMapping(value="/service")
    public ResponseEntity<List<CountryResource>> getCountriesCommandService() throws Exception {
        List<CountryModel> countries = beanFactory.getBean(CountryCommandService.class, "variabile d'istanza di tipo String", 1).execute();
        List<CountryResource> countriesR = new ArrayList<>();
        for (CountryModel cM : countries) {
            countriesR.add(CountryMapper.modelToResource(cM));
        }
        return ResponseEntity.ok(countriesR);
    }

    //    @GetMapping(value="/serviceParametri")
    public ResponseEntity<List<CountryResource>> getCountriesCommandServiceWithParams() throws Exception {
        List<CountryModel> countries = beanFactory.getBean(CountryCommandServiceParam.class, "Germania - Tedesco - Europa", 3L).execute();
        List<CountryResource> countriesR = new ArrayList<>();
        for (CountryModel cM : countries) {
            countriesR.add(CountryMapper.modelToResource(cM));
        }
        return ResponseEntity.ok(countriesR);
    }

    //    @GetMapping(value="connector")
    public ResponseEntity<List<CountryResource>> getCountriesCommandConnector() throws Exception {
        List<CountryResource> countries = beanFactory.getBean(CountryCommandJdbc.class).execute();
        return ResponseEntity.ok(countries);
    }

    @PostMapping
    public ResponseEntity<CountryResource> getCountries(@RequestBody Long id) throws Exception {
        CountryResource countryResource = beanFactory.getBean(CountryCommandJpa.class, id).execute();
        return ResponseEntity.ok(countryResource);
    }

}
