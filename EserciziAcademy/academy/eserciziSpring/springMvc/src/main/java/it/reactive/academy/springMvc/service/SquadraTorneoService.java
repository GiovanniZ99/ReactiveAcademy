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
import java.util.*;
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
            SquadraDTOExtended squadraDTOExtended = squadraDao.findSquadraById(idSquadra);
            if (squadraDTOExtended.getIdSquadra() == null) {
                throw new SquadraNonPresenteException("Squadra non presente");
            }
            SquadraTorneoDTOExtended squadraTorneoDTOExtended = squadraTorneoDao.create(torneoDTOExtended, squadraDTOExtended);
            Set<SquadraDTOExtended> listaSquadre = squadraTorneoDao.readAllTeamsById(torneoDTOExtended);
            Set<SquadraDTOExtended> listaSquadreValorizzate = new HashSet<>();

            listaSquadre.forEach(elem -> {
                try {
                    SquadraDTOExtended squadraValorizzata = squadraDao.findSquadraById(elem.getIdSquadra());
                    listaSquadreValorizzate.add(squadraValorizzata);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            torneoDTOExtended.setSquadre(listaSquadreValorizzate);
            torneoDTOExtended.setIdTorneo(squadraTorneoDTOExtended.getTorneoDTOExtended().getIdTorneo());
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

    public List<Torneo> readAllTornei() {
        try {
            Set<TorneoDTOExtended> tornei = torneoDao.findAll();
            tornei.forEach(elem -> elem.setSquadre(new HashSet<>()));
            for (TorneoDTOExtended torneoDTOExtended : tornei) {
                torneoDTOExtended.setSquadre(squadraTorneoDao.readAllTeamsById(torneoDTOExtended));
                for (SquadraDTOExtended squadraDTOExtended : torneoDTOExtended.getSquadre()) {
                    SquadraDTOExtended squadraValorizzata = squadraDao.findSquadraById(squadraDTOExtended.getIdSquadra());
                    squadraDTOExtended.setNome(squadraValorizzata.getNome());
                    squadraDTOExtended.setColoriSociali(squadraValorizzata.getColoriSociali());
                    squadraDTOExtended.setGiocatori(giocatoreDao.readAllByTeam(squadraDTOExtended));
                    squadraDTOExtended.setTifoseria(tifoseriaDao.readByTeam(squadraDTOExtended));
                }
                tornei.add(torneoDTOExtended);
            }
            return tornei.stream().map(TorneoMapper::torneoDtoExtendedToResource).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}