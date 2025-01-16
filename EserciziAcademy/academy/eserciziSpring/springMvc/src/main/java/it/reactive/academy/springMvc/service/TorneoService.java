package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.TorneoDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
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
            TorneoDTOExtended torneoDTOExtended = torneoDao.findById(id);
            Set<SquadraDTOExtended> squadre = squadraTorneoDao.readAllTeamsById(torneoDTOExtended);
            torneoDao.delete(id);
            squadre.forEach(squadra -> {
                try {
                Set<TorneoDTOExtended> tornei = squadraTorneoDao.readAllTorneoByIdSquadra(squadra);
                 if(tornei.isEmpty()){
                     squadraDao.delete(squadra.getIdSquadra());
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
