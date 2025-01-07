package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.TifoseriaDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.mapper.TifoseriaMapper;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.repository.dao.TifoseriaDao;
import it.reactive.academy.springMvc.resource.Tifoseria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class TifoseriaService {
    private final TifoseriaDao tifoseriaDao;
    private final SquadraDao squadraDao;

    public TifoseriaService(TifoseriaDao tifoseriaDao, SquadraDao squadraDao) {
        this.tifoseriaDao = tifoseriaDao;
        this.squadraDao = squadraDao;
    }

    @Transactional
    public Tifoseria create(TifoseriaDTO tifoseriaDTO, Integer idSquadra) {
        TifoseriaDTOExtended tifoseriaDTOExtended = TifoseriaMapper.tifoseriaDTOToDTOExtended(tifoseriaDTO);
        try {
            SquadraDTOExtended squadraDTOExtended = squadraDao.findSquadraByOd(idSquadra);
            if(squadraDTOExtended.getIdSquadra() == null){
                throw new SquadraNonPresenteException("Squadra non presente");
            }
            if(squadraDTOExtended.getTifoseria() != null){
               return TifoseriaMapper.tifoseriaDtoExtendedToResource(tifoseriaDao.updateName(
                       squadraDTOExtended.getTifoseria().getNomeTifoseria(),
                       squadraDTOExtended.getIdSquadra()));
            }
            tifoseriaDTOExtended = tifoseriaDao.createWithTeam(tifoseriaDTOExtended, idSquadra);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return TifoseriaMapper.tifoseriaDtoExtendedToResource(tifoseriaDTOExtended);
    }





















}
