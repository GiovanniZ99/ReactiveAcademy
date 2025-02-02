package it.reactive.mongomvc.service;

import it.reactive.mongomvc.document.Giocatore;
import it.reactive.mongomvc.exception.GiocatoreNonTrovatoException;
import it.reactive.mongomvc.mapper.GiocatoreMapper;
import it.reactive.mongomvc.resource.GiocatoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class GiocatoreService {
    private final MongoTemplate mongoTemplate;

    private final GiocatoreMapper giocatoreMapper;

    @Autowired
    public GiocatoreService(MongoTemplate mongoTemplate, GiocatoreMapper giocatoreMapper) {
        this.mongoTemplate = mongoTemplate;
        this.giocatoreMapper = giocatoreMapper;
    }
    public GiocatoreResource updateAmmonizioni(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("giocatore.numeroAmmonizioni", 1);
      Giocatore giocatore = mongoTemplate.findAndModify(query, update, Giocatore.class);
      if ((giocatore != null)){
          return giocatoreMapper.giocatoreToResource(giocatore);
      }
      throw new GiocatoreNonTrovatoException("Giocatore non trovato");
    }
}
