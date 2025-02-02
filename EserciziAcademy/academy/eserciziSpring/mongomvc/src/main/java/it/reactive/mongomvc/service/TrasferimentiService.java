package it.reactive.mongomvc.service;

import it.reactive.mongomvc.dto.TrasferimentiDTO;
import it.reactive.mongomvc.mapper.TrasferimentiMapper;
import it.reactive.mongomvc.resource.TrasferimentiResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrasferimentiService {
    @Value("${url-trasferimenti}")
    private String urlTrasferimenti;

    private final TrasferimentiMapper trasferimentiMapper;

    @Autowired
    public TrasferimentiService(TrasferimentiMapper trasferimentiMapper) {
        this.trasferimentiMapper = trasferimentiMapper;
    }

    public Set<TrasferimentiResource> trasferimenti(String nome) {
        String url = urlTrasferimenti + nome;

        RestTemplate restTemplate = new RestTemplate();

        TrasferimentiDTO[] trasferimentiArray = restTemplate.getForObject(url, TrasferimentiDTO[].class);

        if (trasferimentiArray != null) {
            return Arrays
                    .stream(trasferimentiArray)
                    .map(trasferimentiMapper::trasferimentiDTOToResource)
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}
