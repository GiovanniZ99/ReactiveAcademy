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
import org.springframework.dao.EmptyResultDataAccessException;
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
            Set<Integer> setIdSquadre = squadraTorneoDao.readAllTeamsById(torneoDTOExtended.getIdTorneo());
            Set<SquadraDTOExtended> setSquadre = new HashSet<>();

            setIdSquadre.forEach(elem -> {
                try {
                    setSquadre.add(squadraDao.findSquadraById(elem));
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
            List<TorneoDTOExtended> tornei = new ArrayList<>();
            LinkedHashMap<Integer, Set<Integer>> id = squadraTorneoDao.readAllTornei();

            TorneoDTOExtended torneoDTOExtended = null;

            int idTorneoCorrente = -1;
            for (Map.Entry<Integer, Set<Integer>> entry : id.entrySet()) {
                Integer idTorneo = entry.getKey();

                torneoDTOExtended = addTorneoATornei(idTorneo, idTorneoCorrente, tornei, torneoDTOExtended);

                idTorneoCorrente = addSquadraTifoseriaGiocatori(entry, torneoDTOExtended, idTorneoCorrente, idTorneo);

                torneoDTOExtended = addTorneoATornei(idTorneo, idTorneoCorrente, tornei, torneoDTOExtended);
            }

            tornei.add(torneoDTOExtended);

            if(torneoDTOExtended == null){
                throw new TorneoNonTrovatoException("Tornei non trovati");
            }
            return tornei.stream().map(TorneoMapper::torneoDtoExtendedToResource).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private TorneoDTOExtended addTorneoATornei(Integer idTorneo, int idTorneoCorrente, List<TorneoDTOExtended> tornei, TorneoDTOExtended torneoDTOExtended) throws SQLException {
        if (idTorneo != idTorneoCorrente) {
            if (torneoDTOExtended != null) {
                tornei.add(torneoDTOExtended);
            }
            torneoDTOExtended = torneoDao.findById(idTorneo);
            torneoDTOExtended.setSquadre(new HashSet<>());
        }
        return torneoDTOExtended;
    }

    private int addSquadraTifoseriaGiocatori(Map.Entry<Integer, Set<Integer>> entry, TorneoDTOExtended torneoDTOExtended, int idTorneoCorrente, Integer idTorneo) throws SQLException {
        if (entry.getValue() != null) {
            for (Integer idSquadra : entry.getValue()) {
                torneoDTOExtended.getSquadre().add(squadraDao.findSquadraById(idSquadra));
                torneoDTOExtended.getSquadre().forEach(elem -> {

                    try {
                        elem.setGiocatori(giocatoreDao.readAllByTeam(elem));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    TifoseriaDTOExtended tifoseria;
                    try {
                        tifoseria = tifoseriaDao.readByTeam(elem);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }catch (EmptyResultDataAccessException e){
                        tifoseria = new TifoseriaDTOExtended();
                    }
                    tifoseria.setSquadra(elem);
                        elem.setTifoseria(tifoseria);
                    });
            }
            idTorneoCorrente = idTorneo;
        }
        return idTorneoCorrente;
    }
}