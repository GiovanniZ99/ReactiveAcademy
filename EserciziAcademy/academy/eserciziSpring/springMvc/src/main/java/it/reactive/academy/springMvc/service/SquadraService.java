package it.reactive.academy.springMvc.service;


import it.reactive.academy.springMvc.dto.SquadraDTO;
import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.exception.SquadraNonPresenteException;
import it.reactive.academy.springMvc.mapper.SquadraMapper;
import it.reactive.academy.springMvc.repository.statement.SquadraDaoImpl;
import it.reactive.academy.springMvc.resource.Squadra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SquadraService implements ServiceInterface<SquadraDTO, Squadra>{

    @Autowired
    SquadraDaoImpl squadraDaoImpl;

    @Override
    public Squadra create(SquadraDTO input) {
        if(input != null){
            SquadraDTOExtended squadraDTOExtended = SquadraMapper.squadraDtoToDtoExtended(input);
          SquadraDTOExtended squadraDTOResult =  squadraDaoImpl.create(squadraDTOExtended);
            return SquadraMapper.squadraDtoExtendedToResource(squadraDTOResult);
        }else{
            throw new SquadraNonPresenteException("Squadra");
        }
    }

    @Override
    public Squadra read(int id) {
        return null;
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
