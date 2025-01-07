package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.resource.Giocatore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class GiocatoreService {

    private final GiocatoreDao giocatoreDao;

    public GiocatoreService(GiocatoreDao giocatoreDao) {
        this.giocatoreDao = giocatoreDao;
    }

    @Transactional
    public Giocatore updateAmmonizioni(Integer id){
        try {
            giocatoreDao.updateAmmonizioni(id);
            return GiocatoreMapper.giocatoreDTOExtendedToResource(giocatoreDao.findGiocatoreById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
