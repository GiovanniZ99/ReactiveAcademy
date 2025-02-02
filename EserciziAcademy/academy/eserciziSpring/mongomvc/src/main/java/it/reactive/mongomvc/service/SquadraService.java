package it.reactive.mongomvc.service;

import it.reactive.mongomvc.document.Giocatore;
import it.reactive.mongomvc.document.Squadra;
import it.reactive.mongomvc.dto.GiocatoreDTO;
import it.reactive.mongomvc.dto.SquadraDTO;
import it.reactive.mongomvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.mongomvc.exception.GiocatoreGiaCensitoException;
import it.reactive.mongomvc.exception.SquadraGiaCensitaException;
import it.reactive.mongomvc.exception.SquadraNonPresenteException;
import it.reactive.mongomvc.mapper.GiocatoreMapper;
import it.reactive.mongomvc.mapper.SquadraMapper;
import it.reactive.mongomvc.resource.GiocatoreResource;
import it.reactive.mongomvc.resource.SquadraResource;
import it.reactive.mongomvc.utility.Costanti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SquadraService {
    private final MongoTemplate mongoTemplate;

    private final SquadraMapper squadraMapper;

    private final GiocatoreMapper giocatoreMapper;

    private final TrasferimentiService trasferimentiService;


    @Autowired
    public SquadraService(MongoTemplate mongoTemplate, SquadraMapper squadraMapper, GiocatoreMapper giocatoreMapper, TrasferimentiService trasferimentiService) {
        this.mongoTemplate = mongoTemplate;
        this.squadraMapper = squadraMapper;
        this.giocatoreMapper = giocatoreMapper;
        this.trasferimentiService = trasferimentiService;
    }

    public SquadraResource create(SquadraDTO squadraDTO) {
        Squadra squadra = squadraMapper.squadraDTOToDocument(squadraDTO);
        Query query = new Query(Criteria.where("where").is(squadra.getNome()));
        Squadra squadraEsistente = mongoTemplate.findOne(query, Squadra.class);

        if (squadraEsistente != null) {
            throw new SquadraGiaCensitaException(Costanti.SQUADRA_GIA_CENSITA);
        }
        squadra = mongoTemplate.save(squadra);

        return squadraMapper.squadraDocumentToResource(squadra);
    }

    public SquadraResource addPlayer(Integer idSquadra, GiocatoreDTO giocatoreDTO) {
        Giocatore giocatore = giocatoreMapper.giocatoreDTOToDocument(giocatoreDTO);
        Query query = new Query(Criteria.where("idSquadra").is(idSquadra));
        Squadra squadra = mongoTemplate.findOne(query, Squadra.class);

        if (squadra == null) {
            throw new SquadraNonPresenteException("Squadra non presente");
        }

        Set<Giocatore> giocatori = Optional.ofNullable(squadra.getGiocatori()).orElse(new HashSet<>());
        giocatori.add(giocatore);

        squadra.setGiocatori(giocatori);
        mongoTemplate.save(squadra);

        squadra.getGiocatori().stream().map(elem-> {
            GiocatoreResource giocatoreResource = giocatoreMapper.giocatoreToResource(elem);
            giocatoreResource.setTrasferimenti(trasferimentiService.trasferimenti(giocatoreResource.getNomeCognome()));
            return giocatoreResource;
        }).collect(Collectors.toSet());

        return squadraMapper.squadraDocumentToResource(squadra);
    }

    public SquadraResource createWithPlayers(SquadraDiGiocatoriDTO squadraDiGiocatori) {
        Query query = new Query(Criteria.where("nome").is(squadraDiGiocatori.getNome()));
        if (mongoTemplate.exists(query, Squadra.class)) {
            throw new SquadraGiaCensitaException("Squadra già censita");
        }

        Set<GiocatoreDTO> giocatoriCensiti = new HashSet<>();
        squadraDiGiocatori.getListaGiocatori().forEach(elem -> {
            if(giocatoriCensiti.contains(elem)){
                throw new GiocatoreGiaCensitoException("Giocatore già censito");
            }
            giocatoriCensiti.add(elem);
        } );
        Squadra squadra = new Squadra();


        mongoTemplate.save(squadra);
        squadraDiGiocatori.getListaGiocatori().stream().map(elem-> {
            Giocatore giocatore = giocatoreMapper.giocatoreDTOToDocument(elem);
            GiocatoreResource giocatoreResource = giocatoreMapper.giocatoreToResource(giocatore);
            giocatoreResource.setTrasferimenti(trasferimentiService.trasferimenti(giocatoreResource.getNomeCognome()));
            return giocatoreResource;
        }).collect(Collectors.toSet());
        return squadraMapper.squadraDocumentToResource(squadra);
    }

    public List<SquadraResource> read(Boolean completo) {
        List<Squadra> squadre;

        squadre = mongoTemplate.findAll(Squadra.class);

        if (!Boolean.TRUE.equals(completo)) {
            squadre.forEach(elem -> elem.setGiocatori(new HashSet<>()));
        }

        squadre.forEach(squadra -> squadra.getGiocatori().stream().map(elem-> {
            GiocatoreResource giocatoreResource = giocatoreMapper.giocatoreToResource(elem);
            giocatoreResource.setTrasferimenti(trasferimentiService.trasferimenti(giocatoreResource.getNomeCognome()));
            return giocatoreResource;
        }).collect(Collectors.toSet()));

        return squadre.stream()
                .map(squadraMapper::squadraDocumentToResource)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        Query query = new Query(Criteria.where("idSquadra").is(id));
        Squadra squadra = mongoTemplate.findOne(query, Squadra.class);

        if (squadra == null) {
            throw new SquadraNonPresenteException("Squadra non presente");
        }

        mongoTemplate.remove(squadra);
    }
}
