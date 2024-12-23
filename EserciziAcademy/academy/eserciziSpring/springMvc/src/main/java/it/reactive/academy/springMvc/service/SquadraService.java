package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.GiocatoreDTO;
import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.SquadraDiGiocatoriDTO;
import it.reactive.academy.springMvc.dto.extended.GiocatoreDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.exception.GiocatoreGiaCensitoException;
import it.reactive.academy.springMvc.exception.SquadraGiaCensitaException;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.mapper.GiocatoreMapper;
import it.reactive.academy.springMvc.mapper.SquadraDiGiocatoriMapper;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.repository.dao.GiocatoreDao;
import it.reactive.academy.springMvc.repository.dao.SquadraDao;
import it.reactive.academy.springMvc.resource.Squadra;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SquadraService {

    private final SquadraDao squadraDao;
    private final GiocatoreDao giocatoreDao;

    public SquadraService(SquadraDao squadraDaoImpl, GiocatoreDao giocatoreDao) {
        this.squadraDao = squadraDaoImpl;
        this.giocatoreDao = giocatoreDao;
    }

    public Squadra create(SquadraDTO input) {
        if (input == null) {
            throw new SquadraNonPresenteException("Squadra inserita assente");
        }
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

    public Squadra addPlayer(Integer idSquadra, GiocatoreDTO giocatoreDTO) {
        GiocatoreDTOExtended giocatoreDTOExtended = GiocatoreMapper.giocatoreDTOToDtoExtended(giocatoreDTO);
        SquadraDTOExtended squadraDTOExtended;
        try {
            squadraDTOExtended = squadraDao.findSquadraByOd(idSquadra);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (squadraDTOExtended.getIdSquadra() == null) {
            throw new SquadraNonPresenteException("Squadra non presente");
        }
        try {
            if (giocatoreDao.checkdByName(giocatoreDTO.getNomeCognome())) {
                throw new GiocatoreGiaCensitoException("Giocatore già censito");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        giocatoreDTOExtended.setSquadra(squadraDTOExtended);
        Set<GiocatoreDTOExtended> giocatori;
        try {
            giocatori = giocatoreDao.readAllByTeam(squadraDTOExtended);
            squadraDTOExtended.setGiocatori(giocatori);
            if (giocatori == null) {
                giocatori = new HashSet<>();
                squadraDTOExtended.setGiocatori(giocatori);
            }

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

    public Squadra createWithPlayers(SquadraDiGiocatoriDTO squadraDiGiocatori) {
        if (squadraDiGiocatori == null) {
            throw new SquadraNonPresenteException("Squadra inserita assente");
        }
        try {
            if (squadraDao.checkSquadraByName(squadraDiGiocatori.getNome())) {
                throw new SquadraGiaCensitaException("Squadra già censita");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SquadraDTOExtended squadraDTOExtended = SquadraDiGiocatoriMapper.squadraDiGiocatoriToDTOExtended(squadraDiGiocatori);
        // il metodo .create fa perdere i giocatori ai dto extended, squadraDtoResult non li ha
        SquadraDTOExtended squadraDTOResult;
        try {
            squadraDTOResult = squadraDao.create(squadraDTOExtended);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // quindi glieli setto prendendoli da squadraDtoExtended
        squadraDTOResult.setGiocatori(squadraDTOExtended.getGiocatori());
        // i giocatori di squadraDtoResult non hanno la squadra quindi la setto
        for (GiocatoreDTOExtended elem : squadraDTOResult.getGiocatori()) {
            elem.setSquadra(squadraDTOResult);
        }
        // inserisco i giocatori nel db
        Set<GiocatoreDTOExtended> giocatori;
        try {
            giocatori = giocatoreDao.createAll(squadraDTOResult.getGiocatori());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // setto i giocaotri risultanti che hanno anche l'id
        squadraDTOResult.setGiocatori(giocatori);
        return SquadraMapper.squadraDtoExtendedToResource(squadraDTOResult);
    }

    public List<Squadra> read(Boolean completo) {
        if (completo) {
            try {
                return squadraDao.readTeamsAndPlayers()
                        .stream()
                        .map(SquadraMapper::squadraDtoExtendedToResource).collect(Collectors.toList());
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


}
