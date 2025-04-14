package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.exception.GiocatoreGiaCensitoException;
import it.reactive.academy.springMvc.exception.SquadraGiaCensitaException;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.utility.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraDiGiocatoriMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.resource.Squadra;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SquadraService {

    private final SquadraDao squadraDao;
    private final GiocatoreDao giocatoreDao;
    private final TrasferimentiService trasferimentiService;

    public SquadraService(SquadraDao squadraDaoImpl, GiocatoreDao giocatoreDao, TrasferimentiService trasferimentiService) {
        this.squadraDao = squadraDaoImpl;
        this.giocatoreDao = giocatoreDao;
        this.trasferimentiService = trasferimentiService;
    }

    @Transactional
    public Squadra create(SquadraDTO input) {
        try {
            if (squadraDao.checkSquadraByName(input.getNome())) {
                throw new SquadraGiaCensitaException("Squadra già censita");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SquadraDTOExtended squadraDTOExtended = SquadraMapper.squadraDtoToDtoExtended(input);
        SquadraDTOExtended squadraDTOResult;

        try {
            squadraDTOResult = squadraDao.create(squadraDTOExtended);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraDtoExtendedToResource(squadraDTOResult);
    }

    @Transactional
    public Squadra addPlayer(Integer idSquadra, GiocatoreDTO giocatoreDTO) {
        GiocatoreDTOExtended giocatoreDTOExtended = GiocatoreMapper.giocatoreDTOToDtoExtended(giocatoreDTO);
        SquadraDTOExtended squadraDTOExtended;
        try {
            squadraDTOExtended = squadraDao.findSquadraById(idSquadra);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (squadraDTOExtended.getIdSquadra() == null) {
            throw new SquadraNonPresenteException("Squadra non presente");
        }
        try {
            if (giocatoreDao.checkByName(giocatoreDTO.getNomeCognome())) {
                throw new GiocatoreGiaCensitoException("Giocatore già censito");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        giocatoreDTOExtended.setSquadra(squadraDTOExtended);
        Set<GiocatoreDTOExtended> giocatori;
        try {
            giocatori = giocatoreDao.readAllByTeam(squadraDTOExtended);
            giocatori.forEach(elem-> elem.setTrasferimenti(trasferimentiService.trasferimenti(elem.getNomeCognome())));
            squadraDTOExtended.setGiocatori(giocatori);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            squadraDTOExtended.getGiocatori().add(giocatoreDao.create(giocatoreDTOExtended));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraDtoExtendedToResource(squadraDTOExtended);
    }

    @Transactional
    public Squadra createWithPlayers(SquadraDiGiocatoriDTO squadraDiGiocatori) {
        try {
            if (squadraDao.checkSquadraByName(squadraDiGiocatori.getNome())) {
                throw new SquadraGiaCensitaException("Squadra già censita");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SquadraDTOExtended squadraDTOExtended = SquadraDiGiocatoriMapper.squadraDiGiocatoriToDTOExtended(squadraDiGiocatori);

        try {
            SquadraDTOExtended squadraSenzaGiocatori = squadraDao.create(squadraDTOExtended);
            squadraDTOExtended.setIdSquadra(squadraSenzaGiocatori.getIdSquadra());
            squadraDTOExtended.setNome(squadraSenzaGiocatori.getNome());
            squadraDTOExtended.setColoriSociali(squadraSenzaGiocatori.getColoriSociali());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        squadraDTOExtended.getGiocatori().forEach(elem ->{
            elem.setSquadra(squadraDTOExtended);
            elem.setTrasferimenti(trasferimentiService.trasferimenti(elem.getNomeCognome()));
        });
        try {
            Set<String> nomiGiocatori = new HashSet<>();
            squadraDTOExtended.getGiocatori().forEach(elem-> {
                try {
                    if(giocatoreDao.checkByName(elem.getNomeCognome()) || nomiGiocatori.contains(elem.getNomeCognome())){
                        throw new GiocatoreGiaCensitoException("Giocatore già censito");
                    }
                    nomiGiocatori.add(elem.getNomeCognome());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            squadraDTOExtended.setGiocatori(giocatoreDao.createAll(squadraDTOExtended.getGiocatori()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return SquadraMapper.squadraDtoExtendedToResource(squadraDTOExtended);
    }

    @Transactional
    public List<Squadra> read(Boolean completo) {
        if (Boolean.TRUE.equals(completo)) {
            try {
                List<SquadraDTOExtended> listaSquadreDtoExt = squadraDao.readAll();
                listaSquadreDtoExt.forEach(elem -> {
                    try {
                        elem.setGiocatori(giocatoreDao.readAllByTeam(elem));
                        elem.getGiocatori()
                                .forEach(giocatore ->
                                        giocatore.setTrasferimenti
                                                (trasferimentiService
                                                        .trasferimenti
                                                                (giocatore.getNomeCognome())));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                return listaSquadreDtoExt.stream()
                        .map(SquadraMapper::squadraDtoExtendedToResource)
                        .collect(Collectors.toList());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            return squadraDao.readAll()
                    .stream()
                    .map(SquadraMapper::squadraDtoExtendedToResource)
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            if(squadraDao.findSquadraById(id).getIdSquadra() == null){
                throw new SquadraNonPresenteException("Squadra non presente");
            }
            squadraDao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
