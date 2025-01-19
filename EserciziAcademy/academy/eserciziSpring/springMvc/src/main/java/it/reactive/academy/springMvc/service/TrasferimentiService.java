package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.extended.TrasferimentiDTOExtended;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class TrasferimentiService {
    @Value("${urlTrasferimenti}")
    private String urlTrasferimenti;

    public Set<TrasferimentiDTOExtended> trasferimenti(String nome) {
        String url = urlTrasferimenti + nome;

        RestTemplate restTemplate = new RestTemplate();
        Set<TrasferimentiDTOExtended> trasferimentiSet = new HashSet<>();

        TrasferimentiDTOExtended[] trasferimentiArray = restTemplate.getForObject(url, TrasferimentiDTOExtended[].class);

        if (trasferimentiArray != null) {
            Collections.addAll(trasferimentiSet, trasferimentiArray);
        }

        return trasferimentiSet;
    }
}
