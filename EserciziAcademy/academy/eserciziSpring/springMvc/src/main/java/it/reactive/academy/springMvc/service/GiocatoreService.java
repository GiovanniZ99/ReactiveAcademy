package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.resource.Giocatore;
import org.springframework.stereotype.Service;

@Service
public class GiocatoreService {

    private final GiocatoreDao giocatoreDao;

    public GiocatoreService(GiocatoreDao giocatoreDao) {
        this.giocatoreDao = giocatoreDao;
    }

    public Giocatore updateAmmonizioni(Integer id){
       return GiocatoreMapper.giocatoreDTOExtendedToResource(giocatoreDao.updateAmmonizioni(id));
    }
}
