package it.reactive.academy.springMvc.service;

import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.exception.SquadraGiaCensitaException;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.repository.statement.SquadraDaoImpl;
import it.reactive.academy.springMvc.resource.Squadra;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SquadraService implements ServiceInterface<SquadraDTO, Squadra> {

    private final SquadraDaoImpl squadraDaoImpl;

    public SquadraService(SquadraDaoImpl squadraDaoImpl) {
        this.squadraDaoImpl = squadraDaoImpl;
    }

    @Override
    public Squadra create(SquadraDTO input) {
        if (input == null) {
            throw new SquadraNonPresenteException("Squadra inserita assente");
        }
        if (squadraDaoImpl.findSquadraByName(input.getNome())) {
            throw new SquadraGiaCensitaException("Squadra già censita");
        }
        SquadraDTOExtended squadraDTOExtended = SquadraMapper.squadraDtoToDtoExtended(input);
        SquadraDTOExtended squadraDTOResult = squadraDaoImpl.create(squadraDTOExtended);
        return SquadraMapper.squadraDtoExtendedToResource(squadraDTOResult);
    }

    @Override
    public List<Squadra> read(Boolean input) {
        if (input) {
            return squadraDaoImpl.readTeamsAndPlayers()
                    .stream()
                    .map(SquadraMapper::squadraDtoExtendedToResource).collect(Collectors.toList());
        }
        return squadraDaoImpl.readAll()
                .stream()
                .map(SquadraMapper::squadraDtoExtendedToResource)
                .collect(Collectors.toList());
    }

    @Override
    public Squadra update(int id, SquadraDTO input) {
        return null;
    }

    @Override
    public Squadra delete(int id) {
        return null;
    }
}
