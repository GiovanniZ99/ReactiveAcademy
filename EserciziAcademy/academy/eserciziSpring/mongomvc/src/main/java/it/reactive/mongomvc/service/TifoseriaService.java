package it.reactive.mongomvc.service;

import it.reactive.mongomvc.document.Squadra;
import it.reactive.mongomvc.document.Tifoseria;
import it.reactive.mongomvc.dto.TifoseriaDTO;
import it.reactive.mongomvc.exception.SquadraNonPresenteException;
import it.reactive.mongomvc.mapper.SquadraMapper;
import it.reactive.mongomvc.resource.SquadraResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class TifoseriaService {

    private final SquadraMapper squadraMapper;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TifoseriaService(SquadraMapper squadraMapper, MongoTemplate mongoTemplate) {
        this.squadraMapper = squadraMapper;
        this.mongoTemplate = mongoTemplate;
    }
    public SquadraResource create(TifoseriaDTO tifoseriaDTO, Integer idSquadra) {

            Squadra squadra = mongoTemplate.findById(idSquadra, Squadra.class);

            if (squadra == null) {
                throw new SquadraNonPresenteException("Squadra non presente");
            }

            if (squadra.getTifoserie() != null) {
                squadra.getTifoserie().setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());
                mongoTemplate.save(squadra);

                return squadraMapper.squadraDocumentToResource(squadra);
            }

            Tifoseria tifoseria = new Tifoseria();
            tifoseria.setNomeTifoseria(tifoseriaDTO.getNomeTifoseria());

            squadra.setTifoserie(tifoseria);

           return squadraMapper.squadraDocumentToResource(mongoTemplate.save(squadra));
    }
}

