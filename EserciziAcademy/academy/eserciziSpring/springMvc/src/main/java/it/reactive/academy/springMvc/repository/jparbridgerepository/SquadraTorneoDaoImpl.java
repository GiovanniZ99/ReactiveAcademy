package it.reactive.academy.springMvc.repository.jparbridgerepository;

import it.reactive.academy.springMvc.dto.extended.SquadraDTOExtended;
import it.reactive.academy.springMvc.dto.extended.SquadraTorneoDTOExtended;
import it.reactive.academy.springMvc.dto.extended.TorneoDTOExtended;
import it.reactive.academy.springMvc.entity.SquadraEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoEntity;
import it.reactive.academy.springMvc.entity.SquadraTorneoId;
import it.reactive.academy.springMvc.entity.TorneoEntity;
import it.reactive.academy.springMvc.repository.dao.SquadraTorneoDao;
import it.reactive.academy.springMvc.repository.jparepository.SquadraTorneoRepository;
import it.reactive.academy.springMvc.utility.Costanti;
import it.reactive.academy.springMvc.utility.mapper.SquadraMapper;
import it.reactive.academy.springMvc.utility.mapper.SquadraTorneoMapper;
import it.reactive.academy.springMvc.utility.mapper.TorneoMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Profile(Costanti.TORNEO_DAO_SPRING_JPA_JPAREPOSITORY)
public class SquadraTorneoDaoImpl implements SquadraTorneoDao {

    private final SquadraTorneoRepository squadraTorneoRepository;

    public SquadraTorneoDaoImpl(SquadraTorneoRepository squadraTorneoRepository) {
        this.squadraTorneoRepository = squadraTorneoRepository;
    }

    @Override
    public SquadraTorneoDTOExtended create(TorneoDTOExtended torneoDTOExtended, SquadraDTOExtended squadraDTOExtended) throws SQLException {
        SquadraTorneoId squadraTorneoId = new SquadraTorneoId();
        TorneoEntity torneo = TorneoMapper.torneoDTOExtendedToEntity(torneoDTOExtended);
        SquadraEntity squadra = SquadraMapper.squadraDtoExtendedToEntity(squadraDTOExtended);
        squadraTorneoId.setIdTorneo(torneo.getIdTorneo());
        squadraTorneoId.setIdSquadra(squadra.getIdSquadra());
        SquadraTorneoEntity squadraTorneo = new SquadraTorneoEntity();
        squadraTorneo.setId(squadraTorneoId);
        squadraTorneo.setTorneo(torneo);
        squadraTorneo.setSquadra(squadra);

        return SquadraTorneoMapper.squadraTorneoEntityToDtoExtended(squadraTorneoRepository.save(squadraTorneo));
    }

    @Override
    public Set<SquadraDTOExtended> readAllTeamsById(TorneoDTOExtended torneoDTOExtended) throws SQLException {
        Set<SquadraTorneoEntity> squadreTorneoEntity = squadraTorneoRepository.findByTorneoIdTorneo(torneoDTOExtended.getIdTorneo());
        Set<SquadraEntity> squadre = new HashSet<>();
        squadreTorneoEntity.forEach(elem -> squadre.add(elem.getSquadra()));
        return squadre.stream().map(SquadraMapper::squadraEntityToDtoExtendended).collect(Collectors.toSet());
    }


    @Override
    public Set<TorneoDTOExtended> readAllTorneoByIdSquadra(SquadraDTOExtended squadraDTOExtended) throws SQLException {
        Set<SquadraTorneoEntity> squadreTorneoEntity = squadraTorneoRepository.findBySquadraIdSquadra(squadraDTOExtended.getIdSquadra());
        Set<TorneoEntity> tornei = new HashSet<>();
        squadreTorneoEntity.forEach(elem -> tornei.add(elem.getTorneo()));
        return tornei.stream().map(TorneoMapper::torneoEntityToDtoExtended).collect(Collectors.toSet());
    }

    @Override
    @Deprecated
    public Set<TorneoDTOExtended> readAllTorneo() throws SQLException {
        return Collections.emptySet();
    }
}
