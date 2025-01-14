package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.repository.dao.TorneoDao;
import it.reactive.academy.springMvc.resource.Torneo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Set;

@Service
public class TorneoService {
    private  final TorneoDao torneoDao;
    private final SquadraTorneoDao squadraTorneoDao;
    private final SquadraDao squadraDao;
    public TorneoService(TorneoDao torneoDao, SquadraTorneoDao squadraTorneoDao, SquadraDao squadraDao) {
        this.torneoDao = torneoDao;
        this.squadraTorneoDao = squadraTorneoDao;
        this.squadraDao = squadraDao;
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
            Set<Integer> idSquadre = squadraTorneoDao.readAllTeamsById(id);
            torneoDao.delete(id);

            // se una di queste squadre ha ancora dei tornei non eliminarle, altrimenti eliminarle
            idSquadre.forEach(squadra -> {
                try {
                 Set<Integer> idTornei = squadraTorneoDao.readAllTorneoByIdSquadra(squadra);
                 if(idTornei.isEmpty()){
                     squadraDao.delete(squadra);
                 }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
