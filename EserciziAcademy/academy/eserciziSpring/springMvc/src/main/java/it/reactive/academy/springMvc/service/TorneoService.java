package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.mapper.TorneoMapper;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.resource.Torneo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class TorneoService {
    private  final TorneoDao torneoDao;


    public TorneoService(TorneoDao torneoDao) {
        this.torneoDao = torneoDao;
    }

    @Transactional
    public Torneo create(TorneoDTO torneoDTO){
        try {
          return TorneoMapper.torneoDtoExtendedToResource(torneoDao.create(torneoDTO.getNomeTorneo()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Integer id){
        try {
            torneoDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
