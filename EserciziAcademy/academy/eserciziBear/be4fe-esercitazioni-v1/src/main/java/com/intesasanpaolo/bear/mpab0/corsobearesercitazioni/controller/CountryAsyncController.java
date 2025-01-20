package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.CountryAsyncCommand;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("country-async")
public class CountryAsyncController {
    private final BeanFactory beanFactory;

    public CountryAsyncController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/get-language/{language}")
    public ResponseEntity<Boolean> getCountry(@PathVariable("language") String lingua) throws Exception {
        return ResponseEntity.ok(beanFactory.getBean(CountryAsyncCommand.class, lingua).execute());
    }
}
