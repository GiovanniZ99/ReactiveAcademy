package it.reactive.mongomvc.service;

import it.reactive.mongomvc.document.Squadra;
import it.reactive.mongomvc.document.Torneo;
import it.reactive.mongomvc.dto.TorneoDTO;
import it.reactive.mongomvc.exception.SquadraNonTrovataException;
import it.reactive.mongomvc.exception.TorneoNonTrovatoException;
import it.reactive.mongomvc.mapper.GiocatoreMapper;
import it.reactive.mongomvc.mapper.SquadraMapper;
import it.reactive.mongomvc.mapper.TorneoMapper;
import it.reactive.mongomvc.resource.SquadraResource;
import it.reactive.mongomvc.resource.TorneoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TorneoService {
    private final MongoTemplate mongoTemplate;
    private final TorneoMapper torneoMapper;
    private final SquadraMapper squadraMapper;


    @Autowired
    public TorneoService(MongoTemplate mongoTemplate, TorneoMapper torneoMapper, SquadraMapper squadraMapper) {
        this.mongoTemplate = mongoTemplate;
        this.torneoMapper = torneoMapper;
        this.squadraMapper = squadraMapper;
    }

    public TorneoResource create(TorneoDTO torneoDTO) {
        Torneo torneo = torneoMapper.torneoDTOToDocument(torneoDTO);
        return torneoMapper.torneoToResource(mongoTemplate.save(torneo));
    }

    public void delete(Integer id) {
        Query query = new Query(Criteria.where("idTorneo").is(id));
        Torneo torneo = mongoTemplate.findOne(query, Torneo.class);

        if (torneo == null) {
            throw new TorneoNonTrovatoException("Torneo non trovato");
        }

        if (!torneo.getSquadreId().isEmpty()) {
            torneo.getSquadreId().forEach(elem -> {
                Query querySquadre = new Query(Criteria.where("id").in(elem));
                Squadra squadra = mongoTemplate.findOne(querySquadre, Squadra.class);

                Query queryTornei = new Query(Criteria.where("squadreId").is(squadra.getId()));
                long torneiDiSquadra = mongoTemplate.count(queryTornei, Torneo.class);
                if (torneiDiSquadra == 0) {
                    mongoTemplate.remove(squadra);
                }
            });
        }
        mongoTemplate.remove(torneo);
    }

    public List<TorneoResource> getTorneiCompleti() {
        List<Torneo> tornei = mongoTemplate.findAll(Torneo.class);

        Set<Squadra> squadre = getSquadre(tornei);
        Set<SquadraResource> squadreResource = squadre.stream().map(squadraMapper::squadraDocumentToResource).collect(Collectors.toSet());

        return tornei.stream().map(torneoCompleto -> {
            TorneoResource torneoResource = torneoMapper.torneoToResource(torneoCompleto);
            torneoResource.setSquadre(squadreResource);
            return torneoResource;
        }).collect(Collectors.toList());

    }

    private Set<Squadra> getSquadre(List<Torneo> tornei) {
        Set<Squadra> squadre = new HashSet<>();
        for (Torneo torneo : tornei) {
            List<String> squadreIds = torneo.getSquadreId();

            if (squadreIds != null && !squadreIds.isEmpty()) {
                squadreIds.forEach(id -> {
                    Squadra squadra = new Squadra();
                    Query query = new Query(Criteria.where("id").is(squadra.getId()));

                    squadra = mongoTemplate.findOne(query, Squadra.class);
                    if (squadra != null) {
                        squadre.add(squadra);
                    }
                });
            }
        }
        return squadre;
    }

    public TorneoResource censisciSquadraAlTorneo(String idTorneo, String idSquadra) {
            Query queryTorneo = new Query(Criteria.where("id").is(idTorneo));
            Torneo torneo = mongoTemplate.findOne(queryTorneo, Torneo.class);

            if (torneo == null) {
                throw new TorneoNonTrovatoException("Torneo non trovato");
            }

            Query querySquadra = new Query(Criteria.where("id").is(idSquadra));
            Squadra squadra = mongoTemplate.findOne(querySquadra, Squadra.class);

            if (squadra == null) {
                throw new SquadraNonTrovataException("Squadra non trovata");
            }

            List<String> squadreId = torneo.getSquadreId();
            if (!squadreId.contains(idSquadra)) {
                squadreId.add(idSquadra);
            }

            torneo.setSquadreId(squadreId);
           return torneoMapper.torneoToResource(mongoTemplate.save(torneo));
    }

}

