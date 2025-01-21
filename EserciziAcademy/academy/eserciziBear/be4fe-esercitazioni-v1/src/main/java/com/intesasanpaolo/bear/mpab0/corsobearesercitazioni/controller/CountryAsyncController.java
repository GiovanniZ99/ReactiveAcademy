package com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.controller;

import com.intesasanpaolo.bear.core.controller.CoreController;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.command.CountryAsyncCommand;
import com.intesasanpaolo.bear.mpab0.corsobearesercitazioni.dto.CountryLangDTO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("country-async")
public class CountryAsyncController extends CoreController {
    private final BeanFactory beanFactory;

    public CountryAsyncController(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @GetMapping("/lingua")
    public ResponseEntity<Boolean> getCountry(@RequestParam String lingua) throws Exception {
       return ResponseEntity.ok(beanFactory.getBean(CountryAsyncCommand.class, new CountryLangDTO(lingua)).execute());
    }
}
