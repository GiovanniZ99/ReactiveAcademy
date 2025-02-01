package it.reactive.mongomvc.service;

import it.reactive.mongomvc.document.Squadra;
import it.reactive.mongomvc.dto.SquadraDTO;
import it.reactive.mongomvc.exception.SquadraGiaCensitaException;
import it.reactive.mongomvc.mapper.SquadraMapper;
import it.reactive.mongomvc.resource.SquadraResource;
import it.reactive.mongomvc.utility.Costanti;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SquadraService {
    private final MongoTemplate mongoTemplate;

    private final SquadraMapper squadraMapper;

    public SquadraService(MongoTemplate mongoTemplate, SquadraMapper squadraMapper) {
        this.mongoTemplate = mongoTemplate;
        this.squadraMapper = squadraMapper;
    }

    public SquadraResource create(SquadraDTO squadraDTO){
        Squadra squadra = squadraMapper.squadraDTOToDocument(squadraDTO);
        Query query = new Query(Criteria.where("where").is(squadra.getNome()));
        Squadra squadraEsistente = mongoTemplate.findOne(query, Squadra.class);

        if(squadraEsistente != null){
            throw new SquadraGiaCensitaException(Costanti.SQUADRA_GIA_CENSITA);
        }
        squadra = mongoTemplate.save(squadra);

        return squadraMapper.squadraDocumentToResource(squadra);
    }
}
