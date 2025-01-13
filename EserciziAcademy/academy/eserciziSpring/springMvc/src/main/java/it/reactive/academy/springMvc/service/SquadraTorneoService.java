package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TifoseriaDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.exception.TorneoNonTrovatoException;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import it.reactive.academy.springMvc.repository.dao.*;
import it.reactive.academy.springMvc.resource.Torneo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SquadraTorneoService {
    private final SquadraTorneoDao squadraTorneoDao;
    private final SquadraDao squadraDao;
    private final TorneoDao torneoDao;
    private final GiocatoreDao giocatoreDao;
    private final TifoseriaDao tifoseriaDao;

    public SquadraTorneoService(SquadraTorneoDao squadraTorneoDao, SquadraDao squadraDao, TorneoDao torneoDao, GiocatoreDao giocatoreDao, TifoseriaDao tifoseriaDao) {
        this.squadraTorneoDao = squadraTorneoDao;
        this.squadraDao = squadraDao;
        this.torneoDao = torneoDao;
        this.giocatoreDao = giocatoreDao;
        this.tifoseriaDao = tifoseriaDao;
    }

    @Transactional
    public Torneo create(Integer idTorneo, Integer idSquadra) {
        try {
            TorneoDTOExtended torneoDTOExtended = torneoDao.findById(idTorneo);
            if (torneoDTOExtended.getIdTorneo() == null) {
                throw new TorneoNonTrovatoException("Torneo non trovato");
            }
            SquadraDTOExtended squadraDTOExtended = squadraDao.findSquadraByOd(idSquadra);
            if (squadraDTOExtended.getIdSquadra() == null) {
                throw new SquadraNonPresenteException("Squadra non presente");
            }
            SquadraTorneoDTOExtended squadraTorneoDTOExtended = squadraTorneoDao.create(idTorneo, idSquadra);
            Set<Integer> setIdSquadre = squadraTorneoDao.readAllTeams(torneoDTOExtended.getIdTorneo());
            Set<SquadraDTOExtended> setSquadre = new HashSet<>();

            setIdSquadre.forEach(elem -> {
                try {
                    setSquadre.add(squadraDao.findSquadraByOd(elem));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            torneoDTOExtended.setSquadre(setSquadre);
            torneoDTOExtended.setIdTorneo(squadraTorneoDTOExtended.getIdTorneo());
            torneoDTOExtended.getSquadre().forEach(elem -> {
                try {
                    elem.setGiocatori(giocatoreDao.readAllByTeam(elem));

                    TifoseriaDTOExtended tifoseria = tifoseriaDao.readByTeam(elem);
                    tifoseria.setSquadra(elem);
                    elem.setTifoseria(tifoseria);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            return TorneoMapper.torneoDtoExtendedToResource(torneoDTOExtended);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Torneo> readAll() {
        try {
            return squadraTorneoDao.readAllTorneo().stream().map(TorneoMapper::torneoDtoExtendedToResource).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
