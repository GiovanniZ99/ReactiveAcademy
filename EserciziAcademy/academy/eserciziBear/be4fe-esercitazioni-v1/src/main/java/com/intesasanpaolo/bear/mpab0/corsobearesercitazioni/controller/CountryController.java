package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.CountryCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.resource.CountryResource;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("(country")
@RestController
public class CountryController {
    private final BeanFactory beanFactory;

    public CountryController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/country/id")
    public ResponseEntity<CountryResource> getCountriesId(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(beanFactory.getBean(CountryCommand.class, id).execute());
    }
}


